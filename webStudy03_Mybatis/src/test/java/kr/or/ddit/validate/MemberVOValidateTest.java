package kr.or.ddit.validate;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.InsertGroup;
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
//		MemberVO target = MemberVO.builder().build();
//		
//		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target);
//		for (ConstraintViolation<MemberVO> singleViolation : violations) {
//			String key = singleViolation.getPropertyPath().toString();
//			String message = singleViolation.getMessage();
//			logger.info("{} : {}",key,message);
//		}
	}

	@Test
	public void testValidatorUtils() {
		MemberVO target = MemberVO.builder()
								.memHp("123-4567-4567")
								.memComtel("123-123-4567")
								.memHometel("123-4567-75629999999999999999")
								.build();
		Map<String, List<String>> errors = new HashMap<>();
		ValidatorUtils<MemberVO> utils = new ValidatorUtils<>();
//		각 그룹에 맞춰서 검증 하는가?
		boolean valid=utils.validate(target, errors,InsertGroup.class);
//		boolean valid=utils.validate(target, errors,UpdateGroup.class);
//		boolean valid=utils.validate(target, errors,DeleteGroup.class);
		
		if(!valid) {
			for (Entry<String, List<String>> entry : errors.entrySet()) {
				logger.info("{} : {}",entry.getKey(),entry.getValue());
			}
		}
			
	}
}
