package weblanguages.project2.CRUDgroup;

public class Group {
	public Group(){}
	
	private int id_group;
	private String group_name;
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
