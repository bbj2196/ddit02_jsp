package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Servlet Spec : WAS(Servlet Container)에 의해 관리(운영)될수있는 웹 객체에 대한 명세
 * Container? 컨ㅌ테이너에 의해 관리되는 생명주기 관리자
 * 
 * 1. httpServlet의상속 -> 필요한 콜백(callback) 메서드 재정의(overriding)
 * callback 구조란 특정이벤트가 발생하면 시스템 내부적으로 자동 호출되는 구조
 * 2. compile -> /WEB-INF/classes(classpath)아래에 배포
 * 3. 컨테이너에 등ㅇ록
 * 		2.x web.xml -> servlet(servlet-name, servlet-class : qualified name 사용)
 * 		3.x @WebServlet(name,urlPatterns...) - 등록과 매핑을 동시에
 * 4. 클라이언트의 요청과 매핑설정
 * 		2.x web.xml -> servlet-mapping(servlet-name,url-pattern)
 * 5. restart 
 * 
 *  ** Callback 메소드 종류
 *  1. lifecycle callbak
 *  	- init : service instance 생성직후 한번 호출
 *  	- destory : service instance 생성직전 한번호출
 *  2. request callback : 매요청 마나 반복 호출
 *  	- servlet : 재정의시 super.service 호출코드를 제거하면 doXXX는 동작하지 않는다
 *  	- doXXX : 재정의시 반드시 super.XXX호출코드를 제거함
 *  
 *  ** Servlet Container 의 서블릿 관리 특성
 *  1. 싱글톤 형태로 관리
 *  2. 요청이 발생하면
 *  	1)정적요청 여부 판단 : default servlet을 통해 처리
 *  	2) 해당 요청의 url 패턴을 판단하고, 일치하는 등록된 서블릿 검색.
 *  		- 검색 실패시 : 404에러
 *  		- 검색 성공시 : 싱글톤 객체를 찾고 존재시 호출
 *  								존재하지 않는다면, 싱글톤 객체 생성하고 콜백 호출
 *  
 *  
 *  
 *	
 *  
 *  
 */

@WebServlet("/desc.do")
public class DescriptionServlet extends HttpServlet{

	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service의 첫라인");
			super.service(req, resp);
			System.out.println("service의 마지막라인");
		}
	@Override
		public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 생성직후 초기화됨\n",getClass().getName());
		}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get방식으로 요청 현재 쓰레드명 : "+Thread.currentThread().getName());
//		super.doGet(req, resp);
	}
	@Override
		public void destroy() {
		super.destroy();
		System.out.printf("%s 서블릿 객체 소멸 직전",getClass().getName());
		}
}
