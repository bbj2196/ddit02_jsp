package kr.or.ddit.emp.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.EmployeeVO;

/**
 * 직원 관리(CRUD) Persistence Layer
 * @author PC-13
 *
 */
public interface EmployeeDAO {
	/**
	 * 신규 직원 등록
	 * @param employee
	 * @return >0 성공
	 */
	public int insertEmployee(EmployeeVO employee);
	
	/**
	 * 검색조건에 맞는 직원목록 조회
	 * @param pMap 검색조건을 가진 맵
	 * @return
	 */
	public List<EmployeeVO>selectEmployeeList(Map<String, Object> pMap);
	
	/**
	 * 직원 상세조회
	 * @return 조건에 맞는 직원ㅇ ㅣ없을경우 null
	 */
	public EmployeeVO selectEmployee(Map<String,Object> pMap);
	
	/**
	 * 회원정보 수정
	 * @param employee
	 * @return
	 */
public int	updateEmployee(EmployeeVO employee);
/**
 * 직원 정보 삭제
 * @param empno
 * @return
 */
public int	deleteEmpliyee(int empno);
}
