package kr.or.ddit.prop.dao;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

/**
 * DAO(Data Access Object) : 데이터 저장 계층(Persistence Layer)에 접근하는 역할 수행
 * @author PC-13
 *
 */
public interface DataBasePropertyDAO {

	public List<DataBasePropertyVO>selectDataBasePropertyList(DataBasePropertyVO param);
}
