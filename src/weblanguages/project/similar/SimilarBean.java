package weblanguages.project.similar;

public class SimilarBean {
	public SimilarBean(){}
	
	private float ab_similarity;
	private float ba_similarity;
	private float general_similarity;
	
	public float getAb_similarity() {
		return ab_similarity;
	}
	
	public void setAb_similarity(float ab_similarity) {
		this.ab_similarity = ab_similarity;
	}
	
	public float getBa_similarity() {
		return ba_similarity;
	}
	
	public void setBa_similarity(float ba_similarity) {
		this.ba_similarity = ba_similarity;
	}
	
	public float getGeneral_similarity() {
		return general_similarity;
	}
	
	public void setGeneral_similarity(float general_similarity) {
		this.general_similarity = general_similarity;
	}
}
