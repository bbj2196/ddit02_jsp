package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
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

@WebServlet("/05/messageServiceWithLocale")
public class MessageServiceServletWithLocale extends HttpServlet{
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8"); 
		String lang = req.getParameter("lang");
		Locale loc = req.getLocale();
		if(lang!= null && !lang.isEmpty()) {
			loc = Locale.forLanguageTag(lang);
//			for(Locale locales : Locale.getAvailableLocales()) {
//				if(locales.toLanguageTag().equals(lang)) {
//					loc = locales;
//					break;
//				}
//			}
		}
		ResourceBundle bundle = ResourceBundle.getBundle("kr/or/ddit/servlet05/message", loc);
		Map<String, Object> bundleMap = new HashMap<>();
		for (String key : bundle.keySet()) {
			bundleMap.put(key, bundle.getObject(key));
		}
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
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
}
