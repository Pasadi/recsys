package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import jdk.jfr.events.FileWriteEvent;

public class PopularItems {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f=new File("Training_Data.txt");
		FileReader fd=new FileReader(f);
		BufferedReader br=new BufferedReader(fd);
		String s;
		Map<String,Integer> popularity=new HashMap<>();
		while((s=br.readLine())!=null){
			String[]arr=s.split("	");
			if(popularity.containsKey(arr[1])){
				int value=popularity.get(arr[1]);
				popularity.put(arr[1], value+1);
			}
			else{
				popularity.put(arr[1],1);
			}

	    }
		
     fd.close();
     br.close();
     f=new File("Training_Popularity.txt");
     if(!f.exists())
    	 f.createNewFile();
     FileWriter fw=new FileWriter(f);
     BufferedWriter bw=new BufferedWriter(fw); 
   for( Map.Entry<String,Integer> item:popularity.entrySet() ){
	   String  str= item.getKey()+"	"+item.getValue();
	   bw.write(str);
	   bw.newLine();
   }
   bw.close();
   
}
	
}
 