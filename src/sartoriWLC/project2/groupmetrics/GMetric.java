package sartoriWLC.project2.groupmetrics;

/**
 * This bean summarizes the computed metric for a group of authors
 * Is implemented as a Bean in order to easily send it via SOAP using
 * the Axis beanmapping feature.
 * @author enry
 *
 */
public class GMetric {
	/**
	 * Name of the metric used
	 */
	private String metric_name;
	
	/**
	 * Type of the metric used, useful to transform the value in its 
	 * correct representation
	 */
	private String metric_type;
	
	/**
	 * Actual value of the selected metric for the group
	 */
	private String metric_value;
	
	/**
	 * Uri of the report
	 */
	private String group_metric_uri;
	
	public GMetric(){}

	public String getMetric_name() {
		return metric_name;
	}

	public void setMetric_name(String metric_name) {
		this.metric_name = metric_name;
	}

	public String getMetric_type() {
		return metric_type;
	}

	public void setMetric_type(String metric_type) {
		this.metric_type = metric_type;
	}

	public String getMetric_value() {
		return metric_value;
	}

	public void setMetric_value(String metric_value) {
		this.metric_value = metric_value;
	}

	public String getGroup_metric_uri() {
		return group_metric_uri;
	}

	public void setGroup_metric_uri(String group_metric_uri) {
		this.group_metric_uri = group_metric_uri;
	}
}
