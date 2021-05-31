package core.types.RuntimeMaskOfExceptions;

public class RuntimeUnsupportedLookAndFeelException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690946753446299765L;

	public RuntimeUnsupportedLookAndFeelException()
	{
	}

	public RuntimeUnsupportedLookAndFeelException(String message)
	{
		super(message);
	}

	public RuntimeUnsupportedLookAndFeelException(Throwable cause)
	{
		super(cause);
	}

	public RuntimeUnsupportedLookAndFeelException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RuntimeUnsupportedLookAndFeelException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
