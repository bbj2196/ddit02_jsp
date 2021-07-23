package kr.or.ddit.validate;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;

public class ProdVOValidateTest {

	private Logger logger = LoggerFactory.getLogger(ProdVOValidateTest.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		ProdVO prod = ProdVO.builder()
//							.prodName("asd")
							.prodSale(123)
							.prodLgu("1231")
							.prodCost(Integer.MAX_VALUE)
							.build();
		Map<String, List<String>> errors = new HashMap<>();
		ValidatorUtils<ProdVO> validator = new ValidatorUtils<ProdVO>();
		boolean valid = validator.validate(prod, errors , InsertGroup.class);
		assertFalse(valid);
		if(!valid) {
			for (Entry<String, List<String>> entry : errors.entrySet()) {
				String key = entry.getKey();
				 List<String> msg = entry.getValue();
				logger.info("{} : {}",key,msg);
			}
		}
	}

}
