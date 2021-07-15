package kr.or.ddit.enumtype;

public enum MimeType {
	JSON("application/json; charset=utf-8"),
	SCRIPT("text/javascript;"),
	PLAIN("text/plain; charset=utf-8"),
	HTML("text/html; charset=utf-8");
	private String mimeText;
	
	private MimeType(String mimeText) {
		this.mimeText = mimeText;
	}

	public String getMimeText() {
		return mimeText;
	}
	
	public static MimeType findMimeType(String accept) {
		MimeType finded = HTML;
		if(accept != null) {
			accept = accept.toUpperCase();
			for(MimeType tmp : MimeType.values()) {
				if(accept.indexOf(tmp.name())>=0) {
					finded = tmp;
					break;
				}
			}
		}
		return finded;
	}
	
	public static String findMimeText(String accept) {
		MimeType finded = HTML;
		if(accept != null) {
			accept = accept.toUpperCase();
			for(MimeType tmp : MimeType.values()) {
				if(accept.indexOf(tmp.name())>=0) {
					finded = tmp;
					break;
				}
			}
		}
		
		
		return finded.getMimeText();
	}
}
