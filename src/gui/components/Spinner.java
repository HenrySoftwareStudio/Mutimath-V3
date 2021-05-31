package gui.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import core.assistant.Terminate;
import core.environment.event.EndExcecutionEventThrower;
import core.environment.event.InitCompleteEvent;
import core.environment.event.InitCompleteEventThrower;
import core.environment.event.OnTerminationEvent;
import core.environment.event.OnTerminationEventThrower;

public final class Spinner extends AbstractAction implements InitCompleteEvent,OnTerminationEvent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1073466243271343717L;
	private JFrame frame;
	public Spinner()
	{ 
		InitCompleteEventThrower.addListener(this);
		ImageIcon icon=new ImageIcon("resources\\LoadingIcon.gif");
		frame=new JFrame();
		JLabel spinner=new JLabel(icon);
		JRootPane frameRootPane=frame.getRootPane();
		frameRootPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Esc");
		frameRootPane.getActionMap().put("Esc", this);
		frame.setLayout(new GridLayout(1,1));
		frame.setUndecorated(true);
		frame.setSize(200,200);
		frame.setLocationRelativeTo(null);
		frame.add(spinner);
		frame.setVisible(true);
	}

	@Override
	public void throwInitCompleteEvent()
	{
		frame.dispose();		
		enabled=false;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		OnTerminationEventThrower.throwEvent();
		EndExcecutionEventThrower.throwEvent();
	}

	@Override
	public void throwOnTerminationEvent()
	{
		frame.dispose();
		new Terminate();
	}
}