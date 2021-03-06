package sartoriWLC.project2.CRUDgroup;

/**
 * This JavaBean represents a record in the group_label table in the 
 * database
 * @author enry
 *
 */
public class GroupLabel {
	public GroupLabel(){}
	
	/**
	 * unique id of the group
	 */
	private int id_group;
	
	/**
	 * unique in of the label
	 */
	private int id_label;
	
	
	public int getId_group() {
		return id_group;
	}
	
	public void setId_group(int id_group) {
		this.id_group = id_group;
	}
	
	public int getId_label() {
		return id_label;
	}

	public void setId_label(int id_label) {
		this.id_label = id_label;
	}
}
