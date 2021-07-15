package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import kr.or.ddit.mvc.annotation.resolvers.RequsetPartArgumentResolver;

public class FrontController extends HttpServlet {

	private HandlerMapping handlerMapper;
	private HandlerAdapter handlerAdapter;
	private ViewResolver viewResolver;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String basePackage = config.getInitParameter("basePackage");
		String prefix = config.getInitParameter("prefix");
		String suffix = config.getInitParameter("suffix");
		handlerMapper = new RequestMappingHandlerMapping(basePackage);
		handlerAdapter = new RequestMappingHandlerAdapter();
		viewResolver = new InternalResourceViewResolver();
		((InternalResourceViewResolver) viewResolver).setPrefix(prefix);
		((InternalResourceViewResolver) viewResolver).setSuffix(suffix);
		
		
		((RequestMappingHandlerAdapter)handlerAdapter).addArgumentResolvers(new RequsetPartArgumentResolver());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestMappingInfo mappingInfo = handlerMapper.findCommandHandler(req);
		if (mappingInfo == null) {
			resp.sendError(404, req.getRequestURI() + "에 대한 핸들러 없음");
		}else {
		String viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp);
		if (viewName == null) {
			if (!resp.isCommitted()) {
				resp.sendError(500, "논리적인 뷰 네임이 결정되지않음");
			}
		} else {
			viewResolver.viewResolve(viewName, req, resp);
		}
		}
	}

}
