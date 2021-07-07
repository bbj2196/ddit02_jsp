package kr.or.ddit.servlet08;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.OperatorType;
import kr.or.ddit.vo.OperateVO;

@WebServlet("/calculate_sam.do")
public class CalculateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = request.getHeader("Content-Type");
		String accept = request.getHeader("Accept");
		OperateVO operVO = null;
		if(contentType != null && contentType.contains("json")) {
			operVO = generateVOFromJson(request);
		}else {
			operVO = generateVOFromParameter(request);
		}
		
		Object content = null;
		if(accept.contains("json")) {
			response.setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			content = mapper.writeValueAsString(operVO);
		}else {
			response.setContentType("text/plain;charset=utf-8");
			content = operVO.getExpression();
		}
		
		try(
				PrintWriter out = response.getWriter();
				){
			out.print(content);
		}
	}

	private OperateVO generateVOFromParameter(HttpServletRequest request) {
		String leftParam = request.getParameter("left");
		String rightParam = request.getParameter("right");
		String operParam = request.getParameter("operator");
		double left = Double.parseDouble(leftParam);
		double right = Double.parseDouble(rightParam);
		OperatorType operatorType = OperatorType.valueOf(operParam);
		OperateVO operateVO = new OperateVO(left, right, operatorType);
		
		return operateVO;
	}

	private OperateVO generateVOFromJson(HttpServletRequest request)throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		try(
		InputStream is = request.getInputStream();
				){
		return mapper.readValue(is, OperateVO.class);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
