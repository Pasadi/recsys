//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Vector;
//
//import org.apache.jena.query.Query;
//import org.apache.jena.query.QueryExecutionFactory;
//import org.apache.jena.query.QueryFactory;
//import org.apache.jena.query.QuerySolution;
//import org.apache.jena.query.ResultSet;
//import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
//
//
//public class Hello {
//
//	/**
//	 * @param args
//	 * @throws FileNotFoundException 
//	 */
//	public static void main(String[] args) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//// 		String service = "http://dbpedia.org/sparql";
////		String queryString = "";
////		File file=new File("movies.txt");
////		Scanner data=new Scanner(file);
//////		Vector<String[]> matrix=new Vector<String[]>();
////		ArrayList<String[]> matrix=new ArrayList<String[]>();
////		List list=new ArrayList();
////		String line = null;
////		while(data.hasNext()){
////			line=data.nextLine();
////			list.add(line);
////		}
////	
////        queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  " +
////        		"PREFIX db: <http://dbpedia.org/ontology/>" +
////        		"PREFIX prop: <http://dbpedia.org/property/>" +
////        		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
////         		"SELECT    (STR(?movie) as ?movie1) (STR(?author) as ?author1) (STR(?genre) as ?genre1) " +
////            "WHERE {" +
////            "<" +line+ "> rdfs:label ?movie ." +
////            "FILTER (LANG(?movie) = 'en')" +
////            "OPTIONAL{ <" +line+ "> db:writer ?authorlink ." +
////            "?authorlink rdfs:label ?author ." +
////            "FILTER (LANG(?author) = 'en')}" +
////             "OPTIONAL{ <" +line+ "> prop:genre ?genre ." +
////       "FILTER (LANG(?genre) = 'en')}" +
////             "} " +
////             "LIMIT 10";
////     
////		Query query = QueryFactory.create(queryString);
////		QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
////		ResultSet results = qexec.execSelect();
////		   String movieName="",authorName="",genreName="";
////		for ( ; results.hasNext() ; ) {
////			
////		    QuerySolution soln = results.nextSolution() ;
////		    movieName="";
////		    authorName="";
////		    genreName="";
////		   movieName=soln.getLiteral("movie1").toString();
////		   if( soln.getLiteral("author1")!=null){
////		   		    authorName=soln.getLiteral("author1").toString();
//// 		   }
////		   if( soln.getLiteral("genre1")!=null){
////	   		        genreName=soln.getLiteral("genre1").toString();
////		   }
////		 
////		  String arr[]={movieName,authorName,genreName};
////		  System.out.println(movieName+"*****"+authorName);
////          matrix.add(arr);  
//// 		}
////	}
////		Iterator<String[]> itr=matrix.iterator();
////		while(itr.hasNext()){
////			String[] content=itr.next();
////			System.out.println(content[0]+" ++  "+content[1]+"  ++ "+content[2]);
////		}
//// 	}
////	
//	
//		String service = "http://dbpedia.org/sparql";
//		String queryString = "";
//		File file=new File("movies.txt");
//		Scanner data=new Scanner(file);
// 		ArrayList<String[]> matrix=new ArrayList<String[]>();
//		List list=new ArrayList();
//		String line = null;
//		while(data.hasNext()){
//			line=data.nextLine();
// 		
//	String queryString1="prefix owl: <http://www.w3.org/2002/07/owl#>" +
//			"SELECT ?link  WHERE {" +
//		    "<" +line+ "> owl:sameAs ?link ." +
//			"FILTER(contains(str(?link),'wikidata.org'))";
//	Query query = QueryFactory.create(queryString1);
//	QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
//	ResultSet results = qexec.execSelect();
//	   String movieName="",authorName="",genreName="";
//	for ( ; results.hasNext() ; ) {
//     QuerySolution soln = results.nextSolution() ;
//     String wikiLink=soln.getResource("link").toString();
//System.out.println(wikiLink);
//	}
//	}
//	String wikiLink=
////		String query2="PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
////				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
////				"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>" +
////				"PREFIX dcterms: <http://purl.org/dc/terms/>" +
////				"SELECT ?film ?label ?subject WHERE {" +
////				"SERVICE <http://data.linkedmdb.org/sparql> {" +
////				"?film a movie:film ." +
////				"?film rdfs:label ?label ." +
////				"?film owl:sameAs ?dbpediaLink " +
////				"FILTER(regex(str(?dbpediaLink), 'dbpedia', 'i'))" +
////						"}" +
////						"SERVICE <http://dbpedia.org/sparql> {" +
////						"?dbpediaLink dcterms:subject ?subject" +
////						"}" +
////						"}" +
////						"LIMIT 2";
////		JenaARQFederationExample test = new JenaARQFederationExample();
//	 

//	String service2="http://lod.openlinksw.com/sparql"	;
//	Query query = QueryFactory.create(query4);
//	QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service2, query);
//	
//	ResultSet results = qexec.execSelect();
//	
//	for ( ; results.hasNext() ; ) {
//		
//	   QuerySolution soln = results.nextSolution() ;
//	  System.out.println("***"+  soln.toString());
//	}
//}
//}
