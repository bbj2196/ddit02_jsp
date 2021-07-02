package kr.or.ddit.prop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements DataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList(DataBasePropertyVO param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select *");
		sql.append(" from database_properties ");
		if(param !=null) {
			StringBuffer searchSql = new StringBuffer();
			if (StringUtils.isNotBlank(param.getProperty_name())) {
				searchSql.append(" or instr(property_name,?) > 0 ");
			}
			if (StringUtils.isNotBlank(param.getProperty_value())) {
				searchSql.append(" or instr(property_value,?) > 0 ");

			}
			if (StringUtils.isNotBlank(param.getDescription())) {
				searchSql.append(" or instr(description,?) > 0 ");
			}
			int index = -1;
			if( (index = searchSql.indexOf("or")) != -1) {
				searchSql.delete(index, index+2);
				sql.append(" where ");
			}
			sql.append(searchSql.toString());
		}
		try(
				Connection conn = ConnectionFactory.getConnection();
				// 4. query 객체 생성
				PreparedStatement stmt=conn.prepareStatement(sql.toString());
					){
			System.out.println(sql);
			if(param !=null) {
				int idx = 1;
			
				if (StringUtils.isNotBlank(param.getProperty_name())) {
					stmt.setString(idx++, param.getProperty_name());
				}
				
				if (StringUtils.isNotBlank(param.getProperty_value())) {
					stmt.setString(idx++, param.getProperty_value());
				}
				
				if (StringUtils.isNotBlank(param.getDescription())) {
					stmt.setString(idx++, param.getDescription());
				}
			}
				// 5. 쿼리실행
				ResultSet rs=stmt.executeQuery();
				List<DataBasePropertyVO>list = new ArrayList<>();
				while(rs.next()){
					DataBasePropertyVO vo = new DataBasePropertyVO();
					
					vo.setProperty_name(rs.getString("PROPERTY_NAME"));
					vo.setProperty_value(rs.getString("PROPERTY_VALUE"));
					vo.setDescription(rs.getString("DESCRIPTION"));
					list.add(vo);
				}
				rs.close();
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e); 
			}
	}

}
