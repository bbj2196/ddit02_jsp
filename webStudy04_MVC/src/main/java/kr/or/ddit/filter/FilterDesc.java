package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decorating Filter Pattern 의 이용 
 *  	:요청과 응답에 대한 변형을 가하되 원본의 성질 자체를 바꾸지는 않는 부가 레이어
 * @@webFilter로 서블릿과 같이 매핑이 가능하나 순서를 알기 힘들어 가능하면 xml에 등록해서 사용
 * 
 * 
 * 1. Filter의 구현체 정의
 *  	- 필터링 수행 callback (doFilter) : chain의 doFilter를 이용해서 제어권을 적절히 이동.
 * 2. WAS에 등록하고 , Filter Chain이 형성되도록함
 *  	- filter chaing 내에서 필터링 되는순서는 등록된 순서를 따름.
 * 3. 필터링할 요청에 대한 매핑 설정(경로매핑, 확장자 매핑)
 * @author PC-13
 */

public class FilterDesc implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(FilterDesc.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화",getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("{} 요청발생", req.getRequestURI());
		chain.doFilter(request, response);
		long end= System.currentTimeMillis();
		logger.info("{} 소요시간 {} ms", req.getRequestURI(),end-start);
		
	}

	@Override
	public void destroy() {
		logger.info("{} 소멸",getClass().getSimpleName());
	}

}
