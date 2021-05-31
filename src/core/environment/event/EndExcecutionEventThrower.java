package core.environment.event;

import java.util.ArrayList;
import java.util.List;

public class EndExcecutionEventThrower
{
	private static EndExcecutionEvent PreEventAction;
	private static EndExcecutionEvent PostEventAction;
	private static List<EndExcecutionEvent> endExcecutionEventsList=new ArrayList<EndExcecutionEvent>();
	
	public static void bePreEventAction(EndExcecutionEvent e)
	{
		PreEventAction=e;
	}
	
	public static void bePostEventAction(EndExcecutionEvent e)
	{
		PostEventAction=e;
	}
	
	public static void addListener(EndExcecutionEvent e)
	{
		endExcecutionEventsList.add(e);
	}

	public static synchronized void throwEvent()
	{
		System.out.println(EndExcecutionEventThrower.class);
		if(PreEventAction != null)
		{
			PreEventAction.throwEndExcecutionEvent();
		}
		for (int i = 0; i < endExcecutionEventsList.size(); i++)
		{
			endExcecutionEventsList.get(i).throwEndExcecutionEvent();
			
		}
		if(PostEventAction != null)
		{
			PostEventAction.throwEndExcecutionEvent();
		}
	}
}
