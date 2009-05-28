package weblanguages.project.eval;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

public class Evaluator {
	String[] authors;
	String[] auth_urls;
	EvalParser parser;
	
	public Evaluator(String[] authors){
		this.authors = authors;
		auth_urls = name_urls(authors);
		parser = new EvalParser();
	}
	
	public EvalBean get_evaluation(){
		EvalBean eval = new EvalBean();
		float hind = 0, gind = 0, count = 0, cite = 0;
		
		float max_hind = 0, max_gind = 0; 
		int max_count = 0, max_cite = 0;
		
		float min_hind = 0, min_gind = 0; 
		int min_count = 0, min_cite = 0;
		
		ArrayList<String> n_found = new ArrayList<String>();
		for (int k = 0; k < auth_urls.length; k++){
			Hashtable<String,String> res = parser.getEvaluation(auth_urls[k]);
			if (res == null) n_found.add(authors[k]);
			else{
				float h = Float.valueOf(res.get("h-index")); 
				hind += h;
				if (h > max_hind) max_hind = h;
				if (k == 0) min_hind = h;
				else if (h < min_hind) min_hind = h;
				
				float g = Float.valueOf(res.get("g-index")); 
				gind += g;
				if (g > max_gind) max_gind = g;
				if (k == 0) min_gind = g;
				else if (g < min_gind) min_gind = g;
				
				float cit = Float.valueOf(res.get("citationsCount")); 
				cite += cit;
				if (cit > max_cite) max_cite = (int)cit;
				if (k == 0) min_cite = (int)cit;
				else if (cit < min_cite) min_cite = (int)cit;
				
				float pub = Float.valueOf(res.get("numberOfPublications")); 
				count += pub;
				if (pub > max_count) max_count = (int)pub;
				if (k == 0) min_count = (int)pub;
				else if(pub < min_count) min_count = (int)pub;
			}
		}
		int tot = authors.length - n_found.size();
		if (tot != 0){
			eval.setCit_avg(cite/tot);
			eval.setG_index(gind/tot);
			eval.setH_index(hind/tot);
			eval.setPub_count(count/tot);
		}
		
		eval.setCit_max(max_cite);
		eval.setCit_min(min_cite);
		eval.setG_max(max_gind);
		eval.setG_min(min_gind);
		eval.setH_max(max_hind);
		eval.setH_min(min_hind);
		eval.setPub_max(max_count);
		eval.setPub_min(min_count);
		
		String[] nf = new String[n_found.size()];
		n_found.toArray(nf);
		eval.setNot_found(nf);
		
		return eval;
	}
	
	private String[] name_urls(String[] auth){
		String[] res = new String[auth.length];
		String tmp;
		String[] tmp_split;
		String first;
		String last;
		String mid = "";
		for (int i = 0; i < auth.length; i++){
			tmp = auth[i];
			tmp_split = tmp.split(" ");
			first = tmp_split[0] + "/";
			last = "/" + tmp_split[tmp_split.length-1];
			if (tmp_split.length <= 2) mid = "%20";
			else {
				for (int k = 1; k < tmp_split.length - 1; k++){
					if (k == 1) mid += tmp_split[k];
					else mid += "%20"+tmp_split[k];
				}
			}
			res[i] = first + mid + last;
		}
		return res;
	}
	
	
	
	
	

	
}
