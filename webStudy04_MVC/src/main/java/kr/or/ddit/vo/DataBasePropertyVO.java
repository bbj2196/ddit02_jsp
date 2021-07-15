package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="propertyName")
@ToString
public class DataBasePropertyVO {

	private String propertyName;
	private String propertyValue; 
	private String description;
	
}
