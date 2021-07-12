package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * Servlet implementation class MemberListControllerServlet
 */
@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService service ;
	
	@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			service = MemberServiceImpl.getInstance();
		}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String page = request.getParameter("page");
		String searchType=request.getParameter("searchType");
		String searchWord=request.getParameter("searchWord");
		SearchVO simpleSearch = new SearchVO(searchType, searchWord);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(page)) {
		currentPage = Integer.parseInt(page);
		}
		
		PagingVO<MemberVO> paging = new PagingVO<>(5,2);
		paging.setCurrentPage(currentPage);
		paging.setSimpleSearch(simpleSearch);
		
		int totalrecord = service.retrieveMemberCount(paging);
		List<MemberVO> list =service.retrieveMemberList(paging);

		paging.setDatalist(list);
		paging.setTotalRecord(totalrecord);
		request.setAttribute("pagingVO", paging);
		
		String dest = "/WEB-INF/views/member/memberList.jsp";
		request.getRequestDispatcher(dest).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
