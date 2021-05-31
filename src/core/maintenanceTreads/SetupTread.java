package core.maintenanceTreads;

import java.io.FileNotFoundException;

import core.environment.event.InitCompleteEvent;
import core.environment.event.InitCompleteEventThrower;
import core.environment.variable.VariableSetup;

public class SetupTread implements InitCompleteEvent
{
	private Thread selfOnStart;
	private Runnable selfOnStartRun=new Runnable()
	{
		
		@Override
		public void run()
		{
			try
			{
				VariableSetup.onStartUp();
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}			
		}
	};
	private Thread selfOnInitComplete;
	private Runnable selfOnInitCompleteRun=new Runnable()
	{
		
		@Override
		public void run()
		{
			VariableSetup.onInitComplete();			
		}
	};
	
	
	public SetupTread()
	{
		InitCompleteEventThrower.addListener(this);
		this.selfOnStart=new Thread(selfOnStartRun, "Start up thread");
		this.selfOnInitComplete=new Thread(selfOnInitCompleteRun, "On Complete thread");
		this.selfOnStart.run();
	}
	@Override
	public void throwInitCompleteEvent()
	{
		this.selfOnInitComplete.run();
	}
}
