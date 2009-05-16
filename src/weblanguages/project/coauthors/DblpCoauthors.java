package weblanguages.project.coauthors;


public class DblpCoauthors {
	public Coauthors[] getDblpCoauthors(String[] authors){
		Coauthors[] res;
		DblpAnalyzer da = new DblpAnalyzer("/home/enry/dblp.xml");
		res=da.getCoauthors(authors);
		return res;
	}
}
