package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.vo.FreeBoardVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ImageUploadController {
	
	private ServletContext application;
	
	@Inject
	AttatchDAO dao;

	@Inject
	private WebApplicationContext container;
	
	@Value("#{appInfo.boardImageUrl}")
	private String saveFolderUrl;
	private File saveFolder;

	@PostConstruct
	public void init() {
		this.application=container.getServletContext();
		String savePath = application.getRealPath(saveFolderUrl);
		saveFolder = new File(savePath);
		if (!saveFolder.exists())saveFolder.mkdirs();
		
		log.info("게시글 이미지 경로(url) : {}, \n실제경로 : {}",saveFolderUrl,savePath);
	}
	
	@RequestMapping(value = "/board/uploadImage.do", method = RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> uploadImage(@RequestPart("upload") MultipartFile uploadImage, HttpServletRequest req
			) throws IOException {
		Map<String, Object> result = new HashMap<>();
		if (!uploadImage.isEmpty()) {

			String saveName = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName);
			uploadImage.transferTo(saveFile);
			String saveFileUrl = application.getContextPath()+saveFolderUrl+"/"+ saveName;
			result.put("uploaded", 1);
			result.put("fileName", uploadImage.getOriginalFilename());
			result.put("url", saveFileUrl);
		}
		return result;
	}
	
	@RequestMapping("/board/deleteFiles.do")
	public String deleteFiles(@ModelAttribute("board")FreeBoardVO board,HttpServletRequest req,HttpServletResponse resp) {

		System.out.println("-------------------");
		
		int delCnt = board.getDelAttNos().length;
		
		System.out.println(delCnt);
		
		int cnt = dao.deleteAttaches(board);
		
		boolean result = delCnt== cnt;
		
		
		try(
				PrintWriter out = resp.getWriter();
				){
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
