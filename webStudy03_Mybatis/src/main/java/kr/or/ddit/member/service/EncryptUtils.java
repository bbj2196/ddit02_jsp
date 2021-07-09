package kr.or.ddit.member.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {

	public static boolean matches(String plain, String saved) {
		String encoded = encryptSha512Base64(plain);
		return saved.equals(encoded);
	}
	
	
	public static String encryptSha512Base64(String plain) {
		byte[] encryted=DigestUtils.sha512(plain);
		return Base64.encodeBase64String(encryted);
	}
}
