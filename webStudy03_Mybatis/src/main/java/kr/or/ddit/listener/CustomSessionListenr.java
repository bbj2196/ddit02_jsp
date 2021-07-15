package kr.or.ddit.listener;

import java.util.LinkedHashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import kr.or.ddit.vo.MemberVO;

/**
 * Application Lifecycle Listener implementation class CustomSessionListenr
 *
 */
@WebListener
public class CustomSessionListenr implements HttpSessionListener, HttpSessionAttributeListener {
	private ServletContext application;
	private HttpSession session;

    public void sessionCreated(HttpSessionEvent se)  { 
		session = se.getSession();
    	application = session.getServletContext();
    	Integer userCNT = (Integer) application.getAttribute("userCount");
    	Integer currentCNT = (Integer) application.getAttribute("currentUserCount");
    	application.setAttribute("userCount", userCNT+1);
    	application.setAttribute("currentUserCount", currentCNT+1);
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	Integer currentCNT = (Integer) application.getAttribute("currentUserCount");
    	if(currentCNT>0) {
    		application.setAttribute("currentUserCount", currentCNT-1);
    	}
    	
    }

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
//		String added = event.getName();
//		if(added.equals("authMember")) {
//			MemberVO user=(MemberVO) event.getValue();
//			LinkedHashMap<String, MemberVO> currentUsers=(LinkedHashMap<String, MemberVO>) application.getAttribute("currentUserList");
//			currentUsers.put(user.getMemId(), user);
//		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
//    	String deleted = event.getName();
//		if(deleted .equals("authMember")) {
//		LinkedHashMap<String, MemberVO> currentUsers=(LinkedHashMap<String, MemberVO>) application.getAttribute("currentUserList");
//		MemberVO user=(MemberVO) event.getValue();
//		currentUsers.remove(user.getMemId());
//    	}		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
