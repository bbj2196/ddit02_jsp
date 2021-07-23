package kr.or.ddit.prop.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.PagingVO;

public class DataBasePropertyDAOImplTest {

	@Test
	public void test() {
		DataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
		List<DataBasePropertyVO> list = dao.selectDataBasePropertyList(new PagingVO<>());
		assertNotNull(list);
	}

}
