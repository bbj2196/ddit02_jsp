package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 로그인한 유저가 자기정보를 수정함
 */
@Controller
public class MemberUpdateController {
	
	private MemberService service = MemberServiceImpl.getInstance();

	@RequestMapping("/member/update.do")
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO loginMem = (MemberVO) session.getAttribute("authMember");
		if(loginMem == null || StringUtils.isBlank(loginMem.getMemId())){
			response.sendRedirect(request.getContextPath()+"/index.do");
			return null;
		}
		MemberVO updateMem = service.retrieveMember(loginMem.getMemId());
		request.setAttribute("member", updateMem);
		String dest = "member/memberForm";
		return dest;
	}

	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String doPost(@ModelAttribute("member")MemberVO member,@RequestPart("memImage") MultipartFile memImage,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		
		request.setAttribute("member", member);
			member.setMemImage(memImage);
		Map<String, List<String>>errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
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
		
		
		return viewName;
	}
	
}
