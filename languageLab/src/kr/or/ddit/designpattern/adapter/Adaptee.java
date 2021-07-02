package kr.or.ddit.designpattern.adapter;

public class Adaptee {
	public void specificRequest() {
		System.out.println(getClass().getSimpleName()+"에서 처리됨");
	}
}
