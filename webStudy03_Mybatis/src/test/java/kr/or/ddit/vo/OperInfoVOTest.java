package kr.or.ddit.vo;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class OperInfoVOTest {

	@Test
	public void test() throws JAXBException {
		
		JAXBContext context = JAXBContext.newInstance(OperInfoVO.class);
		// 마샬링 테스트
//		OperInfoVO obj = new OperInfoVO();
//		List<OperVO> list = new ArrayList<>();
//		OperVO vo1 = new OperVO();
//		vo1.setSign("+");
//		vo1.setText("plus");
//		OperVO vo2 = new OperVO();
//		vo2.setSign("-");
//		vo2.setText("minus");
//		list.add(vo1);
//		list.add(vo2);
//		obj.setOperlist(list);
		
//		Marshaller marsal = context.createMarshaller();
//		marsal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		marsal.marshal(obj, System.out);
//		
		// 언마샬링 테스트
		Unmarshaller unmarsal = context.createUnmarshaller();
		InputStream is = getClass().getResourceAsStream("../operInfo.xml");
		OperInfoVO infoVO=(OperInfoVO) unmarsal.unmarshal(is);
		System.out.println(is);
		System.out.println(infoVO);
	}

}
