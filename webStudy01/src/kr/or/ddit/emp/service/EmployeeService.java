package kr.or.ddit.emp.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.EmployWrapper;
import kr.or.ddit.vo.EmployeeVO;

public interface EmployeeService {

	
		public ServiceResult createEmployee(EmployeeVO employee);
		public List<EmployWrapper> retrieveEmployeeList(Map<String, Object>pMap);
		public EmployeeVO retrieveEmployee(int empno);
		public ServiceResult modfiy(EmployeeVO employee);
		public ServiceResult removeEmployee(int empno);
}
