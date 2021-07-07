package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/create.do")
public class MemberCreateControllerServlet extends HttpServlet {

	private MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dest="/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Map<String, String[]> map = req.getParameterMap();
		
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		
		Map<String, String>errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			BeanUtils.populate(member, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		boolean valid=validate(member,errors);
		String viewName = "";
		
		if(valid) {
		ServiceResult result = service.createMember(member);
		String message = "";
		switch (result) {
		case OK:
			// 마이페이지로 이동,  요청이 완료되었기때문 -> redirect
			viewName = "redirect:/index.do";
			break;
		case FAIL:
			viewName="member/memberForm";
			message = "가입에 실패했습니다";
			break;
		case PKDUPLICATED:
			viewName="member/memberForm";
			message = "이미 가입된 회원입니다";
			break;
		default:
			// Form으로 이동 ,forward
			viewName="member/memberForm";
			message = "서버오류, 잠시뒤에 다시요청하세요";
			break;
		}
		req.setAttribute("message", message);
		
		}else {
			// Form으로 이동,
			viewName="member/memberForm";
		}
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("rediect:".length()+1);
			resp.sendRedirect(req.getContextPath()+viewName);
		}else {
			String prefix= "/WEB-INF/views/";
			String suffix= ".jsp";
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		}
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(member.getMemId())){valid=false;errors.put("memId","회원 ID 누락");}
		if(StringUtils.isBlank(member.getMemPass())){valid=false;errors.put("memPass","비밀 번호 누락");}
		if(StringUtils.isBlank(member.getMemName())){valid=false;errors.put("memName","회원 명 누락");}
		if(StringUtils.isBlank(member.getMemZip())){valid=false;errors.put("memZip","우편 번호 누락");}
		if(StringUtils.isBlank(member.getMemAdd1())){valid=false;errors.put("memAdd1","주소1 누락");}
		if(StringUtils.isBlank(member.getMemAdd2())){valid=false;errors.put("memAdd2","주소2 누락");}
		if(StringUtils.isBlank(member.getMemMail())){valid=false;errors.put("memMail","이메일 주소 누락");}
		return valid;
	}
}
