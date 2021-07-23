package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardInsertController {
	private FreeBoardService boardService = new FreeBoardServiceImpl();

	@RequestMapping(value="/board/boardInsert.do")
	public String insertFrom(@ModelAttribute("board")FreeBoardVO board,HttpServletRequest req,HttpServletResponse resp) {
		req.setAttribute("board", board);
		return "/board/boardForm";
	}
	
	@RequestMapping(value="/board/boardInsert.do",method=RequestMethod.POST)
	public String insertBoard(@ModelAttribute("board")FreeBoardVO board,@RequestPart(value="files",required=false)MultipartFile[] files,HttpServletRequest req,HttpServletResponse resp) {
		board.setBoFiles(files);
		Map<String, List<String>>errors = new HashMap<>();
		ValidatorUtils<FreeBoardVO>valUtil = new ValidatorUtils<>();
		boolean valid = valUtil.validate(board, errors,InsertGroup.class);
		String viewName="/board/boardForm";
		req.setAttribute("errors", errors);
		if(!valid) {
			
			return viewName;
		}
			ServiceResult result = boardService.createBoard(board);
			switch (result) {
			case OK:
				
				viewName="redirect:/board/boardView.do?what="+board.getBoNo();
				break;
			default:
				req.setAttribute("message", result.toString());
				break;
			}
			
			return viewName;
		
		
	}
}
