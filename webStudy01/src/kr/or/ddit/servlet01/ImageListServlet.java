package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageListServlet extends HttpServlet {
	String contentPath;
	ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		contentPath = application.getInitParameter("contentsPath");
		System.out.printf("%s 서블릿 초기화됨\n",getClass().getName());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		File contentsFolder = new File(contentPath);
		String[] imagList = contentsFolder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				boolean flag=false;
				// mime/sub; charset=encoding
				String mimeText = application.getMimeType(name);
				return mimeText != null && mimeText.startsWith("image/");
			}
		});
		StringBuffer options = new StringBuffer();
		String pattern = "<option>%s</option>";
		
		for (String name : imagList) {
			options.append(String.format(pattern, name));
		}

		InputStream is = getClass().getResourceAsStream("imageList.tmpl");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String tmp =null;
		StringBuffer tmplSource = new StringBuffer();
		while((tmp = br.readLine())!=null) {
			tmplSource.append(tmp+"\n");
		}
		String html = tmplSource.toString().replace("#{data}", options);
		resp.getWriter().println(html);
	}
}
