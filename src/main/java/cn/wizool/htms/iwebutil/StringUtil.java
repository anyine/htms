package cn.wizool.htms.iwebutil;

public abstract class StringUtil {

	public static boolean notEmpty(String str) {
		boolean falg = true;
		if (str == null || str.equals("")) {
			falg = false;
		}
		return falg;
	}
	
}
