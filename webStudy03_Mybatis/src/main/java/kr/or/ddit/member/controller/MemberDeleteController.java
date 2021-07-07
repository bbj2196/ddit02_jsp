package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 로그인되어있는 사용자에 대한 탈퇴 처리.
 *
 */
@WebServlet("/member/memberDelete.do")
public class MemberDeleteController extends HttpServlet{
	private MemberService service = MemberServiceImpl.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO();
		HttpSession session = req.getSession();
		MemberVO savedMember = (MemberVO) session.getAttribute("authMember");
		member.setMemId(savedMember.getMemId());
		String memPass = req.getParameter("memPass");
		member.setMemPass(memPass);
		
		String message = null;
		String viewName = null;
		ServiceResult result = service.removeMember(member);
		switch (result) {
		case OK:
//			welcome page : redirect
			viewName = "redirect:/index.do";
			session.invalidate();
			break;
		case INVALIDPASSWORD:
//			mypage, redirect
			viewName = "redirect:/mypage.do";
			message = "비밀번호 오류";
			session.setAttribute("message", message);
			break;
		default:
//			mypage, redirect
			viewName = "redirect:/mypage.do";
			message = "서버 오류, 좀따~다시.";
			session.setAttribute("message", message);
			break;
		}
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath()+viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		}
	}
}
