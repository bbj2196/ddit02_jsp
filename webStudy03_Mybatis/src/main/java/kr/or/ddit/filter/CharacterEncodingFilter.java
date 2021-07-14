package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterEncodingFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);
	private String encoding;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding=filterConfig.getInitParameter(encoding);
		if(encoding == null || encoding.isEmpty()) {
			encoding = "UTF-8";
		}
		logger.info("인코딩 설정 : {}\t{}",encoding,getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
