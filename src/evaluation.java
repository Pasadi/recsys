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
	File f=new File("similar_user_books.txt");
	FileReader fr=new FileReader(f.getAbsolutePath());
//	List<Book> recs=new ArrayList<>();
	BufferedReader br=new BufferedReader(fr);
	String s;
	int count=0;
	int check=0;
	 int itr=0;
	at:
	while((s=br.readLine())!=null){
 
			String[]arr=s.split("	");
			if(arr[0].equals(userid)){
				check=1;
				itr=0;
 			for(Book b:recs){
				if(b.id.equals(arr[1])){
					count++;
				System.out.println("Found Matching Book:"+b.book+"     "+itr);
				break;
				}
				itr++;
			}	
			}else{
				if(check==1){
					check=0;
 
					break at;
					
				}
			}
	}
	fr.close();
	br.close();
	System.out.println("No. of Matches: "+count);
}
}
