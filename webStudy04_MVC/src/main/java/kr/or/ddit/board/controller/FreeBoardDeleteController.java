package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.hibernate.validator.internal.metadata.aggregated.ValidatableParametersMetaData;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.BrowserType;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardDeleteController {

	private FreeBoardService service = new FreeBoardServiceImpl();
	
	@RequestMapping(value="/board/boardDelete.do",method=RequestMethod.POST)
	public String delete(@ModelAttribute("board")FreeBoardVO board,HttpServletRequest req,HttpSession session) {
		String viewName="redirect:/board/boardView?what="+board.getBoNo();
		ValidatorUtils<FreeBoardVO>validatorUtils = new ValidatorUtils<>();
		Map<String, List<String>> errors = new HashMap<>();
		boolean valid = validatorUtils.validate(board, errors , DeleteGroup.class);
		if(!valid) {
			StringBuffer buffer = new StringBuffer();
			for (String key : errors.keySet()) {
				buffer.append(key);
				buffer.append(" : ");
				buffer.append(errors.get(key));
				buffer.append("\n");
			}
			req.setAttribute("message", buffer.toString());
			return viewName;
		}
		ServiceResult result = service.removeBoard(board);
		
		switch (result) {
		case OK:
			viewName="redirect:/board/boardList.do";
			break;
		case INVALIDPASSWORD:
			viewName="redirect:/board/boardView?what="+board.getBoNo();
			session.setAttribute("message", "비밀번호가 맞지 않습니다");
			break;
		default:
			viewName="redirect:/board/boardView?what="+board.getBoNo();
			session.setAttribute("message", result);
			break;
		}
		
		return viewName;
	}
	
	@RequestMapping("/board/download.do")
	public String download(@RequestParam("what")Integer attNo,HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		AttatchVO attatch = service.download(attNo);
		File file = new File("d:/savedfile/" + attatch.getAttSavename());
		if(!file.exists()) {
			resp.sendError(404);
			return null;
		}
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/octet-stream");
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
		return null;
	}
}
