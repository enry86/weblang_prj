package weblanguages.project2.CRUDpers;

/**
 * This JavaBean represents the fields in the person_label table in the
 * database.
 * Is implemented as a Bean in order to easily send it via SOAP using
 * the Axis beanmapping feature.
 * @author enry
 *
 */
public class PersonLabel {
	public PersonLabel(){}
	
	/**
	 * Unique identifier for a person
	 */
	private int id_user;
	
	/**
	 * Unique identifier for a label
	 */
	private int id_label;
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_label() {
		return id_label;
	}
	public void setId_label(int id_label) {
		this.id_label = id_label;
	}
	
	
}
