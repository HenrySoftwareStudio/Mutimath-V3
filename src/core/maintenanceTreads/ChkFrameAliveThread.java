package core.maintenanceTreads;

import static core.environment.variable.StateVariable.*;

import core.environment.event.EndExcecutionEvent;
import core.environment.event.EndExcecutionEventThrower;


public class ChkFrameAliveThread implements EndExcecutionEvent, Runnable
{
	private Thread self;
	private static volatile boolean Runstate=true;
	
	public ChkFrameAliveThread()
	{
		self=new Thread(this, "Maintenance Thread");
		EndExcecutionEventThrower.addListener(this);
		self.start();
	}

	@Override
	public void throwEndExcecutionEvent()
	{
		Runstate=false;
	}
	
	@Override
	public void run()
	{
		while(Runstate)
		{
			FrameVisble=uiObj.isVisible();
			FrameAlive=uiObj.isEnabled();
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
