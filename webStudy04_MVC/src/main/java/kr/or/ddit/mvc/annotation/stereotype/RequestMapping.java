package kr.or.ddit.mvc.annotation.stereotype;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import kr.or.ddit.mvc.annotation.RequestMethod;

@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
String value();
RequestMethod method() default RequestMethod.GET;
}
