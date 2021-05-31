package core.maintenanceTreads;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import javax.swing.JOptionPane;

import core.assistant.StaticAssistants;
import core.assistant.Terminate;
import core.environment.event.EndExcecutionEvent;
import core.environment.event.EndExcecutionEventThrower;
import core.environment.event.OnCrashEventThrower;
import core.environment.variable.FixedVariables;
import core.environment.variable.StateVariable;

@SuppressWarnings("unused")
public class MaintenanceTread implements EndExcecutionEvent,Runnable,UncaughtExceptionHandler
{
	private Thread self;
	private static volatile transient boolean Runstate=true;
	public MaintenanceTread()
	{
		self=new Thread(this, "Maintenance Thread");
		EndExcecutionEventThrower.bePostEventAction(this);
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
			/*ThreadMXBean info=ManagementFactory.getThreadMXBean();
			for (int i = 0; i < info.getAllThreadIds().length; i++)
			{
				ThreadInfo infos=info.getThreadInfo(info.getAllThreadIds()[i]);
				System.out.println(infos.getThreadName());
			}
			System.out.println("-----");*/
			Thread.setDefaultUncaughtExceptionHandler(this);
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

	@Override
	public void uncaughtException(Thread t, Throwable e)
	{
		boolean thisThrowableFatality=StaticAssistants.isFatal(e);
		JOptionPane.showInternalMessageDialog(null, (e.getClass().getName()+"has happed at thread:"+
		t+"\n Fatality: "+((thisThrowableFatality)? "Yes":"No")),"EXCEPTION",ERROR_MESSAGE);
		if(StateVariable.testMode)
		{
			e.printStackTrace();
		}
		if(thisThrowableFatality)
		{
			OnCrashEventThrower.throwEvent();
			EndExcecutionEventThrower.throwEvent();
			new Terminate();
		}		
	}
}
