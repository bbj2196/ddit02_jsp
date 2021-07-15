package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)를 위한 Persistence Layer
 * 
 *
 */
public interface MemberDAO {

	/**
	 * 식별자(PK)로 레코드 조회
	 * @param mem_id
	 * @return 존재하지 않는 경우 null 반환
	 */
	public MemberVO selectMemberById(String mem_id);
	
	public int insertMember(MemberVO member);
	public List<MemberVO> selectMemeberList(PagingVO<MemberVO> paging);
	public MemberVO selectMemberDetail(String mem_id);
	public int updateMember(MemberVO member);
	public int deleteMember(String mem_id);
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO);
}
