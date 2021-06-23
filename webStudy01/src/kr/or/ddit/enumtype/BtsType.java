package kr.or.ddit.enumtype;

public enum BtsType {
	BUI("bui"),
	JHOP("jhop"),
	JIMIN("jimin"),
	JIN("jin"),
	JUNGKUK("jungkuk"),
	RM("rm"),
	SUGA("suga");
	private String memName;
	
	private BtsType(String name) {  
		this.memName = name;
	}
	
	public String getName() {
		return this.memName;
	}
	
	public static BtsType findBts(String nameval) {
		String val = nameval.toLowerCase();
		BtsType finded = BtsType.RM;
		for(BtsType mem : BtsType.values()) {
			if(mem.getName().indexOf(val)>=0) {
				finded = mem;
				break;
			}
		}
		return finded;
	}
}
