 import java.io.BufferedReader;
import java.util.Collections;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
import java.util.Scanner;
import java.util.Set;

import org.apache.jena.atlas.iterator.Iter;
import org.apache.jena.iri.impl.Force;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.sparql.procedure.library.debug;

public class queryClass {
	static List genresList;
	static List<Map<String,ArrayList<Integer>>> profile=new ArrayList<Map<String,ArrayList<Integer>>>();
	static Map<String, ArrayList<Integer>> bookVector= new HashMap<String, ArrayList<Integer>>(10000);
	static List<Movie> userProfile=new ArrayList<>();
	 static List<Book> bookData=new ArrayList<>();
	public static List<Double> diversity=new ArrayList<>();
public static Map<String,Integer> count=new HashMap<>();
public static Set<String> AggregateDiversity=new HashSet<>();
public queryClass() throws FileNotFoundException{
	System.out.println("constructor");
	
}
	/**
	 * @param dbLinks 
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	public static List<Book> process(List<String> dbLinks ) throws IOException {
		// TODO Auto-generated method stub
	 
		String service = "http://dbpedia.org/sparql";
 	//	File file=new File("movies.txt");
	//	Scanner data=new Scanner(file);
     	Map<String,String> movies=new HashMap<>();
       // String line = null;
		for(String line:dbLinks){
	//		line=data.nextLine();
        String queryString1="prefix owl: <http://www.w3.org/2002/07/owl#>" +
			"SELECT ?link  WHERE {" +
		    "<" +line+ "> owl:sameAs ?link ." +
			"FILTER(contains(str(?link),'wikidata.org'))}";
	 String wikiLink="";
	Query query = QueryFactory.create(queryString1);
	QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
 	ResultSet results = qexec.execSelect();
 	// System.out.println("************");
 	for ( ; results.hasNext() ; ) {
     QuerySolution soln = results.nextSolution() ;
    wikiLink=soln.getResource("link").toString();
  // System.out.println(wikiLink);
	
	String abc[]=wikiLink.split("http://wikidata.org/entity/");
		String queryString3="PREFIX wd: <http://www.wikidata.org/entity/> " +
			"PREFIX sc: <http://schema.org/>" +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
			"PREFIX wdt: <http://www.wikidata.org/prop/direct/>" +
			"SELECT  distinct (STR(?moviename) AS ?movie) (group_concat(?genreinstance ; separator = ':') AS ?genre) " +
			"(group_concat(?genreinstance1 ; separator = ':') AS ?genre1)(group_concat(?author ; separator = ':') AS ?authors) WHERE {" +
 			"wd:"+abc[1]+" wd:P136c ?fgenre,?fgenre1  ;" +
					"rdfs:label ?moviename ." +
					
		         	"FILTER (LANG(?moviename)= 'en' )" +
		         	
		         	"OPTIONAL{"+
					"?fgenre (wd:P31c|wd:P279c)* ?genreins  ." +
					"?genreins wd:P31c wd:Q201658 ." +
					"?genreins  rdfs:label ?genreinstance ." +
		         	"FILTER (LANG(?genreinstance)= 'en' )" 
		         	+"}" +
		         	"OPTIONAL{"+
					"?fgenre1 (wd:P31c|wd:P279c)* ?genreins1  ." +
					"?genreins1 wd:P31c wd:Q223393 ." +
					"?genreins1  rdfs:label ?genreinstance1 ." +
		         	"FILTER (LANG(?genreinstance1)= 'en' )" 
		         	+"}" +
		         	"OPTIONAL{" +
		         	"wd:"+abc[1]+" wd:P58c ?authorlink ." +
					"?authorlink rdfs:label ?author ." +
					"FILTER (LANG(?author)= 'en' )" +
		         	"}" +
		"}"+
		"group by ?moviename";
 		String service2="http://lod.openlinksw.com/sparql"	;
	service2="http://dbpedia.org/sparql";
		Query query2 = QueryFactory.create(queryString3);
 
		QueryEngineHTTP qexec2 = QueryExecutionFactory.createServiceRequest(service2, query2);
 		ResultSet results2 = qexec2.execSelect();
 
		HashSet<String> movieGenre=new HashSet<String>();//because of duplicate results
		 String genreResult="",movieResult="",authorResult="";
String[] genreArray,authorArray;
		for ( ; results2.hasNext() ; ) {
			
		   QuerySolution soln1 = results2.nextSolution() ;
//System.out.println("***"+  soln1.getLiteral("?movie")+"   "+soln1.get("?genre")+"   "+soln1.get("?genre"));
		   movieResult=soln1.getLiteral("?movie").toString().toLowerCase();
		 genreResult= soln1.get("?genre").toString().toLowerCase();
		genreResult =genreResult.concat(":"+soln1.get("?genre1").toString().toLowerCase());
		authorResult=soln1.get("?authors").toString().toLowerCase();
		genreArray= genreResult.split(":");
		authorArray=authorResult.split(":");
 String text="";
	Set<String> duplicates=new HashSet<>();//remove duplicates

		for(String element:genreArray){
if(element.equals("literature")||element.equals("drama film")||element.equals("drama") ){
 
 	continue;
}
 
			if(element.equals("fiction")||element.equals("novel"))
				continue;
			if(element.contains("film")||element.contains("genre")||element.contains("movie")){
				element=element.replace("film", "");
				element=element.replace("genre", "");
				element=element.replace("movie", "");
 				element=element.trim();
			}
		element=element.trim();
		element=element.replace(" ", "_");
		duplicates.add(element);
		
		}
		Iterator itr=duplicates.iterator();
		for(;itr.hasNext();)
		text=text.concat(itr.next()+" ");  
			duplicates.clear();
		for(String element:authorArray){
			if(element.equals("anonymus"))
				continue;
			element=element.replace(" ","_");
			duplicates.add(element);
		}
		String text1="";
		 itr=duplicates.iterator();
		for(;itr.hasNext();){
			text1=text1.concat(itr.next()+" ");  

		}
		text=text.concat("#"+text1);
		//text=text.trim();
  	 if(text.equals("")||text==null) 
		 continue;
		 else
		  movies.put(abc[1], text);
 
		}
		
 	}
 	  
		}
	 
		 System.out.println("*************************");
		Cosine_Similarity cs=new Cosine_Similarity();
		DocumentParser dp=new DocumentParser();
 	    dp.parseMap(movies);
		dp.tfIdfCalculatorMy(); //calculates tfidf
	    dp.getCosineSimilarityMy(); //calculates cosine similarity   
	    
	    userProfile=dp.createUserProfile();
	    for(Movie m:userProfile){
	    	System.out.println(m.movieName+m.similarity);
	    }
 	    bookSimilarity bs=new bookSimilarity();
	    bs.parseUserProfile(userProfile);
	   bookData= Initialize.bookData;
 	 

	 bs.jaccard(bookData, userProfile);
	   List<Book> Recommendations=new ArrayList<>();
	 Recommendations=  createUserProfile(bookData);
   
	
//	  
//		 
	 for(Book b:Recommendations){
		 if(b.id!=null){
			AggregateDiversity.add(b.id);
			
	 }
	 }
	File f=new File("AverageDiversity.txt");
     if(!f.exists())
    	 f.createNewFile();
     FileWriter fw=new FileWriter(f);
     BufferedWriter bw=new BufferedWriter(fw); 
   for( String s:AggregateDiversity ){
	 
	   bw.write(s);
	   bw.newLine();
   }
   bw.close();
   fw.close();
	 return Recommendations;
 

 	}
//	
	 public static List<Book> getUserProfile(List<Book> myList) throws IOException{
		 List<Book> myListNew=new ArrayList<>();
 		    Collections.sort(myList, new OverallSimilarity1());
		    	for(Book b:myList){
		    		File f =new File("books_id_wiki.txt");
	 		   		
 					String x;
 					 
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
 						fr.close();
 	        
  		    	 if(b.getSimilarity()>0.0 && Initialize.popularity.containsKey(b.getId())){
 		    		if(Initialize.popularity.get(b.getId())>1)
		    		 myListNew.add(b);
 		    	 }
		    	}
		    	
		    	System.out.println("###########################"+myListNew.size());
		    	if(myListNew.size()>20)
		    	return myListNew.subList(0, 20);
		    	else
		    		return myListNew;
    }
			public static List<Book> createUserProfile(List<Book> myList) throws IOException{
				 
				 return getUserProfile(myList);
				}
	 
	 
}
class OverallSimilarity1 implements Comparator<Book>{
	   @Override
	    public int compare(Book a, Book b) {
	        return a.getSimilarity()*1000< b.getSimilarity()*1000?1 :a.getSimilarity()*1000== b.getSimilarity()*1000?0:-1;
	    }
}
