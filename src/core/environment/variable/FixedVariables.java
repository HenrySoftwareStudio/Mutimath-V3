package core.environment.variable;

import java.awt.AWTError;
import java.awt.HeadlessException;

public class FixedVariables
{
	@SuppressWarnings("rawtypes")
	public static final transient Class[] FATAL_EXCEPTION_LIST= {HeadlessException.class,AWTError.class};
}
