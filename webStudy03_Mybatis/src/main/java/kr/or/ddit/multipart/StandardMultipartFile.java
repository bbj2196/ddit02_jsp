package kr.or.ddit.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class StandardMultipartFile implements MultipartFile{

	private final Part adaptee;

	private static final String DISPOSITION="Content-Disposition";
	
	public StandardMultipartFile(Part adaptee) {
		super();
		this.adaptee = adaptee;
	}


	private String extractFileName(String disposition) {
//		form-data; name="filePart"; filename=""
		if(StringUtils.isBlank(disposition)) {
			return null;
		}
		int first = disposition.indexOf("filename=");
		String fileName = null;
		if(first != -1) {
			int end = disposition.indexOf(";", first);
			if(end == -1) {
				fileName=disposition.substring(first+"filename=".length());
			}else {
				fileName=disposition.substring(first+"filename=".length(),end);
			}
			fileName=fileName.replace("\"", "");
		}
		return fileName;
	}

	
	@Override
	public String getOriginalFilename() {
		return extractFileName(this.adaptee.getHeader(DISPOSITION));
	}

	@Override
	public boolean isEmpty() {
		return adaptee.getSize() == 0;
	}

	@Override
	public String getName() {
		return this.adaptee.getName();
	}

	@Override
	public String getContentType() {
		return this.adaptee.getContentType();
	}

	@Override
	public long getSize() {
		return this.adaptee.getSize();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return this.adaptee.getInputStream();
	}

	@Override
	public byte[] getBytes() throws IOException {
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public void transferTo(File saveFile) throws IOException {
		this.adaptee.write(saveFile.getPath());
		
	}
	
	
}
