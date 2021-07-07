package kr.or.ddit.prop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prop.service.DataBasePropertyService;
import kr.or.ddit.prop.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

/**
 * 요청을 받고, 분석하고, 로직을 사용하고, 로직으로부터 MODEL 데이터 확보.
 * VIEW 를 선택하고, MODEL을 전달함 -> Controller Layer
 * @author PC-13
 *
 */
@WebServlet("/11/jdbcDesc.do")
public class DataBasePorpertyController extends HttpServlet{

	private DataBasePropertyService service = new DataBasePropertyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getHeader("Accept");
		String search = req.getParameter("search");
		DataBasePropertyVO param = new DataBasePropertyVO();
		param.setPropertyName(search);
		param.setPropertyValue(search);
		param.setDescription(search);
		
		
		if(type.contains("json")) {
			resp.setContentType("application/json;charset=utf-8");
			resp.setCharacterEncoding("utf-8");
			List<DataBasePropertyVO> proplist = service.retrieveDataBaseProperties(param);
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out = resp.getWriter();
					){
			mapper.writeValue(out, proplist);
			}
		}else {
			req.setAttribute("contentsPage", "/WEB-INF/views/11/jdbcDesc.jsp");
			String dest = "/WEB-INF/views/template.jsp";
			req.getRequestDispatcher(dest).forward(req, resp);
		}
	}
}
