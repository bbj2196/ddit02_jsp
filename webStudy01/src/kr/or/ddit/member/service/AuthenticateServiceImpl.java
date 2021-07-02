package kr.or.ddit.member.service;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.exception.UsernotFoundExecption;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberDAO dao = new MemberDaoImpl();
	@Override
	public Object authenticate(MemberVO param) {
		Object resultVal = null;
		MemberVO savedVO = dao.selectMemberById(param.getMem_id());
		if(savedVO == null) {
			throw new UsernotFoundExecption(String.format("%s 회원은 없음.",param.getMem_id()));
		}
		String savedPass = savedVO.getMem_pass();
		String inputPass = param.getMem_pass();
		boolean valid =  savedPass.equals(inputPass);
		if( valid) {
			resultVal=savedVO;
			param.setMem_name(savedVO.getMem_name());
		}else {
			resultVal=ServiceResult.INVALIDPASSWORD;
		}
		return resultVal;
	}
	@Override
	public MemberVO selectMemberById(String mem_id) {
		return dao.selectMemberById(mem_id);
	}
	

}
