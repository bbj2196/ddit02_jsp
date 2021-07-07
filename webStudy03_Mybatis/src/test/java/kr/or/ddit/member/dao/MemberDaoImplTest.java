package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

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
		
		MemberVO member = MemberVO.builder()
							.memId("test")
							.memPass("test")
							.memName("test")
							.memRegno1("test")
							.memRegno2("test")
							.memBir("2021-07-07")
							.memZip("test")
							.memAdd1("test")
							.memAdd2("test")
							.memHometel("test")
							.memComtel("test")
							.memHp("test")
							.memMail("test")
							.memJob("test")
							.memLike("test")
							.memMemorial("test")
							.memMemorialday("2021-07-07")
							.memMileage(111)
								.build();
		
		assertEquals(1, dao.insertMember(member));
	}

	@Test
	public void testSelectMemeberList() {
		List<MemberVO> list = dao.selectMemeberList(new PagingVO<>());
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
