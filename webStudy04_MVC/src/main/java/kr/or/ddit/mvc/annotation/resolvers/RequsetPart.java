package kr.or.ddit.mvc.annotation.resolvers;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface RequsetPart {
	String value();
	boolean required() default true;
}
