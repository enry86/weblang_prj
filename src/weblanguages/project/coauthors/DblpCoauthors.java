package weblanguages.project.coauthors;


public class DblpCoauthors {
	public String[] getDblpCoauthors(String[] authors){
		String[] res;
		DblpAnalyzer da = new DblpAnalyzer();
		res=da.getCoauthors(authors[0]);
		return res;
	}
}
