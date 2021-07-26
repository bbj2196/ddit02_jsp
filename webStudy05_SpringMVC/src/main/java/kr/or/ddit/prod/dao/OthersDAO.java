package kr.or.ddit.prod.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;

import java.util.List;

/**
 * 상품 분류와 거래처를 선택할 수 있는 UI 구성에 사용항 Persistence Layer
 * @author PC-13
 *
 */
@Mapper
public interface OthersDAO {
	
	/**
	 * 분류코드와 분류명으로 구성
	 * @return
	 */
	public List<Map<String, Object>> selectLprodList();
	
	/**
	 * 거래처 코드, 거래처명, 거래처 분류로 구성
	 * @return
	 */
	public List<BuyerVO> selectBuyerList();
}
