import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
//calculates the cosine similarity between two texts / documents etc., (having each word separated by space)
public class Cosine_Similarity
{
 public class values
 {
  int val1;
  int val2;
  values(int v1, int v2)
  {
   this.val1=v1;
   this.val2=v2;
  }
  public void Update_VAl(int v1, int v2)
  {
   this.val1=v1;
   this.val2=v2;
  }
 }//end of class values


 public void inputMap(Map<String,String> map){
	 List avc=new ArrayList<Object>();
 	 Cosine_Similarity cs1 = new Cosine_Similarity();
	 distinctTerms(map.values());

	 System.out.println("[Word # VectorA # VectorB]");
	 int i=0;
	 double sim_score=0;
//     ValueComparator bvc = new ValueComparator(map);

	 Map<String,Double> overallSim=new HashMap<String,Double>();
	 Map<String,Double> overallSim1=new HashMap<String,Double>();

	for(Entry<String, String> entry:map.entrySet()){
		for(Entry<String, String> itr:map.entrySet()){
			if(!itr.getKey().equals(entry.getKey())){
				sim_score+= cs1.Cosine_Similarity_Score(entry.getValue(),itr.getValue());
			}
		}
		sim_score=sim_score/23;
		   overallSim.put(entry.getKey(), sim_score);
		
			}
  
      overallSim1=sortByComparator(overallSim);
      for(Entry<String, Double> entry:overallSim1.entrySet()){
		   System.out.println("Cosine similarity score of entry  "+entry.getKey()+"is: "+entry.getValue());

      }
 }
 public void distinctTerms(Collection<String> collection){
		int i=0;
		Iterator<String> itr=collection.iterator();
	       List<String> allTerms = new ArrayList<String>(); //to hold all terms

		while(itr.hasNext()){
		  String[] tokenizedTerms = itr.next().toString().split(" ");   //to get individual terms
	      for (String term : tokenizedTerms) {
	          if (!allTerms.contains(term)) {  //avoid duplicate entry
	              allTerms.add(term);
	          }
	      }
	      i++;
	 }
	}
// public double idfCalculator(List<String> allTerms, String termToCheck) {
//     double count = 0;
//     for (String  ss : allTerms) {
//              if (ss.equalsIgnoreCase(termToCheck)) {
//            	  System.out.println(arg0);
//                 count++;
//                 break;
//                      }
//     }
//     return 1 + Math.log(allTerms.size() / count);
// }
 public double Cosine_Similarity_Score(String Text1, String Text2)
 {
  double sim_score=0.0000000;
  //1. Identify distinct words from both documents
  String [] word_seq_text1 = Text1.split(" ");
  String [] word_seq_text2 = Text2.split(" ");
  Hashtable<String, values> word_freq_vector = new Hashtable<String, Cosine_Similarity.values>();
  LinkedList<String> Distinct_words_text_1_2 = new LinkedList<String>();
   //******************************This part should be done for all the movie features in the user profile...........///
  //prepare word frequency vector by using Text1
  for(int i=0;i<word_seq_text1.length;i++)
  {
   String tmp_wd = word_seq_text1[i].trim();
   if(tmp_wd.length()>0)
   {
    if(word_freq_vector.containsKey(tmp_wd))
    {
     values vals1 = word_freq_vector.get(tmp_wd);
     int freq1 = vals1.val1+1;
     int freq2 = vals1.val2;
     vals1.Update_VAl(freq1, freq2);
     word_freq_vector.put(tmp_wd, vals1);
    }
    else
    {
     values vals1 = new values(1, 0);
     word_freq_vector.put(tmp_wd, vals1);
     Distinct_words_text_1_2.add(tmp_wd);
    }
   }
  }
   
  //prepare word frequency vector by using Text2
  for(int i=0;i<word_seq_text2.length;i++)
  {
   String tmp_wd = word_seq_text2[i].trim();
   if(tmp_wd.length()>0)
   {
    if(word_freq_vector.containsKey(tmp_wd))
    {
     values vals1 = word_freq_vector.get(tmp_wd);
     int freq1 = vals1.val1;
     int freq2 = vals1.val2+1;
     vals1.Update_VAl(freq1, freq2);
     word_freq_vector.put(tmp_wd, vals1);
    }
    else
    {
     values vals1 = new values(0, 1);
     word_freq_vector.put(tmp_wd, vals1);
     Distinct_words_text_1_2.add(tmp_wd);
    }
   }
  }
   
  //calculate the cosine similarity score.
  double VectAB = 0.0000000;
  double VectA_Sq = 0.0000000;
  double VectB_Sq = 0.0000000;
   
  for(int i=0;i<Distinct_words_text_1_2.size();i++)
  {
   values vals12 = word_freq_vector.get(Distinct_words_text_1_2.get(i));
   
   double freq1 = (double)vals12.val1;
   double freq2 = (double)vals12.val2;
   System.out.println(Distinct_words_text_1_2.get(i)+"#"+freq1+"#"+freq2);
    
   VectAB=VectAB+(freq1*freq2);
    
   VectA_Sq = VectA_Sq + freq1*freq1;
   VectB_Sq = VectB_Sq + freq2*freq2;
  }
  System.out.println("VectAB "+VectAB+" VectA_Sq "+VectA_Sq+" VectB_Sq "+VectB_Sq);
  sim_score = ((VectAB)/(Math.sqrt(VectA_Sq)*Math.sqrt(VectB_Sq)));
   
  return(sim_score);
 }
// public static void main(String[] args)
// {
//  Cosine_Similarity cs1 = new Cosine_Similarity();
// 
//  System.out.println("[Word # VectorA # VectorB]");
//  double sim_score = cs1.Cosine_Similarity_Score("Julie loves me more than Linda loves me", "Julie loves me not");
//  System.out.println("Cosine similarity score = "+sim_score);
// }
 	 private  Map<String, Double> sortByComparator(Map<String, Double> unsortMap) {

			// Convert Map to List
			List<Map.Entry<String, Double>> list = 
				new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

			// Sort list with comparator, to compare the Map values
			Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
				public int compare(Map.Entry<String, Double> o1,
	                                           Map.Entry<String, Double> o2) {
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});

			// Convert sorted map back to a Map
			Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
			for (Iterator<Map.Entry<String, Double>> it = list.iterator(); it.hasNext();) {
				Map.Entry<String, Double> entry = it.next();
				sortedMap.put(entry.getKey(), entry.getValue());
			}
			return sortedMap;
		}
}

  