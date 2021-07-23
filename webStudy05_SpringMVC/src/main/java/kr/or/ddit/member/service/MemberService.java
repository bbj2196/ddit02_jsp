package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public interface MemberService {
	/**
	 * 
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	
	/**
	 * total 데이터 조회
	 * @param pagingVO
	 * @return
	 */
	public int retrieveMemberCount(PagingVO<MemberVO> paging);
	
	/**
	 * 페이징 처리를 위해 구간별 데이터를 조회
	 * @param paging
	 * @return
	 */
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> paging);
	
	/**
	 * 
	 * @param member
	 * @return 존재하지 않는 경우 {@link UserNotFoundExecption} 발생
	 * 				
	 */
	public MemberVO retrieveMember(String mem_id);
	
	/**
	 * 
	 * @param member
	 * @return 존재하지 않는 경우 {@link UserNotFoundExecption} 발생
	 * 				INVALIDPASSWORD,OK,FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 
	 * @param member
	 * @return 존재하지 않는 경우 {@link UserNotFoundExecption} 발생
	 * 				INVALIDPASSWORD,OK,FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
}
