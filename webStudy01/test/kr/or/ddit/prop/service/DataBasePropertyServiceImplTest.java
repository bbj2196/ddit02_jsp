package kr.or.ddit.prop.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImplTest {

	@Test
	public void testRetrieveDataBaseProperties() {
		
		DataBasePropertyService serv = new DataBasePropertyServiceImpl();
		List<DataBasePropertyVO> list = serv.retrieveDataBaseProperties(new DataBasePropertyVO());
		assertNotNull(list);
	}

}
