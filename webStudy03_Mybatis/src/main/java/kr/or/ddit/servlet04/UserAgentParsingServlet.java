package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumtype.BrowserType;
import kr.or.ddit.enumtype.MimeType;
import kr.or.ddit.enumtype.OsType;

@WebServlet("/04/getBrowserName")
public class UserAgentParsingServlet extends HttpServlet{
	// jsp에 뚫은 구멍 메꾸기
	// 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		 String userAgent = req.getHeader("user-agent").toUpperCase();
		 String browser= BrowserType.parseUserAgent(userAgent);
		 OsType os = OsType.findOsType(userAgent);
		 Map<String,Object> target = new HashMap<>();
		 target.put("browser", browser);
		 target.put("os",os.getOsName());
//		 StringBuffer json = new StringBuffer();
//		 String PROPTRN="\"%s\":\"%s\",";
//		 // Marshalling : native로 표현된 데이터를 공통 표현방식(xml,json)으로 바꾸는 과정
//		 // UnMarshalling : 공통표현방식으로 표현된 데이터를 native방식으로 바꾸는 과정
//		 json.append("{");
//		 for (Entry<String,Object> entry : target.entrySet()) {
//			json.append(String.format(PROPTRN, entry.getKey(),Objects.toString(entry.getValue(),"")));
//		}
//		 json.append("}");
		 ObjectMapper mapper = new ObjectMapper();
		 String json =mapper.writeValueAsString(target);
//		 int lastIdx = json.lastIndexOf(",");
//		 if(lastIdx >=0) {
//			 json.deleteCharAt(lastIdx);
//		 }
		 
		 String mime = MimeType.findMimeText(accept);
		 resp.setContentType(mime);
		 try (
		 PrintWriter out =resp.getWriter();
		 ){
		 out.print(json);
		 }
		 
	}
}
