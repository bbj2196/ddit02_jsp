package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.multipart.StandardMultipartHttpServletRequest;

/**
 * @RequestPart 어노테이션을 가진 핸들러 메서드 아규먼트 처리
 * MultipartFile 타입의 아규먼트에 대한 처리
 * @author PC-13
 *
 */
public class RequsetPartArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		return annotation != null && (
				MultipartFile.class.equals(parameterType)
				|| (parameterType.isArray() && parameterType.getComponentType().equals(MultipartFile.class))
				);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!(req instanceof StandardMultipartHttpServletRequest)) {
			throw new ServletException("현재 요청은 MultiPart요청이 아닙니다.");
		}
		
		Class<?> parameterType = parameter.getType();
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		String partName = annotation.value();
		boolean required = annotation.required();
		
		StandardMultipartHttpServletRequest wrapper = (StandardMultipartHttpServletRequest) req;
		List<MultipartFile> files = wrapper.getFiles(partName);
		Object parameterValue = null;
		
		if(files == null && required) {
				throw new BadRequestException("필수 파일이 업로드 되지 않았음");
		}
		if(files!=null && !files.isEmpty()) {
			if(parameterType.isArray()) {
				MultipartFile[] array = new MultipartFile[files.size()];
				parameterValue=files.toArray(array);
			}else {
				parameterValue=files.get(0);
			}
		}
		return parameterValue;
		
	}

}
