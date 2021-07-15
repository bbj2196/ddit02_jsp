package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlindFilter implements Filter{

	private Map<String, String>blindMap;
	private static Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화",getClass().getSimpleName());
		blindMap = new HashMap<>();
		blindMap.put("127.0.0.1","ㄷㄷㄷㅈ");
		blindMap.put("0:0:0:0:0:0:0:1","ㄷㄷㄷㅈ");
		blindMap.put("192.168.44.36","왼쪽자리");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String messageView="/13/blindMessage.jsp";
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		String clientIp = req.getRemoteAddr();
		boolean pass= uri.equals(messageView) || !blindMap.containsKey(clientIp);
		if(!pass) {
			String reason = blindMap.get(clientIp);
			req.getSession().setAttribute("reason", reason);
			resp.sendRedirect(req.getContextPath()+messageView);
		}else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		logger.info("{} 소멸",getClass().getSimpleName());		
	}

}
