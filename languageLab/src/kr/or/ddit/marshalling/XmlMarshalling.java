package kr.or.ddit.marshalling;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.FactorialVO;

public class XmlMarshalling {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, Exception {
		FactorialVO target = new FactorialVO(10);
		JAXBContext context = JAXBContext.newInstance(FactorialVO.class);
		Unmarshaller unmsler = context.createUnmarshaller();
		try(
		InputStream is = XmlMarshallingExample.class.getResourceAsStream("factorial.xml");
				){
		FactorialVO resVo = (FactorialVO) unmsler.unmarshal(is);
		System.out.println(resVo);
		}
//		Marshaller msler = context.createMarshaller();
//		msler.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
//		msler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		msler.marshal(target, System.out);

	}
	public static void marshallingTOJson(FactorialVO target) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(System.out, target);
		try(
		InputStream src = XmlMarshallingExample.class.getResourceAsStream("factorial.json");
				){
			FactorialVO vo = mapper.readValue(src, FactorialVO.class);
			System.out.println(vo);
		}
	}
}
