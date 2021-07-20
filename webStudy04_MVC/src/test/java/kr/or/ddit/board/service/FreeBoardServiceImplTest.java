package kr.or.ddit.board.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;
import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.vo.FreeBoardVO;

public class FreeBoardServiceImplTest extends TestCase {

	private static Logger logger = LoggerFactory.getLogger(FreeBoardServiceImpl.class);
	private FreeBoardService service = new FreeBoardServiceImpl();
	private AttatchDAO dao = new AttatchDAOImpl();
	public void testCreateBoard() {
		FreeBoardVO vo = new FreeBoardVO();

		vo.setBoNo(345);
		vo.setBoPass("java");
		ServiceResult result = service.removeBoard(vo);
		logger.info("결과창 : {}",result);
		assertEquals(ServiceResult.OK, result);
		
		if(true) return;
		int[] delAttNos= {1,2,3};
		vo.setDelAttNos(delAttNos);
		
		int cnt=dao.deleteAttaches(vo);
		assertEquals(3, cnt);
		
		if(true) return;
		vo.setBoTitle("titletitletitle");
		vo.setBoIp("127.0.0.1");
		vo.setBoWriter("Hong_Gil");
		vo.setBoContent("qqqqqqqq");
		vo.setBoEmail("asdasd@asdasd");
		vo.setBoPass("java");
		ServiceResult result1 = service.createBoard(vo);
		assertEquals(ServiceResult.OK, result);
	}

}
