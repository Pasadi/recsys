package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DifferentUsers {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f=new File("Training_Data.txt");
		FileReader fd=new FileReader(f);
		BufferedReader br=new BufferedReader(fd);
		Set<String> sim=new HashSet<>();
		String s;
		while((s=br.readLine())!=null){
			 String[] arr= s.split("	");
			sim.add(arr[0]);
		}
		br.close();
		fd.close();
//
		for(String s1:sim){
			System.out.println(s1);
		}
System.out.println(sim.size());
	}

}
