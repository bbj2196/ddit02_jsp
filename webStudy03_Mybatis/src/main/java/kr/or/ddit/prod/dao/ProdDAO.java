package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer
 * 상품을 등록하면 여러 추가데이터가 발생하기때문에 삭제 메서드가 없다 다만, 현재 판매상태컬럼을 추가해 판매여부를 알수있다
 * @author PC-13
 *
 */
public interface ProdDAO {
	/**
	 * 신규 상품 등록
	 * @param prod PK를 제외한 나머지 상품 데이터
	 * @param sqlSession TODO
	 * @return rowcnt > 0 성공, PK는 call by ref로 확인
	 */
	public int insertProd(ProdVO prod, SqlSession sqlSession);
	
	/**
	 * 조건에 맞는 Prod를 생성
	 * @param pagingVO
	 * @return
	 */
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
	/**
	 * 조건에 맞는 전체 Prod 갯수 
	 * @param PagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ProdVO> PagingVO);
	/**
	 * 상품 상제조회
	 * @param prodId
	 * @return
	 */
	public ProdVO selectProd(String prodId);
	
	public int updateProd(ProdVO prod, SqlSession sqlSession);
}
