package cong.lance.generator;

public class FieldBean {
	
	private String name;
	private String type;
	
	public FieldBean(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public FieldBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
		
	public void setType(String type) {
		this.type = type;
	}
	
	public String genGetter(){
		StringBuilder getter = new StringBuilder();
		getter.append("    public "+getType()+" "+"get"+Util.firstLetterUpperCase(getName())+"() { ").append("\n");
		getter.append("        return "+getName()).append(";").append("\n");
		getter.append("    }");
		return getter.toString();
	}
	
	public String genSetter(){
		StringBuilder getter = new StringBuilder();
		getter.append("    public void "+"set"+Util.firstLetterUpperCase(getName())+"("+getType()+" "+getName()+") { ").append("\n");
		if(Main.getPropertity("normal").equals("no")){
			getter.append("        put(\""+getName()+"\","+getName()+")").append(";").append("\n");	
		}
		getter.append("        this."+getName()+" = "+getName()).append(";").append("\n");
		getter.append("    }");
		return getter.toString();
	}
	
	public static void main(String[] args) {
		FieldBean fieldBean = new FieldBean();
		fieldBean.setName("name");
		fieldBean.setType("String");
		System.out.println(fieldBean.genGetter());
		System.out.println(fieldBean.genSetter());
	}

}
