package kr.or.ddit.enumtype;

public enum OsType {
	UNIX("유닉스"),
	ANDROID("안드로이드"),
	MAC("맥"),
	IPONE("아이폰"),
	IPAD("아이패드"),
	LINUX("리눅스"),
	WINDOWS("윈도우");
	private String osName;
	
	private OsType(String osName) {
		this.osName = osName;
	}
	
	public String getOsName() {
		return osName;
	}
	
}
