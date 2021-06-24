package kr.or.ddit.enumtype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowserTypeClass {
	   public static String MSIE="익스플로러 구버전";
	   public static String TRIDENT="익스플로러 신버전";
	   public static String OPERA="오페라";
	   public static String FIREFOX="파이어폭스";
	   public static String EDG="엣지";
	   public static String CHROME="크롬";
	   public static String SAFARI="사파리";
	   public static String OTHER="기타";
	   
	   public Map<String, String>listMap = new HashMap<>();
	   
	   public BrowserTypeClass() {
		   listMap.put("MSIE","익스플로러 구버전");
		   listMap.put("TRIDENT","익스플로러 신버전");
		   listMap.put("OPERA","오페라");
		   listMap.put("FIREFOX","파이어폭스");
		   listMap.put("EDG","엣지");
		   listMap.put("CHROME","크롬");
		   listMap.put("SAFARI","사파리");
		   listMap.put("OTHER","기타");
	}
	   
	   public void add(String key,String val) {
		   listMap.put(key,val);
	   }
}
