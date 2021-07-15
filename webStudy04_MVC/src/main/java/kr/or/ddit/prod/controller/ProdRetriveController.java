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

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
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

@Controller
public class ProdRetriveController{
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO otherDAO = new OthersDAOImpl();
	
	@RequestMapping("/prod/prodList.do")
	public String prodList(
			@RequestParam(value="page",required=false,defaultValue="1")int currentPage,
			@ModelAttribute("detailSearch") ProdVO detailSearch,
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		// 검색조건 : 상품명, 상품분류코드ㅏ, 거래처 코드 (상세검색)
		String viewName="prod/prodList";
		
		
		
		PagingVO<ProdVO> paging = new PagingVO<>(5, 2);
		paging.setDetailSearch(detailSearch);
		paging.setCurrentPage(currentPage);
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
			return null;
		}else {
			
		
		request.setAttribute("pagingVO", paging);
		request.setAttribute("buyerList", buyerList);
		request.setAttribute("lprodList", lprodList);
		
		return viewName;
		}
	}
	
	@RequestMapping("/prod/prodView.do")
		public String view(@RequestParam("what") String prodId,HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		try {
		ProdVO prod = service.retrieveProd(prodId);
		request.setAttribute("prod", prod);
		}catch(DataNotFoundException e) {
			response.sendError(400,"해당 상품은 없는 상품입니다");
			return null;
		}
		

		return "prod/prodView";
		
		
	}
	
	
	
	
	
	
	
}
