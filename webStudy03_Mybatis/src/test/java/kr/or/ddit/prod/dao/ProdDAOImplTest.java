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
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {
	ProdDAO dao = ProdDAOImpl.getInstance();
	Logger loger = LoggerFactory.getLogger(this.getClass());
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testinsert() {
		ProdVO prod = ProdVO.builder()
								.prodId("prodId")
								.prodName("prodName")
								.prodLgu("P101")
								.prodBuyer("P10101")
								.prodCost(111111)
								.prodPrice(222222)
								.prodSale(333333)
								.prodOutline("prodOutline")
								.prodImg("prodImg")
								.prodTotalstock(555555)
								.prodInsdate("2020-07-07")
								.prodProperstock(666666)
								.prodSize("prodSize")
								.prodColor("prodColor")
								.prodDelivery("prodDelivery")
								.prodUnit("Unit")
						.build();
		
		assertEquals(1,dao.insertProd(prod));
	}
	
	
	@Test
	public void testSelectProd() {
/*		ProdVO prod = dao.selectProd("P101000001");
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
		assertEquals(2, prod.getMemberList().size());*/
	}
	
	@Test
	public void testListProd() {
//		ProdVO caseProd = new ProdVO();
//		caseProd.setProdLgu("P101");
//		caseProd.setProdBuyer("P10101");
//		PagingVO<ProdVO> paging = new PagingVO<>(5, 2);
//		paging.setCurrentPage(2);
////		paging.setDetailSearch(caseProd);
//		
//		List<ProdVO> list = dao.selectProdList(paging);
//		assertNotNull(list);
		
				
	}

}
