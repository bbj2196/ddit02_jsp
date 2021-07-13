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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

/**
 * 접근 제어를 통한 서버보안
 * 인증(Authentocation) + 인가(Authorization)
 * 인증 : 보호자원에 대한 요청을 발생시킨 사용자의 신원을 확인하는 과정
 * 인가 : 보호자원에 대한 요청을 발생시킨 사용자가 해당자원에 대해 접근이 허가 되어있는지 확인하는 과정
 * @author PC-13
 *
 */
public class AuthenticationFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	private Map<String, String[]>securedMap;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 초기화",getClass().getSimpleName());
		securedMap = new HashMap<>();
		Properties props = new Properties();
		String path = filterConfig.getInitParameter("secured_path");
		
		try(InputStream in = getClass().getResourceAsStream(path);) {
			props.loadFromXML(in);
			
			for (Object key : props.keySet()) {
				Object value = props.get(key);
				String resourceUrl = key.toString().trim();
				String[] roles = value.toString().trim().split(",");
				Arrays.sort(roles);
				securedMap.put(resourceUrl,roles);
				logger.info("{} : {}",resourceUrl,Arrays.toString(roles));
			}
			ServletContext application = filterConfig.getServletContext();
			application.setAttribute("securedMap", securedMap);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// 현재 요청이 어떤 자원을 요구하는가
		String uri = req.getRequestURI();
		uri=uri.substring(req.getContextPath().length());
		boolean pass = true;
		// 해당 자원이 보호자원인가
		boolean secured = securedMap.containsKey(uri);
		// 보호자원이면 유저가 해당 자원에 접근이 가능한가
		if (secured) {
			Object user = (MemberVO) session.getAttribute("authMember");
			if (user == null) {
				// 로그인된 유저가 없으므로 로그인창으로
				session.setAttribute("message", "로그인을 해주세요");
				pass = false;
			}
		}

		
		if(pass) {
			session.setAttribute("savedRequestURL", uri);
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
		} else {
			// 보호자원이 아니면 실행
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		logger.info("{} 소멸",getClass().getSimpleName());
		
	}
}
