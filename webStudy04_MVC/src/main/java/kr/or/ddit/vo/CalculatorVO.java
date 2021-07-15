package kr.or.ddit.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class CalculatorVO implements Serializable{
	private int left;
	private int right;
	private OperVO oper;
	public CalculatorVO(int left, int right, OperVO oper) {
		super();
		this.left = left;
		this.right = right;
		this.oper = oper;
	}
	public CalculatorVO() {
	}
	
	public double getResult() {
		double result = 0;
		switch (oper.getText()) {
		case "PLUS":
			result=left+right;
			break;
		case "MINUS":
			result = left - right;
			break;
		case "MUTIPLY":
			result = left * right;
			break;
		case "MOD":
			result = left % right;
			break;
		case "DIVISION":
			result = (double)left / right;
			break;
		}
		return result;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public String getExpression() {
		String fomat = "%d %s %d = ";
		
		double result = getResult();
		
		return String.format(result == (int)result ?fomat+"%.0f":fomat+"%.2f", left,oper.getSign(),right,result);
	}
	public OperVO getOper() {
		return oper;
	}
	public void setOper(OperVO oper) {
		this.oper = oper;
	}
	

	
	
	
}
