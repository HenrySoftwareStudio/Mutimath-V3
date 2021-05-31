package core.environment.event;

import java.util.ArrayList;
import java.util.List;

public class OnCrashEventThrower
{
	private static OnCrashEvent PreEventAction;
	private static OnCrashEvent PostEventAction;
	private static List<OnCrashEvent> OnCrashEventList=new ArrayList<OnCrashEvent>();
	
	public static void bePreEventAction(OnCrashEvent e)
	{
		PreEventAction=e;
	}
	
	public static void bePostEventAction(OnCrashEvent e)
	{
		PostEventAction=e;
	}
	
	public static void addListener(OnCrashEvent e)
	{
		OnCrashEventList.add(e);
	}

	public static synchronized void throwEvent()
	{
		System.out.println(OnCrashEventThrower.class);
		if(PreEventAction != null)
		{
			PreEventAction.throwOnCrashEvent();
		}
		for (int i = 0; i < OnCrashEventList.size(); i++)
		{
			OnCrashEventList.get(i).throwOnCrashEvent();
		}
		if(PostEventAction != null)
		{
			PostEventAction.throwOnCrashEvent();
		}
	}
}
