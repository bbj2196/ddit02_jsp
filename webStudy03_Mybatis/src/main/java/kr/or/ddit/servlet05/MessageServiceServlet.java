package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.MimeType;

@WebServlet("/05/messageService")
public class MessageServiceServlet extends HttpServlet{
	Map<String,Object>bundleMap = new HashMap<>();
	ResourceBundle bundle;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String basename = config.getInitParameter("basename");
		bundle = ResourceBundle.getBundle("kr/or/ddit/servlet05/message");
		for(String key : bundle.keySet()) {
			bundleMap.put(key,bundle.getObject(key));
		}
		
	}	
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		 mime설정
		 MimeType mime = MimeType.findMimeType(req.getHeader("accept"));
		 if(!MimeType.JSON.equals(mime)) {
			 resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			 return;
		 }
		 resp.setContentType(mime.getMimeText());
//		 1. 마샬링
		 ObjectMapper mapper = new ObjectMapper();
//		 String json =mapper.writeValueAsString(bundleMap);
//		 2. 직렬화
		 resp.setCharacterEncoding("utf-8");
		 try(
		 PrintWriter out = resp.getWriter();
				 ){
			 mapper.writeValue(out, bundleMap);
//		 out.print(resp);
		 }
		 
	}
}
