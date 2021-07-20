package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ExtendsSearchVO extends SearchVO{
	private String startDate;
	private String endDate;
	private String sortType;
}
