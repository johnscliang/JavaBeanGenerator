package cong.lance.generator;

public class Util {
	//首字母大写
	public static String firstLetterUpperCase(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}
}
