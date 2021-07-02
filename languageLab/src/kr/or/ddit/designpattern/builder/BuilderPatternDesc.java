package kr.or.ddit.designpattern.builder;

/**
 * Builder Pattern 적용단계
 * 1. 모든 프로퍼티를 받는 생성자 정의(private)
 * 2. inner class 형태로 Builder class정의
 * 	1) 빌드의 대상인 클래스와 동일한 프로퍼티 정의
 * 	2) 프로퍼티이름과 같은메서드 정의(setter) : return this
 * 	3) build 메소드내에서 빌드 대상인 인스턴스 생성.
 * 3. Builder 인스턴스를 반환하는 getBuilder 정의 
 * @author PC-13
 *
 */
public class BuilderPatternDesc {

	public static void main(String[] args) {
		new BuilderPatternDesc().start();
	}
	
	private void start() {

		TestVO vo = new TestVO();
		vo.setProp1("프로퍼티1");
		vo.setProp2("프로퍼티2");
		
		TestVO vo2 = new TestVO("프로퍼티1", "프로퍼티2");
		
		TestVO.getBuilder()
							.prop1("프로퍼티1")
							.prop2("프로퍼티2")
							.build();
	}

	public static class TestVO{
		public static TestVOBuilder getBuilder() {
			return new TestVOBuilder();
		}
		public static class TestVOBuilder{
			private String prop1;
			private String prop2;
			
			public TestVOBuilder prop1(String prop1) {
				this.prop1=prop1;
				return this;
			}
			public TestVOBuilder prop2(String prop2) {
				this.prop2=prop2;
				return this;
			}
			public TestVO build() {
				return new TestVO(prop1,prop2);
			}
		}
		
		private String prop1;
		private String prop2;
		
		public TestVO() {
			// TODO Auto-generated constructor stub
		}
		public TestVO(String prop1, String prop2) {
			super();
			this.prop1 = prop1;
			this.prop2 = prop2;
		}
		public String getProp1() {
			return prop1;
		}
		public void setProp1(String prop1) {
			this.prop1 = prop1;
		}
		public String getProp2() {
			return prop2;
		}
		public void setProp2(String prop2) {
			this.prop2 = prop2;
		}
		
	}
}
