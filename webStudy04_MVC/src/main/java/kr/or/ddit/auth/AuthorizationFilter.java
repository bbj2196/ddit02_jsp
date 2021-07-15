package kr.or.ddit.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

public class AuthorizationFilter implements Filter {

	
	private static Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
	private ServletContext application;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		application=filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Map<String, String[]>securedMap = (Map<String, String[]>) application.getAttribute("securedMap");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		uri=uri.substring(req.getContextPath().length());
		boolean pass = true;
		
		if(securedMap.containsKey(uri)) {
			MemberVO user = (MemberVO) req.getSession().getAttribute("authMember");
			String userRole = user.getMemRole();
			
			String[] roles = securedMap.get(uri);
			int idx = Arrays.binarySearch(roles, userRole);
			pass = !(idx <0);
		}
		
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}



}
