package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.listener.ContestLoaderListener;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {

	private ProdDAO dao = ProdDAOImpl.getInstance();
	
	/**
	 * 상품의 이미지를 저장
	 * @param prod
	 */
	private void processProdImage(ProdVO prod) {
	MultipartFile prodImage = prod.getProdImage();
		if(prodImage == null ) {
			return;
		}
//		if(true) throw new RuntimeException("강제발생");// 트랜잭션 확인용					
		
		try(
			InputStream is=prodImage.getInputStream();	
			){
			File saveFolder= ContestLoaderListener.prodImages;
			String saveName = prod.getProdImg();
			File saveFile = new File(saveFolder, saveName);
			FileUtils.copyInputStreamToFile(is, saveFile);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
//	차후에 spring을 이용하면 AOP방법론에 따라 Platform based transaction Manager 를 이용하여 해결
	@Override
	public ServiceResult createProd(ProdVO prod) {
		// 트랜잭션 시작
		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
		int cnt = dao.insertProd(prod, sqlSession);
		// 이진 데이터 저장
		processProdImage(prod);
		ServiceResult result = null;
		if(cnt > 0 ) {
			result = ServiceResult.OK;
			sqlSession.commit();
		}else {
			result = ServiceResult.FAIL;
		}
		// 트랜잭션 종료
		
		return result;
		}
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
		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
		int cnt = dao.updateProd(prod, sqlSession);
		// 이진 데이터 저장
		processProdImage(prod);
		ServiceResult result = null;
		if(cnt > 0 ) {
			result = ServiceResult.OK;
			sqlSession.commit();
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
		}
	}

}
