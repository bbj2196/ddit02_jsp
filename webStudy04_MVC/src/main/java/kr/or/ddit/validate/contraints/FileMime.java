package kr.or.ddit.validate.contraints;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD,METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=FileMimeVlidator.class)
public @interface FileMime {
	String message() default ("kr.or.ddit.validate.contraints.FileMime.message");
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	
	
	String mime();
}
