package com.jossv.framework.exception;

/**
 * Created by yangjiankang on 15/11/18.
 */
public class BaseException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1845007684503296398L;

	public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
