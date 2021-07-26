package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.enumtype.BrowserType;
import kr.or.ddit.vo.AttatchVO;

public class DownloadView extends AbstractView {

	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		AttatchVO attatch = (AttatchVO) model.get("attach");
		
		File file = new File(saveFolder+"/" + attatch.getAttSavename());
		if(!file.exists()) {
			resp.sendError(404);
		}
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-Length", attatch.getAttFilesize()+"");
		String fileName = attatch.getAttFilename();
		String header = req.getHeader("User-Agent");
		BrowserType type = BrowserType.findBrowserType(header);
		switch (type) {
		case MSIE:
		case TRIDENT:
			fileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
			break;
		default:
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
			break;
		}
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//			resp.setHeader("Content-Disposition", "attachment; fileName= " + name);
			
		try (OutputStream os = resp.getOutputStream();) {
			FileUtils.copyFile(file, os);
		}

	}

}
