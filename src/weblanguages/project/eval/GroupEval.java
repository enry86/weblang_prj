package weblanguages.project.eval;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GroupEval {
	public EvalBean getAuthorsRank(String[] authors){
		Properties p = System.getProperties();
		Properties file = new Properties();
		/*try {
			file.load(new FileReader("properties/evalGroup.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.put("https.proxyHost",file.get("proxy_host"));
		p.put("https.proxyPort",file.get("proxy_port"));
		*/
		Evaluator eval = new Evaluator(authors);
		EvalBean res = eval.get_evaluation();
		return res;
	}
}
