package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.board.service.FreeBoardServiceImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
@RequestMapping("/board/boardUpdate.do")
public class FreeBoardUpdateController {

	@Inject
	FreeBoardService service;
	
	@Inject
	Validator validator;
	
	@GetMapping
	public String updateForm(@RequestParam("boNo")Integer boNo,Model model) {
		String viewName="board/boardForm";
		
		FreeBoardVO board = service.retriveBoard(boNo);
		
		model.addAttribute("board", board);
		
		
		return viewName;
				
	}
	
	@PostMapping
	public String updateBoard(
			@Validated(UpdateGroup.class) @ModelAttribute("board")FreeBoardVO board,
			Errors errors,
			Model model
			) {
		String viewName="board/boardForm";
		// vo로 받고
		// 파일이름을 별도로 받아서
		// 파일의 이름이 이미 있으면 있는파일
		// 파일이름구분 어떻게? 파일명은 중복될수있음 해당글번호에 있는 원본파일명과비교?
		// xml추가해야되나
		// 폼에 attNo를 숨겨놓는다?
		if(!errors.hasErrors()) {
		ServiceResult result = service.modifyBoard(board);
		
		switch (result) {
		case OK:
			viewName="redirect:/board/boardList.do";
			break;
		default:
			model.addAttribute("message", result.toString());
			viewName="board/boardForm";
			break;
		}
		}
		
		return viewName;
				
	}
	

}
