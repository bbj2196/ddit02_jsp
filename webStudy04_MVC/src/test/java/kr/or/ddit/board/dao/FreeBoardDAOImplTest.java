package kr.or.ddit.board.dao;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

public class FreeBoardDAOImplTest extends TestCase {

	FreeBoardDAO dao = new FreeBoardDAOImpl();
	private static Logger logger = LoggerFactory.getLogger(FreeBoardDAOImplTest.class);
	

	@Test
	public void testes() {
//		FreeBoardVO board = dao.selectBoard(333);
//		assertNotNull(board);
//		List<FreeReplyVO> asd = board.getReplyList();
		int boNo = 327;
		FreeBoardVO vo = dao.selectBoard(boNo);
		assertNotNull(vo);
		List<FreeReplyVO> reList = vo.getReplyList();
		List<AttatchVO> attList = vo.getAttatchList();
		assertNotNull(reList);
		assertNotNull(attList);
		
		System.out.println(reList);
		System.out.println(attList);
		
		if(true) return;
		SearchVO search = new SearchVO("title", "신규");
		PagingVO<FreeBoardVO> paging = new PagingVO<>(5, 2);
		paging.setCurrentPage(1);
		paging.setSimpleSearch(search);
		List<FreeBoardVO> asd = dao.selectBoardList(paging);
		System.out.println("=============================");
		System.out.println(asd.size());
		assertNotNull(asd);
		assertNotEquals(asd.size(), 0);
		
		System.out.println(asd.get(0).getClass().getSimpleName());
	}
	
	@Test
	public void view() {
	
	}
}
