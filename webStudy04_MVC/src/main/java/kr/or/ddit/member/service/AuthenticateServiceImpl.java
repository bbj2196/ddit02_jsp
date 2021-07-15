package kr.or.ddit.member.service;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberDAO dao = new MemberDaoImpl();
	@Override
	public Object authenticate(MemberVO param) {
		Object resultVal = null;
		MemberVO savedVO = dao.selectMemberById(param.getMemId());
		if(savedVO == null) {
			throw new UserNotFoundExecption(String.format("%s 회원은 없음.",param.getMemId()));
		}
		String savedPass = savedVO.getMemPass();
		String inputPass = param.getMemPass();
		
		
//		String encoded=null;
//		try {
//			MessageDigest md= MessageDigest.getInstance("SHA-512");
//			byte[] input = inputPass.getBytes();
//			byte[] encrypted = md.digest(input);
//			 encoded = Base64.getEncoder().encodeToString(encrypted);
//			
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException(e);
//		}
//		
//		
//		boolean valid =  savedPass.equals(encoded);
		boolean valid =  EncryptUtils.matches(inputPass, savedPass);
		
		if( valid) {
			resultVal=savedVO;
			MemberVO detail = dao.selectMemberDetail(savedVO.getMemId());
			if(detail.getMemDelete()==Boolean.TRUE) {
				resultVal=ServiceResult.DROPEDMEMBER;
			}
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
