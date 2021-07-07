package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDaoImpl implements MemberDAO {

	private static MemberDAO instance;
	public static MemberDAO getInstance() {
		if(instance == null)instance = new MemberDaoImpl();
		return instance;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public MemberVO selectMemberById(String mem_id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMemberById", mem_id);
		}
		
	}

	
	@Override
	public int insertMember(MemberVO member) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
					MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
					int cnt = mapper.insertMember(member);
					sqlSession.commit();
					return cnt;
				}
		
		
	}

	@Override
	public List<MemberVO> selectMemeberList(PagingVO paging) {
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			return sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemeberList",paging);
		}
	}

	@Override
	public MemberVO selectMemberDetail(String mem_id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			return mapper.selectMemberDetail(mem_id);
		}
	}
	

	@Override
	public int updateMember(MemberVO member) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int cnt = mapper.updateMember(member);
			sqlSession.commit();
			return cnt; 
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
					MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
					int rowcnt= mapper.deleteMember(mem_id);
					sqlSession.commit();
					return rowcnt;
				}
	}


	@Override
	public int selectTotalRecord(PagingVO pagingVO) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
					MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
					return mapper.selectTotalRecord(pagingVO);
				}
	}


}
