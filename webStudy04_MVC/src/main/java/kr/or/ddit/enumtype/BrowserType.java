package kr.or.ddit.enumtype;

public enum BrowserType{
	   MSIE("익스플로러 구버전"),
	   TRIDENT("익스플로러 신버전"),
	   OPERA("오페라"),
	   FIREFOX("파이어폭스"),
	   EDG("엣지"),
	   CHROME("크롬"),
	   SAFARI("사파리"),
	   OTHER("기타");
	   private String browserName;
	   private BrowserType(String browserName){
		   this.browserName = browserName;
	   }
	   public String getBrowserName(){
		   return this.browserName;
	   }
	   public static String parseUserAgent(String userAgent) {
		   BrowserType finded = OTHER;
		   if(userAgent != null) {
		   userAgent = userAgent.toUpperCase();
		   for(BrowserType tmp : values()){
				if(userAgent.indexOf(tmp.name())>=0){
					finded = tmp;
					break;
				}
			}
		   }
		   return finded.getBrowserName();
	   }
}
