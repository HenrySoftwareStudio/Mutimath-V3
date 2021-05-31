package core.launch;

import core.maintenanceTreads.SetupTread;
import gui.components.JFrameWithBuildInListeners;

public class ProgramInstance
{
	public ProgramInstance()
	{
		System.setErr(System.out);
		new SetupTread();
		new JFrameWithBuildInListeners();
	}
}
