package csv2json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException{
		
		String arg1 = args.length>0?args[0]:null;
		String arg2 = args.length>1?args[1]:null;
		String arg3 = args.length>2?args[2]:null;
		String arg4 = args.length>3?args[3]:null;
		
		if(arg1==null)
		{
			System.out.print("Missing arguments, use the 'help' as first argument, for more information.");
		}
		else if(arg1.toLowerCase().contentEquals("help"))
		{
			System.out.print("--- Help CSV2JSON ---\n\nUsing: help|about|<arg1: input.csv file> <arg2: output.json file> <arg3: [p|w|pw] flags> <arg4: [a-zA-Z\\d\\W]* delimiter(s)>\n\nExample: file.csv file.json pw\n\np: just print output\nw: just write output\npw: print and write output\n\n");
		}
		else if(arg1.toLowerCase().contentEquals("about"))
		{
			System.out.print("--- About CSV2JSON ---\n\nAuthor: Gergely Peter Katai\nContact: kataigp@gmail.com\nInstitute: Kaposvar University - University Library");
		}
		else if(arg1.toLowerCase().contentEquals(".csv") || arg2.toLowerCase().contentEquals(".json"))
		{
			System.out.print("Missing file extension(s)");
		}
		else if(arg2==null || arg3==null || (arg3.toLowerCase().contentEquals("p")==false && arg3.toLowerCase().contentEquals("w")==false && arg3.toLowerCase().contentEquals("pw")==false) || arg4==null )
		{
			System.out.print("Missing / Not allowed argument(s)");
		}
		else if(arg1!=null || arg2!=null || arg3!=null || arg4!=null)
		{
			System.out.println("--- Convert CSV2JSON --- \n\nInput file: "+arg1.toUpperCase()+"\nOutput file: "+arg2.toUpperCase());
		
			String delimiter = arg4;
			
			ReadFile rf = new ReadFile(delimiter);
		
			boolean printOutput = false;
			boolean writeOutput = false;
			
			if(arg3.toLowerCase().contentEquals("p"))
			{
				printOutput = true;
				writeOutput =  false;
			}
			if(arg3.toLowerCase().contentEquals("w"))
			{
				printOutput = false;
				writeOutput =  true;
			}
			if(arg3.toLowerCase().contentEquals("pw"))
			{
				printOutput = true;
				writeOutput =  true;
			}
			
			WriteFile wf = new WriteFile(printOutput, writeOutput);
			
			try {
				
				List<List<String>> csv = rf.Reading(arg1);
				wf.saveToJSON(csv,arg2);
				
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("File not found!");
			}
			catch (IOException e) 
			{
				System.out.println("File read / rrite error!");
				
			}
		}
	}
}
