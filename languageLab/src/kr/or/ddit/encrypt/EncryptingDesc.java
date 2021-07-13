package kr.or.ddit.encrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * 단방향 암호화 : 평문복원이 불가능한 방식
 *  	SHA-512 다양한 입력 데이터가 일정 갯수의 해시문자(512)로 만들어짐 MessageDiagest
 * 양방향 암호화 : 평문복원이 가능한 방식 Cipher
 *  	대칭키 방식 : 하나의 비밀키에 의해 암복호화 수행, AES
 *  	비대칭 방식 : 한 쌍(개인키,공개키)의 키로 암호화,복호화 수행 RSA
 * @author PC-13
 *
 */
public class EncryptingDesc {
	public static void main(String[] args) throws Exception {
		String plain="java";
		
		String encoded=sha512Encrypting(plain);
		System.out.println(encoded);
		
		
		
	}
	
	public static void rsaSample(String plain) {
		try {
		//설정중
				KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
				pairGen.initialize(2048);
				KeyPair pair = pairGen.genKeyPair();
				PrivateKey privateKey = pair.getPrivate();
				PublicKey publicKey = pair.getPublic();
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, privateKey);
				
				// 암호화중
				byte[] input = plain.getBytes();
				byte[] encrypted = cipher.doFinal(input);
				String encoded = Base64.getEncoder().encodeToString(encrypted);
				
				// 복호화중
				byte[] decoded = Base64.getDecoder().decode(encoded);
				cipher.init(Cipher.DECRYPT_MODE, publicKey);
				byte[] decrypted = cipher.doFinal(decoded);
				
				// 확인
				String rePlain = new String(decrypted);
				System.out.println(rePlain);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String aesSample(String plain) {
		String rePlain=null;
		try {
			// 설정중..
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey key = keyGen.generateKey();
		String ivText = "초기화 글씨";
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] iv = md.digest(ivText.getBytes());
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		// 암호화중..
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key,ivSpec);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		
		// 복호화중...
		byte[] decoded = Base64.getDecoder().decode(encoded);
		cipher.init(Cipher.DECRYPT_MODE, key,ivSpec);
		byte[] decrypted = cipher.doFinal(decoded);
		
		// 확인
		rePlain = new String(decrypted);
		System.out.println(rePlain);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rePlain;
	}
	
	public static String sha512Encrypting(String plain) {
		
		try {
			
		// 설저중...
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		
		// 암호화중....
		byte[] input = plain.getBytes();
		byte[] encrypted = md.digest(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		
		return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
	}
}
