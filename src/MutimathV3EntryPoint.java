import core.assistant.Terminate;
import core.environment.event.StartExcecutionEvent;
import core.environment.event.StartExcecutionEventThrower;
import core.environment.variable.StateVariable;
import core.launch.ProgramInstance;
import core.maintenanceTreads.MaintenanceTread;
import gui.components.Spinner;

public class MutimathV3EntryPoint implements StartExcecutionEvent
{
	private StartExcecutionEvent getEvent()
	{
		return this;
	}
	
	public static void main(String[] args)
	{
		StartExcecutionEventThrower.bePreEventAction(new MutimathV3EntryPoint().getEvent());
		StartExcecutionEventThrower.throwEvent();
		try
		{
			new ProgramInstance();
		}
		catch (Exception e) 
		{
			if(StateVariable.Ready)
			{
				throw e;
			}
			else 
			{
				e.printStackTrace();
				new Terminate();
			}
		}
	}

	@Override
	public void ThrowStartExcecutionEvent()
	{	
		new MaintenanceTread();
		new Spinner();
	}

}
