package kr.or.ddit.designpattern.templetmethod;

public abstract class TemplateClass {

	public final void template() {
		firstStep();
		secondStep();
		thirdStep();
	}
	
	private void firstStep() {
		System.out.println("첫번째 단계");
	}
	
	protected abstract void secondStep();
	
	
	private void thirdStep() {
		System.out.println("세번째 단계");
	}
}
