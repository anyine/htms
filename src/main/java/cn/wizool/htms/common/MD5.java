package cn.wizool.htms.common;

import java.security.MessageDigest;

public class MD5 {

	private static String byteArrayToHex(byte[] b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			ob[0] = Digit[(b[i] >>> 4) & 0X0F];
			ob[1] = Digit[b[i] & 0X0F];
			s.append(new String(ob));
		}
		return s.toString();
	}

	public static String Md5(String str) {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			return byteArrayToHex(sha.digest(str.getBytes()));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.Md5("poiuyt"));
	}
}
