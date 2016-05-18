package csv2json;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class WriteFile {

	private boolean printOutput;
	private boolean writeOutput;
	
	public WriteFile(boolean printOutput, boolean writeOutput)
	{
		this.printOutput = printOutput;
		this.writeOutput = writeOutput;
	}
	
	public void saveToJSON(List<List<String>> csv, String newfile) throws IOException
	{
		Writer bw=null;


		if(writeOutput)
		{
			System.out.println("Writing output...");
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newfile), "UTF-8"));
			bw.write("[");
		}
		if(printOutput)
		{
			System.out.print("Print output: ");
			System.out.print("[");
		}
		int mod = (ReadFile.numOfHeader)-1;
		int l=0;
		int c=0;
		for(String lines : csv.get(1))
		{
			String header = csv.get(0).get(l);
			if(l==0)
			{
				
				if(writeOutput)
				{
					bw.write("{");
				}
				if(printOutput)
				{
					System.out.print("{");
				}
			}

			if(writeOutput)
			{
				bw.write("\""+header+"\":\""+lines+"\"");
			}
			if(printOutput)
			{
				System.out.print("\""+header+"\":\""+lines+"\"");
			}
			
			if(l==mod)
			{
				if(writeOutput)
				{
					bw.write("}");
				}
				if(printOutput)
				{
					System.out.print("}");
				}
			}
			else
			{
				if(writeOutput)
				{
					bw.write(",");
				}
				if(printOutput)
				{
					System.out.print(",");
				}
			}
			
			if(l%mod!=0||l==0)
			{
				l++;
			}
			else
			{
				if((ReadFile.numOfLines-1)!=c)
				{
					if(writeOutput)
					{
						bw.write(",\n");
					}
					if(printOutput)
					{
						System.out.print(",\n");
					}
				}
				l=0;
			}
			c++;
		}
		if(printOutput)
		{
			System.out.print("]\n");
		}
		if(writeOutput)
		{
			bw.write("]");
			bw.close();
			System.out.println("Write SUCCESS!\n-------------------------");
		}
	}
	
}
