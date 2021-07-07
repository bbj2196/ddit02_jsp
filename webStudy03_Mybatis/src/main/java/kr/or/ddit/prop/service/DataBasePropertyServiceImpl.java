package kr.or.ddit.prop.service;

import java.util.Calendar;
import java.util.List;

import kr.or.ddit.prop.dao.DataBasePropertyDAO;
import kr.or.ddit.prop.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService{

	private DataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
	@Override
	public List<DataBasePropertyVO> retrieveDataBaseProperties(DataBasePropertyVO param) {
		//row data
		List<DataBasePropertyVO> list =dao.selectDataBasePropertyList(param);
		// logic -> infomation
		Calendar cal = Calendar.getInstance();
		String pattern="%s,%tc";
		for (DataBasePropertyVO vo : list) {
			String infoVal=String.format(pattern, vo.getPropertyValue(),cal);
			vo.setPropertyValue(infoVal);
		}
		return list;
	}
}
