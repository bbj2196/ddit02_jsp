package kr.or.ddit.designpattern.adapter;

public class OtherConcreate implements Target {

	@Override
	public void request() {
		// TODO Auto-generated method stub
		System.out.println(getClass().getSimpleName()+"에서 처리함");
	}

}
