package kr.or.ddit.prod.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	private static ProdDAO instance;
	private ProdDAOImpl() {
	}
	
	public static ProdDAO getInstance() {
		if(instance == null) instance = new ProdDAOImpl();
		return instance;
	}
	
	
	@Override
	public ProdVO selectProd(String prodId) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
					ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
					return mapper.selectProd(prodId);
				}
	}

}
