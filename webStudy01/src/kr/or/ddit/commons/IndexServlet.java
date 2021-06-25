package kr.or.ddit.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.OperInfoVO;
import kr.or.ddit.vo.ServiceInfoVO;

@WebServlet(value="/index.do",loadOnStartup=1)
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application=getServletContext();
		try(
		InputStream is =getClass().getResourceAsStream("../serviceInfo.xml");
				){
			
			JAXBContext context = JAXBContext.newInstance(ServiceInfoVO.class);
			Unmarshaller unmar = context.createUnmarshaller();
			ServiceInfoVO vo = (ServiceInfoVO) unmar.unmarshal(is);
			application.setAttribute("serviceInfo", vo);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		// cal용 서버에 oper 올리기
		try(
				InputStream is =getClass().getResourceAsStream("../operInfo.xml");
				){
			JAXBContext context = JAXBContext.newInstance(OperInfoVO.class);
			Unmarshaller unmarsal = context.createUnmarshaller();
			OperInfoVO infoVO=(OperInfoVO) unmarsal.unmarshal(is);
			application.setAttribute("operInfo", infoVO);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String service = request.getParameter("service");
		ServiceInfoVO infoVO = (ServiceInfoVO) application.getAttribute("serviceInfo");
		List<MenuVO>menuList = infoVO.getMenuList();
		String contentsPage=null;
		int status=HttpServletResponse.SC_OK;
		String errMsg = "";
		String dest = "/WEB-INF/views/template.jsp";
		if(service == null || service.isEmpty()) {
			contentsPage="/WEB-INF/views/index.jsp";
		}else {
			MenuVO searchCondition = new MenuVO();
			searchCondition.setCode(service);
			int index = menuList.indexOf(searchCondition);
			if(index !=-1) {
				MenuVO finded =  menuList.get(index);
				contentsPage=finded.getLink();
			}else {
				status=HttpServletResponse.SC_NOT_FOUND;
				errMsg=String.format("%s 서비스 제공 불가", service);
			}
		}
		if(status == HttpServletResponse.SC_OK) {
			request.setAttribute("contentsPage", contentsPage);
			request.getRequestDispatcher(dest).forward(request, response);
		}else {
			response.sendError(status, errMsg);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
