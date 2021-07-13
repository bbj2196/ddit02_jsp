package kr.or.ddit;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	@Test
	public void test() throws JsonProcessingException {
		ResourceBundle bundle = ResourceBundle.getBundle("kr/or/ddit/servlet05/message");
		Map<String, Object>bundleMap = new HashMap<>();
		for(String key : bundle.keySet()) {
			bundleMap.put(key,bundle.getObject(key));
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bundleMap);
		System.out.println(json);
	}

}
