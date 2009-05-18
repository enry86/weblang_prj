package weblanguages.project2.groupmetrics;

public class GMetric {
	private String metric_name;
	private String metric_type;
	private String metric_value;
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
