package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {
	ProdDAO dao = ProdDAOImpl.getInstance();
	Logger loger = LoggerFactory.getLogger(this.getClass());
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
		if(loger.isInfoEnabled()) {
			loger.info("Prod : {}", prod);
		}
		assertNotNull(prod.getBuyer());
		if(loger.isInfoEnabled()) {
			loger.info("Buyerd : {}", prod.getBuyer());
		}
		assertNotNull(prod.getMemberList());
//		if(loger.isDebugEnabled()) {
			loger.debug("MemList : {}", prod.getMemberList());
//		}
		assertEquals(2, prod.getMemberList().size());
	}

}
