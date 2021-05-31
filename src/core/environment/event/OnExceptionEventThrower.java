package core.environment.event;

import java.util.ArrayList;
import java.util.List;

public class OnExceptionEventThrower
{
	private static OnExceptionEvent PreEventAction;
	private static OnExceptionEvent PostEventAction;
	private static List<OnExceptionEvent> endExcecutionEventsList=new ArrayList<OnExceptionEvent>();
	
	public static void bePreEventAction(OnExceptionEvent e)
	{
		PreEventAction=e;
	}
	
	public static void bePostEventAction(OnExceptionEvent e)
	{
		PostEventAction=e;
	}
	
	public static void addListener(OnExceptionEvent e)
	{
		endExcecutionEventsList.add(e);
	}

	public static synchronized void throwEvent()
	{
		System.out.println(EndExcecutionEventThrower.class);
		if(PreEventAction != null)
		{
			PreEventAction.throwOnExceptionEvent();
		}
		for (int i = 0; i < endExcecutionEventsList.size(); i++)
		{
			endExcecutionEventsList.get(i).throwOnExceptionEvent();
			
		}
		if(PostEventAction != null)
		{
			PostEventAction.throwOnExceptionEvent();
		}
	}
}
