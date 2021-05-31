package core.types.RuntimeMaskOfExceptions;

public class RuntimeClassNotFoundException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3709405487242512326L;

	public RuntimeClassNotFoundException()
	{
	}

	public RuntimeClassNotFoundException(String message)
	{
		super(message);
	}

	public RuntimeClassNotFoundException(Throwable cause)
	{
		super(cause);
	}

	public RuntimeClassNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RuntimeClassNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
