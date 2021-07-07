package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewControllerServlet extends HttpServlet{

	MemberService serivce = MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String who=req.getParameter("who");
		// 1. who 받기
		// 2. 검증
		if(StringUtils.isBlank(who)) {
			resp.sendError(400,"who없음");
			return;
		}
		// 3. service 호출
		// 4. service의 메서드 리턴
		MemberVO vo = null;
		try {
		vo = serivce.retrieveMember(who);
		}catch(UserNotFoundExecption e) {
			resp.sendError(400,"존재하지않는 유저");
			return;
		}
		// 5. 결과값 보내주기
		req.setAttribute("member", vo);
		String dest = "/WEB-INF/views/member/memberView.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String who=req.getParameter("who");
		// 1. who 받기
		// 2. 검증
		if(StringUtils.isBlank(who)) {
			resp.sendError(400,"who없음");
			return;
		}
		// 3. service 호출
		// 4. service의 메서드 리턴
		MemberVO vo = null;
		try {
		vo = serivce.retrieveMember(who);
		}catch(UserNotFoundExecption e) {
			resp.sendError(400,"존재하지않는 유저");
			return;
		}
		req.setAttribute("member", vo);
		// 5. 결과값 보내주기
		req.getRequestDispatcher("/WEB-INF/views/member/memberView.jsp").forward(req, resp);
	}
}
