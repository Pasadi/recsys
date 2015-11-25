import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import org.apache.commons.lang3.ArrayUtils;

import jdk.jfr.events.FileWriteEvent;

/**
 * Class to read documents
 *
 * @author Mubin Shrestha
 */
public class DocumentParser   {

    //This variable will hold all terms of each document in an array.
    private List<String[]> termsDocsArray = new ArrayList<>();
    private List<String[]> termsDocsArrayAuthor = new ArrayList<>();

    private List allTermsGenres = new ArrayList<>(); //to hold all terms
    private List allTermsAuthors = new ArrayList<>(); //to hold all terms

    private List tfidfDocsVector = new ArrayList<>();
  //  private Map<String,List> reference=new HashMap<>();
 private List<Movie> reference=new ArrayList<>();
 private List<Movie> userProfile=new ArrayList<>();
 private List<Book> bookData=new ArrayList<>();
 private List<Book> referenceBook=new ArrayList<>();

     /**
     * Method to read files and store in array.
     * @param filePath : source file path
     * @return 
     * @throws FileNotFoundException
     * @throws IOException
     */
//    public void parseFiles(String filePath) throws FileNotFoundException, IOException {
//        File[] allfiles = new File(filePath).listFiles();
//        BufferedReader in = null;
//        for (File f : allfiles) {
//            if (f.getName().endsWith(".txt")) {
//                in = new BufferedReader(new FileReader(f));
//                StringBuilder sb = new StringBuilder();
//                String s = null;
//                while ((s = in.readLine()) != null) {
//                    sb.append(s);
//                }
//                String[] tokenizedTerms = sb.toString().replaceAll("[\\W&&[^\\s]]", "").split("\\W+");   //to get individual terms
//                for (String term : tokenizedTerms) {
//                    if (!allTerms.contains(term)) {  //avoid duplicate entry
//                        allTerms.add(term);
//                    }
//                }
//              termsDocsArray.add(tokenizedTerms);
//            }
//        }
//
//    }
    public List<Movie> parseMap(Map<String, String> movies) throws FileNotFoundException, IOException {
        
    	for(Entry<String, String> entry:movies.entrySet()){
     		String[] seperator=entry.getValue().toString().split("#");
                String[] tokenizedTerms =seperator[0].split(" ");   //to get individual terms
            
                for (String term : tokenizedTerms) {
                	 if(!term.equals("")){
                     if (!allTermsGenres.contains(term)) {  //avoid duplicate entry
                        allTermsGenres.add(term);
                        System.out.print("** unique term :"+term);
                    }
                 }
                }
                termsDocsArray.add(tokenizedTerms);
            
               Movie m=new Movie();
               m.setMovieName(entry.getKey());
               m.setMovieTerms(tokenizedTerms);
               String[] arr={""};
          if(seperator.length>1) {
        	  
        	   for (String term : seperator[1].split(" ")) {
        		   if(!term.equals("")){
                   if (!allTermsAuthors.contains(term)) {  //avoid duplicate entry
                      allTermsAuthors.add(term);
                   }
        		   }  
               }
               m.setAuthors(seperator[1].split(" "));
               termsDocsArrayAuthor.add(seperator[1].split(" "));
               
          }
         
          else m.setAuthors(arr);
          m.setAllTerms(ArrayUtils.addAll(m.getMovieTerms(),m.getAuthors()));
       reference.add(m);
                
        }
    	return reference;
    }

   public List<Book> parseMapBook() throws FileNotFoundException, IOException {
        File f=new File("dbBookGenres.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String a;
        String[] arr={"",""};
        String[] other={"","",""};
        File fd=new File("BooksToCompare.txt");
     //   File  writeFile=new File("dbBookGenres.txt");
        
		// if file doesnt exists, then create it
		 

//		FileWriter fw = new FileWriter(fd.getAbsoluteFile(),true);
//		BufferedWriter bw = new BufferedWriter(fw);
//        
        Map<String,String> books=new HashMap<>();
        while((a=br.readLine())!=null){
        	if(a.contains("****")){
        		arr=a.split("[*]+");
        	}else{
        		
        		other=a.split("[*]+");
        		arr[0]=other[0];
        		arr[1]=other[2];
        	}
        //	System.out.println("````````````````````````"+arr[1]);
        	Set<String> duplicates=new HashSet<>();//remove duplicates
    String[] genreArray=arr[1].split("\\s+");
    		for(String element:genreArray){

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
        	books.put(arr[0], text);

                  Book b=new Book();
                  b.setBookName(arr[0]);
                  b.setBookTerms(tokenizedTerms);
                  bookData.add(b);
    			
     			
    		 
        }
//    	for(Entry<String, String> entry:books.entrySet()){
//            String[] tokenizedTerms =entry.getValue().toString().split(" ");   //to get individual terms
//            for (String term : tokenizedTerms) {
//                if (!allTerms.contains(term)) {  //avoid duplicate entry
//                    allTerms.add(term);
//                    System.out.print("** unique term :"+term);
//                }
//            }
//            termsDocsArray.add(tokenizedTerms);
//        
//    	}   
//            
     return bookData;
   //     bw.close();
  //     br.close();
    	
//    	for(Entry<String, String> entry:books.entrySet()){
//                String[] tokenizedTerms =entry.getValue().toString().split(" ");   //to get individual terms
//                for (String term : tokenizedTerms) {
//                    if (!allTerms.contains(term)) {  //avoid duplicate entry
//                        allTerms.add(term);
//                        System.out.print("** unique term :"+term);
//                    }
//                }
//                termsDocsArray.add(tokenizedTerms);
            
//               Movie m=new Movie();
//               m.setMovieName(entry.getKey());
//               m.setMovieTerms(tokenizedTerms);
//               reference.add(m);
                
        }

    

    /**
     * Method to create termVector according to its tfidf score.
     */ public void tfIdfCalculatorMy() {
    	  double tf; //term frequency
          double idf; //inverse document frequency
          double tfidf; //term requency inverse document frequency    
          for(Movie m:reference){
        	  String[] docTermsArray=m.getMovieTerms();
        	  double[] tfidfvectors = new double[allTermsGenres.size()];
              int count = 0;
              Iterator<String> itr=allTermsGenres.iterator();
              while(itr.hasNext()){
            	  String terms=itr.next();
            	   
            	  tf = new TfIdf().tfCalculator(docTermsArray, terms);
                  idf = new TfIdf().idfCalculator(termsDocsArray, terms);
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
     public void tfIdfCalculator(List<Movie> movies,List<Book> books, List all) {
   	  double tf; //term frequency
         double idf; //inverse document frequency
         double tfidf; //term requency inverse document frequency    
         for(Movie m:movies){
       	  String[] docTermsArray=m.getMovieTerms();
       	  double[] tfidfvectors = new double[allTermsGenres.size()];
             int count = 0;
             Iterator<String> itr=allTermsGenres.iterator();
             while(itr.hasNext()){
           	  String terms=itr.next();
           	   
           	  tf = new TfIdf().tfCalculator(docTermsArray, terms); //doctermsarray of each book against allterms in movie profile
                 idf = new TfIdf().dfCalculator(termsDocsArray, terms);//terms docs arrays of all the books
                 tfidf = tf * idf;
                 tfidfvectors[count] = tfidf;
                 count++;
             }
             m.setTfVector(tfidfvectors);
         }
   	 
    }
//    public void tfIdfCalculator() {
//        double tf; //term frequency
//        double idf; //inverse document frequency
//        double tfidf; //term requency inverse document frequency        
//        Iterator<String[]>  it = termsDocsArray.iterator();
//         int i=0;
//        while(it.hasNext()) {
//          String[] docTermsArray  = it.next();
//          
//          System.out.println("Printing terms in document no: "+i+"  "+Arrays.toString(docTermsArray));
//          i++;
//          double[] tfidfvectors = new double[allTerms.size()];
//          int count = 0;
//          Iterator<String> itr=allTerms.iterator();
//          while(itr.hasNext()){
//        	  String terms=itr.next();
//        	   
//        	  tf = new TfIdf().tfCalculator(docTermsArray, terms);
//              idf = new TfIdf().idfCalculator(termsDocsArray, terms);
//              tfidf = tf * idf;
//              tfidfvectors[count] = tfidf;
//              count++;
//          }
//          tfidfDocsVector.add(tfidfvectors);  //storing document vectors; 
//        for(Movie m: reference1){
//        	if(m.movieTerms.equals(docTermsArray)){
//        		m.tfVector=tfidfvectors;
//        	}
//        	System.out.println(m.getMovieName()+m.getMovieTerms()+m.getTfVector());
//        	        }
//        
//        
//          //System.out.println("tf idf vectos of this doc is: "+Arrays.toString(tfidfvectors));
//           
//        }
//        for (String[] docTermsArray : termsDocsArray) {
//            double[] tfidfvectors = new double[allTerms.size()];
//            int count = 0;
//            for (String terms : allTerms) {
//                tf = new TfIdf().tfCalculator(docTermsArray, terms);
//                idf = new TfIdf().idfCalculator(termsDocsArray, terms);
//                tfidf = tf * idf;
//                tfidfvectors[count] = tfidf;
//                count++;
//            }
//            tfidfDocsVector.add(tfidfvectors);  //storing document vectors;            
//        }
//    }

    /**
     * Method to calculate cosine similarity between all the documents.
     */
//    public void getCosineSimilarity() {
//    	double overallSimilarity=0.0;
//        for (int i = 0; i < tfidfDocsVector.size(); i++) {
//        	overallSimilarity=0.0;
//            for (int j = 0; j < tfidfDocsVector.size(); j++) {
//            	if(i!=j){
//            	overallSimilarity+=new CosineSimilarity().cosineSimilarity((double[])tfidfDocsVector.get(i),(double[])tfidfDocsVector.get(j) );
////                System.out.println("between " + i + " and " + j + "  =  "
////                                   + new CosineSimilarity().cosineSimilarity
////                                       (
////                                         (double[])tfidfDocsVector.get(i), 
////                                         (double[])tfidfDocsVector.get(j)
////                                       )
////                                  );
//            	}
//            }
//            System.out.println("********************Overall Similarity of "+i+" : "+overallSimilarity);
//        }
//    }
    public void getCosineSimilarityMy() {
    	double overallSimilarityGenre=0.0,overallSimilarityAuthor=0.0;
    	int count=0;
    	for(Movie m: reference){
    		count=0;
        	overallSimilarityGenre=0.0;
        	overallSimilarityAuthor=0.0;

     		for(Movie j:reference){
    			count++;
    			if(!m.getMovieName().equals(j.getMovieName())){
    				
                	overallSimilarityGenre+=new CosineSimilarity().cosineSimilarity(m.getTfVector(),j.getTfVector());
                	overallSimilarityAuthor+=new CosineSimilarity().cosineSimilarity(m.getTfVectorAuthor(),j.getTfVectorAuthor());

    				
    			}
    			if(Double.isNaN(overallSimilarityGenre))
        			overallSimilarityGenre=0.0;
    			if(Double.isNaN(overallSimilarityAuthor))
        			overallSimilarityAuthor=0.0;
     		}
    		m.setSimilarity((overallSimilarityGenre+overallSimilarityAuthor)/count);
        //    System.out.println("#############################Overall Similarity of "+m.getMovieName()+" : "+overallSimilarity);
    	}
    }
    public void getUserProfile(){
    Collections.sort(reference, new OverallSimilarity());
//    	for(Movie m:reference){
//    	 System.out.println(m.getMovieName()+"     "+Arrays.toString(m.movieTerms)+"    " +m.getSimilarity());
//   
//    	}
    }
	public List<Movie> createUserProfile(){
	  this.getUserProfile();
	 	 return reference.subList(0,5);
		}
	}
 
class OverallSimilarity implements Comparator<Movie> {
    @Override
    public int compare(Movie a, Movie b) {
        return a.getSimilarity()*1000< b.getSimilarity()*1000?1 :a.getSimilarity()*1000== b.getSimilarity()*1000?0:-1;
    }

	
}
