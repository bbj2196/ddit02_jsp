package kr.or.ddit.prod.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.filter.wrapper.SampleHttpServletRequestWrapper;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

/**
 * Servlet implementation class ProdViewControllerServlet
 */
@WebServlet("/prod/prodView.do")
public class ProdViewControllerServlet extends HttpServlet {
	
	ProdService service = new ProdServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		if(request instanceof SampleHttpServletRequestWrapper) {
			String customData=((SampleHttpServletRequestWrapper) request).getCustomData();
		}
		
		
		String prodId=request.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			response.sendError(400,"올바르지못한 상품입니다");
			return;
		}
		
		try {
		ProdVO prod = service.retrieveProd(prodId);
		request.setAttribute("prod", prod);
		}catch(DataNotFoundException e) {
			response.sendError(400,"해당 상품은 없는 상품입니다");
			return;
		}
		

		String viewName="/prod/prodView";
		
		if(viewName.startsWith("redirect:")) {
			viewName.substring("rediect:".length());
			response.sendRedirect(request.getContextPath()+viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			request.getRequestDispatcher(prefix+viewName+suffix).forward(request, response);
		}
		
	}

}
