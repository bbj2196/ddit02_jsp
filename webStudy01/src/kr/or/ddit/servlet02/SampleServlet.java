package kr.or.ddit.servlet02;

import java.util.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/sample.tmpl")
public class SampleServlet extends ReadTmlServlet{

	@Override
	protected void makeData(HttpServletRequest req) {
		req.setAttribute("today", new Date());
		
	}

	@Override
	protected String getMime() {
		return "text/html; charset=utf-8";
	}



}
