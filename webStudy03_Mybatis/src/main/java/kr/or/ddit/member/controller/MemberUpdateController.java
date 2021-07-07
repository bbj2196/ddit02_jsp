package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXB;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
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
		}
		MemberVO updateMem = service.retrieveMember(loginMem.getMemId());
		request.setAttribute("member", updateMem);
		String dest = "/WEB-INF/views/member/memberForm.jsp";
		request.getRequestDispatcher(dest).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> map = request.getParameterMap();
		
		MemberVO member = new MemberVO();
		request.setAttribute("member", member);
		
		Map<String, String>errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		try {
			BeanUtils.populate(member, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		boolean valid=validate(member,errors);
		String viewName = "";
		
		if(valid) {
		ServiceResult result = service.modifyMember(member);
		String message = "";
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

	/**
	 * Member 테이블의 제약조건에 따른 검증
	 * @param member
	 * @param errors
	 * @return
	 */
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원 ID 누락");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "비밀 번호 누락");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "회원 명 누락");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편 번호 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "이메일 주소 누락");
		}
		
		if(StringUtils.isNotBlank(member.getMemMemorialday())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(member.getMemMemorialday());
			} catch (ParseException e) {
				valid = false;
				errors.put("memMemorialday","날짜 형식 확인");
			}
		}
		return valid;
	}

}
