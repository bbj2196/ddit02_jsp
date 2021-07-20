package kr.or.ddit.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

/**
 * server side application 에서의 이벤트 처리
 * 
 * 1. 이벤트 타겟 결정
 * 2. 이벤트 종류결정
 *  	request, session, application
 * 3. 이벤트 핸들러 구현 : Listener
 * 4. 이벤트 타겟에 핸들러 연결 : web.xml로 핸들러등록 및 부착
 */
public class ContestLoaderListener implements ServletContextListener{

	private static Logger logger = LoggerFactory.getLogger(ContestLoaderListener.class);
	public static File prodImages;
	public static File memImages;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		String saveFolderUrl = "/resources/prodImages";
		application.setAttribute("prodImagesUrl", saveFolderUrl);
		String saveFolderPath = application.getRealPath(saveFolderUrl);
		prodImages = new File(saveFolderPath );
		if(!prodImages.exists()) {
			prodImages.mkdirs();
		}
		saveFolderPath = application.getRealPath("/resources/memImages");
		memImages = new File(saveFolderPath);
		if(!memImages.exists()) {
			memImages.mkdirs();
		}
		application.setAttribute("userCount", new Integer(0));
		application.setAttribute("currentUserCount", new Integer(0));
		application.setAttribute("currentUserList", new LinkedHashMap<String,MemberVO>());
		
	
		logger.info("{} 어플리케이션 초기화",application.getContextPath());
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
