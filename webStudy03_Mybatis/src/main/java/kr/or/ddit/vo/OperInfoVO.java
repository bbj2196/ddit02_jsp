package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="operInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperInfoVO implements Serializable {

	@XmlElementWrapper // 안쓰면 menuList가 안나옴
	@XmlElementRefs(@XmlElementRef) // 안쓰면 내부 menu태그가 안나옴
	private List<OperVO>operList;

	public List<OperVO> getOperlist() {
		return operList;
	}

	public void setOperlist(List<OperVO> operlist) {
		this.operList = operlist;
	}

	@Override
	public String toString() {
		return "OperListVO [operlist=" + operList + "]";
	}
	
	
}
