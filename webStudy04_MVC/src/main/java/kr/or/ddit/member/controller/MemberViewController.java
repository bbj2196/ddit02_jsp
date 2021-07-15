package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

/**
 * POJO(Plain Old Java Object)
 * @author PC-13
 *
 */
@Controller
public class MemberViewController{

	MemberService serivce = MemberServiceImpl.getInstance();
	
	@RequestMapping(value="/member/memberView.do")
	public String view(@RequestParam("who") String who,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. who 받기
		// 2. 검증
		if(StringUtils.isBlank(who)) {
			resp.sendError(400,"who없음");
			return null;
		}
		// 3. service 호출
		// 4. service의 메서드 리턴
		MemberVO vo = null;
		try {
		vo = serivce.retrieveMember(who);
		}catch(UserNotFoundExecption e) {
			resp.sendError(400,"존재하지않는 유저");
			return null;
		}
		// 5. 결과값 보내주기
		req.setAttribute("member", vo);
		return "member/memberView";
		
	}

}
