package core.types.types;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import core.types.RuntimeMaskOfExceptions.RuntimeIOException;

public class Function implements Closeable,ActionListener
{
	public static volatile ArrayList<Function> allFunctions=new ArrayList<>();
	
	private volatile String name;
	private volatile String[] launchArg;
	private volatile String title;
	private volatile String[] inputPromt;
	private volatile transient int inputFieldNeed=1;
	private int thisInstanceLocation;
	private volatile ArrayList<JTextComponent> textFields=new ArrayList<>();
	

	public Function(String Name,String Title,String[] InputPromt,int InputFieldNeed, String[] LaunchArg)
	{
		if(InputFieldNeed != InputPromt.length)
		{
			throw new AssertionError("InputPromt.length does not equal InputFieldNeed, this will cause other fatal event", null);
		}
		this.name=Name;
		this.launchArg=LaunchArg;
		this.inputPromt=InputPromt;
		this.title=Title;
		allFunctions.add(this);
		thisInstanceLocation=allFunctions.indexOf(this);
	}
	
	public JPanel generatePanel()
	{
		JPanel returnObj=new JPanel();
		JPanel subReturnObj=new JPanel();
		JPanel lSubReturnObj=new JPanel();
		JPanel rSubReturnObj=new JPanel();	
		
		GridLayout thislayout=new GridLayout(0,1);
		GridLayout subreturnObjLayout=new GridLayout(1, 2);
		BorderLayout returnObjLayout=new BorderLayout();
		
		lSubReturnObj.setLayout(thislayout);
		rSubReturnObj.setLayout(thislayout);
		subReturnObj.setLayout(subreturnObjLayout);
		returnObj.setLayout(returnObjLayout);
		
		returnObj.setName(name);
		
		for (int i = 0; i <= inputFieldNeed; i++)
		{
			JTextField field=new JTextField();
			textFields.add(field);
			JLabel label=new JLabel(inputPromt[i]);
			label.setLabelFor(field);
			lSubReturnObj.add(label);
			rSubReturnObj.add(field);
		}
		
		subReturnObj.add(lSubReturnObj);
		subReturnObj.add(rSubReturnObj);
		
		returnObj.add(subReturnObj,BorderLayout.CENTER);
		
		JButton enter=new JButton("enter",null);
		enter.setMinimumSize(new Dimension(50, 30));
		enter.addActionListener((ActionListener)this);
		returnObj.add(enter,BorderLayout.SOUTH);
		
		return returnObj;
	}
	
	public String getName()
	{
		return name;
	}

	@Override
	public void close() throws IOException
	{
		allFunctions.remove(thisInstanceLocation);
		function();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Runtime rt=Runtime.getRuntime();
		Process process;
		try
		{
			ArrayList<String> cmdArrayList=new ArrayList<>();
			for (int i = 0; i < launchArg.length; i++)
			{
				cmdArrayList.add(launchArg[i]);
			}
			for(int i=0;i<textFields.size();i++)
			{
				cmdArrayList.add(textFields.get(i).getText());
			}
			String[] cmdArray=cmdArrayList.toArray(new String[0]);
			process=rt.exec(cmdArray);
		}
		catch (IOException e1)
		{
			throw new RuntimeIOException(e1.getMessage(),e1.getCause());
		}
		Scanner scanner=new Scanner( new BufferedReader(new InputStreamReader(process.getInputStream())));
		String PrgOutput="";
		while (scanner.hasNext())
		{
			PrgOutput+=scanner.next();
		}
		System.out.println(PrgOutput);
		JButton from=(JButton) e.getSource();
		JFrame fromBttHost= (JFrame) from.getRootPane().getParent();
		scanner.close();
		fromBttHost.dispose();
	}
	public JButton generateButton()
	{
		JButton jb=new JButton(title);
		jb.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFrame ft=new JFrame(title);
				ft.add(generatePanel());
				ft.setVisible(true);
			}
		});
		return jb;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return this;
	}	
	
	public int getInputFieldNeed()
	{
		return inputFieldNeed;
	}
	
	public String[] getLaunchArg()
	{
		return launchArg;
	}
	
	public int getThisInstanceLocation()
	{
		return thisInstanceLocation;
	}
	
	public String getTile()
	{
		return title;
	}
	
	private void function()
	{
		this.name=null;
		this.launchArg=null;
		this.title=null;
		this.inputPromt=null;
		this.inputFieldNeed=-1;
	}
	
}
