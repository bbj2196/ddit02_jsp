package kr.or.ddit.servlet07;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bts")
public class BTSServlet2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dest = "/WEB-INF/views/08/btsForm2.jsp";
		RequestDispatcher rd =  req.getRequestDispatcher(dest);
		rd.forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("btsMember");
		String dest = "/WEB-INF/views/bts/"+id+".jsp";
		RequestDispatcher rd =  req.getRequestDispatcher(dest);
		rd.forward(req,resp);
	}
	
	
}
