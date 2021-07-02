package kr.or.ddit.emp.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImplTest {

	EmployeeDAO dao;
	Map<String, Object>pMap;
	
	@Before
	public void setUp() throws Exception {
		dao = EmployeeDAOImpl.getInstance();
		pMap= new HashMap<>();
	}

	@Test
	public void testInsertEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectEmployeeList() {
		
		List<EmployeeVO>list = dao.selectEmployeeList(pMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(false, list.get(0).getLeaf());
		System.out.println(list.get(0));
	}

	@Test
	public void testSelectEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteEmpliyee() {
		fail("Not yet implemented");
	}

}
