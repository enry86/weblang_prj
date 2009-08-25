package sartoriWLC.project2.groupmetrics;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.rpc.ServiceException;

import it.eval.wl.sartoriWLC.*;

public class GMetricDb {
	private Connection con;
	private Statement stm;
	private ResultSet rs;
	
	public GMetricDb(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SartoriWLC?" + "user=SartoriWLC&password=tM6GdwYQ");
			stm = con.createStatement();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public GMetric eval_group(int id_group, int id_metric){
		GMetric res = null;
		String[] auth = get_authors(id_group);
		get_metric_info(id_metric, res);
		if (res != null && auth.length > 0){
			res.setMetric_value(get_evaluation(auth, id_metric));
		}
		close_db();
		return res;
	}
	
	private String get_evaluation(String[] auths, int metric){
		String res = null;
		EvalBean eb;
		GroupEval stub_e;
		GroupEvalService serv_e;
		serv_e = new GroupEvalServiceLocator();
		try {
			stub_e = serv_e.getSartoriWLC_GroupEval();
			eb = stub_e.getAuthorsRank(auths);
		} catch (ServiceException e) {
			eb = null;
		} catch (RemoteException e) {
			eb = null;
		}
		if (eb != null){
			if (metric == 1) res = Float.toString(eb.getH_index());
			else if (metric == 2) res = Float.toString(eb.getG_index());
			else if (metric == 3) res = Float.toString(eb.getCit_avg());
			else if (metric == 4) res = Float.toString(eb.getPub_count());
			else if (metric == 5) res = Float.toString(eb.getH_max());
			else if (metric == 6) res = Float.toString(eb.getH_min());
			else if (metric == 7) res = Float.toString(eb.getG_max());
			else if (metric == 8) res = Float.toString(eb.getG_min());
			else if (metric == 9) res = Integer.toString(eb.getPub_max());
			else if (metric == 10) res = Integer.toString(eb.getPub_min());
			else if (metric == 11) res = Integer.toString(eb.getCit_max());
			else if (metric == 12) res = Integer.toString(eb.getCit_min());

		}
		return res;
	}
	
	private String[] get_authors(int g){
		String[] res;
		int count;
		int i = 0;
		String sql = "SELECT concat(first_name, ' ', last_name) as `name` from `person`, `group_membership` where `person`.`id_person` = `group_membership`.`id_person` and `group_membership`.`id_group` = " + g;
		try {
			rs = stm.executeQuery(sql);
			rs.last();
			count = rs.getRow();
			rs.beforeFirst();
		} catch (SQLException e) {
			count = 0;
		}
		res = new String[count];
		try {
			while(rs.next()){
				res[i] = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private void get_metric_info(int id_metric, GMetric gm){
		String sql = "select `metric_name`, `metric_type` from `metric` where `id_metric` = " + id_metric;
		int count = 0;
		try {
			rs = stm.executeQuery(sql);
			rs.last();
			count = rs.getRow();
			rs.beforeFirst();
		} catch (SQLException e) {
			gm = null;
		}
		if (count != 1) gm = null;
		else {
			try {
				while (rs.next()){
					gm.setMetric_name(rs.getString("metric_name"));
					gm.setMetric_type(rs.getString("metric_type"));
				}
			} catch (SQLException e) {
				gm = null;
			}
			
		}
	}
	
	private void close_db(){
		if (con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
		if (stm != null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stm = null;
		}
		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
}
