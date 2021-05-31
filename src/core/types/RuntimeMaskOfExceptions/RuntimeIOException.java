package core.types.RuntimeMaskOfExceptions;

public class RuntimeIOException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4714240060537622323L;

	public RuntimeIOException()
	{
	}

	public RuntimeIOException(String message)
	{
		super(message);
	}

	public RuntimeIOException(Throwable cause)
	{
		super(cause);
	}

	public RuntimeIOException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RuntimeIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
