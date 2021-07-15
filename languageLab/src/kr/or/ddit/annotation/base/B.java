package kr.or.ddit.annotation.base;

import javax.annotation.Resource;

import kr.or.ddit.annotation.Component;

@Component("BBB")
public class B {

	@Resource(name="a")
	A a;
	public void logicB() {
		System.out.println("start logic B");
	}
}
