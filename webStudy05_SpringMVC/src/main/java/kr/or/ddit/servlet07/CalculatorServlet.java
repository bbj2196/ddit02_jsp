package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.MimeType;
import kr.or.ddit.vo.CalculatorVO;
import kr.or.ddit.vo.OperInfoVO;
import kr.or.ddit.vo.OperVO;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/caculate.do")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramLeft = req.getParameter("left");
		String paramRight = req.getParameter("right");
		String operator = req.getParameter("oper");
		String accept = req.getParameter("mime");
		OperInfoVO infoVO = (OperInfoVO)getServletContext().getAttribute("operInfo");
		List<OperVO>opList = infoVO.getOperlist();
		
		if(accept == null || accept.isEmpty()) {
			accept = req.getHeader("accept");
		}
		int status = 200;
		if(paramLeft ==null || paramRight ==null) {
			status = 400;
		}else {
			int left = Integer.parseInt(paramLeft);
			int right = Integer.parseInt(paramRight);
			
//			if(!opList.contains(operator)) {
//				System.out.println("해당된 oper가 없음");
//				status=400;
//			}
			OperVO opervo=null;
			for (OperVO operVO : opList) {
				if(operVO.getText().equals(operator)) {
					opervo = operVO;
					break;
				}
			}
			if(opervo == null) {
				System.out.println("해당된 oper가 없음");
				status=400;
			}
			
		if(status==200) {
			String contentType = MimeType.findMimeText(accept);
			resp.setContentType(Objects.toString(contentType,"text/html"));
			try(
				PrintWriter out = resp.getWriter();
			){
				String contents = generateContents(new CalculatorVO(left, right, opervo),req);
				out.print(contents);
			}
		}else {
			resp.sendError(status);
		}
	}
	}
	private String generateContents(CalculatorVO target,HttpServletRequest req) throws JsonProcessingException{
		
		String contents = null;
		String contentType = null;
		String accept = req.getHeader("Accept");
		if(accept.contains("json")) {
			contentType = "application/json;charset=UTF-8";
			ObjectMapper mapper = new ObjectMapper();
			contents = mapper.writeValueAsString(target);

		}else {
			contentType = "text/plain;charset=UTF-8";
			contents = target.getExpression();
		}
		req.setAttribute("contentType", contentType);
		return contents;
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
