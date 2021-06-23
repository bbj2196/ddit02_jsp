package kr.or.ddit.commons;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1. 파라미터 확보
		String id = request.getParameter("mem_id");
		String pass = request.getParameter("mem_pass");
		HttpSession session = request.getSession();
		Map<String,String>map = new HashMap<>();
		session.setAttribute("errors", map);
		boolean valid = validate(id,pass,map);
		String goPage=null;
		boolean redirect = false;
		
		//2. 검증
		//필수파라미터 누락여부 확인(400)
		
		if(!valid) {
//			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return;
			goPage="/login/loginForm.jsp";
			redirect=true;
		}
		if(authenticated(id,pass)) {
			//	1)성공 : welcome page로 이동(redirect)
			goPage="/";
			redirect=true;
			session.setAttribute("authId", id);
		}else {
			//	2)실패 : login page로 이동(forward)
			session.setAttribute("mem_id", id);
			goPage="/login/loginForm.jsp";
		}
		//3. 인증
		if(redirect) {
			response.sendRedirect(request.getContextPath()+goPage);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher(goPage);
			rd.forward(request, response);
		}
		System.out.println(id);
		System.out.println(pass);

		
		}
	

	private boolean authenticated(String id, String pass) {
//		return id.equals(pass);
		return true;
	}


	private boolean validate(String id, String pass,Map<String,String>errors) {
		boolean valid = true;
		if(id == null || id.isEmpty()) {
			valid=false;
			errors.put("mem_id","아이디는 필수 입력");
		}
		if(pass==null || pass.isEmpty()) {
			valid=false;
			errors.put("mem_id","비밀번호 필수입력");
		}else {
		Pattern regPatern = Pattern.compile("(^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\\$%\\^\\&\\*]+).{4,8})$");
		Matcher matcher = regPatern.matcher(pass);
		if(!matcher.find()) {
			valid=false;
			errors.put("mem_id","비밀번호는 영대소문자 숫자 특수문자를 포함한 4~8글자 이내");
			}else {
				System.out.println(matcher.group(1));
			}
		}
		return valid;
	}

}
