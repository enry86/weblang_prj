package weblanguages.project.eval;

public class GroupEval {
	public EvalBean getAuthorsRank(String[] authors){
		Evaluator eval = new Evaluator(authors);
		EvalBean res = eval.get_evaluation();
		return res;
	}
}
