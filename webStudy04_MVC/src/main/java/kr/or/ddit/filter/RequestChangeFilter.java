package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.filter.wrapper.SampleHttpServletRequestWrapper;

/**
 * 원본 요청을 변경해서, 파라미터중에 what 라는 파라미터의 값을 특정 값으로 대체.
 * @author PC-13
 *
 */
public class RequestChangeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		SampleHttpServletRequestWrapper wrapper = new SampleHttpServletRequestWrapper(req);
		chain.doFilter(wrapper, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
