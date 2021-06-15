package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class ReadTmlServlet extends HttpServlet {

	ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer template=readTemplate(req);
		makeData(req);
		String mime = getMime();
		resp.setContentType(mime);
		makeResponseContents(template,req,resp);
		}
	
	protected abstract String getMime();
//	1.tmpl 읽기
	private StringBuffer readTemplate(HttpServletRequest req) throws IOException {
		String tmplPath = req.getServletPath();
		StringBuffer template = null;
		InputStream is = application.getResourceAsStream(tmplPath);
		if(is != null) {
			
			try(BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
				
				String tmp = null;
				template = new StringBuffer();
				while ((tmp=br.readLine())!=null) {
					template.append(String.format("%s\n", tmp));
				}
			}
		}
		return template;
	}
//	2. 데이터 만들기
	protected abstract void makeData(HttpServletRequest req);
//	3. 실제데이터로 채워넣기
//	4. 컨텐츠로 응답데이터 전송
	private void makeResponseContents(StringBuffer template, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if(template == null) {return;}
		if(resp.isCommitted()) {return;}
		
		String tmplSrc = template.toString();
		Pattern regex = Pattern.compile("#\\{([\\w_]+)\\}");
		Matcher matcher = regex.matcher(tmplSrc);
		StringBuffer html = new StringBuffer();
		while(matcher.find()) {
			String name = matcher.group(1);
			Object data = req.getAttribute(name);
			matcher.appendReplacement(html, Objects.toString(data,""));
		}
		matcher.appendTail(html);
		try(PrintWriter out = resp.getWriter();){
			out.print(html);
		}
//		req.getAttribute("test1");
//		req.getAttribute("test2");
	}
}
