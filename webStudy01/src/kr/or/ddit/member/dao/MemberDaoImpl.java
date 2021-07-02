package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberById(String mem_id) {
		String sql = "select * from member where mem_id= ? ";
		try(
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, mem_id);
		ResultSet rs =stmt.executeQuery();
		MemberVO savedVO = null;
		if(rs.next()) {
			savedVO = new MemberVO();
			savedVO.setMem_id(rs.getString("mem_id"));
			savedVO.setMem_pass(rs.getString("mem_pass"));
			savedVO.setMem_name(rs.getString("mem_name"));
		}
		return savedVO;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
