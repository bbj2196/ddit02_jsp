package kr.or.ddit.prop.service;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.PagingVO;

public interface DataBasePropertyService {
	
	/**
	 * Business Logic Layer : raw 데이터 가공 logic을 운영하기 위한 계층.
	 * @param pagingVO TODO
	 * @return
	 */
	public List<DataBasePropertyVO>retrieveDataBaseProperties(PagingVO<DataBasePropertyVO> pagingVO);
}
