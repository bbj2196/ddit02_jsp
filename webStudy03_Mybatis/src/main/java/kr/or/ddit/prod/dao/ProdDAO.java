package kr.or.ddit.prod.dao;

import kr.or.ddit.vo.ProdVO;

public interface ProdDAO {
	/**
	 * 상품 상제조회
	 * @param prodId
	 * @return
	 */
	public ProdVO selectProd(String prodId);
}
