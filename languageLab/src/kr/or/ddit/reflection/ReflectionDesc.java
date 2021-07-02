package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {
	public static void main(String[] args){
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		Class type = obj.getClass();
		System.out.println(type);
		
		// 전역변수는 모두 필드
		// 자바 규약에따라만들면 프로퍼티
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			try {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
				Method getter = pd.getReadMethod();
				Method setter = pd.getWriteMethod();
				System.out.printf("%s = %s\n",pd.getName(),getter.invoke(obj));
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void reflect1(Object obj) {
		Class type = obj.getClass();
		System.out.println(type);
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
//			field.setAccessible(true);
			String fieldName = field.getName();
			String getterName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			
			try {
				Method getter = type.getMethod(getterName);
				System.out.printf("%s = %s\n",fieldName,getter.invoke(obj));
//				System.out.printf("%s = %s\n",fieldName,field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(fieldName+": 접근거부");
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
