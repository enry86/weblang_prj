package sartoriWLC.project2.CRUDgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GroupDb {
	Connection con;
	Statement stm;
	ResultSet res_set;
	
	public GroupDb(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SartoriWLC?"+"user=SartoriWLC&password=tM6GdwYQ");
			stm = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean insert_group(Group g){
		boolean res = true;
		String name = g.getGroup_name();
		String uri = g.getGroup_uri();
		if(name == null || uri == null) res = false;
		else{
			String sql = "INSERT INTO `group` VALUES (NULL,'" + name + "','" + uri + "')";
			try {
				stm.execute(sql);
			} catch (SQLException e) {
				res = false;			
			}
			close_db();
		}
 		return res;
	}
	
	public Group[] query_group(Group g){
		Group[] res = null;
		String sql2 = "";
		ArrayList<String> fields = get_fields(g, true);
		for (int i = 0; i < fields.size(); i++){
			if (i == 0) sql2 = "where " + fields.get(i);
			else sql2 += " and " + fields.get(i);
		}
		
		String sql = "SELECT * FROM `group`" + sql2;
		try {
			res_set = stm.executeQuery(sql);
			res = to_group_beans();
		} catch (SQLException e) {
			res = null;
		}
		return res;
	}
	
	public boolean update_group(Group mg, Group ug){
		boolean res = true;
		String sql;
		String sql_set = "";
		String sql_where = "";
		ArrayList<String> f_match = get_fields(mg, false);
		ArrayList<String> f_upd = get_fields(ug, false);
		if (f_upd.size() == 0) res = false;
		else{
			for (int i = 0; i < f_upd.size(); i++){
				if (i == 0) sql_set = " set " + f_upd.get(i);
				else sql_set += ", " + f_upd.get(i);
			}
			for (int k = 0; k < f_match.size(); k++){
				if (k == 0) sql_where = " where " + f_match.get(k);
				else sql_where += " and " + f_match.get(k);
			}
			sql = "UPDATE `group`" + sql_set + sql_where;
			try {
				stm.execute(sql);
			} catch (SQLException e) {
				res = false;
			}
		}
		return res;
	}
	
	public boolean delete_group(Group g){
		boolean res = true;
		ArrayList<String> fields = get_fields(g,true);
		String sql2 = "";
		for (int i = 0; i < fields.size(); i++){
			if (i == 0) sql2 = " where " + fields.get(i);
			else sql2 += " and " + fields.get(i);
		}
		String sql = "DELETE FROM `group`" + sql2;
		try {
			stm.execute(sql);
		} catch (SQLException e) {
			res = false;
		}
		return res;
	}
	
	private Group[] to_group_beans(){
		Group[] res = null;
		int count = 0;
		try {
			res_set.last();
			count = res_set.getRow();
			res_set.beforeFirst();
		} catch (SQLException e) {
			res = null;
		}
		res = new Group[count];
		count = 0;
		try {
			while (res_set.next()){
				res[count] = new Group();
				res[count].setGroup_name(res_set.getString("group_name"));
				res[count].setGroup_uri(res_set.getString("group_uri"));
				res[count].setId_group(res_set.getInt("id_group"));
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private ArrayList<String> get_fields(Group g, boolean get_id){
		String id, name, uri;
		ArrayList<String> fields = new ArrayList<String>();
		if (g.getId_group() > 0 && get_id){
			id = "id_group = " + Integer.toString(g.getId_group());
			fields.add(id);
		}
		if (g.getGroup_name() != null){
			name = "group_name = '" + g.getGroup_name() + "' ";
			fields.add(name);
		}
		if (g.getGroup_uri() != null){
			uri = "group_uri = '" + g.getGroup_uri() + "' ";
			fields.add(uri);
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
