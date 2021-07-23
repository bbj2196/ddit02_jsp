package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); 
	
	@Override
	public int insertBoard(FreeBoardVO board) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int cnt =  mapper.insertBoard(board);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int selectTotalRecord(PagingVO<FreeBoardVO> pagingVO) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public List<FreeBoardVO> selectBoardList(PagingVO<FreeBoardVO> pagingVO) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectBoardList(pagingVO);
		}
	}

	@Override
	public FreeBoardVO selectBoard(int boNo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectBoard(boNo);
		}
	}

	@Override
	public int updateBoard(FreeBoardVO board) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int cnt =  mapper.updateBoard(board);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteBoard(int boNo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int cnt = mapper.deleteBoard(boNo);
			sqlSession.commit();
			return cnt; 
		}
	}

	@Override
	public int incrementHit(int boNo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int cnt = mapper.incrementHit(boNo);
            sqlSession.commit();
            return cnt;
		}
	}

	@Override
	public int incrementRec(int boNo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int cnt = mapper.incrementRec(boNo);
            sqlSession.commit();
            return cnt;
		}
	}

	@Override
	public int incrementRep(int boNo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int cnt = mapper.incrementRep(boNo);
            sqlSession.commit();
            return cnt;
		}
	}

}
