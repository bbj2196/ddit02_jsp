package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.board.dao.FreeBoardDAOImpl;
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
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardUpdateController {

	FreeBoardService service = new FreeBoardServiceImpl();
	AttatchDAO dao = new AttatchDAOImpl();
	
	@RequestMapping("/board/boardUpdate.do")
	public String updateForm(@RequestParam("boNo")Integer boNo,HttpServletRequest req,HttpServletResponse resp) {
		String viewName="/board/boardForm";
		
		FreeBoardVO board = service.retriveBoard(boNo);
		
		req.setAttribute("board", board);
		
		
		return viewName;
				
	}
	
	@RequestMapping(value="/board/boardUpdate.do",method=RequestMethod.POST)
	public String updateBoard(@ModelAttribute("board")FreeBoardVO board,@RequestPart("files")MultipartFile[] files,HttpServletRequest req,HttpServletResponse resp) {
		String viewName="";
		// vo로 받고
		// 파일이름을 별도로 받아서
		// 파일의 이름이 이미 있으면 있는파일
		// 파일이름구분 어떻게? 파일명은 중복될수있음 해당글번호에 있는 원본파일명과비교?
		// xml추가해야되나
		// 폼에 attNo를 숨겨놓는다?
		board.setBoFiles(files);
		ServiceResult result = service.modifyBoard(board);
		
		switch (result) {
		case OK:
			viewName="redirect:/board/boardList.do";
			break;
		default:
			req.setAttribute("message", result.toString());
			viewName="/board/boardForm";
			break;
		}
		
		
		return viewName;
				
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
