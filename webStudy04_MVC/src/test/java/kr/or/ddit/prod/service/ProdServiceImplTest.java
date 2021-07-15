package kr.or.ddit.prod.service;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImplTest extends TestCase {

	private ProdService service = new ProdServiceImpl();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCreateProd() {
		fail("Not yet implemented");
	}

	public void testRetrieveProdList() {
		
//		ProdVO caseProd = new ProdVO();
//		caseProd.setProdLgu("P101");
//		caseProd.setProdBuyer("P10101");
//		PagingVO<ProdVO> paging = new PagingVO<>(5, 2);
//		paging.setCurrentPage(1);
//		paging.setDetailSearch(caseProd);
//		
//		service.retrieveProdList(paging);
//		List<ProdVO> list = paging.getDatalist();
//		assertNotNull(paging.getDatalist());
//		
//		if(list != null) {
//			for (ProdVO prodVO : list) {
//				System.out.println(prodVO);
//			}
//		}
		
	}

	public void testRetrieveProd() {
		fail("Not yet implemented");

	}

	public void testModifyProd() {
		fail("Not yet implemented");
	}

}
