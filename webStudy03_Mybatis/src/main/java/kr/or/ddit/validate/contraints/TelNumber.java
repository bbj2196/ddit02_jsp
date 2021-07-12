package kr.or.ddit.validate.contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import  static java.lang.annotation.RetentionPolicy.*;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;

/**
 * 전화번호 형식 확인을 위한 검증 어노테이션
 * 
 *
 */
// value값이 하나인 singleValue 어노테이션은 value를 생략가능하다
// singleValue : 코드 어시스트시 속성이 하나 인것, 기본값 유무 (앞에 아이콘이 초록 동그라미)
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy= TelNumberValidator.class)
public @interface TelNumber {
	String message() default "{kr.or.ddit.validate.contraints.TelNumber.message}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	
	String regexp() default "\\d{2,4}-\\d{3,4}-\\d{4}";
	String placeholder() default "000-1234-1234";
	
}
