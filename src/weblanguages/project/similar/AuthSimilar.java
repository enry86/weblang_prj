package weblanguages.project.similar;

public class AuthSimilar {
	public SimilarBean get_similarity(String author_a, String author_b){
		GetSimilarity g = new GetSimilarity(author_a, author_b);
		SimilarBean sb = g.get_value();
		return sb;
	}
}
