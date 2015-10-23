//package test;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class mapBooks {
//
//	/**
//	 * @param args
//	 * @throws IOException 
//	 */
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//File f=new File("Authors.txt");
//FileReader fd=new FileReader(f);
//BufferedReader br=new BufferedReader(fd);
//File f1=new File("dbBookGenres.txt");
//FileReader fd1=new FileReader(f1);
//BufferedReader br1=new BufferedReader(fd1);
//File f2=new File("books_final.txt");
//if(!f2.exists())
//	f2.createNewFile();
//FileWriter fd2=new FileWriter(f2);
//BufferedWriter br2=new BufferedWriter(fd2);
//String s,s1;
//try{
//while(( s=br.readLine())!=null && ( s1=br1.readLine())!=null){
//	//String[] arr=s.split("	");
//	String write="";
//	if(!s.equals("")){
//	  write=s1+"#"+s;
//	}
//	else
//		write=s1;
//	System.out.println(write);
//	br2.write(write);
//	br2.newLine();
//}
//}
//finally{
//	br2.close();
//	br1.close();
//	br.close();
//}}
//
//}
