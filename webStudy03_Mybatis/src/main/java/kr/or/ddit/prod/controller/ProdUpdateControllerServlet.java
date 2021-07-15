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


import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodUpdate.do")
@MultipartConfig
public class ProdUpdateControllerServlet extends HttpServlet{

	ProdService service = new ProdServiceImpl();
	OthersDAO otherDao = new OthersDAOImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정화면으로이동
		
		String dest="/WEB-INF/views/prod/prodForm.jsp";
		String prodId = req.getParameter("what");
		
		ProdVO prod=null;
		try {
		prod = service.retrieveProd(prodId);
		}catch(DataNotFoundException e) {
			resp.sendError(400,"잘못된 상품 코드");
			return;
		}
		List<BuyerVO> buyerList = otherDao.selectBuyerList();
		List<Map<String, Object>> lprodList = otherDao.selectLprodList();
		req.setAttribute("buyerList", buyerList);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("prod", prod);
		req.getRequestDispatcher(dest).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정하기
		String message = null;
		String viewName="prod/prodForm";
		ProdVO prod = new ProdVO();
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest)req).getFile("prodImage");
			prod.setProdImage(prodImage);
		}
		Map<String, String[]> params = req.getParameterMap();
		try {
			BeanUtils.populate(prod, params);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			resp.sendError(500,"VO 변환불가");
			return;
		}
		Map<String, List<String>> errors = new HashMap<>();
		ValidatorUtils<ProdVO> validator = new ValidatorUtils<ProdVO>();
		req.setAttribute("prod", prod);
		req.setAttribute("errors", errors);
		boolean valid = validator.validate(prod, errors, UpdateGroup.class);
		
		if(valid) {
			ServiceResult result=null;
			try {
			result = service.modifyProd(prod);
			}catch(DataNotFoundException e) {
				resp.sendError(400,"해당 상품은 없습니다");
				return;
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
			req.setAttribute("message", message);
		}
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath()+viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		}
		
	}
}
