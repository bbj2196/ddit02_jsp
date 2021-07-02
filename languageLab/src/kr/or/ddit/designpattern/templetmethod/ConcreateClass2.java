package kr.or.ddit.designpattern.templetmethod;

public class ConcreateClass2 extends TemplateClass {

	@Override
	protected void secondStep() {
		System.out.println("두번째 단계 - 또다른 작업수행");
	}

}
