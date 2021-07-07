package kr.or.ddit.member.service;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.vo.MemberVO;

/**
 * 인증 처리를 위한  Business Logic Layer
 * 
 * @author PC-13
 *
 */
public interface AuthenticateService {

	/**
	 * 
	 * @param param
	 * @return 인증성공: {@link MemberVO}, 비번오류 : {@link ServiceResult}
	 * 	존재하지않으면, {@link UserNotFoundExecption}발생
	 */
	public Object authenticate(MemberVO param);
	
	public MemberVO selectMemberById(String mem_id);
}
