package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/")
public class CustomDefaultServler extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = (request.getRequestURI());
		String cPath = request.getContextPath();
		uri = uri.substring(cPath.length());
		
//		InputStream is = application.getResourceAsStream(uri);
		URL resUrl=application.getResource(uri);
		if(resUrl==null) { 
					response.sendError(HttpServletResponse.SC_NOT_FOUND,uri);
					return;
				}
		// os 열기전?커밋전?
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Pragma", "no-store");
		response.setDateHeader("Expires", 0);
		try (
			OutputStream os = response.getOutputStream();
			){
			Path resPath= Paths.get(resUrl.toURI());
			Files.copy(resPath,os);
			
		} catch (URISyntaxException e) {
			throw new IOException(e);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
