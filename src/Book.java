
public class Book {
	String id;//bo111
	String book;//actual name
	String bookName;//wikilink
	String[] bookTerms;
	String[] authors;
	double[] tfVectorAuthor;

	public double[] getTfVectorAuthor() {
		return tfVectorAuthor;
	}
	public void setTfVectorAuthor(double[] tfVectorAuthor) {
		this.tfVectorAuthor = tfVectorAuthor;
	}
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	double similarity=0.0;
	double[] tfVector;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
   public String getBook() {
	return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public double[] getTfVector() {
		return tfVector;
	}
	public void setTfVector(double[] tfVector) {
		this.tfVector = tfVector;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String movieName) {
		this.bookName = movieName;
	}
	public String[] getBookTerms() {
		return bookTerms;
	}
	public void setBookTerms(String[] movieTerms) {
		this.bookTerms = movieTerms;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
 

}

