package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;


import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

/**
 * 로그인한 유저가 자기정보를 수정함
 */
@WebServlet("/member/update.do")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService service = MemberServiceImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO loginMem = (MemberVO) session.getAttribute("authMember");
		if(loginMem == null || StringUtils.isBlank(loginMem.getMemId())){
			response.sendRedirect(request.getContextPath()+"/index.do");
			return;
		}
		MemberVO updateMem = service.retrieveMember(loginMem.getMemId());
		request.setAttribute("member", updateMem);
		String dest = "/WEB-INF/views/member/memberForm.jsp";
		request.getRequestDispatcher(dest).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		
		MemberVO member = new MemberVO();
		request.setAttribute("member", member);
		
		Map<String, List<String>>errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		try {
			BeanUtils.populate(member, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		ValidatorUtils<MemberVO> utils = new ValidatorUtils<>();
		boolean valid=utils.validate(member, errors,UpdateGroup.class );
		String viewName = "";
		
		if(valid) {
		ServiceResult result = service.modifyMember(member);
		String message = null;
		switch (result) {
		case OK:
			// 마이페이지로 이동,  요청이 완료되었기때문 -> redirect
			viewName = "redirect:/mypage.do";
			break;
		case INVALIDPASSWORD:
			// Form으로 이동 ,forward
			viewName="member/memberForm";
			message = "비밀번호 오류	";
			break;
		default:
			// Form으로 이동 ,forward
			viewName="member/memberForm";
			message = "서버오류, 잠시뒤에 다시요청하세요";
			break;
		}
		
		}else {
			// Form으로 이동,
			viewName="member/memberForm";
		}
		
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("rediect:".length()+1);
			response.sendRedirect(request.getContextPath()+viewName);
		}else {
			String prefix= "/WEB-INF/views/";
			String suffix= ".jsp";
			request.getRequestDispatcher(prefix+viewName+suffix).forward(request, response);
		}
	}
	
}
