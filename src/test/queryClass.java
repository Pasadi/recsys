package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

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
public queryClass() throws FileNotFoundException{
	System.out.println("constructor");
	
}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		genresList=createInitialVector();
		String service = "http://dbpedia.org/sparql";
		String queryString = "";
		File file=new File("movies.txt");
		Scanner data=new Scanner(file);
		List<ArrayList<String>> genre=new ArrayList<ArrayList<String>>();
		ArrayList<String> mb=new ArrayList<String>();
		 mb.add("adventure");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("science fiction");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("drama");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("romance");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("thriller");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("mystery");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("horror");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("comedy");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("crime");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 mb.clear();
 		 mb.add("fantasy");
		 mb.add("");
		 mb.add("");
		 mb.add("");
		 genre.add(mb);
		 genre.get(1);
 
		String[] filmbook={"adventure film","wd:Q319221"};
		 
 		ArrayList<String[]> matrix=new ArrayList<String[]>();
		List list=new ArrayList();
		String line = null;
		while(data.hasNext()){
			line=data.nextLine();
 		
	String queryString1="prefix owl: <http://www.w3.org/2002/07/owl#>" +
			"SELECT ?link  WHERE {" +
		    "<" +line+ "> owl:sameAs ?link ." +
			"FILTER(contains(str(?link),'wikidata.org'))}";
	 String wikiLink="";
	Query query = QueryFactory.create(queryString1);
	QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
	ResultSet results = qexec.execSelect();
	   String movieName="",authorName="",genreName="";
	for ( ; results.hasNext() ; ) {
     QuerySolution soln = results.nextSolution() ;
    wikiLink=soln.getResource("link").toString();
//System.out.println(wikiLink);
	
	String abc[]=wikiLink.split("http://wikidata.org/entity/");
//	System.out.println(abc[1]);	
	String queryString3=" PREFIX wd: <http://www.wikidata.org/entity/> " +
			"PREFIX sc: <http://schema.org/>" +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
			"PREFIX wdt: <http://www.wikidata.org/prop/direct/>" +
			"SELECT  distinct (STR(?moviename) AS ?movie) (STR(?genreinstance) AS ?genre) WHERE {" +
 			"wd:"+abc[1]+" wd:P136c ?fgenre  ;" +
					"rdfs:label ?moviename ." +
		         	"FILTER (LANG(?moviename)= 'en' )" +

					"?fgenre wd:P31c* ?genreins  ." +
					"?genreins wd:P31c wd:Q201658 ." +
					"?genreins  rdfs:label ?genreinstance ." +
		         	"FILTER (LANG(?genreinstance)= 'en' )" 
		  +"}";
		
	String queryString2=" PREFIX wd: <http://www.wikidata.org/entity/> " +
			"PREFIX sc: <http://schema.org/>" +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
			"PREFIX wdt: <http://www.wikidata.org/prop/direct/>" +
			"SELECT  distinct ?book  (GROUP_CONCAT(?genrename;separator='|') as ?genrenames)  WHERE {" +
 			"wd:"+abc[1]+" wd:P136c ?fgenre  ;" +
					"rdfs:label ?book ." +
		         	"FILTER (LANG(?book)= 'en' )" +
			"?fgenre rdfs:label ?genrename ." +
         	"FILTER (LANG(?genrename)= 'en' )" +
 //					"?book wd:P31c wd:Q571 ." +
//					"	?book wd:P136c ?bgenre ." +

//			"?fgenre wd:P279c ?finstance ." +
//			
//			"?book wd:P31c wd:Q571 ." +
//			"	?book wd:P136c ?bgenre ." +
//			"?bgenre wd:P279c ?finstance ." +
//
//			"?book  rdfs:label ?bookname ." +
//			"FILTER (LANG(?bookname) = 'en')" +
 			"}" +
 			"group by ?book";
//	"?finstance rdfs:label ?finstanceName ." +
//	"FILTER (LANG(?finstanceName) = 'en')" +
		String service2="http://lod.openlinksw.com/sparql"	;
		Query query2 = QueryFactory.create(queryString3);
		QueryEngineHTTP qexec2 = QueryExecutionFactory.createServiceRequest(service2, query2);
		
		ResultSet results2 = qexec2.execSelect();
		HashSet<String> movieGenre=new HashSet<String>();//because of duplicate results
		 String genreResult="",movieResult="";
		for ( ; results2.hasNext() ; ) {
			
		   QuerySolution soln1 = results2.nextSolution() ;
		//  System.out.println("***"+  soln1.getLiteral("?movie")+"   "+soln1.getLiteral("?genre"));
		   movieResult=soln1.getLiteral("?movie").toString().toLowerCase();
		 genreResult= soln1.getLiteral("?genre").toString().toLowerCase();
		 String resultSet[]=null;
		 if(genreResult.contains(" ") ){
			 if(genreResult.contains("film")){
					genreResult= genreResult.replace("film", "");
					genreResult=genreResult.trim();
					if(!genreResult.contains(" ")|| genreResult.contains("science fiction"))
						movieGenre.add(genreResult);
			 }
			 if(genreResult.contains(" ") && !genreResult.contains("science fiction")){
				 resultSet=genreResult.split(" ");
			 for(String a:resultSet){
				 a=a.trim();
				 movieGenre.add(a);
			 }
			 }
		 }
		 		 else	 
	 	     movieGenre.add(genreResult.trim());

		  
 		
//		String array2[]={"comedy","romance","thriller","science fiction"};
//		for(String myarr:array1){
// 		}
	}
	
		createItemVector(movieResult,movieGenre);
  	}
	 
	}
	}
	public static List createInitialVector() throws FileNotFoundException{
		List<String> genres=new ArrayList<String>();
		File f=new File("genres.txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		String line;
		try {
			line = br.readLine();
			while(line!=null){
 
				genres.add(line);
		          line = br.readLine();

			}
		} catch (IOException e) {
			   System.out.println(e+" error from create vectors");
		}
		 
		return genres;
	
		
	}
	public static void createItemVector(String movieResult, HashSet<String> movieGenre) {
		Map<String, ArrayList<Integer>> vector= new HashMap<String, ArrayList<Integer>>();
	//	vector.put(movieResult, arg1)
		ArrayList<Integer> genreList = new ArrayList<Integer>(10);
		 
 
		Iterator itr=genresList.iterator();
		breakloop:
		while(itr.hasNext()){
			String test=itr.next().toString();
			if(!test.equals("science fiction")){
			
			String[] x=test.split(" ");
 			for(String d:x){
 	 			if(movieGenre.contains(d)){
 	 			genreList.add(1);
	 			continue breakloop;
				}
				 
				}
 			genreList.add(0);

		}
			else{
				if(movieGenre.contains(test)){
					genreList.add(1);
		 			continue breakloop;
				}
				else
		 			genreList.add(0);

				
			}
		}
		
		 
 		vector.put(movieResult,genreList);
		for (Entry<String, ArrayList<Integer>> e : vector.entrySet()) {
		    //to get key
		    System.out.println("Key: "+e.getKey());
		    //and to get value
		    itr=e.getValue().iterator();
		    System.out.println("Values: ");
		    while(itr.hasNext())
		    	System.out.print(itr.next()+"  ");
 		}
		profile.add(vector);

	}
	

}
