package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements MemberService {

	private static MemberService instance;
	private AuthenticateService authServ = new AuthenticateServiceImpl();
	
	private MemberDAO dao;
	{
		dao = MemberDaoImpl.getInstance();
	}

	public static MemberService getInstance() {
		if (instance == null)
			instance = new MemberServiceImpl();
		return instance;
	}

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result =null;
		try {
		retrieveMember(member.getMemId());
		
		result = ServiceResult.PKDUPLICATED;
		}catch (UserNotFoundExecption e) {
			String encoded = EncryptUtils.encryptSha512Base64(member.getMemPass());
			member.setMemPass(encoded);
			int cnt=dao.insertMember(member);
			if(cnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}
		
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> paging) {
		return dao.selectMemeberList(paging);
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO vo = null;
		vo = dao.selectMemberDetail(mem_id);
		if (vo == null) {
			throw new UserNotFoundExecption();
		}
		return vo;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		Object authResult = authServ.authenticate(member);
		ServiceResult result = null;
		if(authResult instanceof MemberVO) {
			int rowcnt = dao.updateMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = (ServiceResult) authResult;
		}
		return result;
	}

	
	@Override
	public ServiceResult removeMember(MemberVO member) {
		Object authResult = authServ.authenticate(member);
		
		ServiceResult result = null;
		if(authResult instanceof MemberVO) {
			int rowcnt = dao.deleteMember(member.getMemId());
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = (ServiceResult) authResult;
		} 
		return result;
	}

	@Override
	public int retrieveMemberCount(PagingVO<MemberVO> paging) {
		return dao.selectTotalRecord(paging);
	}

}
