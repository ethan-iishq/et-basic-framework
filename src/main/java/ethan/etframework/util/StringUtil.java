package ethan.etframework.util;

public class StringUtil {
	public static boolean isStrEmpty(String str){
		if(str == null || "".equals(str)) return true;
		return false;
	}
	
	public static boolean isStrEqual(String str1, String str2){
		if(str1 == str2) return true;
		if((str1 == null && str2 != null) || (str1 != null && str2 == null)) return false;
		return (str1.equals(str2));
	}
}
