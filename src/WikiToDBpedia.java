import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		File f=new File("items_books.txt");
		String scan;
		String[] partition=new String[2];
		String resultString;
        FileReader file = new FileReader(f);
        BufferedReader br = new BufferedReader(file);
        StringTokenizer s;
        File  writeFile=new File("wikiData.txt");
         
		// if file doesnt exists, then create it
		if (!writeFile.exists()) {
			System.out.println("creating file");
			writeFile.createNewFile();
		}

		FileWriter fw = new FileWriter(writeFile.getAbsoluteFile());
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
		while((scan = br.readLine()) != null)
        {
   partition=scan.split("	", 3);
// System.out.println("*"+partition[2]+"*");
  
    resultString=(queryDBPedia(partition[2]));
    
     bw.write(resultString);
     bw.newLine();
    
        }
        br.close();
 		bw.close();

        }
public static String queryDBPedia(String queryString){
	queryString="prefix owl: <http://www.w3.org/2002/07/owl#>" +
			"SELECT distinct ?link  WHERE {" +
		    "<" +queryString+ "> owl:sameAs ?link ." +
			"FILTER(contains(str(?link),'wikidata.org'))}";
	String service = "http://dbpedia.org/sparql";
	Query query = QueryFactory.create(queryString);
	QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
	ResultSet results = qexec.execSelect();
	 String result="";
	for ( ; results.hasNext() ; ) {
     QuerySolution soln = results.nextSolution() ;
     result=soln.getResource("?link").toString();
     System.out.println(result);

	}
	return result;
}
}
