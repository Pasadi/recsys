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
import java.util.Map.Entry;


public class bookSimilarity {
	 private List<Book> bookData=new ArrayList<>();
	    private List<String[]> termsDocsArray = new ArrayList<>();
	    private List<String[]> termsDocsArrayAuthor = new ArrayList<>();

	    private List allTermsGenres = new ArrayList<>(); //to hold all terms
	    private List allTermsAuthors = new ArrayList<>(); //to hold all terms

	  //  Map<String,Integer> alltermcount=new HashMap<String,Integer>();

	/**
	 * @param args
	 */
//	public List<Book> parseMapBook() throws FileNotFoundException, IOException {
//        File f=new File("dbBookGenres.txt");
//        FileReader fr=new FileReader(f);
//        BufferedReader br=new BufferedReader(fr);
//        String a;
//        String[] arr={"","",""};
//        String[] other={"","",""};
//        File fd=new File("BooksToCompare.txt");
//     //   File  writeFile=new File("dbBookGenres.txt");
//        
//		// if file doesnt exists, then create it
//		 
//
////		FileWriter fw = new FileWriter(fd.getAbsoluteFile(),true);
////		BufferedWriter bw = new BufferedWriter(fw);
////        
//        Map<String,String> books=new HashMap<>();
//        while((a=br.readLine())!=null){
//        	if(a.contains("****")){
//        		other=a.split("[*]+");
//        		arr[0]=other[0];
//        		arr[2]=other[1];
//        	}else{
//        		
//        		
//        		arr=a.split("[*]+");
//        	}
//        //	System.out.println("````````````````````````"+arr[1]);
//        	Set<String> duplicates=new HashSet<>();//remove duplicates
//    String[] genreArray=arr[2].split("\\s+");
//    		for(String element:genreArray){
//
//    			duplicates.add(element);
//    		}
//    		String text="";	
//    		Iterator itr=duplicates.iterator();
//    			for(;itr.hasNext();)
//    			text=text.concat(itr.next()+" ");  
//    		//	System.out.println("Cleaned *****"+text);
//    		//	System.out.println("===================>"+arr[0]+" "+text);
//    	//		bw.write(arr[0]+" "+text);
//    		//	bw.newLine();
//    		String tokenizedTerms[]=	text.toString().split("\\s+");
//       			//  books.put(arr[0], tokenizedTerms);
//     //   	books.put(arr[0], text);
////termsDocsArray.add(tokenizedTerms);
//                  Book b=new Book();
//                  b.setBookName(arr[0]);
//                  b.setBook(arr[1]);
//                  b.setBookTerms(tokenizedTerms);
//                  bookData.add(b);
//        }		
//    	 
//        return bookData;
//        }
//	public List<Book> getByGenre(List<Book> bookdata){
//		Set<Book> genreSort=new HashSet<>();
//		my:
//		for(Book b:bookdata){
//			for(String term: b.getBookTerms()){
//				if(allTerms.contains(term)){
//					genreSort.add(b);
//					continue my;
//				}
//			}
//		}
//		 
//		return bookData;
//	}
public void parseUserProfile(List<Movie> userProfile) throws FileNotFoundException, IOException {
        
    	for(Movie entry:userProfile){
                 for (String term : entry.getMovieTerms()) {
                	 if(!term.equals("")){
                		 if (!allTermsGenres.contains(term)) {  //avoid duplicate entry
                             allTermsGenres.add(term);
                 	 }
                     
                       
                     }
                }
               // System.out.println("terms docs array"+Arrays.toString(entry.getMovieTerms()));
           termsDocsArray.add(entry.getMovieTerms());
           for (String term : entry.getAuthors()) {
        	   if(!term.equals("")){
               if (!allTermsAuthors.contains(term)) {  //avoid duplicate entry
                  allTermsAuthors.add(term);

              }}
          }
           termsDocsArrayAuthor.add(entry.getAuthors());

        }
   // 	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
       tfIdfCalculatorUserProf(userProfile);
     
    }
    public void tfIdfCalculator(List<Book> books) {
     	  double tf; //term frequency
           double idf; //inverse document frequency
           double tfidf; //term requency inverse document frequency   
             for(Book m:books){
            	
         	  String[] docTermsArray=m.getBookTerms();
           	  double[] tfidfvectors = new double[allTermsGenres.size()];
               int count = 0;
              
               Iterator<String> itr=allTermsGenres.iterator();
               while(itr.hasNext()){
             	  String terms=itr.next();
             	   
             	 tf=0.0;
                 idf=0.0;
                 tfidf=0.0; 
             	 
             	  tf = new TfIdf().tfCalculator(docTermsArray, terms); //doctermsarray of each book against allterms in movie profile
                  idf = new TfIdf().idfCalculator(termsDocsArray, terms);//terms docs arrays of all the books
                   tfidf = tf  *idf ;
                   
                   tfidfvectors[count] = tfidf;
                   if(m.book.equals("crime and punishment"))
                       System.out.println(":"+tfidf);
                   count++;
               }
               m.setTfVector(tfidfvectors);
               //////////////for authors
               String[] docTermsArrayAuthors=m.getAuthors();
               double[] tfidfvectorsauthors = new double[allTermsAuthors.size()];
  
               int count1 = 0;
                 itr=allTermsAuthors.iterator();
               while(itr.hasNext()){
             	  String terms=itr.next();
              	  tf = new TfIdf().tfCalculator(docTermsArrayAuthors, terms);
                   idf = new TfIdf().idfCalculator(termsDocsArrayAuthor, terms);
                   tfidf = tf * idf;
                   tfidfvectorsauthors[count1] = tfidf;
                   count1++;
               }
               m.setTfVectorAuthor(tfidfvectorsauthors);
          //   System.out.println(Arrays.toString(m.getTfVector()));
           }
     	 
      }
    public void tfIdfCalculatorUserProf(List<Movie> movie) {
   	  double tf; //term frequency
         double idf; //inverse document frequency
         double tfidf; //term requency inverse document frequency    
         for(Movie m:movie){
       	  String[] docTermsArray=m.getMovieTerms();
       	  double[] tfidfvectors = new double[allTermsGenres.size()];
             int count = 0;
             Iterator<String> itr=allTermsGenres.iterator();
             while(itr.hasNext()){
           	  String terms=itr.next();
           	   
           	  tf = new TfIdf().tfCalculator(docTermsArray, terms); //doctermsarray of each book against allterms in movie profile
                 idf = new TfIdf().idfCalculator(termsDocsArray, terms);//terms docs arrays of all the books
                 tfidf = tf * idf;
               
                 tfidfvectors[count] = tfidf;
                 count++;
             }
             m.setTfVector(tfidfvectors);
             //////////////for authors
             String[] docTermsArrayAuthors=m.getAuthors();
             double[] tfidfvectorsauthors = new double[allTermsAuthors.size()];

             int count1 = 0;
               itr=allTermsAuthors.iterator();
             while(itr.hasNext()){
           	  String terms=itr.next();
            	  tf = new TfIdf().tfCalculator(docTermsArrayAuthors, terms);
                 idf = new TfIdf().idfCalculator(termsDocsArrayAuthor, terms);
                 tfidf = tf * idf;
                 tfidfvectorsauthors[count1] = tfidf;
                 count1++;
             }
             m.setTfVectorAuthor(tfidfvectorsauthors);
         }
   	 
    }
    public void jaccard(List<Book> bookData, List<Movie> userProfile){
 	   for(Book singleBook:bookData){
		   Set<String>  s1 = new HashSet<>(),u,i ;
 		   for(String s:singleBook.getBookTerms()){
			   s1.add(s);
		   }
		   
		   double indSim=0.0;
		   int count=0;
		   for(Movie singleMovie:userProfile){
			   count++;
			  Set<String> s2 = new HashSet<>();
			  for(String m:singleMovie.getMovieTerms()){
				   s2.add(m);
			   }
			   u = new HashSet<>();
			      u.addAll(s1);
			      u.addAll(s2);
			     i = new HashSet<>();
			      i.addAll(s1);
			      i.retainAll(s2);
			      indSim+= (double) i.size() / (double) u.size();
			 //   if(singleBook.getSimilarity()<indSim)  	

 		   }
	// double overallSimilarity=indSim/count;
		   singleBook.setSimilarity(indSim);
    }
    }
    public void getCosineSimilarityMy(List<Book> BookData,List<Movie> userProfile) {
    	double overallSimilarityGenre=0.0,overallSimilarityAuthor=0.0;
    	int count=0;
    	for(Book m: BookData){
    		m.setSimilarity(0.0);
    		count=0;
    		overallSimilarityGenre=0.0;
        	overallSimilarityAuthor=0.0;

    		for(Movie j:userProfile){
    			count++;
     				
    			overallSimilarityGenre+=new CosineSimilarity().cosineSimilarity(m.getTfVector(),j.getTfVector());
                	overallSimilarityAuthor+=new CosineSimilarity().cosineSimilarity(m.getTfVectorAuthor(),j.getTfVectorAuthor());
                	if(Double.isNaN(overallSimilarityGenre))
            			overallSimilarityGenre=0.0;
        			if(Double.isNaN(overallSimilarityAuthor))
            			overallSimilarityAuthor=0.0;
    				
    			 
    		}
    		
    		m.setSimilarity((overallSimilarityGenre+overallSimilarityAuthor)/count);
    		
     
    }
}
}