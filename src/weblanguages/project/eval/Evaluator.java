package weblanguages.project.eval;

public class Evaluator {
	String[] authors;
	String[] auth_urls;
	
	public Evaluator(String[] authors){
		this.authors = authors;
		auth_urls = name_urls(authors);
	}
	
	public EvalBean get_evaluation(){
		EvalBean eval = new EvalBean();
		return eval;
	}
	
	private String[] name_urls(String[] auth){
		String[] res = new String[auth.length];
		String tmp;
		String[] tmp_split;
		for (int i = 0; i < auth.length; i++){
			tmp = auth[i];
			tmp_split = tmp.split(" ");
			
		}
		return res;
	}
}
