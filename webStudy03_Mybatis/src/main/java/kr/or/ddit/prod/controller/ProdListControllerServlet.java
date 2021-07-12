package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * Servlet implementation class ProdListControllerServlet
 */
@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet {
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO otherDAO = new OthersDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 검색조건 : 상품명, 상품분류코드ㅏ, 거래처 코드 (상세검색)
		String viewName="/prod/prodList";
		
		String prodName = request.getParameter("prodName");
		String prodLgu = request.getParameter("prodLgu");
		String prodBuyer = request.getParameter("prodBuyer");
		String page = request.getParameter("page");
		
		ProdVO searchVO = new ProdVO();
		searchVO.setProdName(prodName);
		searchVO.setProdLgu(prodLgu);
		searchVO.setProdBuyer(prodBuyer);
		
		PagingVO<ProdVO> paging = new PagingVO<>(5, 2);
		paging.setDetailSearch(searchVO);
		int curPage = 1;
		if(StringUtils.isNumeric(page)) {
			curPage = Integer.parseInt(page);
		}
		paging.setCurrentPage(curPage);
		service.retrieveProdList(paging);
		List<BuyerVO> buyerList = otherDAO.selectBuyerList();
		List<Map<String, Object>> lprodList = otherDAO.selectLprodList();
		
		String accept = request.getHeader("accept");
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			
			response.setContentType("application/json;charset=utf-8");
			
			ObjectMapper mapper = new ObjectMapper();
			
			try(
					PrintWriter out = response.getWriter();
					){
			mapper.writeValue(out, paging);
			}
		}else {
			
		
		request.setAttribute("pagingVO", paging);
		request.setAttribute("buyerList", buyerList);
		request.setAttribute("lprodList", lprodList);
		
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		doGet(request, response);
	}

}
