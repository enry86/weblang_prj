package weblanguages.project.coauthors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class DblpAnalyzer  {
	private ArrayList<String> authors;
	private HashSet<String>[] coauthors;
	
	private String filename;
	private DblpParser parser;
	
	public DblpAnalyzer(String file){
		filename = file;
		parser = new DblpParser(filename);
	}
	
	public Coauthors[] getCoauthors(String[] auth){
		authors = get_authors(auth);
		coauthors = parser.start_parsing(authors);
		Coauthors[] results = coauthors_beans(authors,coauthors);
		return results;
	}
	
	private Coauthors[] coauthors_beans(ArrayList<String> auth,HashSet<String>[] coauth){
		Coauthors[] res = new Coauthors[auth.size()];
		for (int i = 0; i < auth.size(); i++){
			res[i] = new Coauthors();
			res[i].setAuthor(auth.get(i));
			res[i].setCoauthors(convert_string(coauth[i]));
		}
		return res;
	}
	
	private String[] convert_string(HashSet<String> hash){
		String[] res = new String[hash.size()];
		Iterator<String> i = hash.iterator();
		int count = 0;
		while (i.hasNext()){
			res[count] = i.next();
			count++;
		}
		return res;
	}
	
	
	private ArrayList<String> get_authors(String[] auth){
		ArrayList<String> res = new ArrayList<String>(auth.length);
		for (int i = 0; i < auth.length; i++){
			res.add(auth[i]);
		}
		return res;
	}
		
	public static void main(String[] args){
		DblpAnalyzer a = new DblpAnalyzer("/home/enry/dblp.xml");
		Coauthors[] res;
		String[] input = new String[2];
		input[0] = "Fabio Casati";
		input[1] = "Maurizio Marchese";
		res=a.getCoauthors(input);
		for (int i = 0; i < input.length; i++){
			System.out.println("#######"+input[i]);
			String[] coauth = res[i].getCoauthors();
			for (int k = 0; k < coauth.length; k++){
				System.out.println(coauth[k]);
			}
		}
	}
}
