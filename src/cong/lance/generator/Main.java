package cong.lance.generator;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

	// 数据库连接
	private static Connection mConn = null;

	// 读取设置
	public static String getPropertity(String key) {
		Properties prop = null;
		InputStream in = null;
		try {
			prop = new Properties();
//			in = new BufferedInputStream(new FileInputStream("conf/config4test.properties"));
			in = new BufferedInputStream(new FileInputStream("conf/config.properties"));
			prop.load(in);
			String ret = prop.getProperty(key).trim();
			in.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getJavaTypeName(int type) {
		String ret = null;
		switch (type) {
		case Types.TINYINT:
		case Types.SMALLINT:
		case Types.INTEGER:
		case Types.BIGINT:
			ret = "int";
			break;

		case Types.FLOAT:
		case Types.REAL:
		case Types.DOUBLE:
		case Types.NUMERIC:
		case Types.DECIMAL:
			ret = "double";
			break;

		default:
			ret = "String";
			break;
		}
		return ret;
	}

	public static Connection getConnection() throws Exception {

		if (mConn != null && !mConn.isClosed())
			return mConn;

		String driver = getPropertity("jdbc.driver");
		String url = getPropertity("jdbc.url");
		String username = getPropertity("jdbc.username");
		String password = getPropertity("jdbc.password");

		Class.forName(driver);
		mConn = DriverManager.getConnection(url, username, password);
		return mConn;

	}

	public static String[] getAllTables() throws Exception {
		DatabaseMetaData databaseMetaData = getConnection().getMetaData();
		ResultSet tables = databaseMetaData.getTables(null, null, "%", null);
		ArrayList<String> tablesList = new ArrayList<String>();
		while (tables.next()) {
			tablesList.add(tables.getString("TABLE_NAME"));
		}
		return tablesList.toArray(new String[tablesList.size()]);
	}

	// 获取字段
	public static void start() {

		try {
			String[] tables = getPropertity("tables").split(",");
			if (tables.length == 1 && tables[0].equals("all")) {
				tables = getAllTables();
			}
			//
			for (String tablename : tables) {
				List<FieldBean> fieldList = new ArrayList<FieldBean>();// 一个表的
				Statement statement = getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * from "
						+ tablename);
				ResultSetMetaData metaData = resultSet.getMetaData();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					// resultSet数据下标从1开始
					String columnName = metaData.getColumnName(i + 1);
					int type = metaData.getColumnType(i + 1);
					FieldBean fieldBean = new FieldBean(columnName,	getJavaTypeName(type));
					// System.out.print(" "+columnName + " " + getJavaTypeName(type));
					fieldList.add(fieldBean);
				}
				// 获取一个表
				genClassFiles(tablename, fieldList);
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("出错");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		start();
	}

	public static void genClassFiles(String tablename, List<FieldBean> fieldList) {
		String extendClass = getPropertity("extend");
		String className = Util.toCamelName(getPropertity("prefix") + Util.firstLetterUpperCase(tablename) + getPropertity("suffix"));
		StringBuilder ret = new StringBuilder();
		ret.append("package ").append(getPropertity("package")).append(";").append("\n").append("\n");
		ret.append("import java.io.Serializable;").append("\n");
		if (!extendClass.equals("")) {
			ret.append("import " + extendClass + ";");
			ret.append("\n");
		}
		ret.append("\n");
		ret.append("public class " + className);

		if (!extendClass.equals("")) {
			ret.append(" extends " + extendClass.split("\\.")[extendClass.split("\\.").length - 1]);
		}
		ret.append(" implements Serializable {");
		ret.append("\n");
		ret.append("\n");
		ret.append("	// ");
		for (int i = 0; i < fieldList.size(); i++) {
			ret.append((i == 0 ? "":",") + fieldList.get(i).getName());
		}
		ret.append("\n");
		ret.append("    private static final long serialVersionUID = 1L;").append("\n");
		ret.append("\n");
		for (FieldBean fieldBean : fieldList) {
			ret.append("    private " + fieldBean.getType() + " " + fieldBean.getName()).append(";").append("\n");
		}
		ret.append("\n");
		ret.append("    //getter");
		ret.append("\n");
		for (FieldBean fieldBean : fieldList) {
			ret.append(fieldBean.genGetter());
			ret.append("\n");
		}
		ret.append("\n");
		ret.append("    //setter");
		ret.append("\n");
		for (FieldBean fieldBean : fieldList) {
			ret.append(fieldBean.genSetter());
			ret.append("\n");
		}
		ret.append("\n");
		//toString()
		ret.append("    @Override").append("\n");
		ret.append("    public String toString() {").append("\n");
		ret.append("        StringBuilder sb = new StringBuilder();").append("\n");
		ret.append("        sb.append(getClass().getSimpleName());").append("\n");
		ret.append("        sb.append(\" [\");").append("\n");
		ret.append("        sb.append(\"Hash = \").append(hashCode());").append("\n");
		for (FieldBean fieldBean : fieldList) {
			ret.append("        sb.append(\", "+fieldBean.getName()+"=\").append("+fieldBean.getName()+");").append("\n");
		}
		ret.append("        sb.append(\", serialVersionUID=\").append(serialVersionUID);").append("\n");
		ret.append("        sb.append(\"]\");").append("\n");
		ret.append("        return sb.toString();").append("\n");
		ret.append("    }");
		
		ret.append("\n");
		ret.append("\n");
		ret.append("}");
		// System.out.println(ret.toString());
		writeFile(className, ret.toString());
	}

	public static void writeFile(String className, String content) {
		try {
			String output = getPropertity("output");
			if (output == null || output.equals("")) {
				output = "gens";
			}
			File file = new File(output + File.separator + className + ".java");
			System.out.println(file.getAbsolutePath());
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
