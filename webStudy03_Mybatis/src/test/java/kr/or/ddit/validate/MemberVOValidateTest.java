package kr.or.ddit.validate;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

public class MemberVOValidateTest {

	private static Validator validator;
	private static final Logger logger = LoggerFactory.getLogger(MemberVOValidateTest.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void test() {
		MemberVO target = MemberVO.builder().build();
		
		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target);
		for (ConstraintViolation<MemberVO> singleViolation : violations) {
			String key = singleViolation.getPropertyPath().toString();
			String message = singleViolation.getMessage();
			logger.info("{} : {}",key,message);
		}
	}

}
