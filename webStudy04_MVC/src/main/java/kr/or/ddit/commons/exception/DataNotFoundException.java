package kr.or.ddit.commons.exception;

/**
 * @author PC-13
 *
 */
public class DataNotFoundException extends RuntimeException{

	public DataNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataNotFoundException(String message) {
		super(String.format("%s PK에 해당하는 데이터가 없음 "+message));
		// TODO Auto-generated constructor stub
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
