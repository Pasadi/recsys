import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapLikesToLinks {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void mapping(List<String[]> abc) throws IOException {
		// TODO Auto-generated method stub
//		File f=new File("likes_movies.txt");
//		FileReader fr=new FileReader(f.getAbsolutePath());
		List<Book> recs=new ArrayList<>();
//		BufferedReader br=new BufferedReader(fr);
		String s="";
		List<String> dbLinks=new ArrayList<>();
		File f1=new File("items_movies.txt");
		String user="";
		try{
			String prev="";
		for(String[] arr:abc){
		  	user=arr[0];
 			FileReader fr1=new FileReader(f1.getAbsolutePath());
 			BufferedReader br1=new BufferedReader(fr1);
 			String s1="";String[] arr1={"","",""};
 		 //	System.out.println(arr[1]);
 			
			ar:
			while((s1=br1.readLine())!=null){
				arr1=s1.split("	");
 
				if(arr1[0].equals(arr[1])){
					dbLinks.add(arr1[2]);
				//	System.out.println("adding"+arr1[2]);
					break ar ;
				}
			}
			br1.close();
		}
		}finally{
		
		
		recs=queryClass.process(dbLinks);
		File f =new File("books_id_wiki.txt");
		
			String x;
			for(Book b:recs){
				FileReader fr=new FileReader(f.getAbsolutePath());
				BufferedReader br=new BufferedReader(fr);
				at:
			while((x=br.readLine())!=null){
				String[] arr=x.split("	");
						if(arr[1].equals(b.getBookName())){
 						
						b.setId(arr[0]);
						break at;
					}
			}
				br.close();
			}
		evaluation.test(recs,user);
		}
		
	}

}
