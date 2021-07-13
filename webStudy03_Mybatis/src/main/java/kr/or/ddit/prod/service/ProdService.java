package kr.or.ddit.prod.service;


import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Business Logic Layer(Service Layer)
 * @author PC-13
 *
 */
public interface ProdService {

	/**
	 * 신규 상품등록
	 * pk를 입력받지 않고 생성하므로 중복발생은 없다
	 * @param prod
	 * @return OK 성공 시 call by ref로 PK조회가능 ,FAIL 실패
	 */
	public ServiceResult createProd(ProdVO prod);
	
	/**
	 * 
	 * @param pagingVO call by ref 로 dataList와 totalRecord 채운다
	 */
	public void retrieveProdList(PagingVO<ProdVO> pagingVO) ;
	
	/**
	 * 상품상세조회
	 * @param prodId
	 * @return 존재하지않으면 {@link DataNotFoundException}발생
	 */
	public ProdVO retrieveProd(String prodId);
	
	/**
	 * 
	 * @param prod
	 * @return 존재하지않으면 {@link DataNotFoundException} , 성공 OK , 실패 FAIL
	 */
	public ServiceResult modifyProd(ProdVO prod);
}
