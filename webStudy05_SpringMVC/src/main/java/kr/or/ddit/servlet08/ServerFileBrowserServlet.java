package kr.or.ddit.servlet08;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.FancyTreeNodeAdapter;
////////////////////////////////////////////////////////////////////////
// Sam Code
//////////////////////////////////////////////////////////////////
@WebServlet("/fileBrowser.do")
public class ServerFileBrowserServlet extends HttpServlet {

	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application=getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		if(accept.indexOf("json")>=0) {
			processJsonRequest(req,resp);
		}else {
			String dest="/WEB-INF/views/serverBrowser.jsp";
			req.getRequestDispatcher(dest).forward(req, resp);
		}
		
		
	}

	private void processJsonRequest(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException {
		String base=req.getParameter("base");
		
		if(base == null || base.isEmpty()) {
			base="/";
		}
		 Set<String> childern = application.getResourcePaths(base);
		 if(childern == null) {
			 resp.sendError(404);
			 return;
		 }
		 List<File> fileList = new ArrayList<>();
		 
		 for (String child : childern) {
			System.out.println(child);
			String realPath = application.getRealPath(child);
			File file = new File(realPath);
			FancyTreeNodeAdapter wrapper = new FancyTreeNodeAdapter(file, realPath);
			fileList.add(wrapper);
		}
		 Collections.sort(fileList);
		 resp.setContentType("application/json;charset=utf-8");
		 try(PrintWriter out = resp.getWriter();){
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.writeValue(out, fileList);
		 }
	}
}
