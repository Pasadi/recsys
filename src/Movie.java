
public class Movie {
	String movieName;
	String[] movieTerms;
	double similarity;
	double[] tfVector;
	double[] tfVectorAuthor;

	public double[] getTfVectorAuthor() {
		return tfVectorAuthor;
	}
	public void setTfVectorAuthor(double[] tfVectorAuthor) {
		this.tfVectorAuthor = tfVectorAuthor;
	}
	String[] authors;
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
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
