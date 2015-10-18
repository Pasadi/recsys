

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
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
import java.util.Set;

import org.apache.jena.atlas.iterator.Iter;
import org.apache.jena.iri.impl.Force;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.core.QueryHashCode;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.sparql.procedure.library.debug;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class bookDataSet {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f=new File("wikiData.txt");
		BufferedReader br=new BufferedReader(new FileReader(f));
		String[] arr=new String[2];
		  Map<String,String> books=new HashMap<>();
			File output=new File("BookLinks1.txt");
			if(!output.exists()){
				System.out.println("Creating file");
				output.createNewFile();
			}
			FileWriter fw=new FileWriter(output.getAbsoluteFile());
		      BufferedWriter bw=new BufferedWriter(fw);
		      try{
		    	  String a;
		while((a=br.readLine())!=null){
			arr=new String[2];
			arr=a.split("http://wikidata.org/entity/");
		System.out.println("====+++++++++"+arr[1]);
//		String queryString2="PREFIX wd: <http://www.wikidata.org/entity/>"+  
//		" PREFIX sc: <http://schema.org/> "+
//		 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
//		" PREFIX wdt: <http://www.wikidata.org/prop/direct/> "+
//		" SELECT  distinct ?book (group_concat( distinct ?genreinstance ; separator = '|') AS ?genre)(group_concat( distinct ?genreinstance1 ; separator = '|') AS ?genre1)  WHERE { "+
//		   "?book wd:P31c wd:Q571 ;"+
//		   " wd:"+arr[1]+"     wd:P136c ?bgenre, ?bgenre1 ."+
//		   " OPTIONAL{ "+
//		   "  ?bgenre1  (wd:P31c|wd:P279c)* ?genreins1 ."+
//		   "  ?genreins1 wd:P31c wd:Q483394 ."+
//		   "  ?genreins1   rdfs:label ?genreinstance1 ."+
//		   "   FILTER (LANG(?genreinstance1)= 'en' ) "+
//		   "  } 			"+	
//		   "OPTIONAL{  "+
//		   "  ?bgenre (wd:P31c|wd:P279c)* ?genreins  . "+
//		   "  ?genreins wd:P31c wd:Q223393 ."+
//		   "     ?bgenre  rdfs:label ?genreinstance ."+
//		   " FILTER (LANG(?genreinstance)= 'en' ) "+
//		   "} "+
//		   " } "+
//		   "group by ?book";
			 String queryString="PREFIX wd: <http://www.wikidata.org/entity/> "+ 
					 "PREFIX sc: <http://schema.org/> "+ 
					 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+ 
					 "PREFIX wdt: <http://www.wikidata.org/prop/direct/> " +
					 "prefix owl: <http://www.w3.org/2002/07/owl#>" +
					 "prefix prop: <http://dbpedia.org/property/>" +
					 "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
					 "PREFIX dbowl: <http://dbpedia.org/ontology/>"+
					 
					"SELECT  distinct ?resource   (group_concat(?genreinstance3 ; separator = ':') AS ?g2)  WHERE {"+ 
					              "?resource  rdf:type dbowl:Book ." +
					              "?resource owl:sameas wd:Q8337 ." +
					              		"?resource prop:genre ?genre3 ."+
					              		 "?genre3 rdfs:label ?genreinstance3 ."+ 
					              	   "  FILTER (LANG(?genreinstance3)= 'en' ) " +
					              	   "}" +
					              	   "group by ?resource";		 
			String queryString1="PREFIX wd: <http://www.wikidata.org/entity/> "+ 
 "PREFIX sc: <http://schema.org/> "+ 
 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+ 
 "PREFIX wdt: <http://www.wikidata.org/prop/direct/> " +
 "prefix owl: <http://www.w3.org/2002/07/owl#>" +
 "prefix prop: <http://dbpedia.org/property/>" +
 "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
 "PREFIX dbowl: <http://dbpedia.org/ontology/>"+
 
"SELECT  distinct  (STR(?book) AS ?bookn) (group_concat(?genreinstance ; separator = ':') AS ?g) (group_concat(?genreinstance1 ; separator = ':') AS ?g1)   (group_concat(?genreinstance2 ; separator = ':') AS ?g2)  WHERE {"+ 
 
               
"wd:"+arr[1]+" wd:P136c   ?bgenre,?bgenre1,?bgenre2 ;" +
    "         rdfs:label ?book ." +
   "?bgenre rdfs:label ?genreinstance ."+ 
   "  FILTER (LANG(?genreinstance)= 'en' ) "+ 
 "FILTER (LANG(?book)= 'en' )" +
  "OPTIONAL{ "+ 
     " ?bgenre1  (wd:P31c|wd:P279c)* ?genreins1 ."+ 
    "?genreins1 wd:P31c wd:Q483394 ."+ 
    "?genreins1   rdfs:label ?genreinstance1 ."+ 
   "  FILTER (LANG(?genreinstance1)= 'en' ) "+ 
 "  } 	" +
   "OPTIONAL{  "+ 
    "  ?bgenre2 (wd:P31c|wd:P279c)* ?genreins2  . "+ 
   "?genreins2 wd:P31c wd:Q223393 ."+ 
    "?genreins2  rdfs:label ?genreinstance2 ."+ 
   " FILTER (LANG(?genreinstance2)= 'en' ) "+ 
  " } "+ 
 " 		   }" +
 "group by  ?book"  
;
	 
			String service2="http://lod.openlinksw.com/sparql"	;
		service2="http://dbpedia.org/sparql";
				Query query2 = QueryFactory.create(queryString1);
		 
				QueryExecution qexec2 = QueryExecutionFactory.sparqlService(service2, query2);
		 
				ResultSet results2 = qexec2.execSelect();
		 
				HashSet<String> movieGenre=new HashSet<String>();//because of duplicate results
				 String genreResult="",movieResult="";
		String[] genreArray;
		String x = "";
				for ( ; results2.hasNext() ; ) {
					 
				   QuerySolution soln1 = results2.nextSolution() ;
	 	 System.out.println("***"+  soln1.get("?bookn").toString()+"   "+soln1.getLiteral("?g").toString()+"    "+soln1.get("?g1")+"    "+soln1.get("?g2"));
//		 System.out.println("***"+  soln1.get("?resource").toString()+"   "+soln1.getLiteral("?g2").toString());
		
		 movieResult=soln1.getLiteral("?bookn").toString().toLowerCase();
				 genreResult= soln1.get("?g").toString().toLowerCase();
				 if(soln1.getLiteral("?g1")!=null)
				genreResult =genreResult.concat(":"+soln1.get("?g1").toString().toLowerCase());
				 if(soln1.getLiteral("?g2")!=null)
						genreResult =genreResult.concat(":"+soln1.get("?g2").toString().toLowerCase());
				}
				genreArray= genreResult.split(":");
		 String text="";
			Set<String> duplicates=new HashSet<>();//remove duplicates

				for(String element:genreArray){
					if(element.equals("poorly formed")|| element.equals("novel")||element.equals("non_fiction")||element.equals("fiction"))
						continue;
					if(element.contains("novel")||element.contains("genre")||element.contains("novella")){
						element=element.replace("novel", "");
						element=element.replace("genre", "");
					//	element=element.replace("fiction", "");
					//	element=element.replace("literature", "");
						element=element.trim();
					}
					element=element.trim();
					element=element.replace(" ", "_");
					duplicates.add(element);
		 			
				}
				Iterator itr=duplicates.iterator();
				for(;itr.hasNext();)
				text=text.concat(itr.next()+" ");  
				System.out.println("*******Cleaned *****"+text);
		    	 System.out.println("===============>"+arr[1]+"		"+text);
				// bw.write();

		    	 bw.write("http://wikidata.org/entity/"+arr[1]+"**"+movieResult+"**"+text);
		    	 bw.newLine(); 
		    	// throw new IllegalArgumentException("Myexception");
				}	 
		
 	
 	}
 	finally{ br.close(); bw.close();}
 	
		// System.out.println("************************************");

}

//      String key="";
//      String texts="";
//      for (Map.Entry<String,String> entry : books.entrySet()) {
//    	  key=  entry.getKey();
//    	 texts=   entry.getValue();
//    	 System.out.println("===============>"+key+"		"+texts);
//    	 bw.write(key+"		"+texts);
//    	 bw.newLine();
//    	}
//         bw.close();
	 
		 
	 
}
