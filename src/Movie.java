
public class Movie {
	String movieName;
	String[] movieTerms;
	double similarity;
	double[] tfVector;
	public double[] getTfVector() {
		return tfVector;
	}
	public void setTfVector(double[] tfVector) {
		this.tfVector = tfVector;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String[] getMovieTerms() {
		return movieTerms;
	}
	public void setMovieTerms(String[] movieTerms) {
		this.movieTerms = movieTerms;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
 

}
