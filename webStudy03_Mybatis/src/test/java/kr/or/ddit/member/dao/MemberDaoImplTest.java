package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDaoImplTest {

	MemberDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = MemberDaoImpl.getInstance();
	}

	@Test
	public void testSelectMemberById() {
		MemberVO vo = dao.selectMemberById("a001");
		assertNotNull(vo);
	}

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectMemeberList() {
		List<MemberVO> list = dao.selectMemeberList(null);
		assertNotNull(list);
	}

	@Test
	public void testSelectMemberDetail() {
		MemberVO member = dao.selectMemberDetail("a001");
		
		System.out.println(member.getMemBir());
		assertNotNull(member);
	}

	@Test
	public void testUpdateMember() {
//		MemberVO member = dao.selectMemberDetail("a001");
//		member.setMemName("김은대");
//		System.out.println(member);
//		int rowcnt = dao.updateMember(member);
//		System.out.println(rowcnt);
//		assertEquals(1, rowcnt);
	}

	@Test
	public void testDeleteMember() {
		int rowcnt = dao.deleteMember("a001");
		assertEquals(1, rowcnt);
	}

}
