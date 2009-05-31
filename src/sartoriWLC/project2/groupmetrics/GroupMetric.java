package sartoriWLC.project2.groupmetrics;

/**
 * This class provides support for computing and reporting a scientific metric
 * for a group of authors
 * @author enry
 *
 */
public class GroupMetric {
	/**
	 * Computes the specified metric for the selected group and generates
	 * an html report reachable at the uri in the GMetric bean 
	 * @param id_group group identifier
	 * @param id_metric metric identifier
	 * @return a GMetric Bean containing the informations about 
	 * the computed metric
	 */
	public GMetric getGroupMetric(int id_group, int id_metric){
		GMetric gm = new GMetric();
		gm.setMetric_name("Prova");
		return gm;
	}
	
}
