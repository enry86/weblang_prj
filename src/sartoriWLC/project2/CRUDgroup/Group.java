package sartoriWLC.project2.CRUDgroup;

/**
 * This class represents a group tuple in the database, is in the form of 
 * JavaBean in order to be sent in SOAP messages through the Axis 
 * beanmapping 
 * @author enry
 *
 */
public class Group {
	public Group(){}
	
	/**
	 * Group unique identifier
	 */
	private int id_group;
	
	/**
	 * Name of the group
	 */
	private String group_name;
	
	/**
	 * Uri of the group
	 */
	private String group_uri;
	
	
	public int getId_group() {
		return id_group;
	}
	public void setId_group(int id_group) {
		this.id_group = id_group;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_uri() {
		return group_uri;
	}
	public void setGroup_uri(String group_uri) {
		this.group_uri = group_uri;
	}
}
