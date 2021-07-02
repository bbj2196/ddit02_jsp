package kr.or.ddit.designpattern.adapter;

public class Adapter implements Target {

	private Adaptee adaptee;
	@Override
	public void request() {
		adaptee.specificRequest();
	}
	public Adapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}
	

}
