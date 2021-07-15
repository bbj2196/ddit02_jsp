package kr.or.ddit.prod.controller;

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
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

/**
 * Servlet implementation class ProdInsertControllerServlet
 */
@WebServlet("/prod/prodInsert.do")
@MultipartConfig
public class ProdInsertControllerServlet extends HttpServlet {

	OthersDAO other = new OthersDAOImpl();
	ProdService service = new ProdServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewName="prod/prodForm";

		
		List<BuyerVO> buyerList = other.selectBuyerList();
		List<Map<String, Object>> lrpdoList = other.selectLprodList();
		
		request.setAttribute("buyerList", buyerList);
		request.setAttribute("lprodList", lrpdoList);
		
		
		if(viewName.startsWith("redirect:")) {
			viewName.substring("rediect:".length());
			response.sendRedirect(request.getContextPath()+viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			request.getRequestDispatcher(prefix+viewName+suffix).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String viewName="";
		ProdVO prod = new ProdVO();
		if(request instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest)request).getFile("prodImage");
			prod.setProdImage(prodImage);
		}
		Map<String, String[]> params = request.getParameterMap();
		Map<String, List<String>> errors = new HashMap<>();
		
		request.setAttribute("prod", prod);
		try {
			BeanUtils.populate(prod, params);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		ValidatorUtils<ProdVO> validator = new ValidatorUtils<ProdVO>();
		boolean valid = validator.validate(prod, errors , InsertGroup.class);
		boolean pass=false;
		if(valid) {
		ServiceResult result = service.createProd(prod);
		String message = null;
		switch (result) {
		case OK:
			viewName="redirect:/prod/prodView.do?what="+prod.getProdId();
//			request.getSession().setAttribute("message", "상품등록 성공");
			break;
		case FAIL:
			viewName="prod/prodForm";
			message="상품등록 실패";
			break;
		default:
			viewName="prod/prodForm";
			message="서버 에러 다시시도";
			break;
		}
		request.setAttribute("message", message);
		}else {
			viewName="prod/prodForm";
			request.setAttribute("errors", errors);
		}
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("rediect:".length()+1);
			response.sendRedirect(request.getContextPath()+viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			request.getRequestDispatcher(prefix+viewName+suffix).forward(request, response);
		}
	}


}
