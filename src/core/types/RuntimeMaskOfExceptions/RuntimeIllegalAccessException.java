package core.types.RuntimeMaskOfExceptions;

public class RuntimeIllegalAccessException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6855129235847039373L;

	public RuntimeIllegalAccessException()
	{
	}

	public RuntimeIllegalAccessException(String message)
	{
		super(message);
	}

	public RuntimeIllegalAccessException(Throwable cause)
	{
		super(cause);
	}

	public RuntimeIllegalAccessException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RuntimeIllegalAccessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
