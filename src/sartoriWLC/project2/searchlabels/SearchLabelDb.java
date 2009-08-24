package sartoriWLC.project2.searchlabels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchLabelDb {
	private Connection con;
	private Statement stm;
	private ResultSet rs;
	
	public SearchLabelDb(){
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
	
	public Item[] search_single(Label ml){
		ArrayList<String> values = get_values(ml);
		String sql_w = build_sql(values, false);
		String sub_sql = "SELECT `id_label` FROM `label` " + sql_w;
		return query_db(sub_sql);
	}
	
	public Item[] search_multi(Label[] mls, boolean all){
		String op;
		if (all) op = " and ";
		else op = " or ";
		ArrayList<String> values;
		String sub_sql = "SELECT `id_label` FROM `label` ";
		for (int i = 0; i < mls.length; i++){
			values = get_values(mls[i]);
			String tmp = build_sql(values, true);
			if (i == 0) sub_sql += "where ( " + tmp + " )";
			else sub_sql += op + "( " + tmp + " )";
		}
		return query_db(sub_sql);
	}

	
	private String build_sql(ArrayList<String> values, boolean multi){
		String sql_w = "";
		String w = "where";
		if (multi) w = "";
		for (int i = 0; i < values.size(); i++){
			if (i == 0) sql_w = w + " " + values.get(i);
			else sql_w += " and " + values.get(i);
		}
		return sql_w;
	}
	
	
	private Item[] query_db(String sub_sql){
		Item[] res = null;
		String tmp_tab = "CREATE TEMPORARY TABLE `items` (id_item integer, item_type varchar(30), item_name varchar(50), item_uri text)";
		String sql_per = "SELECT `id_person`, 'person', concat(first_name, ' ', last_name) as name, person_uri from `person`, `person_label` where `person`.id_person = `person_label`.id_user and `person_label`.id_label in (" + sub_sql + ")";
		String sql_grp = "SELECT `group`.`id_group`, 'group', group_name, group_uri from `group`, `group_label` where `group`.id_group = `group_label`.id_group and `group_label`.id_label in (" + sub_sql + ")";
		String sql_met = "SELECT `metric`.`id_metric`, 'metric', metric_name, metric_uri from `metric`, `metric_label` where `metric`.id_metric = `metric_label`.id_metric and `metric_label`.id_label in (" + sub_sql + ")";
		String sql_com = "SELECT `comparison`.`id_comparison`, 'comparison', 'comparison', comp_uri from `comparison`, `comparison_label` where `comparison`.id_comparison = `comparison_label`.id_comparison and `comparison_label`.id_label in (" + sub_sql + ")";
		String base_sql = "insert into `items` ";
		String query = "select * from `items`";
		try {
			stm.execute(tmp_tab);
			stm.execute(base_sql + sql_per);
			stm.execute(base_sql + sql_grp);
			stm.execute(base_sql + sql_met);
			stm.execute(base_sql + sql_com);
			rs = stm.executeQuery(query);
			res = to_item_beans();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private Item[] to_item_beans(){
		Item[] res;
		int count = 0;
		try {
			rs.last();
			count = rs.getRow();
			rs.beforeFirst();
		} catch (SQLException e) {
			res = null;
		}
		res = new Item[count];
		int i = 0;
		try {
			while (rs.next()){
				res[i] = new Item();
				res[i].setId_item(rs.getInt("id_item"));
				res[i].setItem_type(rs.getString("item_type"));
				res[i].setItem_name(rs.getString("item_name"));
				res[i].setItem_uri(rs.getString("item_uri"));
				i++;
			}
		} catch (SQLException e) {
			res = null;
		}
		return res;		
	}
	
	private ArrayList<String> get_values(Label l){
		ArrayList<String> res = new ArrayList<String>();
		int id = l.getId_label();
		String name = l.getLabel_name();
		String value = l.getLabel_value();
		if (id > 0){
			res.add("id_label = " + id);
			
		}
		else {
			if (name != null) res.add("label_name = '" + name + "' ");
			if (value != null) res.add("label_value = '" + value + "' ");
		}
		return res;		
	}
}
