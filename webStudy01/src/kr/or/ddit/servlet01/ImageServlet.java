package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

public class ImageServlet extends HttpServlet{
	ServletContext application;
	File contentsFolder;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application=getServletContext();
		String contentPath = application.getInitParameter("contentsPath");
		contentsFolder = new File(contentPath);
	}
	
    public void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{
	req.setCharacterEncoding("utf-8");
	String name = req.getParameter("image");
	if(name == null || name.isEmpty()) {
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "이미지이름이 없습니다");
		return;
	}
        resp.setContentType("image/jpeg");
//        String source ="d:/contents";
        OutputStream os = resp.getOutputStream();
        File srcfile = new File(contentsFolder,name);
        FileInputStream fis = new FileInputStream(srcfile);
        byte[] buffer = new byte[1024];
        int pointer = -1;
        while((pointer = fis.read(buffer)) != -1){
            os.write(buffer,0,pointer);
        }
        fis.close();
        os.close();
    }
}