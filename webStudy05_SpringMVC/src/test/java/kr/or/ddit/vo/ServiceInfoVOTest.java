package kr.or.ddit.vo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

public class ServiceInfoVOTest {

	@Test
	public void test() throws JAXBException {
		MenuVO menu1 = new MenuVO();
		menu1.setCode("STANDARD");
		menu1.setLink("/02/standard.jsp");
		menu1.setText("표준");
		MenuVO menu2 = new MenuVO();
		menu2.setCode("STANDARD");
		menu2.setLink("/02/standard.jsp");
		menu2.setText("표준");
		
		List<MenuVO>menuList = new ArrayList<>();
		menuList.add(menu1);
		menuList.add(menu2);
		ServiceInfoVO vo =new ServiceInfoVO();
		vo.setMenuList(menuList);
		JAXBContext context = JAXBContext.newInstance(ServiceInfoVO.class);
		Marshaller marshal=context.createMarshaller();
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshal.marshal(vo, System.out);
	}

}
