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
//public class sameUserCheck {
//
//	/**
//	 * @param args
//	 * @throws IOException 
//	 */
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		File f=new File("user_movies.txt");
//		FileReader fd=new FileReader(f);
//		BufferedReader br=new BufferedReader(fd);
//		File f1=new File("user_books.txt");
////		File ff=new File("similar_user_books.txt");
////		if(!ff.exists())
////			ff.createNewFile();
////		FileWriter fw=new FileWriter(ff);
////		BufferedWriter bw=new BufferedWriter(fw);
//		File ff1=new File("similar_user_books.txt");
//		if(!ff1.exists())
//			ff1.createNewFile();
//		FileWriter fw1=new FileWriter(ff1,true);
//		BufferedWriter bw1=new BufferedWriter(fw1);
//
//		String s,s1;
//		 
//			String prv="";
//			boolean checked=false;
//		while((s=br.readLine())!=null){
//	
//			String[] arr={"",""};
//			arr=s.split("	");
//			if(arr[0].equals(prv)){
//				System.out.println("-------------");
//
//				continue;
//			}
//			
//
//			prv=arr[0];
//			FileReader fd1=new FileReader(f1);
//
//			BufferedReader br1=new BufferedReader(fd1);
//
//			while((s1=br1.readLine())!=null){
//
//				String[] arr1={"",""};
//				arr1=s1.split("	");
//				if(arr[0].equals(arr1[0])){
//					System.out.println("-------------"+arr[0]+"			"+arr1[0]);
//					//System.out.println("writing");
//					checked=true;
//			//		String out=arr[0]+"	"+arr[1];
//					String out1=arr1[0]+"	"+arr1[1];
//				//	bw.write(out);
//				//	bw.newLine();
//					bw1.write(out1);
//					bw1.newLine();
//				}
//				
//			}
//			br1.close();
//		}
////		}finally{
////			bw.close();
//			bw1.close();
//			br.close();
////		}
//	}
//
//}
