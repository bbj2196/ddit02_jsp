package kr.or.ddit.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImpl implements EmployeeDAO{

	private static EmployeeDAO instance;
	private EmployeeDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	public static EmployeeDAO getInstance() {
		if(instance == null) instance = new EmployeeDAOImpl();
		return instance;
	}
	@Override
	public int insertEmployee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmployeeVO> selectEmployeeList(Map<String, Object> pMap) {
		StringBuffer sql =new StringBuffer();
		sql.append(" select EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,a.DEPTNO DEPTNO,DNAME ");
		sql.append(" ,(select decode(count(*),0,'1','0') from emp C where C.mgr = a.empno)as LEAF");
		sql.append(" from emp a,dept b");
		sql.append(" where a.DEPTNO = b.DEPTNO and ");
		if(pMap!= null && pMap.containsKey("mgr")) {
			sql.append(" mgr = ?");
		}else {
			sql.append(" mgr is null");
		}
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			if(pMap!= null && pMap.containsKey("mgr")) {
				pstmt.setInt(1, (Integer)pMap.get("mgr"));
			}
			ResultSet rs = pstmt.executeQuery();
			
			List<EmployeeVO>list = new ArrayList<>();
			
			while(rs.next()) {
				EmployeeVO vo = EmployeeVO.builder()
											.empno(rs.getInt("EMPNO"))
											.ename(rs.getString("ENAME"))
											.job(rs.getString("JOB"))
											.mgr(rs.getInt("MGR"))
											.hiredate(rs.getString("HIREDATE"))
											.sal(rs.getInt("SAL"))
											.comm(rs.getInt("COMM"))
											.deptno(rs.getInt("DEPTNO"))
											.dname(rs.getString("DNAME"))
											.leaf(rs.getBoolean("LEAF"))
											.build();
				list.add(vo);
			}
			rs.close();
			return list;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public EmployeeVO selectEmployee(Map<String, Object> pMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmployee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmpliyee(int empno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
