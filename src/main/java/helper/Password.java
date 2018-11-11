package helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Password {
	
	private static final Random RANDOM = new SecureRandom();
	private static final String ENCODING = "UTF-16";
	
	private Password() {}
	
	public static String getSalt() {
		String result = null;
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		try {
			result = new String(salt, ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getPassword(String password, String salt) {
		String hash = null;
		try {
			MessageDigest message = MessageDigest.getInstance("SHA-512");
			message.update(salt.getBytes(ENCODING));
			byte[] bytes = message.digest(password.getBytes(ENCODING));
			
			StringBuilder string = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				string.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			hash = string.toString();
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hash;
	}

}
