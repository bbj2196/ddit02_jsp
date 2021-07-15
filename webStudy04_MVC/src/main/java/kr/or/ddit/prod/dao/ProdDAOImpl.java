package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
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

	@Override
	public int insertProd(ProdVO prod, SqlSession sqlSession) {
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return sqlSession.insert("kr.or.ddit.prod.dao.ProdDAO.insertProd",prod);
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProdList(pagingVO);
		}
	}

	@Override
	public int selectTotalRecord(PagingVO<ProdVO> PagingVO) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectTotalRecord(PagingVO);
		}
	}

	@Override
	public int updateProd(ProdVO prod, SqlSession sqlSession) {
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return sqlSession.update("kr.or.ddit.prod.dao.ProdDAO.updateProd",prod);
		}
	}


