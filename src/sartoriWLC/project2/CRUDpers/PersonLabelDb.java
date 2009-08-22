package sartoriWLC.project2.CRUDpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class PersonLabelDb {
	private Connection con;
	private Statement stm;
	private ResultSet res_set;
	
	public PersonLabelDb(){
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
	
	public boolean create_pl(PersonLabel pl){
		String sql = "";
		boolean res = true;
		if (pl.getId_user() > 0 && pl.getId_label() > 0){
			sql = "INSERT INTO `person_label` values (" + pl.getId_user() + ", " + pl.getId_label() + ")";
			try {
				stm.execute(sql);
			} catch (SQLException e) {
				res = false;
			}
		}
		close_db();
		return res;
	}
	
	public PersonLabel[] query_pl(PersonLabel pl){
		PersonLabel[] res = null;
		String sql2 = "";
		ArrayList<String> fields = get_fields(pl);
		for (int i = 0; i < fields.size(); i++){
			if (i == 0) sql2 = " where " + fields.get(i);
			else sql2 += " and " + fields.get(i);
		}
		String sql = "SELECT * FROM `person_label`" + sql2;
		try {
			res_set = stm.executeQuery(sql);
			res = to_personlabel_beans();
		} catch (SQLException e) {
			res = null;
		}
		close_db();
		return res;
	}
	
	public boolean update_pl(PersonLabel m_pl, PersonLabel u_pl){
		boolean res = true;
		String sql_set = "";
		String sql_where = "";
		ArrayList<String> m_fields = get_fields(m_pl);
		ArrayList<String> u_fields = get_fields(u_pl);
		if (u_fields.size() == 0) res = false;
		else {
			for (int i = 0; i < u_fields.size(); i++){
				if (i == 0) sql_set = " set " + u_fields.get(i);
				else sql_set += ", " + u_fields.get(i);
			}
			for (int k = 0; k < m_fields.size(); k++){
				if (k == 0) sql_where = " where " + m_fields.get(k);
				else sql_where += ", " + m_fields.get(k);
			}
			String sql = "UPDATE `person_label`" + sql_set + sql_where;
			try {
				stm.execute(sql);
			} catch (SQLException e) {
				res = false;
			}
		}
		close_db();
		return res;
	}
	
	public boolean delete_pl(PersonLabel pl){
		boolean res = true;
		String sql2 = "";
		String sql = "";
		ArrayList<String> fields = get_fields(pl);
		for (int i = 0; i < fields.size(); i++){
			if (i == 0) sql2 = " where " + fields.get(i);
			else sql2 += " and " + fields.get(i);
		}
		sql = "DELETE FROM `person_label`" + sql2;
		try {
			stm.execute(sql);
		} catch (SQLException e) {
			res = false;
		}
		close_db();
		return res;
	}
	
	private PersonLabel[] to_personlabel_beans(){
		PersonLabel[] res;
		int count = 0;
		try {
			res_set.last();
			count = res_set.getRow();
			res_set.beforeFirst();
		} catch (SQLException e) {
			res = null;
		}
		res = new PersonLabel[count];
		int i = 0;
		try {
			while (res_set.next()){
				res[i] = new PersonLabel();
				res[i].setId_user(res_set.getInt("id_user"));
				res[i].setId_label(res_set.getInt("id_label"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;		
	}
	
	private ArrayList<String> get_fields(PersonLabel pl){
		String usr, lab;
		ArrayList<String> fields = new ArrayList<String>();
		if (pl.getId_user() > 0){
			usr = "id_user = " + Integer.toString(pl.getId_user());
			fields.add(usr);
		}
		if (pl.getId_label() > 0){
			lab = "id_label = '" + pl.getId_label() + "' ";
			fields.add(lab);
		}
		return fields;
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
		if (res_set != null){
			try {
				res_set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			res_set = null;
		}
	}
	
	
	
	
}
