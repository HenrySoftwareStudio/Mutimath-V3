package core.environment.variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import core.assistant.StaticAssistants;
import core.environment.event.InitCompleteEvent;
import core.types.types.Function;

public class VariableSetup extends StateVariable implements InitCompleteEvent
{
	public static synchronized void onStartUp() throws FileNotFoundException
	{
		functions=makeFunctions();
	}

	public static synchronized void onInitComplete()
	{
		Ready=true;
	}

	@Override
	public void throwInitCompleteEvent()
	{
		onInitComplete();		
	}
	
	private static Function[] makeFunctions() throws FileNotFoundException
	{
		ArrayList<Function> toReturn=new ArrayList<>();
		Scanner s=new Scanner(new File("resources\\functions.metadata"));
		s.useDelimiter("/n");
		String string = "";
		while (s.hasNext())
		{
			string+=s.next();
		}
		s.close();
		int repeat=StaticAssistants.countCharRepeat(string, '@');
		for(int i=0;i<repeat;i++)
		{
			int nameBegin=string.indexOf("name{")+5;
			int nameEnd=string.indexOf("}", nameBegin);
			
			int titleBegin=string.indexOf("title{")+6;
			int titleEnd=string.indexOf("}", titleBegin);
			
			int inputPromtsBegin=string.indexOf("inputPromt{")+11;
			int inputPromtsEnd=string.indexOf("}", inputPromtsBegin);
			String inputPromtWhole=string.substring(inputPromtsBegin, inputPromtsEnd);
			ArrayList<String> inputPromtPre=new ArrayList<>();
			int lastEnd=0;
			for (int j = 0; j < StaticAssistants.countCharRepeat(inputPromtWhole, ',')-1; j++)
			{
				int begin=inputPromtWhole.indexOf(',',lastEnd)+1;
				int end=inputPromtWhole.indexOf(',',begin);
				lastEnd=end;
				inputPromtPre.add(inputPromtWhole.substring(begin,end));
			}
			String[] inputPromt=inputPromtPre.toArray(new String[0]);

			int cmdBegin=string.indexOf("cmd{")+4;
			int cmdEnd=string.indexOf("}", cmdBegin);
			String cmdWhole=string.substring(cmdBegin, cmdEnd);
			ArrayList<String> cmdPre=new ArrayList<>();
			int lastEndC=0;
			for (int j = 0; j < StaticAssistants.countCharRepeat(cmdWhole, ',')-1; j++)
			{
				int begin=cmdWhole.indexOf(',',lastEndC)+1;
				int end=cmdWhole.indexOf(',',begin);
				lastEndC=end;
				cmdPre.add(cmdWhole.substring(begin,end));
			}
			String[] cmd= cmdPre.toArray(new String[0]);
			
			int neededFieldBegin=string.indexOf("inputfieldNeed{")+15;
			int neededFieldEnd=string.indexOf("}", neededFieldBegin);
			
			toReturn.add(new Function(string.substring(nameBegin, nameEnd), string.substring(titleBegin, titleEnd)
					, inputPromt, Integer.parseInt(string.substring(neededFieldBegin, neededFieldEnd)), cmd));
			if(i<repeat-1)
			{
				string=string.substring(string.indexOf(";")+1);
			}
		}
		return toReturn.toArray(new Function[0]);
	}
}
