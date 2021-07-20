package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class ImageUploadController {

	@RequestMapping(value = "/board/uploadImage.do", method = RequestMethod.POST)
	public String uploadImage(@RequestPart("upload") MultipartFile uploadImage, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		Map<String, Object> result = new HashMap<>();
		if (!uploadImage.isEmpty()) {
			String saveFolderUrl = "/resources/boardImages/";
			String savePath = req.getServletContext().getRealPath(saveFolderUrl);
			File saveFolder = new File(savePath);
			
			if (!saveFolder.exists())
				saveFolder.mkdirs();
			String saveName = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName);
			uploadImage.transferTo(saveFile);
			String saveFileUrl = req.getContextPath() + saveFolderUrl + saveName;
			result.put("uploded", 1);
			result.put("fileName", uploadImage.getOriginalFilename());
			result.put("url", saveFileUrl);
		}
		resp.setContentType("application/json;charset=utf-8");
		try (PrintWriter out = resp.getWriter();) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, result);
		}
		return null;
	}
}
