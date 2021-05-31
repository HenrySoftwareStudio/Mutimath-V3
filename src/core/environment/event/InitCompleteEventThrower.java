package core.environment.event;

import java.util.ArrayList;
import java.util.List;

public class InitCompleteEventThrower
{
	private static InitCompleteEvent PreEventAction;
	private static InitCompleteEvent PostEventAction;
	private static List<InitCompleteEvent> InitCompleteEventsList=new ArrayList<InitCompleteEvent>();
	
	public static void bePreEventAction(InitCompleteEvent e)
	{
		PreEventAction=e;
	}
	
	public static void bePostEventAction(InitCompleteEvent e)
	{
		PostEventAction=e;
	}
	
	public static void addListener(InitCompleteEvent e)
	{
		InitCompleteEventsList.add(e);
	}

	public static synchronized void throwEvent()
	{
		System.out.println(InitCompleteEventThrower.class);
		if(PreEventAction != null)
		{
			PreEventAction.throwInitCompleteEvent();
		}
		for (int i = 0; i < InitCompleteEventsList.size(); i++)
		{
			InitCompleteEventsList.get(i).throwInitCompleteEvent();
		}
		if(PostEventAction != null)
		{
			PostEventAction.throwInitCompleteEvent();
		}
	}
}
