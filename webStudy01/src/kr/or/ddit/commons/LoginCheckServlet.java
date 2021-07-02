package kr.or.ddit.commons;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.UsernotFoundExecption;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AuthenticateService service = new AuthenticateServiceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1. 파라미터 확보
		String id = request.getParameter("mem_id");
		String pass = request.getParameter("mem_pass");
		HttpSession session = request.getSession();
		Map<String,String>map = new HashMap<>();
		session.setAttribute("errors", map);
		
		MemberVO param = new MemberVO(id,pass);
		
		boolean valid = validate(param,map);
		String goPage=null;
		boolean redirect = false;
		
		//2. 검증
		//필수파라미터 누락여부 확인(400)
		System.out.println("-----------");
		System.out.println(id);
		System.out.println(pass);
		System.out.println(valid);
		if(!valid) {
			goPage="/login/loginForm.jsp";
			redirect=true;
		}
		try {
		Object resultValue = service.authenticate(param);
		if(resultValue instanceof MemberVO) {
			//	1)성공 : welcome page로 이동(redirect)
			goPage="/";
			redirect=true;
			// 로그인에 성공하면 웰컴페이지로 이동해서 성공한 이름을 보여줄것
			session.setAttribute("authMember", resultValue);
		}else {
			//	2)실패 : login page로 이동(forward)
			session.setAttribute("mem_id", id);
			goPage="/login/loginForm.jsp";
			session.setAttribute("failId", id);
			session.setAttribute("message", "비밀번호 오류");
		}
		}catch(UsernotFoundExecption e) {
			goPage="/login/loginForm.jsp";
			redirect=true;
			session.setAttribute("message", e.getMessage());
		}
		
		//3. 인증	
		System.out.println(id);
		if(redirect) {
			response.sendRedirect(request.getContextPath()+goPage);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher(goPage);
			rd.forward(request, response);
		}

		
		}
	

	private boolean validate(MemberVO param,Map<String,String>errors){
		boolean valid = true;
		if(StringUtils.isBlank(param.getMem_id())) {
			valid=false;
			errors.put("mem_id","아이디는 필수 입력");
			throw new UsernotFoundExecption("사용자가 존재하지않음");
		}
		if(StringUtils.isBlank(param.getMem_pass())) {
			valid=false;
			errors.put("mem_id","비밀번호 필수입력");
		}
		/*else {
		Pattern regPatern = Pattern.compile("(^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\\$%\\^\\&\\*]+).{4,8})$");
		Matcher matcher = regPatern.matcher(param.getMem_pass());
		if(!matcher.find()) {
			valid=false;
			errors.put("mem_id","비밀번호는 영대소문자 숫자 특수문자를 포함한 4~8글자 이내");
			}else {
				System.out.println(matcher.group(1));
			}
		}*/
		return valid;
	}

}
