package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.controller.MemberListController;

public class FrontController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri=uri.substring(req.getContextPath().length()).split(";")[0];
		String viewName="/member/memberList.do";
		if("/member/memberList.do".equals(uri)) {
			MemberListController controller = new MemberListController();
			viewName = controller.list(req, resp);
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
