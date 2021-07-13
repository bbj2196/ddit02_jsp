package kr.or.ddit.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;

@WebServlet("/fileUploadUsingFilter.do")
@MultipartConfig
public class FileUploadServletUsingMultipartFilter extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String textParam=req.getParameter("textParam");
		req.getSession().setAttribute("textParam", textParam);
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			StandardMultipartHttpServletRequest wrapper = (StandardMultipartHttpServletRequest) req;
			
			MultipartFile file = wrapper.getFile("FilePart");
			System.out.println(wrapper.getPartNames());
			if(file!=null && !file.isEmpty()) {
				String contentType = file.getContentType();
				if(contentType.startsWith("image/")) {
					String saveFolderUrl = "/resources/images";
					String saveFolderPath = req.getServletContext().getRealPath(saveFolderUrl);
					File saveFolder = new File(saveFolderPath);
					String saveName = UUID.randomUUID().toString();
					File saveFile = new File(saveFolder,saveName);
					
					file.transferTo(saveFile);
					req.getSession().setAttribute("imageFile", file);
					req.getSession().setAttribute("imageURL", saveFolderUrl+"/"+saveFile.getName());
				}// end if
			}// end if(file!=null)
		}
		
		resp.sendRedirect(req.getContextPath()+"/13/fileUploadForm.jsp");
	}
}
