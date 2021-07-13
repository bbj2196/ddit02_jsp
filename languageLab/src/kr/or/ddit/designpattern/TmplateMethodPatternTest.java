package kr.or.ddit.designpattern;

import java.util.Arrays;
import java.util.List;

import kr.or.ddit.designpattern.templetmethod.ConcreateClass1;
import kr.or.ddit.designpattern.templetmethod.ConcreateClass2;
import kr.or.ddit.designpattern.templetmethod.TemplateClass;

public class TmplateMethodPatternTest {

	public static void main(String[] args) {
		List<TemplateClass>list = Arrays.asList(new ConcreateClass1(),new ConcreateClass2());
		for (TemplateClass tmp : list) {
			tmp.template();
		}
	}
}
