package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;

public class AttatchDAOImpl implements AttatchDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertAttatches(FreeBoardVO board) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			AttatchDAO mapper = sqlSession.getMapper(AttatchDAO.class);
			int cnt =  mapper.insertAttatches(board);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public AttatchVO selectAttach(int attNo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			AttatchDAO mapper = sqlSession.getMapper(AttatchDAO.class);
			return mapper.selectAttach(attNo);
		}
	}

	@Override
	public int deleteAttaches(FreeBoardVO board) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			AttatchDAO mapper = sqlSession.getMapper(AttatchDAO.class);
			int cnt = mapper.deleteAttaches(board);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteAll(int boNo, SqlSession sqlSession) {
		
			return sqlSession.delete("kr.or.ddit.board.dao.AttatchDAOImpl.deleteAll",boNo);
		
	}

	@Override
	public int increamentDownCount(int attNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
