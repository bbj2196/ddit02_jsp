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
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequsetPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController{

	OthersDAO other = new OthersDAOImpl();
	ProdService service = new ProdServiceImpl();
	
	private void addAttribute(HttpServletRequest request) {
		List<BuyerVO> buyerList = other.selectBuyerList();
		List<Map<String, Object>> lrpdoList = other.selectLprodList();
		
		request.setAttribute("buyerList", buyerList);
		request.setAttribute("lprodList", lrpdoList);
	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addAttribute(request);
		return"prod/prodForm";
	}


	@RequestMapping(value="/prod/prodInsert.do",method=RequestMethod.POST)
	public String prodInsert(@ModelAttribute("prod")ProdVO prod,@RequsetPart("prodImage") MultipartFile prodImage,HttpServletRequest request){
		String viewName=null;
		
		if(request instanceof StandardMultipartHttpServletRequest) {
			prod.setProdImage(prodImage);
		}
		
		Map<String, List<String>> errors = new HashMap<>();
		ValidatorUtils<ProdVO> validator = new ValidatorUtils<ProdVO>();
		boolean valid = validator.validate(prod, errors , InsertGroup.class);
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
		
		return viewName;
	}


}
