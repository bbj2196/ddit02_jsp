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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/create.do")
@MultipartConfig
public class MemberCreateControllerServlet extends HttpServlet {

	private MemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dest="/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(dest).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> map = req.getParameterMap();
		
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		if(req instanceof StandardMultipartHttpServletRequest) {
			StandardMultipartHttpServletRequest request = (StandardMultipartHttpServletRequest) req;
			MultipartFile memImage = request.getFile("memImage");
			member.setMemImage(memImage);
		}
		Map<String, List<String>>errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			BeanUtils.populate(member, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		ValidatorUtils<MemberVO> utils = new ValidatorUtils<>();
		boolean valid=utils.validate(member, errors,InsertGroup.class );
		String viewName = "";
		
		if(valid) {
		ServiceResult result = service.createMember(member);
		String message = "";
		switch (result) {
		case OK:
			// ?????????????????? ??????,  ????????? ????????????????????? -> redirect
			viewName = "redirect:/index.do";
			req.getSession().setAttribute("message", "???????????? ??????");
			break;
		case FAIL:
			viewName="member/memberForm";
			message = "????????? ??????????????????";
			break;
		case PKDUPLICATED:
			viewName="member/memberForm";
			message = "?????? ????????? ???????????????";
			break;
		default:
			// Form?????? ?????? ,forward
			viewName="member/memberForm";
			message = "????????????, ???????????? ?????????????????????";
			break;
		}
		req.setAttribute("message", message);
		
		}else {
			// Form?????? ??????,
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


}
