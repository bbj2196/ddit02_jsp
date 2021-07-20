package kr.or.ddit.multipart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class StandardMultipartHttpServletRequest extends HttpServletRequestWrapper{

	// 일반리퀘스트와 다른점
	// 업로드 파일들을 보관하고있는 Map이 있따.
	// 보관된 파일들을 처리할수있는 메서드들이 몇개 있따
	private Map<String, List<MultipartFile>>multipartFiles;// 파일들을 저장할 맵   [이름: 파일들]
	public StandardMultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		multipartFiles= new HashMap<>();
		parseRequeset(request);
	}

	
	private void parseRequeset(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		// part를 하나씩꺼내서 이름:파일들 의 형태로 넣음
		// 이름으로 구분하고 여러개의 파일이 올수도 있기 때문에 List에 담아 Map에 최종적으로 담아준다
		for (Part single : parts) {
			if(single.getContentType() == null) {
				continue;
			}
			MultipartFile file=new StandardMultipartFile(single);
			
			String partName = file.getName();
			List<MultipartFile> already = multipartFiles.get(partName);
			if(already == null) {
				already = new ArrayList<>();
				multipartFiles.put(partName,already);
			}
			already.add(file);
		}
		
	}
	
	public MultipartFile getFile(String partName) {
		List<MultipartFile>files = multipartFiles.get(partName);
		MultipartFile file = null;
		if(files != null && !files.isEmpty()) {
			file=files.get(0);
		}
		return file;
	}
	
	public List<MultipartFile> getFiles(String partName) {
		return multipartFiles.get(partName);
	}
	
	public Map<String, List<MultipartFile>> getMultipartFiles() {
		return multipartFiles;
	}

	public Enumeration<String> getPartNames() {
		// collection view
		Iterator<String> keys = multipartFiles.keySet().iterator();
		return new Enumeration<String>() {

			@Override
			public boolean hasMoreElements() {
				return keys.hasNext();
			}

			@Override
			public String nextElement() {
				return keys.next();
			}
			
		};
	}
}
