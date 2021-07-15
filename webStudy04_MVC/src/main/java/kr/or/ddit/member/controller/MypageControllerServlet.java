package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

/**
 * Servlet implementation class MypageControllerServlet
 */
@Controller
public class MypageControllerServlet{
       
   @RequestMapping("/mypage.do")
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		MemberVO user = (MemberVO)session.getAttribute("authMember");
		if(user == null) {
			response.sendError(400,"로그인 안됨");
			return null;
		}
		
		return "redirect:/member/memberView.do?who="+user.getMemId();
		
	}

	

}
