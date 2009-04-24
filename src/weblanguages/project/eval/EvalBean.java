package weblanguages.project.eval;

public class EvalBean {
	public EvalBean(){}
	
	private double h_rank;
	private double g_rank;
	private double h_max;
	private double h_min;
	private double g_max;
	private double g_min;
	private int no_rank;
	
	public double getH_rank(){
		return h_rank;
	}
	
	public void setH_rank(double h_rank){
		this.h_rank=h_rank;
	}
	
	public double getG_rank(){
		return g_rank;
	}
	
	public void setG_rank(double g_rank){
		this.g_rank=g_rank;
	}
	
	public double getH_max(){
		return h_max;
	}
	
	public void setH_max(double h_max){
		this.h_max=h_max;
	}
	
	public double getH_min(){
		return h_min;
	}
	
	public void setH_min(double h_min){
		this.h_min=h_min;
	}
	
	public double getG_max(){
		return g_max;
	}
	
	public void setG_max(double g_max){
		this.g_max=g_max;
	}
	
	public double getG_min(){
		return g_min;
	}
	
	public void setG_min(double g_min){
		this.g_min=g_min;
	}
	
	public int getNo_rank(){
		return no_rank;
	}
	
	public void setNo_rank(int no_rank){
		this.no_rank=no_rank;
	}
}
