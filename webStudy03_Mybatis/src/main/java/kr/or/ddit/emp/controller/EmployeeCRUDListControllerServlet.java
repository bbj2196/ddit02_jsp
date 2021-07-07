package kr.or.ddit.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.emp.service.EmployeeService;
import kr.or.ddit.emp.service.EmployeeServiceImpl;
import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.vo.EmployWrapper;
import kr.or.ddit.vo.EmployeeVO;

@WebServlet("/employee/empCRUD.do")
public class EmployeeCRUDListControllerServlet extends HttpServlet{
	private EmployeeService serv = EmployeeServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String empNo = req.getParameter("empno");
		
		if(empNo == null||!StringUtils.isNumeric(empNo)) {
			resp.sendError(400,"숫자가아님");
			return;
		}
		EmployeeVO vo = null;
		try {
			vo=serv.retrieveEmployee(Integer.parseInt(empNo));
		}catch(UserNotFoundExecption e) {
			resp.sendError(400,"회원이 없음");
		}
		
			resp.setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			try (
					PrintWriter out = resp.getWriter();
					){
				mapper.writeValue(out,vo);
			}

	}
}
