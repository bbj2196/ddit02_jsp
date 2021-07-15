package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link HttpServletRequest}, {@link HttpServletResponse}, {@link HttpSession}
 * 세가지 타입의 파라미터를 처리하기위한 전략객체
 * @author PC-13
 *
 */
public class ServletSpecArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		return HttpServletRequest.class.equals(parameterType) 
				||HttpServletResponse.class.equals(parameterType)
				||HttpSession.class.equals(parameterType);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object ParameterValue = null;
		if(HttpServletRequest.class.equals(parameterType)) {
			ParameterValue = req;
		}else if(HttpServletResponse.class.equals(parameterType)) {
			ParameterValue =resp;
		}else {
			ParameterValue =req.getSession();
		}
		return ParameterValue ;
	}

}
