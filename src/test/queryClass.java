//package test;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Scanner;
//import java.util.Set;
//
//import org.apache.jena.atlas.iterator.Iter;
//import org.apache.jena.iri.impl.Force;
//import org.apache.jena.query.Query;
//import org.apache.jena.query.QueryExecutionFactory;
//import org.apache.jena.query.QueryFactory;
//import org.apache.jena.query.QuerySolution;
//import org.apache.jena.query.ResultSet;
//import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
//import org.apache.jena.sparql.procedure.library.debug;
//
//public class queryClass {
//	static List genresList;
//	static List<Map<String,ArrayList<Integer>>> profile=new ArrayList<Map<String,ArrayList<Integer>>>();
//	static Map<String, ArrayList<Integer>> bookVector= new HashMap<String, ArrayList<Integer>>(10000);
//
//public queryClass() throws FileNotFoundException{
//	System.out.println("constructor");
//	
//}
//	/**
//	 * @param args
//	 * @throws IOException 
//	 */
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
// 		String service = "http://dbpedia.org/sparql";
//		String queryString = "";
//		File file=new File("originalwidikata.txt");
//		Scanner data=new Scanner(file);
//		List<ArrayList<String>> genre=new ArrayList<ArrayList<String>>();
//		ArrayList<String> mb=new ArrayList<String>();
//		  
//	 
//		 
// 		ArrayList<String[]> matrix=new ArrayList<String[]>();
//		List list=new ArrayList();
//		String line = "";
//		File ff=new File("Authors.txt");
//		if(!ff.exists())
//			ff.createNewFile();
//		
//		FileWriter fw=new FileWriter(ff,true);
//		BufferedWriter bw=new BufferedWriter(fw);
//		try{
//		while(data.hasNext()){
//			line=data.nextLine();
// 		
//	 
//	if(line.contains("*******"))
//		continue;
//	String abc[]=line.split("http://wikidata.org/entity/");
////	System.out.println(abc[1]);	
//	String queryString3=" PREFIX wd: <http://www.wikidata.org/entity/> " +
//			"PREFIX sc: <http://schema.org/>" +
//			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
//			"PREFIX wdt: <http://www.wikidata.org/prop/direct/>" +
//			"SELECT   (GROUP_CONCAT(?author;separator=':') as  ?authors)  WHERE {" +
// 			"wd:"+abc[1]+" wd:P50c ?authorlink  ." +
//					 
//					"?authorlink rdfs:label ?author  ." +
//				 
//		         	"FILTER (LANG(?author)= 'en' )" 
//		  +"}";
//		
//	
////	"?finstance rdfs:label ?finstanceName ." +
////	"FILTER (LANG(?finstanceName) = 'en')" +
//		String service2="http://lod.openlinksw.com/sparql"	;
//		Query query2 = QueryFactory.create(queryString3);
//		QueryEngineHTTP qexec2 = QueryExecutionFactory.createServiceRequest(service2, query2);
//		
//		ResultSet results2 = qexec2.execSelect();
//		Set<String> authorList=new HashSet<String>();//because of duplicate results
//		 String authors="",movieResult="";
//		for ( ; results2.hasNext() ; ) {
//			
//		   QuerySolution soln1 = results2.nextSolution() ;
//		//  System.out.println("***"+  soln1.getLiteral("?movie")+"   "+soln1.getLiteral("?genre"));
//		//   movieResult=soln1.getLiteral("?movie").toString().toLowerCase();
//		 authors= soln1.getLiteral("?authors").toString().toLowerCase();
//		// String resultSet[]=null;
//		 if(authors.contains(":") ){
//			String[] arr=authors.split(":");
//			for(String a:arr){
//				a=a.replace(" ","_");
//		 	     authorList.add(a.trim());
//
//			}
// 		 }
//		 		 else	 {
//		 			 authors=authors.replace(" ", "_");
//	 	     authorList.add(authors.trim());
//
//		  
//		 		 }
//		 String text="";
//		for(String myarr:authorList){
//			if(myarr.equals("anonymous")||myarr.equals("anonymus"))
//				continue;
//			text=text.concat(myarr+" ");
//		}
//		System.out.println(text);
//	bw.write(text);
//	bw.newLine();
//  	}
//	
//   	}
//	}	finally{
//   bw.close();
//   data.close();
//		}
//	}
//	 
////		Iterator<Map<String,ArrayList<Integer>>> itr=profile.iterator();
////		ArrayList<Integer> frequency=new ArrayList<Integer>();
////		int j=0;
////		int[] arrayCount =new int[10];
////		while(j<10){
////			frequency.add(0);
////			j++;
////		}
//// 		while(itr.hasNext()){
////			int i=0,count=0;
////
////			for(Map.Entry<String, ArrayList<Integer>> map:itr.next().entrySet()){
//// 				while(i<map.getValue().size()){
//// 					arrayCount[i]+=map.getValue().get(i);
//// 	 			i++;
////				}
////			} 
////		}
//// 		System.out.print("Count is: ");
////  		double avg=0.0;
//// 	for	(int i:arrayCount){
//// 		avg+=i;
//// 			System.out.print(i+" ");
//// 	}
//// 	avg=avg/10;
//// 	List<String> genresToQuery=new ArrayList<String>();
//// 	File f=new File("bookGenre.txt");
//// 	Scanner sc=new Scanner(f);
//// 	 
//// 	 	String s="";
//// 	for	(int i=0;i<arrayCount.length;i++){
//// 		s=sc.nextLine();
//// 		if(arrayCount[i]>avg){
//// 			genresToQuery.add(s);
//// 			System.out.println("Genres to query: "+s);
//// 		}
//// 	}
//// 	Iterator tr=genresToQuery.iterator();
//// 	String gn="";
//// 	while(tr.hasNext()){
//// 		gn=itr.next().toString();
//// 	String queryString4="PREFIX wd: <http://www.wikidata.org/entity/> " +
////			"PREFIX sc: <http://schema.org/>" +
////			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
////			"PREFIX wdt: <http://www.wikidata.org/prop/direct/>" +
////			"SELECT  distinct (STR(?bookname) AS ?book) (STR(?genreinstance) AS ?genre) WHERE {" +
////			"?bk wd:P31c wd:Q571 ;" +
////			"rdfs:label ?bookname ." +
////         	"FILTER (LANG(?bookname)= 'en' )" +
//// 			"?bk wd:P136c ?bgenre  ." +
////					"?bgenre wd:P31c* "+gn+"  ." +
////					"?genreins wd:P31c wd:Q223393 ." +
////					"?genreins  rdfs:label ?genreinstance ." +
////		         	"FILTER (LANG(?genreinstance)= 'en' )" 
////		  +"}";
//// 	Query query2 = QueryFactory.create(queryString4);
////	String service2="http://lod.openlinksw.com/sparql"	;
////	QueryEngineHTTP qexec2 = QueryExecutionFactory.createServiceRequest(service2, query2);
////	
////	ResultSet results2 = qexec2.execSelect();
////	HashSet<String> bookGenre=new HashSet<String>();//because of duplicate results
////	 String genreResult="",bookResultNow="",bookResultPrev="";
////	for ( ; results2.hasNext() ; ) {
////			   QuerySolution soln1 = results2.nextSolution() ;
////	//  System.out.println("***"+  soln1.getLiteral("?movie")+"   "+soln1.getLiteral("?genre"));
////	   bookResultNow=soln1.getLiteral("?book").toString().toLowerCase();
////	   bookResultPrev=bookResultNow;
////		 genreResult= soln1.getLiteral("?genre").toString().toLowerCase();
////		 String resultSet[]=null;
////		 if(genreResult.contains(" ") ){
////			 if(genreResult.contains("novels")||genreResult.contains("literature")){
////					genreResult= genreResult.replace("novel", "");
////					genreResult= genreResult.replace("literature", "");
//// 
////					genreResult=genreResult.trim();
////					if(!genreResult.contains(" ")|| genreResult.contains("science fiction"))
////						bookGenre.add(genreResult);
////			 }
////			 if(genreResult.contains(" ") && !genreResult.contains("science fiction")){
////				 resultSet=genreResult.split(" ");
////			 for(String a:resultSet){
////				 a=a.trim();
////				 bookGenre.add(a);
////			 }
////			 }
////		 }
////		 		 else	 
////	 	     bookGenre.add(genreResult.trim());
//
////	}
//  //   createBookVector(bookResult, bookGenre);
//
////  	}
// 	
////		 
////	public static List createInitialVector() throws FileNotFoundException{
////		List<String> genres=new ArrayList<String>();
////		File f=new File("genres.txt");
////		BufferedReader br=new BufferedReader(new FileReader(f));
////		String line;
////		try {
////			line = br.readLine();
////			while(line!=null){
//// 
////				genres.add(line);
////		          line = br.readLine();
////
////			}
////		} catch (IOException e) {
////			   System.out.println(e+" error from create vectors");
////		}
////		 
////		return genres;
////	
////		
////	}
////	public static void createBookVector(String bookResult, HashSet<String> bookGenre){
////		//	vector.put(movieResult, arg1)
////			ArrayList<Integer> genreList = new ArrayList<Integer>(10);
////			Iterator itr=genresList.iterator();
////			breakloop:
////			while(itr.hasNext()){
////				String test=itr.next().toString();
////				if(!test.equals("science fiction")){
////				String[] x=test.split(" ");
////	 			for(String d:x){
////	 	 			if(bookGenre.contains(d)){
////	 	 			genreList.add(1);
////		 			continue breakloop;
////					}
////	 	 			}
////	 			genreList.add(0);
////			}
////				else{
////					if(bookGenre.contains(test)){
////						genreList.add(1);
////			 			continue breakloop;
////					}
////					else
////			 			genreList.add(0);
////				}
////			}
////		bookVector.put(bookResult, genreList);	
////	
////	}
////	public static void createItemVector(String movieResult, HashSet<String> movieGenre) {
////		Map<String, ArrayList<Integer>> vector= new HashMap<String, ArrayList<Integer>>();
////	//	vector.put(movieResult, arg1)
////		ArrayList<Integer> genreList = new ArrayList<Integer>(10);
////		 
//// 
////		Iterator itr=genresList.iterator();
////		breakloop:
////		while(itr.hasNext()){
////			String test=itr.next().toString();
////			if(!test.equals("science fiction")){
////			
////			String[] x=test.split(" ");
//// 			for(String d:x){
//// 	 			if(movieGenre.contains(d)){
//// 	 			genreList.add(1);
////	 			continue breakloop;
////				}
////				 
////				}
//// 			genreList.add(0);
////
////		}
////			else{
////				if(movieGenre.contains(test)){
////					genreList.add(1);
////		 			continue breakloop;
////				}
////				else
////		 			genreList.add(0);
////
////				
////			}
////		}
////		
////		 
//// 		vector.put(movieResult,genreList);
////		for (Entry<String, ArrayList<Integer>> e : vector.entrySet()) {
////		    //to get key
////		    System.out.println("Key: "+e.getKey());
////		    //and to get value
////		    itr=e.getValue().iterator();
////		    System.out.println("Values: ");
////		    while(itr.hasNext())
////		    	System.out.print(itr.next()+"  ");
////	    	System.out.println();
////
//// 		}
////		profile.add(vector);
////
////	}
//	
//
//}
