package com.sns.common;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		// changes Charsequence to a string
		String message = rawPassword.toString();
		
		String encData = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] bytes = message.getBytes();
			md.update(bytes);
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; i++) {
				encData += Integer.toHexString(digest[i] & 0xff); // 16진수로 변환하는 과정
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encData;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String encodedPasswordOfTheRawPassword = this.encode(rawPassword);
		return encodedPasswordOfTheRawPassword.equals(encodedPassword);
	}

}
