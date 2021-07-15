package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * logical ViewName에 따라 실제 뷰로 이동하는 코드를가짐
 * viewName이 "redirect:"로 시작되면, redirection
 * 
 * @param viewName
 * @param req
 * @param resp
 * @throws ServletException
 * @throws IOException
 */
public interface ViewResolver {
	public void viewResolve(String viewName,HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException;
}
