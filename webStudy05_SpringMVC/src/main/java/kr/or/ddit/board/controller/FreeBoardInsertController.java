package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
@RequestMapping(value="/board/boardInsert.do")
public class FreeBoardInsertController {
	@Inject
	private FreeBoardService boardService;

	@GetMapping
	public String insertFrom(@ModelAttribute("board")FreeBoardVO board,Model model) {
		model.addAttribute("board", board);
		return "/board/boardForm";
	}
	
	@PostMapping
	public String insertBoard(
			@Validated(InsertGroup.class) @ModelAttribute("board")FreeBoardVO board,
			BindingResult errors,
			@RequestPart(value="files",required=false)MultipartFile[] files,
			Model model
			) {
		String viewName="/board/boardForm";
		model.addAttribute("errors", errors);
		if(errors.hasErrors()) {
			
			return viewName;
		}
			ServiceResult result = boardService.createBoard(board);
			switch (result) {
			case OK:
				
				viewName="redirect:/board/boardView.do?what="+board.getBoNo();
				break;
			default:
				model.addAttribute("message", result.toString());
				break;
			}
			
			return viewName;
		
		
	}
}
