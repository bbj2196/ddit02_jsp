package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImplTest {
	private MemberService service = MemberServiceImpl.getInstance();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMemberList() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test(expected=UserNotFoundExecption.class)
	public void testRemoveMemberException() {
		// 아이디가 맞지 않을때
		MemberVO member = MemberVO.builder()
									.memId("asdasdasd")
									.build();
		
		service.removeMember(member);
	}
	
	@Test
	public void testRemoveMemberNotPass() {
		// 비밀번호가 맞지 않을때
		MemberVO member = MemberVO.builder()
									.memId("a001")
									.memPass("123123123")
									.build();
		
		ServiceResult result = service.removeMember(member);
		assertEquals(ServiceResult.INVALIDPASSWORD, result);
	}
	@Test
	public void testRemoveMember() {
		// 정상적일때
		MemberVO member = MemberVO.builder()
				.memId("a001")
				.memPass("asdfasdf")
				.build();
		
		ServiceResult result = service.removeMember(member);
		assertEquals(ServiceResult.OK, result);
	}

}
