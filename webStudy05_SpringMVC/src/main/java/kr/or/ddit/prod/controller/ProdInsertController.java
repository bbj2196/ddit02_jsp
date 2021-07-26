package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController{

	@Inject
	OthersDAO other;
	
	@Inject
	ProdService service;
	
	private void addAttribute(Model model) {
		List<BuyerVO> buyerList = other.selectBuyerList();
		List<Map<String, Object>> lrpdoList = other.selectLprodList();
		
		model.addAttribute("buyerList", buyerList);
		model.addAttribute("lprodList", lrpdoList);
	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String form(Model model, HttpServletResponse response) throws ServletException, IOException {
		addAttribute(model);
		return"prod/prodForm";
	}


	@RequestMapping(value="/prod/prodInsert.do",method=RequestMethod.POST)
	public String prodInsert(
			@Validated(InsertGroup.class) @ModelAttribute("prod")ProdVO prod,
			Errors errors,
			@RequestPart("prodImage") MultipartFile prodImage,
			Model model
			){
		String viewName=null;
		
		if(!errors.hasErrors()) {
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
		model.addAttribute("message", message);
		}else {
			viewName="prod/prodForm";
			model.addAttribute("errors", errors);
		}
		
		return viewName;
	}


}
