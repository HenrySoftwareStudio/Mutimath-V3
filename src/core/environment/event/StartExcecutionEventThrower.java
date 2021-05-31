package core.environment.event;

import java.util.ArrayList;
import java.util.List;

public class StartExcecutionEventThrower
{
	private static List<StartExcecutionEvent> startExcecutionEventsList=new ArrayList<StartExcecutionEvent>();
	private static StartExcecutionEvent preEventAction;
	private static StartExcecutionEvent postEventAction;
	
	public static void bePreEventAction(StartExcecutionEvent e)
	{
		preEventAction=e;
	}
	
	public static void bePostEventAction(StartExcecutionEvent e)
	{
		postEventAction=e;
	}
	
	public static void addListener(StartExcecutionEvent e)
	{
		startExcecutionEventsList.add((StartExcecutionEvent) e);
	}
	
	public static synchronized void throwEvent()
	{
		System.out.println(StartExcecutionEventThrower.class);
		if (preEventAction != null)
		{
			preEventAction.ThrowStartExcecutionEvent();
		}
		for (int i = 0; i < startExcecutionEventsList.size(); i++)
		{
			startExcecutionEventsList.get(i).ThrowStartExcecutionEvent();
		}
		if (postEventAction != null)
		{
			postEventAction.ThrowStartExcecutionEvent();
		}
	}

}
