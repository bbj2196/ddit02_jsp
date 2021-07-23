package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.board.dao.FreeBoardDAOImpl;
import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Inject
	private FreeBoardDAO boardDao;
	@Inject
	private AttatchDAO attDao;
	private File saveDir=new File("d:/savedfile");
	@Override
	public ServiceResult createBoard(FreeBoardVO board) {
		if(board == null) {
			return ServiceResult.NOTEXIST;
		}
		int fileCnt=0;
		List<AttatchVO> attList = board.getAttatchList();
		
		if(attList != null && !attList.isEmpty()) {
			fileCnt=saveFiles(board);
			if(fileCnt<1) {
				return ServiceResult.FILE_IO_ERROR;
			}
		}
//		String encoded=EncryptUtils.encryptSha512Base64(board.getBoPass());
//		board.setBoPass(encoded);
		int cnt=boardDao.insertBoard(board);
		if(cnt >0) {
			if(fileCnt >0) {
			int attCnt = attDao.insertAttatches(board);
			if(attCnt < 1) {
				return ServiceResult.FAIL;
			}
			}
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAIL;
		}
		
	}
	private int saveFiles(FreeBoardVO board) {
		List<AttatchVO> fileList = board.getAttatchList();
		if(!saveDir.exists()) saveDir.mkdir();
		int idx=0;
		for (AttatchVO file : fileList) {
			try {
				file.saveToBinary(saveDir);
				idx++;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return idx;
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
		FreeBoardVO saved = boardDao.selectBoard(board.getBoNo());
//		String encoded = EncryptUtils.encryptSha512Base64(board.getBoPass());
//		String savedPass = saved.getBoPass();
		
//		if(!savedPass.equals(encoded)) {
//			return ServiceResult.INVALIDPASSWORD;
//		}
		// 새로 추가된거 저장
		if(board.getAttatchList() != null && !board.getAttatchList().isEmpty()) {
		saveFiles(board);
		attDao.insertAttatches(board);
		}
		// 삭제
		int[] arr = board.getDelAttNos();
		if(arr != null) {
		for (int i = 0; i < arr.length; i++) {
			AttatchVO attatch = attDao.selectAttach(arr[i]);			
			File file= new File(saveDir,attatch.getAttSavename());
			file.delete();
		}
		attDao.deleteAttaches(board);
		}
		// 게시글 변경
		int cnt=boardDao.updateBoard(board);
		if(cnt>0) {
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAIL;
		}
	}
	

	@Override
	public ServiceResult removeBoard(FreeBoardVO board) {
		// 비밀번호확인 -> 맞다면 게시글삭제 -> 첨부파일이있다면 첨부파일테이블삭제, 첨부파일 삭제
		
		// 비밀번호 확인
		FreeBoardVO saved = boardDao.selectBoard(board.getBoNo());
//		String encoded = EncryptUtils.encryptSha512Base64(board.getBoPass());
		String savedPass = saved.getBoPass();
		boolean flag = true;
//		if(!savedPass.equals(encoded)) {
//			return ServiceResult.INVALIDPASSWORD;
//		}
		try(
				SqlSession sqlSession = CustomSqlSessionFactoryBuilder.getSqlSessionFactory().openSession();
				){
			// 첨부파일여부 확인
			List<AttatchVO> attList = saved.getAttatchList();
			if (attList != null && !attList.isEmpty()) {
				// attatch 삭제
				int attCnt = attDao.deleteAll(saved.getBoNo(), sqlSession);
				if (attCnt > 0) {
					// 파일삭제
					int delCnt = 0;
					for (AttatchVO vo : attList) {
						new File("d:/savedfile/" + vo.getAttSavename()).delete();
						delCnt++;
					}
					flag = delCnt == attCnt;
				}
			}
			sqlSession.commit();
		}// end try
		if (flag) {
			// 게시글 삭제
			int cnt = boardDao.deleteBoard(board.getBoNo());
			if (cnt > 0)
				return ServiceResult.OK;
		}
		return ServiceResult.FAIL;

		
	}

	@Override
	public AttatchVO download(int attNo) {
		AttatchVO vo = attDao.selectAttach(attNo);
		if(vo == null) {
			throw new DataNotFoundException(attNo+"");
		}
		attDao.increamentDownCount(attNo);
		return vo;
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
	
	private void deleteBinaryFile(String...saveNames) {
		if(saveNames == null || saveNames.length <=0) {
			return;
		}
		for(String saveName:saveNames) {
			FileUtils.deleteQuietly(new File(saveDir,saveName));
		}
	}

}
