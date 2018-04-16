package com.test.weassignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class zReaddictionary 
{
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args)
	{
		
		try
		{
			String filetoRead = System.getProperty("user.dir")+"//dictionary.txt";
			File file = null;
			 
			//==>> Access The FIle 
			if(doesFileExist(filetoRead))
			{
				  file = new File(filetoRead);
			}
			else
			{
				System.out.println("Error - File is not Exit on Given Path : " + filetoRead);
				return; // Getting Out from program File is not Exist no point to move frowrd
			}
		 
			//==>> Read & Print the File 
			HashMap<String, String> dictionarykeyvalue = new HashMap<String, String>();
			dictionarykeyvalue = ReadAndParseTheFile(file);
			if(!dictionarykeyvalue.isEmpty())
			{
				printFileFromHasmap(dictionarykeyvalue);
			}
			else
			{
				System.out.println("Error : dictionary File Was Empty please Check the FIle : " + filetoRead);
			}
		 
		}
		catch(Exception e)
		{
			System.out.println("Something is Wrong Please Check The Technical Error Below ");
			e.printStackTrace();
		}
	}
	
	
	public static boolean doesFileExist(String path)
	{
		
		File file = new File(path);
		
		if (file.exists())
		{
			return true;
		}
		
		return false;
	}
	public static HashMap<String, String> ReadAndParseTheFile(File file)
	{
		HashMap<String, String> dictionarykeyvalue = new HashMap<String, String>();
		BufferedReader br = null;
		String st;
		try 
		{
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null)
			{
				//=== Debug Steps 
				//System.out.println(st);
				String key = st.split("–")[0];
				String value = st.split("–")[1];
				dictionarykeyvalue.put(key, value);
			}
		}
		catch (FileNotFoundException e) 
		{
			//==>> FileNotFoundException Exception massage Goes Here
			System.out.println("File Not Found Check Log Below ");
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			//==>> I-O Exception massage Goes Here 
			System.out.println("Input Out Error Check Log Below ");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Something is Wrong in Read File and Load into Hasmap  ");
			e.printStackTrace();
		}
		
		return 	dictionarykeyvalue;

	}
	@SuppressWarnings("rawtypes")
	public static void printFileFromHasmap(HashMap<String, String> dictionarykeyvalue)
	{
		Set set = dictionarykeyvalue.entrySet();
	    // Get an iterator
	    Iterator i = set.iterator();
	      
	      // Display elements
	      int wordcount = 1;
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.println("word " + wordcount + "\t\t:" + me.getKey());
	         System.out.println("******************************************");
	
	         String[] meaningArray = me.getValue().toString().split(",");
	         for(int x=0;x<meaningArray.length;x++)
	         {
		         System.out.println("Meaning " + x + "\t:" + meaningArray[x]);
	         }
	         wordcount++;
		     System.out.println();
	      }
	}
	

}
