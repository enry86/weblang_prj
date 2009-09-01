package weblanguages.project.similar;

public class GetSimilarity {
	private String author_a, author_b;
	private SimilParser sp;
	
	public GetSimilarity(String a, String b){
		this.author_a = a;
		this.author_b = b;
		sp = new SimilParser(a, b);
	}
	
	public SimilarBean get_value(){
		SimilarBean res = null;
		res = sp.retrieve_vals("/home/enry/dblp.xml");
		return res;
	}
	
	
}
