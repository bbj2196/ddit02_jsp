package kr.or.ddit.vo;

public class CalculVO {

	private String oper;
	private int left;
	private int right;
	private double result;
	
	
	public CalculVO(String oper, int left, int right) {
		super();
		this.oper = oper;
		this.left = left;
		this.right = right;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
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
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	
	
}
