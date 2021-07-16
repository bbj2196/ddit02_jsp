package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardService.CountType;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.vo.ExtendsSearchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class FreeBoardRetrieveController {

	FreeBoardService boardService = new FreeBoardServiceImpl();
	@RequestMapping("/board/boardList.do")
	public String list(@RequestParam(value="currentPage",required=false,defaultValue="1")int currentPage,@ModelAttribute("search")ExtendsSearchVO search,HttpServletRequest req,HttpServletResponse resp) {
		//동기 방식 목록조회
		// 검색조건 제목,작성자,
		// 최근 작성글이 먼저 나오도록
		PagingVO<FreeBoardVO>paging = new PagingVO<>(5, 5);
		paging.setSimpleSearch(search);
		paging.setCurrentPage(currentPage);
		int total = boardService.retriveBoardCount(paging);
		paging.setTotalRecord(total);
		List<FreeBoardVO> dataList = boardService.retriveBoardList(paging);
		paging.setDatalist(dataList);
		req.setAttribute("pagingVO", paging);
		req.setAttribute("boardList", dataList);
		
		return "board/boardList";
	}
	
	@RequestMapping("/board/boardView.do")
	public String view(@RequestParam("what")int boNo,HttpServletRequest req,HttpServletResponse resp) {
		
		FreeBoardVO board=null;
		try {
		board = boardService.retriveBoard(boNo);
		boardService.incrementCount(boNo,CountType.SEE);
		req.setAttribute("freeboard", board);
		}catch(DataNotFoundException e) {
			new RuntimeException(e);
		}
		
		return "board/boardView";
	}
	
	@RequestMapping(value="/board/boardView.do",method=RequestMethod.POST)
	public String func(@RequestParam("type")String type,@RequestParam("what")int boNo,HttpServletRequest req,HttpServletResponse resp) {
		
		FreeBoardVO board=null;
		CountType conType=null;
		switch (type) {
		case "rec":
			conType=CountType.RECOMMEND;
			break;
		case "rep":
			conType=CountType.REPORT;
			break;
		}
		resp.setCharacterEncoding("utf-8");
		
		try {
		board = boardService.retriveBoard(boNo);
		boardService.incrementCount(boNo,conType);
		req.setAttribute("freeboard", board);
		resp.getWriter().print("ok");
		//TODO 비동기로 뿌리는거 어떻게?
		}catch(DataNotFoundException | IOException e) {
			new RuntimeException(e);
		}
		return null;
	}
	
	@RequestMapping("/board/boardInsert.do")
	public String insertFrom(HttpServletRequest req,HttpServletResponse resp) {
		
		return "/board/boardForm";
	}
	
	public String insertBoard(@ModelAttribute("board")FreeBoardVO board,@ModelAttribute("files")MultipartFile[] file,HttpServletRequest req,HttpServletResponse resp) {
		Map<String, List<String>>errors = new HashMap<>();
		ValidatorUtils<FreeBoardVO>valUtil = new ValidatorUtils<>();
		boolean valid = valUtil.validate(board, errors,Default.class);
		String viewName=null;
		req.setAttribute("errors", errors);
		req.setAttribute("board", board);
		if(!valid) {
			
			return "/board/boardForm";
		}
			ServiceResult result = boardService.createBoard(board);
			switch (result) {
			case OK:
				
				viewName="redirect:/board/boardView.do?what="+board.getBoNo();
				break;
			case PKDUPLICATED:
				req.setAttribute("message", "PK중복");
				viewName="";
				break;
			default:
				
				break;
			}
			
			

			return viewName;
		
		
	}
}
