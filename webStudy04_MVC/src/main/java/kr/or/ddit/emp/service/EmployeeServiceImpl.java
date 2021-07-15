package kr.or.ddit.emp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.emp.dao.EmployeeDAO;
import kr.or.ddit.emp.dao.EmployeeDAOImpl;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.exception.UserNotFoundExecption;
import kr.or.ddit.vo.EmployWrapper;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO dao = EmployeeDAOImpl.getInstance();
	private static EmployeeService instance;
	public static EmployeeService getInstance() {
		if(instance == null)instance = new EmployeeServiceImpl();
		return instance;
	}
	
	private EmployeeServiceImpl() {
		
	}
	
	
	@Override
	public ServiceResult createEmployee(EmployeeVO employee) {
		return null;
	}

	@Override
	public List<EmployWrapper> retrieveEmployeeList(Map<String, Object> pMap) {
		List<EmployeeVO>list =  dao.selectEmployeeList(pMap);
		List<EmployWrapper>wrapperList = new ArrayList<>();
		list.forEach((employee)->{wrapperList.add(new EmployWrapper(employee));});
		return wrapperList;
	}

	@Override
	public EmployeeVO retrieveEmployee(int empno) {
		Map<String, Object>pMap = new HashMap<>();
		pMap.put("empno",empno);
		EmployeeVO vo = null;
		vo = dao.selectEmployee(pMap);
		if(vo == null) throw new UserNotFoundExecption();
		return vo;
	}

	@Override
	public ServiceResult modfiy(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeEmployee(int empno) {
		// TODO Auto-generated method stub
		return null;
	}

}
