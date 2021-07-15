package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Front로부터 전달받은 핸들러에대한 정보를 바탕으로,
 * commnad 하나를 호출하는 역활을 수행
 * @author PC-13
 *
 */
public interface HandlerAdapter {
	public String invokeHandler(RequestMappingInfo mappingInfo,HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException;
}
