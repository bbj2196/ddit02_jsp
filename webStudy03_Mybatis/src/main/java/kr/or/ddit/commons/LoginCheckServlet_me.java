package kr.or.ddit.commons;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/loginCheck_me.do")
public class LoginCheckServlet_me extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String okId = "a001";
		String okPass="!1qwe";
		//1. 파라미터 확보
		String id = request.getParameter("mem_id");
		String pass = request.getParameter("mem_pass");
		//2. 검증
		//필수파라미터 누락여부 확인(400)
		if(id == null || id.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"아이디 누락");
			return;
		}
		
		if(pass == null || pass.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"비번 누락");
			return;
		}
		System.out.println(id);
		System.out.println(pass);
		//3. 인증
		//	1)성공 : welcome page로 이동(redirect)
		if(okId.equals(id) && okPass.equals(pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("authId", id);
			response.sendRedirect(request.getContextPath());
			//	2)실패 : login page로 이동(forward)
		}else {
			request.setAttribute("mem_id", id);
			request.getRequestDispatcher("/login/loginForm.jsp").forward(request, response);;
		}
	}

}
