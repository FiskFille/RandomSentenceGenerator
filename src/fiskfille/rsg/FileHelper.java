package fiskfille.rsg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class FileHelper
{
	public static ArrayList<String> readFile(String fileDirectory)
	{
		String s;
		ArrayList<String> list = new ArrayList<String>();
		
		try
		{
			fileDirectory = "categories/" + fileDirectory;
			File file = new File(fileDirectory);
			
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			@SuppressWarnings("resource")
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileDirectory)));
			
			while ((s = input.readLine()) != null)
			{
				list.add(s);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static String getRandomEntry(String fileDirectory)
	{
		Random rand = new Random();
		ArrayList<String> list = readFile(fileDirectory);
		return list.get(rand.nextInt(list.size()));
	}
}