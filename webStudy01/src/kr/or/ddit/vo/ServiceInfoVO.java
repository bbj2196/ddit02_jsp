package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="serviceInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceInfoVO implements Serializable{

	// 리스트, 맵등 다른 것을 여러개 참고중일때
	@XmlElementWrapper // 안쓰면 menuList가 안나옴
	@XmlElementRefs(@XmlElementRef) // 안쓰면 내부 menu태그가 안나옴
	private List<MenuVO> menuList;

	public List<MenuVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuVO> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "ServiceInfoVO [menuList=" + menuList.size() + "(cnt)]";
	}
	
	

}
