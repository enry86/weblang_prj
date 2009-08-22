package sartoriWLC.project2.CRUDpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;


public class PersonDb {
	private Connection con;
	private Statement stm;
	private ResultSet res_set;
	
	public PersonDb(){
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
	
	public boolean insert_person(Person p){
		boolean res = true;
		String vals = "";
		ArrayList<String> values = new ArrayList<String>();
		values.add(p.getFirst_name());
		values.add(p.getLast_name());
		values.add(p.getCitizenship());
		values.add(p.getTitle());
		values.add(p.getEmail());
		values.add(p.getPerson_uri());
		Iterator<String> i = values.iterator();
		while (i.hasNext() && res){
			String tmp = i.next();
			if (tmp == null) res = false;
			else if (!i.hasNext()){
				vals += "'" + tmp + "'";
			}
			else vals += "'" + tmp + "', ";
		}
		if (res){
			String sql = "INSERT INTO `person` VALUES (NULL," + vals + ")";
			try {
				stm.execute(sql);
			} catch (SQLException e) {
				res = false;			
			}
		}
		close_db();
 		return res;
	}
	
	public Person[] query_person(Person g){
		Person[] res = null;
		String sql2 = "";
		ArrayList<String> fields = get_fields(g, true);
		for (int i = 0; i < fields.size(); i++){
			if (i == 0) sql2 = "where " + fields.get(i);
			else sql2 += " and " + fields.get(i);
		}
		String sql = "SELECT * FROM `person`" + sql2;
		try {
			res_set = stm.executeQuery(sql);
			res = to_person_beans();
		} catch (SQLException e) {
			res = null;
		}
		close_db();
		return res;
	}
	
	public boolean update_person(Person mp, Person ug){
		boolean res = true;
		String sql;
		String sql_set = "";
		String sql_where = "";
		ArrayList<String> f_match = get_fields(mp, true);
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
			sql = "UPDATE `person`" + sql_set + sql_where;
			try {
				stm.execute(sql);
			} catch (SQLException e) {
				res = false;
			}
		}
		close_db();
		return res;
	}
	
	public boolean delete_person(Person p){
		boolean res = true;
		ArrayList<String> fields = get_fields(p,true);
		String sql2 = "";
		for (int i = 0; i < fields.size(); i++){
			if (i == 0) sql2 = " where " + fields.get(i);
			else sql2 += " and " + fields.get(i);
		}
		String sql = "DELETE FROM `person`" + sql2;
		try {
			stm.execute(sql);
		} catch (SQLException e) {
			res = false;
		}
		close_db();
		return res;
	}
	
	private Person[] to_person_beans(){
		Person[] res = null;
		int count = 0;
		try {
			res_set.last();
			count = res_set.getRow();
			res_set.beforeFirst();
		} catch (SQLException e) {
			res = null;
		}
		res = new Person[count];
		count = 0;
		try {
			while (res_set.next()){
				res[count] = new Person();
				res[count].setId_person(res_set.getInt("id_person"));
				res[count].setFirst_name(res_set.getString("first_name"));
				res[count].setLast_name(res_set.getString("last_name"));
				res[count].setCitizenship(res_set.getString("citizenship"));
				res[count].setTitle(res_set.getString("title"));
				res[count].setEmail(res_set.getString("email"));
				res[count].setPerson_uri(res_set.getString("person_uri"));
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private ArrayList<String> get_fields(Person p, boolean get_id){
		String id, fname, lname, cit, title, email, uri;
		ArrayList<String> fields = new ArrayList<String>();
		if (p.getId_person() > 0 && get_id){
			id = "id_person = " + Integer.toString(p.getId_person());
			fields.add(id);
		}
		if (p.getFirst_name() != null){
			fname = "first_name = '" + p.getFirst_name() + "' ";
			fields.add(fname);
		}
		if (p.getLast_name() != null){
			lname = "last_name = '" + p.getLast_name() + "' ";
			fields.add(lname);
		}
		if (p.getCitizenship() != null){
			cit = "citizenship = '" + p.getCitizenship() + "' ";
			fields.add(cit);
		}
		if (p.getTitle() != null){
			title = "title = '" + p.getTitle() + "' ";
			fields.add(title);
		}
		if (p.getEmail() != null){
			email = "email = '" + p.getEmail() + "' ";
			fields.add(email);
		}
		if (p.getPerson_uri() != null){
			uri = "person_uri = '" + p.getPerson_uri() + "' ";
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
