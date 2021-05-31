package core.assistant;

import static core.environment.variable.FixedVariables.FATAL_EXCEPTION_LIST;

import java.io.PrintWriter;
import java.io.StringWriter;;

public class StaticAssistants
{
	public static boolean isFatal(Throwable e)
	{
		for (int i = 0; i < FATAL_EXCEPTION_LIST.length; i++)
		{
			if(e.getClass()==FATAL_EXCEPTION_LIST[i])
			{
				return true;
			}
		}
		return false;
	}
	
	public static String exceptionToString(Throwable e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	public static int countCharRepeat(final String input,final char toBeCounted)
	{
		int toReturn=0;
		for (int i = 0; i < input.length(); i++)
		{
			char c=input.charAt(i);
			toReturn+=(c==toBeCounted)?1:0;
		}
		return toReturn;
	}
}
