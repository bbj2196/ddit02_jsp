package kr.or.ddit.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * encoding(부호화) : 데이터의 전송이나 저장시에 해당 매체가 인식할 수 있는 데이터 표현방식으로 표현을 변환하는 과정.
 *  	URL encoding(Percent encoding)Base64 encoding
 * encrypting(암호화) : 허가되지않은 사용자로부터 데이터를 보호할 목적으로 변환하는 과정
 * 
 * @author PC-13
 *
 */
public class EncodingDesc {
public static void main(String[] args) throws IOException {
	String plaing = "대충 스트링";
	String encoded=URLEncoder.encode(plaing,"utf-8");
	System.out.println(encoded);
	String decoded = URLDecoder.decode(encoded, "utf-8");
	System.out.println(decoded);
	
	String resPath = "D:/contents/destination-2.jpg";
	
	File file = new File(resPath);
	try(
	FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			){
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while ((pointer = fis.read(buffer))!=-1) {
			baos.write(buffer,0,pointer);
		}
		byte[] readData = baos.toByteArray();
		System.out.println(readData.length);
		System.out.println("================================================");
		encoded=Base64.getEncoder().encodeToString(readData);
		System.out.println(encoded);
		System.out.println("================================================");
		byte[] decodedArray = Base64.getDecoder().decode(encoded);
		System.out.println(decodedArray);
	}
}
}
