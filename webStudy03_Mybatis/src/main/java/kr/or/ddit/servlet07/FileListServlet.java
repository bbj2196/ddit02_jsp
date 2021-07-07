package kr.or.ddit.servlet07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.FileVO;


@WebServlet("/fileList.do")
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String param_path = request.getParameter("path");
		int status=200;
		String path=getServletContext().getRealPath(param_path);
		System.out.println(path);
		if(path == null || path.isEmpty()) {
			System.out.println("파라미터 누락");
			status = 400;
		}
		
		System.out.println(path);
		File dir = new File(path);
		
		if(!dir.isDirectory()) {
			System.out.println("폴더가 아님");
			status = 400;
		}
		if(status != 200) {
			response.sendError(status);
			return;
		}
		
		File[] files = dir.listFiles();
//		StringBuffer buffer = new StringBuffer();
		ObjectMapper mapper = new ObjectMapper();
		List<FileVO> voList = new ArrayList<>();
		for (File file : files) {
			String f_type;
			String f_name = file.getName();
			String f_path = file.getPath();
			
			if(file.isDirectory()) {
				f_type="dir";
//				f_path+="\\";
			}else {
				f_type="file";
			}
			f_path=f_path.replace('\\', '/');
			String contextPath = request.getContextPath();
			int idx = f_path.indexOf(contextPath);
			f_path = f_path.substring(idx+contextPath.length());
			FileVO vo = new FileVO(f_type, f_name, f_path);
			voList.add(vo);
//			buffer.append(mapper.writeValueAsString(vo));
		}
		response.getWriter().print(mapper.writeValueAsString(voList));
		
	}
}
