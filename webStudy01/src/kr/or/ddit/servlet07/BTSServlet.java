package kr.or.ddit.servlet07;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumtype.BtsType;

/**
 * Servlet implementation class BTSServlet
 */
@WebServlet("/bts.do")
public class BTSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="/WEB-INF/views/08/btsForm.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btsmem = request.getParameter("btsMember");
		
		BtsType ty = BtsType.findBts(btsmem);
		String path = "/WEB-INF/views/bts/";
		
		String fileName = ty.getName()+".jsp";
		
		request.getRequestDispatcher(path+fileName).forward(request, response);
		
	}

}
