import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class evaluation {
	public static ArrayList<Double> f_score=new ArrayList<>();

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
	 int testItems=0;
	at:
	while((s=br.readLine())!=null){
 
			String[]arr=s.split("	");
			if(arr[0].equals(userid)){
				check=1;
				itr=0;
				testItems++;
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
	double count1=count;
	double F1=0.0;
	if(count>0){
 		double precision=count1/20;
		double recall=count1/testItems;
	    F1=2*(precision*recall)/(precision+recall);
		System.out.println("Precision: "+precision);
		System.out.println("Recall: "+recall);
		System.out.println("F1 Score: "+F1);
		f_score.add(F1);
	} 
	File fd=new File("Cosine_fscore.txt");
	if(!fd.exists()){
		fd.createNewFile();
	}
	String str=F1+"";
	FileWriter fw=new FileWriter(fd,true);
	BufferedWriter bw=new BufferedWriter(fw);
	
	bw.write(str);
	bw.newLine();
	bw.close();
	fw.close();

	
}
}
