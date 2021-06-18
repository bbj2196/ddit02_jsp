package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.MimeType;

/**
 * Servlet implementation class FactorialTest
 */
@WebServlet("/05/factorial")
public class FactorialTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public int fact(int n) {
		if (n <= 1)
			return n;
		else 
			return fact(n-1) * n;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String strMime = request.getParameter("mime");
		String left = request.getParameter("left");
		MimeType mime = MimeType.JSON;
		if(strMime != null && !strMime.isEmpty()) {
		mime = MimeType.findMimeType(strMime);
		}
		int cnt=1;
		if(left != null && !left.isEmpty()) {
			try {
			cnt = Integer.parseInt(left);
			}catch(Exception e){
				System.out.println("숫자가 아님");
				cnt=1;
			}
		}
		response.setContentType(mime.getMimeText());
		String expres = String.format("%d!=%d", cnt,fact(cnt));
		try(
		PrintWriter out = response.getWriter();
		){
			if(MimeType.JSON.equals(mime)) {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object>map = new HashMap<>();
			map.put("left",cnt);
			map.put("operator","!");
			map.put("expression",expres);
			mapper.writeValue(out, map);
			}else if(MimeType.PLAIN.equals(mime)) {
				out.print(expres);
			}else if(MimeType.HTML.equals(mime)) {
				out.println("<result>");
				out.println("<left>");
				out.println(cnt);
				out.println("</left>");
				out.println("<operator>");
				out.println("!");
				out.println("</operator>");
				out.println("<expression>");
				out.println(expres);
				out.println("</expression>");
				out.println("</result>");
			}else {
				response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
