import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;


public class WikiToDBpedia {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File abc=new File("BookLinks1.txt");
		FileReader fr=new FileReader(abc);
		BufferedReader dbc=new BufferedReader(fr);
		List<String> myList=new ArrayList<>();
		String b;
		while((b=dbc.readLine())!=null){
			System.out.println("===="+b);
			myList.add(b);
		}
		dbc.close();
		File f=new File("items_books.txt");
		String scan;
		String[] partition=new String[2];
		String resultString;
        FileReader file = new FileReader(f);
        BufferedReader br = new BufferedReader(file);
        StringTokenizer s;
        File  writeFile=new File("dbBookGenres.txt");
         
		// if file doesnt exists, then create it
		if (!writeFile.exists()) {
			System.out.println("creating file");
			writeFile.createNewFile();
		}

		FileWriter fw = new FileWriter(writeFile.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
//		loop:
//        while((scan = br.readLine()) != null)
//                {
//           partition=scan.split("	", 3);
//        // System.out.println("*"+partition[2]+"*");
//           if(partition[0].contains("bo788"))
//        	   break loop ;
//            resultString= scan.concat(queryDBPedia(partition[2]));
//            
//             bw.write(resultString);
//             bw.newLine();
//            
//                }
		try{
			int i=0;
		loop:
		while((scan = br.readLine()) != null)
        {
   partition=scan.split("	", 3);
// System.out.println("*"+partition[2]+"*");
  
     String queryString="prefix owl: <http://www.w3.org/2002/07/owl#>" +
			"PREFIX wd: <http://www.wikidata.org/entity/> "+ 
					 "PREFIX sc: <http://schema.org/> "+ 
					 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+ 
					 "PREFIX wdt: <http://www.wikidata.org/prop/direct/> " +
 					 "prefix prop: <http://dbpedia.org/property/>" +
					 "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
					 "PREFIX dbowl: <http://dbpedia.org/ontology/>"+
					"SELECT distinct ?link  (group_concat(?genreins; separator = ':') AS ?g) WHERE {" +
		    "<" +partition[2]+ "> owl:sameAs ?link ;" +
		    		"prop:genre ?genre ." +
			"FILTER(contains(str(?link),'wikidata.org'))" +
			"?genre rdfs:label ?genreins ." +
	            	   "  FILTER (LANG(?genreins)= 'en' ) " +
			"}" +
			"group by ?link";
	String service = "http://dbpedia.org/sparql";
	Query query = QueryFactory.create(queryString);
	QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
	ResultSet results = qexec.execSelect();
	 String nameResult="";
	 String genreResult="";
		String[] genreArray;
	for ( ; results.hasNext() ; ) {
     QuerySolution soln = results.nextSolution() ;
     nameResult=soln.getResource("?link").toString();
     if(soln.getLiteral("?g")!=null){
    	 genreResult=soln.getLiteral("?g").toString().toLowerCase();
     }
      
	}
	String line=myList.get(i);
	if(!line.contains(nameResult)){
		System.out.println("*********************************************************888");
	 continue;
	}
	i++;
	genreArray= genreResult.split(":");
	 String text="";
		Set<String> duplicates=new HashSet<>();//remove duplicates

			for(String element:genreArray){
			 
				if(element.contains("novel")||element.contains("genre")){
					element=element.replace("(novel)", "");
					element=element.replace("(genre)", "");
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
			line=line.concat(" "+text);
	    	 System.out.println("===============>"+line);
			// bw.write();

	    	 bw.write(line);
	    	 bw.newLine(); 
	    	 if(i==3220)
	    	 throw new IllegalArgumentException();
        }
        }
		finally{
        br.close();
 		bw.close();
		}
        }
 
}
