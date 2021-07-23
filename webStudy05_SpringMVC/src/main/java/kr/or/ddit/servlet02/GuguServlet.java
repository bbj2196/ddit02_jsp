package kr.or.ddit.servlet02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/gugudan.tmpl")
public class GuguServlet extends ReadTmlServlet {

	@Override
	protected String getMime() {
		return "text/html; charset=utf-8";
	}

	@Override
	protected void makeData(HttpServletRequest req) {
		StringBuffer data = new StringBuffer();
		String str_dan = req.getParameter("dan");
		
		int minDan=1;
		int maxDan=9;
		int dan=1;
		
		
		try {
			if(str_dan != null && str_dan.isEmpty()) {
			dan = Integer.parseInt(str_dan);
			minDan = dan;
			maxDan = dan;
//			minDan = Integer.parseInt(req.getParameter("minDan"));
//			maxDan = Integer.parseInt(req.getParameter("maxDan"));
			}
		}catch(NumberFormatException e) {
			System.out.println("숫자변환불가");
		}
		
		data.append("<tr>");
		for (int j = minDan; j <= maxDan; j++) {
			data.append("<td>"+j+"단</td>");
		}
		data.append("</tr>");
		
		for(int i=1;i<=9;i++) {
			data.append("<tr>");
			for (int j = minDan; j <= maxDan; j++) {
				data.append("<td>");
				data.append(j+"*"+i+"="+j*i);
				data.append("</td>");
				
			}
			data.append("</tr>");
		}
		req.setAttribute("gugudan", data);

	}

}
