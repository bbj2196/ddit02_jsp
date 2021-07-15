package kr.or.ddit.servlet06;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionExtend
 */
@WebServlet("/sessionExtend")
public class SessionExtend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("요청 하위~");
	}
}
