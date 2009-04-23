package weblanguages.project.eval;

import java.io.Serializable;

public class EvalBean implements Serializable {
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
	
}
