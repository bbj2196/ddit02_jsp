package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {

	private ProdDAO dao = ProdDAOImpl.getInstance();
	
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int cnt = dao.insertProd(prod);
		ServiceResult result = null;
		if(cnt > 0 ) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public void retrieveProdList(PagingVO<ProdVO> pagingVO) {
		
		List<ProdVO> datalist=null;
		datalist = dao.selectProdList(pagingVO);
		pagingVO.setDatalist(datalist);
		pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));

	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod= null;
		prod = dao.selectProd(prodId);
		if(prod == null) {
			throw new DataNotFoundException();
		}
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProdId());
		int cnt = dao.updateProd(prod);
		ServiceResult result = null;
		if(cnt > 0 ) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

}
