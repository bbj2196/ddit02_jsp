package kr.or.ddit.prop.service;

import java.util.Calendar;
import java.util.List;

import kr.or.ddit.prop.dao.DataBasePropertyDAO;
import kr.or.ddit.prop.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.PagingVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService{

private DataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
	
	@Override
	public List<DataBasePropertyVO> retrieveDataBaseProperties(PagingVO<DataBasePropertyVO> pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		// raw data
		List<DataBasePropertyVO> propList = 
				dao.selectDataBasePropertyList(pagingVO);
		pagingVO.setDatalist(propList);
		// logic -> information
		Calendar cal = Calendar.getInstance();
		String pattern = "%s, %tc";
		for(DataBasePropertyVO prop : propList) {
			String infoValue = String.format(pattern, prop.getPropertyValue(), cal);
			prop.setPropertyValue(infoValue);
		}
		return propList;
	}
	}

