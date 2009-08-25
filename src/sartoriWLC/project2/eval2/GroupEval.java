package sartoriWLC.project2.eval2;

public class GroupEval {
	public EvalBean getAuthorsRank(String[] authors){
		Evaluator eval = new Evaluator(authors);
		EvalBean res = eval.get_evaluation();
		return res;
	}
}
