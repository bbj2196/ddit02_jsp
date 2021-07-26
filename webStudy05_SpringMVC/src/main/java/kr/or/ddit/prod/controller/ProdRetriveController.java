package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * Servlet implementation class ProdListControllerServlet
 */

@Controller
public class ProdRetriveController{
	
	@Inject
	private ProdService service; 
	
	@Inject
	private OthersDAO otherDAO;
	
	@RequestMapping(value="/prod/prodList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProdVO> ajaxfrom(@RequestParam(value="page",required=false,defaultValue="1")int currentPage,
			@ModelAttribute("detailSearch") ProdVO detailSearch,
			Model model,
			HttpServletResponse response
			) throws ServletException, IOException {
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setDetailSearch(detailSearch);
		pagingVO.setCurrentPage(currentPage);
		service.retrieveProdList(pagingVO);
		
		return pagingVO;
		
	}
	
	
	@RequestMapping("/prod/prodList.do")
	public String prodList(
			@RequestParam(value="page",required=false,defaultValue="1")int currentPage,
			@ModelAttribute("sadsdasad") ProdVO detailSearch,
			Model model,
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
		
		
		model.addAttribute("pagingVO", paging);
		model.addAttribute("buyerList", buyerList);
		model.addAttribute("lprodList", lprodList);
		
		return viewName;
		
	}
	
	@RequestMapping("/prod/prodView.do")
		public String view(@RequestParam("what") String prodId,Model model, HttpServletResponse response) throws IOException {
				
		try {
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		}catch(DataNotFoundException e) {
			response.sendError(400,"해당 상품은 없는 상품입니다");
			return null;
		}
		

		return "prod/prodView";
		
		
	}
	
	
	
	
	
	
	
}
