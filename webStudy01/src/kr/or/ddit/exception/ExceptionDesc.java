package kr.or.ddit.exception;

import java.io.IOException;

/**
 * 에러? 예외? : 예상하지 않았던 비정상 상황(Throwable)
 * Error : 개발자가 처리하지않는 비정상 상황, compile error
 * Exception : 개발자가 처리할 수 있는 비정상 예외
 * 		checked exception : 예외가 던져지면 반드시 처리해야하는 예외
 * 			:IOException,SQLExcepion
 * 		unchecked exception : 예외가 던져지고, 명시적인 예외처리코드가 없으면 자동으로 VM에 예외가 전달
 * 			:NullPointerExcepition
 * 
 * ***예외 처리 방법
 * 
 * try{
 * }catch(){
 * }fainally{
 * }
 * 호출자에게 전달 : throws
 *  
 *  ***Custom exception 정의
 *  	: 예외의 특성을 결정하고, 처리정책에 따라 상위가 결정됨
 *  
 *  
 *  
 *  
 **/
public class ExceptionDesc {

	public static void main(String[] args) {
		
	}
	private static void test1() {
		if(1==1) {
			try {
				throw new IOException("강제발생");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
