package kr.or.ddit.servlet07;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumtype.BtsType;

@WebServlet("/bts.do")
public class BTSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="/WEB-INF/views/08/btsForm.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btsmem = request.getParameter("btsMember");
		if(btsmem == null || btsmem.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"필수 파라미터 누락");
			return;
		}
		BtsType ty = BtsType.findBts(btsmem);
		Date searchTime = new Date();
		request.setAttribute("searchTime", searchTime);
		String path = "/WEB-INF/views/bts/";
		
		String fileName = ty.getName()+".jsp";
		
		request.getRequestDispatcher(path+fileName).forward(request, response);
		
	}

}
