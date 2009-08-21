package sartoriWLC.project2.CRUDgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupLabelDb {
	private Connection con;
	private Statement stm;
	private ResultSet res_set;
	
	public GroupLabelDb(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SartoriWLC?"+"user=SartoriWLC&password=tM6GdwYQ");
			stm = con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean create_gl(GroupLabel gl){
		String sql = "";
		boolean res = true;
		if (gl.getId_group() > 0 && gl.getId_label() > 0){
			sql = "INSERT INTO `group_label` values (" + gl.getId_group() + ", " + gl.getId_label() + ")";
			try {
				stm.execute(sql);
			} catch (SQLException e) {
				res = false;
			}
		}
		close_db();
		return res;
	}
	
	public GroupLabel[] query_gl(GroupLabel gl){
		GroupLabel[] res = null;
		String sql2 = "";
		ArrayList<String> fields = get_fields(gl);
		for (int i = 0; i < fields.size(); i++){
			if (i == 0) sql2 = " where " + fields.get(i);
			else sql2 += " and " + fields.get(i);
		}
		String sql = "SELECT * FROM `group_label`" + sql2;
		try {
			res_set = stm.executeQuery(sql);
			res = to_grouplabel_beans();
		} catch (SQLException e) {
			res = null;
		}
		close_db();
		return res;
	}
	
	public boolean update_gl(GroupLabel m_gl, GroupLabel u_gl){
		boolean res = true;
		String sql_set = "";
		String sql_where = "";
		ArrayList<String> m_fields = get_fields(m_gl);
		ArrayList<String> u_fields = get_fields(u_gl);
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
			String sql = "UPDATE `group_label`" + sql_set + sql_where;
			try {
				stm.execute(sql);
			} catch (SQLException e) {
				res = false;
			}
		}
		close_db();
		return res;
	}
	
	public boolean delete_gl(GroupLabel gl){
		boolean res = true;
		String sql2 = "";
		String sql = "";
		ArrayList<String> fields = get_fields(gl);
		for (int i = 0; i < fields.size(); i++){
			if (i == 0) sql2 = " where " + fields.get(i);
			else sql2 += " and " + fields.get(i);
		}
		sql = "DELETE FROM `group_label`" + sql2;
		try {
			stm.execute(sql);
		} catch (SQLException e) {
			res = false;
		}
		
		close_db();
		return res;
	}
	
	private GroupLabel[] to_grouplabel_beans(){
		GroupLabel[] res;
		int count = 0;
		try {
			res_set.last();
			count = res_set.getRow();
			res_set.beforeFirst();
		} catch (SQLException e) {
			res = null;
		}
		res = new GroupLabel[count];
		int i = 0;
		try {
			while (res_set.next()){
				res[i] = new GroupLabel();
				res[i].setId_group(res_set.getInt("id_group"));
				res[i].setId_label(res_set.getInt("id_label"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;		
	}
	
	private ArrayList<String> get_fields(GroupLabel gl){
		String gr, lab;
		ArrayList<String> fields = new ArrayList<String>();
		if (gl.getId_group() > 0){
			gr = "id_group = " + Integer.toString(gl.getId_group());
			fields.add(gr);
		}
		if (gl.getId_label() > 0){
			lab = "id_label = '" + gl.getId_label() + "' ";
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
