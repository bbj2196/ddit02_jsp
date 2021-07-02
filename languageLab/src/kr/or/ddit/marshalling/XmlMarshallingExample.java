package kr.or.ddit.marshalling;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.FactorialVO;

public class XmlMarshallingExample {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
//		FactorialVO target = new FactorialVO(10);
		
		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(System.out, target);
		try(
		InputStream src = XmlMarshalling.class.getResourceAsStream("factorial.json");
				){
			FactorialVO vo = (FactorialVO)mapper.readValue(src, FactorialVO.class);
			System.out.println(vo);
		}
	}
}
