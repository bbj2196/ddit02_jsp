package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdUpdateControllerServlet {

	@Inject
	ProdService service;
	@Inject
	OthersDAO otherDao;
	@RequestMapping("/prod/prodUpdate.do")
	public String doGet(@RequestParam("what") String prodId,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정화면으로이동
		
		String dest="prod/prodForm";
		
		ProdVO prod=null;
		try {
		prod = service.retrieveProd(prodId);
		}catch(DataNotFoundException e) {
			resp.sendError(400,"잘못된 상품 코드");
			return null;
		}
		List<BuyerVO> buyerList = otherDao.selectBuyerList();
		List<Map<String, Object>> lprodList = otherDao.selectLprodList();
		req.setAttribute("buyerList", buyerList);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("prod", prod);
		return dest;
	}
	
	@RequestMapping(value="/prod/prodUpdate.do",method=RequestMethod.POST)
	public String doPost(
			@RequestPart("prodImage")MultipartFile image,
			@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod,
			Errors errors,
			Model model,
			HttpServletResponse resp) throws ServletException, IOException {
		// 수정하기
		String message = null;
		String viewName="prod/prodForm";
		
		model.addAttribute("prod", prod);
		
		if(!errors.hasErrors()) {
			ServiceResult result=null;
			try {
			result = service.modifyProd(prod);
			}catch(DataNotFoundException e) {
				resp.sendError(400,"해당 상품은 없습니다");
				return null;
			}
			
			switch (result) {
			case OK:	
				message="수정 완료";
				viewName="redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			case FAIL:
				message="수정 실패 ";
				viewName="prod/prodForm";
				break;
			default:
				message = "서버오류 잠시후에";
				viewName="prod/prodForm";
				break;	
			}
			model.addAttribute("message", message);
		}
		
		return viewName;
		
	}
}
