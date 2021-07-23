package kr.or.ddit.prop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.PagingVO;

public class DataBasePropertyDAOImpl implements DataBasePropertyDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int selectTotalRecord(PagingVO<DataBasePropertyVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			DataBasePropertyDAO mapper = sqlSession.getMapper(DataBasePropertyDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}
	
	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList(
			PagingVO<DataBasePropertyVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			DataBasePropertyDAO mapper = sqlSession.getMapper(DataBasePropertyDAO.class);
			return mapper.selectDataBasePropertyList(pagingVO);
		}
	}

	
	
	
}
