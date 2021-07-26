package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private FreeBoardService service;
	
	@RequestMapping(value="/board/boardList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<FreeBoardVO> listForAjax(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
		@ModelAttribute("simpleSearch") ExtendsSearchVO simpleSearch
	) {
		PagingVO<FreeBoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(simpleSearch);
		int totalRecord = service.retriveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<FreeBoardVO> boardList = service.retriveBoardList(pagingVO);
		pagingVO.setDatalist(boardList);
		return pagingVO;
	} 
	
	@RequestMapping("/board/boardList.do")
	public String boardList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
		@ModelAttribute("simpleSearch") ExtendsSearchVO simpleSearch,
		Model model
	) {
		
		PagingVO<FreeBoardVO> pagingVO = listForAjax(currentPage, simpleSearch);
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
	}
	
	@RequestMapping("/board/boardView.do")
	public String boardView(
		@RequestParam("what") int boNo
		, Model model
	) {
		FreeBoardVO board = service.retriveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardView";
	}
	
	@RequestMapping(value="/board/boardView.do",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<CountType, String> incread(
			@RequestParam("countType")String type,
			@RequestParam("what")int boNo,
			HttpServletRequest req,
			HttpServletResponse resp
			) throws IOException {
		FreeBoardVO board=null;
		CountType conType=null;
		
		try{
		board = service.retriveBoard(boNo);
		Integer cnt=-1;
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
		ServiceResult result = service.incrementCount(boNo,conType);
		Map<CountType, String>resultMap = new HashMap<>();
		resultMap.put(conType, "OK");
		req.setAttribute("board", board);
		return resultMap;
		}catch(DataNotFoundException e) {
			resp.sendError(400);
		}
		return null;
		
	}
	
	
}
