package kr.or.ddit.servlet07;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/innerAccess.do")
public class Model2TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contents = "김치찌개";
		request.setAttribute("contents", contents);
		HttpSession session = request.getSession();
		session.setAttribute("contents", contents);
//		String dest = "/WEB-INF/views/inner.jsp";
//		RequestDispatcher rd = request.getRequestDispatcher(dest);
//		rd.forward(request, response);
		String dest = request.getContextPath()+"/07/destination.jsp";
		response.sendRedirect(dest);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
