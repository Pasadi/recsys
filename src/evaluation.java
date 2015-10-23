import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class evaluation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
public static void test(List<Book> recs, String userid) throws IOException{
	File f=new File("likes_books.txt");
	FileReader fr=new FileReader(f.getAbsolutePath());
//	List<Book> recs=new ArrayList<>();
	BufferedReader br=new BufferedReader(fr);
	String s;
	int count=0;
	System.out.println("Checking for user "+userid);
	while((s=br.readLine())!=null){
			String[]arr=s.split("	");
			for(Book b:recs){
				if(b.id.equals(arr[1])){
					count++;
				System.out.println("Found Matching Book:"+b.book);
				}
				 
			}	
	}
	System.out.println("No. of Matches: "+count);
}
}
