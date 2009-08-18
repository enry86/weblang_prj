package weblanguages.project.similar;

public class AuthSimilar {
	public SimilarBean get_similarity(String author_a, String author_b){
		GetSimilarity g = new GetSimilarity(author_a, author_b);
		SimilarBean sb = g.get_value();
		return sb;
	}
	
	public static void main(String[] args){
		GetSimilarity g = new GetSimilarity("Fabio Casati", "Maurizio Marchese");
		SimilarBean sb = g.get_value();
		System.out.println(sb.getAb_similarity());
		System.out.println(sb.getBa_similarity());
		System.out.println(sb.getGeneral_similarity());

	}
}
