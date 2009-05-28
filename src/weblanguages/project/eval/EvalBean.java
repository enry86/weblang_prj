package weblanguages.project.eval;

public class EvalBean {
	public EvalBean(){}
	
	private float h_index;
	private float g_index;
	private float cit_avg;
	private float pub_count;
	
	private float h_max;
	private float h_min;
	
	private float g_max;
	private float g_min;
	
	private int cit_max;
	private int cit_min;
	
	private int pub_max;
	private int pub_min;
	
	private String[] not_found;

	public float getH_index() {
		return h_index;
	}

	public void setH_index(float h_index) {
		this.h_index = h_index;
	}

	public float getG_index() {
		return g_index;
	}

	public void setG_index(float g_index) {
		this.g_index = g_index;
	}

	public float getCit_avg() {
		return cit_avg;
	}

	public void setCit_avg(float cit_avg) {
		this.cit_avg = cit_avg;
	}

	public float getPub_count() {
		return pub_count;
	}

	public void setPub_count(float pub_count) {
		this.pub_count = pub_count;
	}

	public float getH_max() {
		return h_max;
	}

	public void setH_max(float h_max) {
		this.h_max = h_max;
	}

	public float getH_min() {
		return h_min;
	}

	public void setH_min(float h_min) {
		this.h_min = h_min;
	}

	public float getG_max() {
		return g_max;
	}

	public void setG_max(float g_max) {
		this.g_max = g_max;
	}

	public float getG_min() {
		return g_min;
	}

	public void setG_min(float g_min) {
		this.g_min = g_min;
	}

	public int getCit_max() {
		return cit_max;
	}

	public void setCit_max(int cit_max) {
		this.cit_max = cit_max;
	}

	public int getCit_min() {
		return cit_min;
	}

	public void setCit_min(int cit_min) {
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
