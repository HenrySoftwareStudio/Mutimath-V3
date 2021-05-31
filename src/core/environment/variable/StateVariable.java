package core.environment.variable;

import core.types.types.Function;
import gui.components.JFrameWithBuildInListeners;

public class StateVariable
{
	public static volatile transient boolean testMode=true;
	public static volatile transient boolean Ready=false;
	public static volatile transient boolean FrameVisble=false;
	public static volatile transient boolean FrameAlive=false;
	public static volatile transient JFrameWithBuildInListeners uiObj;
	public static volatile transient Function[] functions;
}
