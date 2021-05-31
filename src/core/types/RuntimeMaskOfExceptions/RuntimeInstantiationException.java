package core.types.RuntimeMaskOfExceptions;

public class RuntimeInstantiationException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7314561128863569143L;

	public RuntimeInstantiationException()
	{
	}

	public RuntimeInstantiationException(String message)
	{
		super(message);
	}

	public RuntimeInstantiationException(Throwable cause)
	{
		super(cause);
	}

	public RuntimeInstantiationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RuntimeInstantiationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
