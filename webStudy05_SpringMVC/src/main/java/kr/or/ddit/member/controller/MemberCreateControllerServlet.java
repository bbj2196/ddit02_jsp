package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
@Controller
public class MemberCreateControllerServlet {

	private MemberService service = MemberServiceImpl.getInstance();
	
	@RequestMapping("/member/create.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "member/memberForm";
	}
	
	@RequestMapping(value="/member/create.do",method=RequestMethod.POST)
	public String doPost(@ModelAttribute("member")MemberVO member,@RequestPart("memImage") MultipartFile memImage,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("member", member);
		member.setMemImage(memImage);
		Map<String, List<String>>errors = new HashMap<>();
		
		req.setAttribute("errors", errors);
		ValidatorUtils<MemberVO> utils = new ValidatorUtils<>();
		boolean valid=utils.validate(member, errors,InsertGroup.class );
		String viewName = "";
		List<String>list = new ArrayList<String>();
		if(valid) {
		ServiceResult result = service.createMember(member);
		String message = "";
		switch (result) {
		case OK:
			// 마이페이지로 이동,  요청이 완료되었기때문 -> redirect
			viewName = "redirect:/index.do";
			req.getSession().setAttribute("message", "회원가입 완료");
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
		return viewName;

	}


}
