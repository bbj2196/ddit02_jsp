package kr.or.ddit.filter.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SampleHttpServletRequestWrapper extends HttpServletRequestWrapper{

	public SampleHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getParameter(String name) {
		if("what".equals(name)) {
			return "P101000001";
		}else {
			return super.getParameter(name);
		}
	}
	
	public String getCustomData() {
		return "customData";
	}

}
