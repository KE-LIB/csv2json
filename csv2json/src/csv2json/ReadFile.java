package csv2json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

	private String delimiter;
	static int numOfHeader;
	static int numOfLines;
	
	public ReadFile(String delimiter)
	{
		this.delimiter = delimiter;
	}
	
	public List<List<String>> Reading(String inputFile) throws IOException, FileNotFoundException
	{
		List<List<String>> returnList = new ArrayList<>();
		List<String> headers = new ArrayList<>();
		List<String> csvContent = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line;
		int lineNum = 0;
		
		while ((line = br.readLine()) != null)
		{
			String[] columns = line.split(delimiter);
			
			for(int i=0;i<columns.length;i++)
			{
				if(lineNum == 0)
				{
					headers.add(columns[i]);
				}
				else
				{
					csvContent.add(columns[i]);
				}
			}
			lineNum++;
		}
		br.close();
		
		numOfHeader = headers.size();
		numOfLines = csvContent.size();
		
		returnList.add(headers);
		returnList.add(csvContent);
		
		return returnList;
	}

	
}
