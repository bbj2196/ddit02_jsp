package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.FreeBoardService;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;

@Controller
public class FreeBoardDeleteController {


	@Inject
	private FreeBoardService service;
	
	@RequestMapping(value="/board/boardDelete.do",method=RequestMethod.POST)
	public String delete(
			@Validated(DeleteGroup.class) @ModelAttribute("board")FreeBoardVO board,
			Errors errors,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		String viewName="redirect:/board/boardView.do?what="+board.getBoNo();

		if(errors.hasErrors()) {
			
			return viewName;
		}
		ServiceResult result = service.removeBoard(board);
		
		switch (result) {
		case OK:
			viewName="redirect:/board/boardList.do";
			break;
		case INVALIDPASSWORD:
			viewName="redirect:/board/boardView.do?what="+board.getBoNo();
			redirectAttributes.addFlashAttribute("message", "비밀번호가 맞지 않습니다");
			break;
		default:
			viewName="redirect:/board/boardView.do?what="+board.getBoNo();
			redirectAttributes.addFlashAttribute("message", result);
			break;
		}
		
		return viewName;
	}
	
	@RequestMapping(value="/board/download.do",produces="application/octet-stream")
	public String download(@RequestParam("what")Integer attNo,Model model,HttpServletResponse resp) throws IOException {
		
		AttatchVO attatch = service.download(attNo);
		model.addAttribute("attach",attatch);
		
		return "downloadView";
	}
}
