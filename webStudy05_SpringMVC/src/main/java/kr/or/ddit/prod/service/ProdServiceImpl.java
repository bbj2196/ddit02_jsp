package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Service
public class ProdServiceImpl implements ProdService {


	@Inject
	private ProdDAO dao;
	
	@Value("#{appInfo.prodImageUrl}")
	private String prodImages;
	
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
			File saveFolder= new File(prodImages);
			String saveName = prod.getProdImg();
			File saveFile = new File(saveFolder, saveName);
			FileUtils.copyInputStreamToFile(is, saveFile);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;
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
