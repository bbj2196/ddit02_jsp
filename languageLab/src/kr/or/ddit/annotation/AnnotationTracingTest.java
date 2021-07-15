package kr.or.ddit.annotation;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.InjectableValues;

import kr.or.ddit.annotation.base.B;
import kr.or.ddit.utils.ReflectionUtils;

public class AnnotationTracingTest {

	public static void main(String[] args) {
		
		Map<Class<?>, Component> clzMap = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Component.class, "kr.or.ddit.annotation.base");
		Map<String,Object>pojoContainer = new LinkedHashMap<>();
		clzMap.forEach((clz,component)->{
			System.out.printf("%s : %s\n",clz,component);
			try {
				Object instance = clz.newInstance();
				String instName = component.value();
				if(instName.isEmpty()) {
					String simpleName = clz.getSimpleName();
					instName=StringUtils.uncapitalize(simpleName);
				}
				pojoContainer.put(instName,instance);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		System.out.println(pojoContainer);
		pojoContainer.forEach((name,instance)->{
			Class<?> clz = instance.getClass();
			Map<Field, Resource> fldMap = ReflectionUtils.getFieldsWithAnnotationWithClass(clz, Resource.class);
			fldMap.forEach((field,resource)->{
				String instName = resource.name();
				Object injectTarget = pojoContainer.get(instName);
				field.setAccessible(true);
				try {
					field.set(instance,injectTarget);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		});
		B b=(B) pojoContainer.get("BBB");
		b.logicB();
	}
}
