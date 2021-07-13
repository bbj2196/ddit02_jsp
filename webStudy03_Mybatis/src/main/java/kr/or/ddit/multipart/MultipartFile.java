package kr.or.ddit.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface MultipartFile {

	public String getOriginalFilename();
	public boolean isEmpty();
	public String getName();
	public String getContentType();
	public long getSize();
	
	public InputStream getInputStream()throws IOException;
	public byte[] getBytes()throws IOException;
	public void transferTo(File saveFile)throws IOException;
}
