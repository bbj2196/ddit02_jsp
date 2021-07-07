package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

/**
 * Servlet implementation class MypageControllerServlet
 */
@WebServlet("/mypage.do")
public class MypageControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer dest = new StringBuffer();
		dest.append("/member/memberView.do?who=");
		HttpSession session = request.getSession();
		
		MemberVO user = (MemberVO)session.getAttribute("authMember");
		if(user == null) {
			response.sendError(400,"로그인 안됨");
			return;
		}
		dest.append(user.getMemId());
		request.getRequestDispatcher(dest.toString()).forward(request, response);
		
	}

	

}
