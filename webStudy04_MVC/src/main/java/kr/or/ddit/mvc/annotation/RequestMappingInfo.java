package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

public class RequestMappingInfo {

	private RequestMappingCondition mappingCondition;
	private Object commandHandlerMethod;
	private Method handlerMethod;
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandlerMethod,
			Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandlerMethod = commandHandlerMethod;
		this.handlerMethod = handlerMethod;
	}
	public RequestMappingCondition getMappingCondition() {
		return mappingCondition;
	}
	public Object getCommandHandlerMethod() {
		return commandHandlerMethod;
	}
	public Method getHandlerMethod() {
		return handlerMethod;
	}
	@Override
	public String toString() {
		return String.format("%s = %s.%s(%s)",mappingCondition,commandHandlerMethod.getClass().getName(),handlerMethod.getName(),"");
	}
	
	
}
