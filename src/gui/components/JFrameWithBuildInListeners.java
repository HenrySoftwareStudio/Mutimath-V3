package gui.components;

import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import core.assistant.Terminate;
import core.environment.event.EndExcecutionEvent;
import core.environment.event.EndExcecutionEventThrower;
import core.environment.event.InitCompleteEvent;
import core.environment.event.InitCompleteEventThrower;
import core.environment.event.OnTerminationEvent;
import core.environment.event.OnTerminationEventThrower;
import core.environment.variable.StateVariable;
import core.maintenanceTreads.ChkFrameAliveThread;
import core.types.RuntimeMaskOfExceptions.RuntimeClassNotFoundException;
import core.types.RuntimeMaskOfExceptions.RuntimeIllegalAccessException;
import core.types.RuntimeMaskOfExceptions.RuntimeInstantiationException;
import core.types.RuntimeMaskOfExceptions.RuntimeUnsupportedLookAndFeelException;

public class JFrameWithBuildInListeners extends JFrame
		implements WindowListener, ComponentListener, EndExcecutionEvent, InitCompleteEvent, OnTerminationEvent
{
	private Thread FrameStartup;
	private JFrameWithBuildInListeners passThrough;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2814140714210690328L;
	
	public JFrameWithBuildInListeners()
	{
		super.setName(getName());
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		passThrough=this;
		FrameStartup=new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				new ChkFrameAliveThread();
				try
				{
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				}
				catch (ClassNotFoundException e)
				{
					throw new RuntimeClassNotFoundException(e.getMessage(), e.getCause());
				}
				catch (InstantiationException e)
				{
					throw new RuntimeInstantiationException(e.getMessage(), e.getCause());
				}
				catch (IllegalAccessException e)
				{
					throw new RuntimeIllegalAccessException(e.getMessage(), e.getCause());
				}
				catch (UnsupportedLookAndFeelException e)
				{
					throw new RuntimeUnsupportedLookAndFeelException(e.getMessage(), e.getCause());
				}
				JTabbedPane jtp=new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
				JScrollPane jsp=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				JPanel jpanel=new JPanel();
				JPanel warper=new JPanel(new GridLayout(1,1));
				passThrough.addComponentListener((ComponentListener) passThrough);
				passThrough.addWindowListener((WindowListener) passThrough);
				for (int i = 0; i < StateVariable.functions.length; i++)
				{
					jpanel.add(StateVariable.functions[i].generateButton());
				}
				jpanel.setLayout(new BoxLayout(jpanel,BoxLayout.PAGE_AXIS));
				jsp.setViewportView(jpanel);
				warper.add(jsp);
				jtp.addTab("Main",jsp);
				passThrough.add(jtp);
				passThrough.setVisible(true);
				InitCompleteEventThrower.throwEvent();
			}
		}, "FrameStartUp Thread");
		InitCompleteEventThrower.addListener(this);
		EndExcecutionEventThrower.addListener(this);
		OnTerminationEventThrower.addListener(this);
		FrameStartup.start();
		StateVariable.uiObj=this;
	}
	
	@Override
	public void throwOnTerminationEvent()
	{
		this.dispose();	
	}
	
	@Override
	public void throwInitCompleteEvent()
	{

	}

	@Override
	public void throwEndExcecutionEvent()
	{		
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
				
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
		
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
				
	}

	@Override
	public void componentHidden(ComponentEvent e)
	{
				
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		OnTerminationEventThrower.throwEvent();	
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		EndExcecutionEventThrower.throwEvent();
		new Terminate();
	}

	@Override
	public void windowIconified(WindowEvent e){}

	@Override
	public void windowDeiconified(WindowEvent e){}

	@Override
	public void windowActivated(WindowEvent e){}

	@Override
	public void windowDeactivated(WindowEvent e){}


}
