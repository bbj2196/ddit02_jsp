package kr.or.ddit.enumtype;

public enum OsType {
	UNIX("Unix"),
	ANDROID("Andorid"),
	MAC("Mac"),
	IPONE("Ipohone"),
	IPAD("Ipad"),
	LINUX("Linux"),
	WINDOWS("windows"),
	OTHERS("기타");
	private String osName;
	
	private OsType(String osName) {
		this.osName = osName;
	}
	
	public String getOsName() {
		return osName;
	}
	
	public static OsType findOsType(String agent) {
		OsType finded = OTHERS;
		if(agent !=null) {
			agent = agent.toUpperCase();
			for(OsType tmp : values()) {
				if(agent.indexOf(tmp.name())>=0) {
					finded = tmp;
					break;
				}
			}
		}
		return finded;
	}
	
}
