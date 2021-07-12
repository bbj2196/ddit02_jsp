package kr.or.ddit.prod.controller;

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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

/**
 * Servlet implementation class ProdInsertControllerServlet
 */
@WebServlet("/prod/prodInsert.do")
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String viewName="";
		Map<String, String[]> params = request.getParameterMap();
		ProdVO prod = new ProdVO();
		Map<String, Object> errors = new HashMap<>();
		
		request.setAttribute("prod", prod);
		try {
			BeanUtils.populate(prod, params);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		boolean valid = validate(prod,errors);
		if(valid) {
		ServiceResult result = service.createProd(prod);
		String message = null;
		switch (result) {
		case OK:
			viewName="redirect:/prod/prodInsert.do";
			request.getSession().setAttribute("message", "상품등록 성공");
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
//			String prefix = "/WEB-INF/views/";
//			String suffix = ".jsp";
//			request.getRequestDispatcher(prefix+viewName+suffix).forward(request, response);
			doGet(request, response);
		}
	}

	private boolean validate(ProdVO prod,Map<String, Object> errors) {
		boolean valid = true;
		
		
		if(StringUtils.isBlank(prod.getProdName())){valid=false;errors.put("prodName","상품 명 누락");}
		if(StringUtils.isBlank(prod.getProdLgu())){valid=false;errors.put("prodLgu","상품 분류 코드 누락");}
		if(StringUtils.isBlank(prod.getProdBuyer())){valid=false;errors.put("prodBuyer","거래처 코드 누락");}
		if(StringUtils.isBlank(prod.getProdOutline())){valid=false;errors.put("prodOutline","상품 개략 설명 누락");}
		if(StringUtils.isBlank(prod.getProdImg())){valid=false;errors.put("prodImg","이미지(소) 누락");}
		if(prod.getProdCost() == null){valid=false;errors.put("prodCost","매입가 누락");}
		if(prod.getProdPrice()== null){valid=false;errors.put("prodPrice","소비자가 누락");}
		if(prod.getProdSale()== null){valid=false;errors.put("prodSale","판매가 누락");}
		if(prod.getProdTotalstock()== null){valid=false;errors.put("prodTotalstock","재고수량 누락");}
		if(prod.getProdProperstock()== null){valid=false;errors.put("prodProperstock","안전 재고 수량 누락");}
		
		return valid;
	}

}
