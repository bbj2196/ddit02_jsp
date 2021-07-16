package kr.or.ddit.board.service;

import java.util.List;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BooleanDeserializer;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.board.dao.FreeBoardDAOImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

public class FreeBoardServiceImpl implements FreeBoardService {

	private FreeBoardDAO boardDao = new FreeBoardDAOImpl();
	private AttatchDAO attDao;
	@Override
	public ServiceResult createBoard(FreeBoardVO board) {
		// TODO 파일 업로드 처리해야함
		try {
		FreeBoardVO saved = retriveBoard(board.getBoNo());
		if(saved != null) {
			return ServiceResult.PKDUPLICATED;
		}
		}catch(DataNotFoundException e) {}
		String encoded=EncryptUtils.encryptSha512Base64(board.getBoPass());
		board.setBoPass(encoded);
		int cnt=boardDao.insertBoard(board);
		if(cnt >0) {
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAIL;
		}
		
	}

	@Override
	public int retriveBoardCount(PagingVO<FreeBoardVO> paging) {
		return boardDao.selectTotalRecord(paging);
	}

	@Override
	public List<FreeBoardVO> retriveBoardList(PagingVO<FreeBoardVO> pagingVO) {
		return boardDao.selectBoardList(pagingVO);
	}

	@Override
	public FreeBoardVO retriveBoard(int boNo) {
		FreeBoardVO board =null;
		board = boardDao.selectBoard(boNo);
		if(board == null) {
			throw new DataNotFoundException(boNo+"번 게시물은 없음");
		}
			return board;
		}

	@Override
	public ServiceResult modifyBoard(FreeBoardVO board) {
		// TODO 파일 업로드 처리
		FreeBoardVO saved = boardDao.selectBoard(board.getBoNo());
		String encoded = EncryptUtils.encryptSha512Base64(board.getBoPass());
		String savedPass = saved.getBoPass();
		if(!savedPass.equals(encoded)) {
			return ServiceResult.INVALIDPASSWORD;
		}
		int cnt=boardDao.updateBoard(board);
		if(cnt>0) {
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAIL;
		}
	}
	

	@Override
	public ServiceResult removeBoard(FreeBoardVO board) {
		FreeBoardVO saved = boardDao.selectBoard(board.getBoNo());
		String encoded = EncryptUtils.encryptSha512Base64(board.getBoPass());
		String savedPass = saved.getBoPass();
		if(!savedPass.equals(encoded)) {
			return ServiceResult.INVALIDPASSWORD;
		}
		int cnt=boardDao.deleteBoard(board.getBoNo());
		if(cnt>0) {
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAIL;
		}
	}

	@Override
	public AttatchVO download(int attNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult incrementCount(int boNo, CountType type) {
		int cnt=0;
		ServiceResult result = ServiceResult.FAIL;
		switch (type) {
		case RECOMMEND: // ㅊㅊ
			cnt = boardDao.incrementRec(boNo);
			break;
		case REPORT: // 신고
			cnt = boardDao.incrementRep(boNo);
			break;
		case SEE:
			cnt = boardDao.incrementHit(boNo);
			break;
		default:
			new DataNotFoundException("해당 타입을 찾을 수 없습니다");
			break;
		}
		if(cnt>0) {
			result=ServiceResult.OK;
		}

		return result;
	}

}
