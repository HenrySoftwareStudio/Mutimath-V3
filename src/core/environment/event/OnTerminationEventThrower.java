package core.environment.event;

import java.util.ArrayList;
import java.util.List;

public class OnTerminationEventThrower
{
	private static OnTerminationEvent PreEventAction;
	private static OnTerminationEvent PostEventAction;
	private static List<OnTerminationEvent> onTerminationEventsList=new ArrayList<OnTerminationEvent>();
	
	public static void bePreEventAction(OnTerminationEvent e)
	{
		PreEventAction=e;
	}
	
	public static void bePostEventAction(OnTerminationEvent e)
	{
		PostEventAction=e;
	}
	
	public static void addListener(OnTerminationEvent e)
	{
		onTerminationEventsList.add(e);
	}

	public static synchronized void throwEvent()
	{
		System.out.println(OnTerminationEventThrower.class);
		if(PreEventAction != null)
		{
			PreEventAction.throwOnTerminationEvent();
		}
		for (int i = 0; i < onTerminationEventsList.size(); i++)
		{
			onTerminationEventsList.get(i).throwOnTerminationEvent();
		}
		if(PostEventAction != null)
		{
			PostEventAction.throwOnTerminationEvent();
		}
	}
}
