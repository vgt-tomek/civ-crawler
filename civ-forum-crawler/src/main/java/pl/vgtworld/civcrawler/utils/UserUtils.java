package pl.vgtworld.civcrawler.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtils {
	
	private static final String CHARSET_NAME = "UTF-8";
	
	public static String generateSalt() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String seed = "" + System.currentTimeMillis();
		return generateHash(seed);
	}
	
	public static String generatePasswordHash(String password, String salt) throws NoSuchAlgorithmException,
		UnsupportedEncodingException {
		String seed = password + salt;
		return generateHash(seed);
	}
	
	private static String generateHash(String seed) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] result = md.digest(seed.getBytes(CHARSET_NAME));
		BigInteger bigInt = new BigInteger(1, result);
		String hashtext = bigInt.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}
}
