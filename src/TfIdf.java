import java.util.Iterator;
import java.util.List;

/**
 * Class to calculate TfIdf of term.
 * @author Mubin Shrestha
 */
public class TfIdf {
    
    /**
     * Calculates the tf of term termToCheck
     * @param totalterms : Array of all the words under processing document
     * @param termToCheck : term of which tf is to be calculated.
     * @return tf(term frequency) of term termToCheck
     */
    public double tfCalculator(String[] totalterms, String termToCheck) {
        double count = 0;  //to count the overall occurrence of the term termToCheck
        for (String s : totalterms) {
                     if (s.contains(termToCheck)) {
                count++;
               
            }
        }
        return count ;
    }

    /**
     * Calculates idf of term termToCheck
     * @param allTerms : all the terms of all the documents
     * @param termToCheck
     * @return idf(inverse document frequency) score
     */
    public double idfCalculator(List allTerms, String termToCheck) {
        double count = 0;
        Iterator<String[]> itr=allTerms.iterator();
        while(itr.hasNext()){
         
        	String[] ss =itr.next();
        	for (String s : ss) {
                if (s.equals(termToCheck)) {
                    count++;
                    break;
                }
            }
        	
        	}
        
//        for (String[] ss : allTerms) {
//            for (String s : ss) {
//                if (s.equalsIgnoreCase(termToCheck)) {
//                    count++;
//                    break;
//                }
//            }
//        }
        if(count==0){
        	count-=count;
        }
        return 1 + Math.log(allTerms.size() / count);
    }
    public double dfCalculator(List allTerms, String termToCheck) {
        double count = 0;
        Iterator<String[]> itr=allTerms.iterator();
        while(itr.hasNext()){
        	String[] ss =itr.next();
        	for (String s : ss) {
                if (s.contains(termToCheck)) {
                	
                    count++;
                    break;
                }
            }
        	}
        
//        for (String[] ss : allTerms) {
//            for (String s : ss) {
//                if (s.equalsIgnoreCase(termToCheck)) {
//                    count++;
//                    break;
//                }
//            }
//        }
        return  count/allTerms.size();
    }
}