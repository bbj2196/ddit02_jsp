package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardService.CountType;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.ExtendsSearchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class FreeBoardRetrieveController {

	@Inject
	FreeBoardService boardService;
	@RequestMapping("/board/boardList.do")
	public String list(@RequestParam(value="page",required=false,defaultValue="1")int currentPage,@ModelAttribute("search")ExtendsSearchVO search,HttpServletRequest req,HttpServletResponse resp) {
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
		req.setAttribute("board", board);
		}catch(DataNotFoundException e) {
			new RuntimeException(e);
		}
		
		return "board/boardView";
	}
	
	@RequestMapping(value="/board/boardView.do",method=RequestMethod.POST)
	public String func(@RequestParam("countType")String type,@RequestParam("what")int boNo,HttpServletRequest req,HttpServletResponse resp) throws IOException {
		FreeBoardVO board=null;
		CountType conType=null;
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		
		try (
				PrintWriter out = resp.getWriter();
				){
		board = boardService.retriveBoard(boNo);
		int cnt=-1;
		switch (type) {
		case "RECOMMEND":
			conType=CountType.RECOMMEND;
			cnt = board.getBoRec();
			break;
		case "REPORT":
			conType=CountType.REPORT;
			cnt=board.getBoRep();
			break;
		default:
			resp.sendError(400);
			return null;
		}
		ServiceResult result = boardService.incrementCount(boNo,conType);
		Map<CountType, String>resultMap = new HashMap<>();
		resultMap.put(conType, "OK");
		req.setAttribute("board", board);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, resultMap);
		}catch(DataNotFoundException e) {
			resp.sendError(400);
		}
		return null;
	}
	
	
}
