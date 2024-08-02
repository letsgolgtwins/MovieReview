package com.movie.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	// i: userPassword 날 것 (근데 이름은 message로)
	// o: 해싱된 userPassword
	public static String md5(String message) {
		String encData = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] bytes = message.getBytes();
			md.update(bytes);
			byte[] digest = md.digest();
			
			for (int i = 0; i < digest.length; i++) {
				// 16진수로 변환
				encData += Integer.toHexString(digest[i] & 0xff);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encData;
	}
}
