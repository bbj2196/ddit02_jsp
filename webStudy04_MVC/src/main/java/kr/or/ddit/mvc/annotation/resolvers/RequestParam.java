package kr.or.ddit.mvc.annotation.resolvers;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) // 어느시점에 어노테이션이 활성화 될것인지? 어노테이션을 사용할 메서드,클래스등 보다 먼저 VM에 올려야함
@Target(PARAMETER) //  어느곳에 어노테이션을 사용할것인지? 클래스,메서드,변수등...
public @interface RequestParam {

	String value(); // default 값이 없으면 어노테이션을 사용할때 반드시 써줘야한다
	boolean required() default true;
	String defaultValue() default "";
}
