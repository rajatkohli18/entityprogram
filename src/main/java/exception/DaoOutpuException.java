package exception;

public class DaoOutpuException extends Exception {

	public DaoOutpuException() {
		
	}

	public DaoOutpuException(String message) {
		super(message);
		
	}

	public DaoOutpuException(Throwable cause) {
		super(cause);
	
	}

	public DaoOutpuException(String message, Throwable cause) {
		super(message, cause);

	}

	public DaoOutpuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}

