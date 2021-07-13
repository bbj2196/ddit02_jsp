package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Stram : 연속성을 가진 일련의 데이터 집합이면서 동시에 전송(단방향)통로
 * 
 * 스트림 종류
 * 1. 데이터 전송 크기에 따른 분류
 * 	1) byte stream : ~~InputStream/~~OutputStream
 * 	2) char stream : ~~Reader/~~Writer
 * 
 * 2. 스트립의 생성 방법 및 필터링 여부
 * 1) 1차스트림 : 매체를 대상으로 직접적으로 생성가능한 스트림
 * 	FileInputStream(file),SocketInputStream / socket.getInputStream()
 * 2) 2차스트림(연결형) : 1차스트림을 대상으로 생성하는 스트림
 * 	BufferdReader,DataInputStream,ObjectInputStream
 * 	BufferdWriter,DataOutputStream,ObjectOutputStream
 * 3.스트림 사용단계
 * 1) 매체(meadia)를 어플리케이션 내에서 제어할 수 있도록 객체화
 * 2) 매체를 대상으로 1차스트림 생성
 * 3) 데이터를 필터링 할 수있는 2차 스트림 사용(optional)
 * 4) 기록이나 읽어들이는 작업 반복(EOF,-1,null)
 * ******* 자원의 해제(finally, try~with~resource)
 * 
 * 
 * 
 * @author PC-13
 *
 */
public class StreamDesc {

	public static void main(String[] args) throws Exception {

		URL imgUrl = new URL("https://www.google.com/logos/doodles/2021/get-vaccinated-wear-a-mask-save-lives-june-22-6753651837109280-law.gif");
		File saveFile = new File("d:/google.gif");
		byte[] buffer = new byte[1024];
		int pointer = -1;
		try(
			InputStream is = imgUrl.openStream();
			FileOutputStream fos = new FileOutputStream(saveFile);
			){
			while((pointer=is.read(buffer))!=-1) {
				fos.write(buffer, 0, pointer);
			}
			fos.flush();
			System.out.println("다운로드완료");
		}
		
		
	}
	
	@SuppressWarnings("unused")
	private static void serialize() throws IOException, ClassNotFoundException {
		//객체의 직렬화
		File writeFile = new File("d:/test.dat");
//		TestVO vo = new TestVO(12, "text");
//		vo.setRegno1("123123123");
//		try(
//		FileOutputStream fos = new FileOutputStream(writeFile);
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//			){
//		oos.writeObject(vo);
//		}
		
		// 객체 역직렬화
		try(
			FileInputStream fis = new FileInputStream(writeFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			){
			//desrialization
			TestVO readData = (TestVO) ois.readObject();
			System.out.println(readData);
		}
		System.out.println("end");
	}
	@SuppressWarnings("unused")
	private void readClassResourceStream() throws IOException {
		URL url = StreamDesc.class.getResource("/kr/or/ddit/io/오래된 노래.txt");
		String filePath = (URLDecoder.decode(url.getFile(),"utf-8"));
		System.out.println(filePath);
		if (url != null) {
			File readFile = new File(filePath);
			try (
//					FileReader rd = new FileReader(readFile);
					FileInputStream fis = new FileInputStream(readFile);
					InputStreamReader isr = new InputStreamReader(fis, "ms949");
					BufferedReader br = new BufferedReader(isr);
				) {
				String tmp = null;
				while ((tmp = br.readLine()) != null) {
					System.out.println(tmp);
				}
			}
		}
	}
	@SuppressWarnings("unused")
	private void readClassResource() throws IOException{
		URL url = StreamDesc.class.getResource("/kr/or/ddit/io/오래된 노래_utf8.txt");
		String filePath = (URLDecoder.decode(url.getFile(),"utf-8"));
		System.out.println(filePath);
		if (url != null) {
			File readFile = new File(filePath);
			try (FileReader rd = new FileReader(readFile); BufferedReader br = new BufferedReader(rd);) {
				String tmp = null;
				while ((tmp = br.readLine()) != null) {
					System.out.println(tmp);
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void readFileSystemResource()throws IOException {
		File readFile = new File("d:/contents/another day.txt");
		System.out.println(readFile.length());
		try(
		FileInputStream fis = new FileInputStream(readFile);
				){
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while((pointer=fis.read(buffer))!=-1) {
			System.out.write(buffer, 0, pointer);
		}
		}
	}
}
