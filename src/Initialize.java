import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;


public class Initialize {
	public static List<Book> bookData=new ArrayList<>();
    public static	Map<String,Integer> popularity=new HashMap<>();
    

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		parseMapBook();
		popularityMap();
		File f=new File("similar_user_movies.txt");
		FileReader fr=new FileReader(f.getAbsolutePath());
		List<Book> recs=new ArrayList<>();
		BufferedReader br=new BufferedReader(fr);
		String s="",prev="*";
		List<String[]>abc=new ArrayList<>();
		while((s=br.readLine())!=null){
			String[] arr={"",""};
			arr=s.split("	");
// 		 if(arr[0].equals("u115927")){
//			 abc.add(arr);
//			 prev="u115927";
//			 continue;
//		 }
//		 if(prev.equals("u115927")){
//			 System.out.println("::");
//
//				MapLikesToLinks.mapping(abc);
//				break;
//		 }
			if(!prev.equals("*")&&!prev.equals(arr[0])){
				System.out.println("sending "+prev);
				MapLikesToLinks.mapping(abc);
				System.out.println("done "+prev);
				
				abc.clear();
				abc.add(arr);
				 
			  
			}else{
 				abc.add(arr);
			}
			
			prev=arr[0];
	
			
		}
	 
		

	}
	public static List<Book> parseMapBook() throws FileNotFoundException, IOException {
        File f=new File("books_final.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String a = "",line;
        String[] arr={"","",""};
        String[] other={"","",""};
        String[] temp={"",""};
         String authorline="";
       // File fd=new File("BooksToCompare.txt");
     //   File  writeFile=new File("dbBookGenres.txt");
        
		// if file doesnt exists, then create it
		 

//		FileWriter fw = new FileWriter(fd.getAbsoluteFile(),true);
//		BufferedWriter bw = new BufferedWriter(fw);
//        
        Map<String,String> books=new HashMap<>();
        while((line=br.readLine())!=null){
        if(line.contains("#")){
        	temp=line.split("#");
        	a=temp[0];
        	if(temp[1].equals(" "))
        		authorline="";
        	else
        	authorline=temp[1];
        }
        	if(a.contains("****")){
        		other=a.split("[*]+");
        		arr[0]="";
        		arr[1]="";
        		arr[2]="";
        		arr[0]=other[0];
         		arr[2]=other[1];
        	}else{
        		arr[0]="";
        		arr[1]="";
           		arr[2]="";
           		arr=a.split("[*]+");
        	}	
        	Set<String> duplicates=new HashSet<>();//remove duplicates
            String[] genreArray=arr[2].split("\\s+");
    		for(String element:genreArray){
    			if(element.equals("")||element.equals(" ")||element==null||element.equals("[ ]+")||element.equals("fantasy_literature"))
    				continue;
    			duplicates.add(element);
    		}
    		String text="";	
    		Iterator itr=duplicates.iterator();
    			for(;itr.hasNext();)
    			text=text.concat(itr.next()+" ");  
    		//	System.out.println("Cleaned *****"+text);
    		//	System.out.println("===================>"+arr[0]+" "+text);
    	//		bw.write(arr[0]+" "+text);
    		//	bw.newLine();
    		String tokenizedTerms[]=	text.toString().split("\\s+");
       			//  books.put(arr[0], tokenizedTerms);
     //   	books.put(arr[0], text);
//termsDocsArray.add(tokenizedTerms);
                  Book b=new Book();
                  b.setBookName(arr[0]);
                  b.setBook(arr[1]);
                 
                  b.setBookTerms(tokenizedTerms);
                  b.setSimilarity(0.0);
              	String[] authors=authorline.split(" ");
             for(int i=0;i<authors.length;i++){
            	 authors[i]=authors[i].trim();
             }
                   b.setAuthors(authors);
                   
                   b.setAllTerms(ArrayUtils.addAll(b.getBookTerms(), b.getAuthors()));
                //   System.out.println(Arrays.toString(b.getAllTerms()));
                   bookData.add(b);
        }		
    	 br.close();
        return bookData;
        
        }
	public static void popularityMap() throws NumberFormatException, IOException{
		File f=new File("Popularity.txt");
		FileReader fd=new FileReader(f);
		BufferedReader br=new BufferedReader(fd);
		String s;
		
		while((s=br.readLine())!=null){
			String[]arr=s.split("	");
			popularity.put(arr[0], Integer.parseInt(arr[1]));
		  //  System.out.println("~~~~~~~~~~~~~~~~~~~"+popularity.get(arr[0]));
	}
		br.close();
		fd.close();
		}
}
