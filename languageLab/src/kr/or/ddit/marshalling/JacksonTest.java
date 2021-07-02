package kr.or.ddit.marshalling;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	public static void main(String[] args) throws IOException {
		Map<String, Object>map = new HashMap<>();
		map.put("prop1","텍스트");
		map.put("prop2",32);
		map.put("prop3",true);
		map.put("prop4",null);
		map.put("prop5",new String[] {"val1","val2"});
		map.put("prop6",Collections.singletonMap("innerPorp", "내부 맵데이터"));
		
		ObjectMapper mapper = new ObjectMapper();
		// JsonProcessingException 의 최상위 부모 는 IOException이다
		String json=mapper.writeValueAsString(map);
		System.out.println(json);
		
		Map<String, Object>destMap = mapper.readValue(json, Map.class);
		System.out.println(destMap);
	}
}
