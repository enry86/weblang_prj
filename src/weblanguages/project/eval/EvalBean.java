package weblanguages.project.eval;

public class EvalBean {
	public EvalBean(){}
	
	private double h_index;
	private double g_index;
	private double cit_avg;
	private double pub_count;
	
	private double h_max;
	private double h_min;
	
	private double g_max;
	private double g_min;
	
	private double cit_max;
	private double cit_min;
	
	private int pub_max;
	private int pub_min;
	
	private String[] not_found;

	public double getH_index() {
		return h_index;
	}

	public void setH_index(double h_index) {
		this.h_index = h_index;
	}

	public double getG_index() {
		return g_index;
	}

	public void setG_index(double g_index) {
		this.g_index = g_index;
	}

	public double getCit_avg() {
		return cit_avg;
	}

	public void setCit_avg(double cit_avg) {
		this.cit_avg = cit_avg;
	}

	public double getPub_count() {
		return pub_count;
	}

	public void setPub_count(double pub_count) {
		this.pub_count = pub_count;
	}

	public double getH_max() {
		return h_max;
	}

	public void setH_max(double h_max) {
		this.h_max = h_max;
	}

	public double getH_min() {
		return h_min;
	}

	public void setH_min(double h_min) {
		this.h_min = h_min;
	}

	public double getG_max() {
		return g_max;
	}

	public void setG_max(double g_max) {
		this.g_max = g_max;
	}

	public double getG_min() {
		return g_min;
	}

	public void setG_min(double g_min) {
		this.g_min = g_min;
	}

	public double getCit_max() {
		return cit_max;
	}

	public void setCit_max(double cit_max) {
		this.cit_max = cit_max;
	}

	public double getCit_min() {
		return cit_min;
	}

	public void setCit_min(double cit_min) {
		this.cit_min = cit_min;
	}

	public int getPub_max() {
		return pub_max;
	}

	public void setPub_max(int pub_max) {
		this.pub_max = pub_max;
	}

	public int getPub_min() {
		return pub_min;
	}

	public void setPub_min(int pub_min) {
		this.pub_min = pub_min;
	}

	public String[] getNot_found() {
		return not_found;
	}

	public void setNot_found(String[] not_found) {
		this.not_found = not_found;
	}	
}
