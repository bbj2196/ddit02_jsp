package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImplTest extends TestCase {

	OthersDAO dao = new OthersDAOImpl(); 
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testSelectLprodList() {
		List<Map<String, Object>> list = dao.selectLprodList();
		
		assertNotNull(list);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	public void testSelectBuyerList() {
		List<BuyerVO> list = dao.selectBuyerList();
		System.out.println(list);
		assertNotNull(list);
	}

}
