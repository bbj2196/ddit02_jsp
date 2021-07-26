package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO {

	@Inject
	private SqlSession sqlSession; 
	
	@Override
	public int insertBoard(FreeBoardVO board) {
		return sqlSession.insert("kr.or.ddit.board.dao.FreeBoardDAO.insertBoard",board);
	}

	@Override
	public int selectTotalRecord(PagingVO<FreeBoardVO> pagingVO) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectTotalRecord(pagingVO);
	}

	@Override
	public List<FreeBoardVO> selectBoardList(PagingVO<FreeBoardVO> pagingVO) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectBoardList(pagingVO);
	}

	@Override
	public FreeBoardVO selectBoard(int boNo) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectBoard(boNo);
	}

	@Override
	public int updateBoard(FreeBoardVO board) {
		return sqlSession.update("kr.or.ddit.board.dao.FreeBoardDAO.updateBoard",board);
	}

	@Override
	public int deleteBoard(int boNo) {
		return sqlSession.delete("kr.or.ddit.board.dao.FreeBoardDAO.deleteBoard",boNo); 
	}

	@Override
	public int incrementHit(int boNo) {
		return sqlSession.update("kr.or.ddit.board.dao.FreeBoardDAO.incrementHit",boNo);
	}

	@Override
	public int incrementRec(int boNo) {
		return sqlSession.update("kr.or.ddit.board.dao.FreeBoardDAO.incrementRec",boNo);
	}

	@Override
	public int incrementRep(int boNo) {
		return sqlSession.update("kr.or.ddit.board.dao.FreeBoardDAO.incrementRep",boNo);
	}

}
