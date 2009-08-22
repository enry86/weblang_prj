package sartoriWLC.project2.CRUDpers;


/**
 * This class performs crud operation over the person and person_label 
 * tables in the database
 * @author enry
 *
 */
public class PersonOperation {
	/**
	 * Creates a new person in the table
	 * @param person A Person Bean containing the values for the new person
	 * @return success or failure of the operation
	 */
	public boolean createPerson(Person person){
		PersonDb db = new PersonDb();
		return db.insert_person(person);
	}
	
	/**
	 * Performs a search operation in the person table. The variables in the
	 * input person Bean which are unset will not be considered for the search
	 * criteria.
	 * @param person Person Bean containing the search criteria
	 * @return an array of Person beans satisfying the search criteria 
	 */
	public Person[] readPerson(Person person){
		PersonDb db = new PersonDb();
		return db.query_person(person);
	}
	
	/**
	 * Updates the records identified by match_person object with the not null
	 * values of upd_person
	 * @param match_person Bean containing the search criteria for record to be
	 * updated
	 * @param upd_person Bean containing the new values for the records 
	 * @return success or failure of the operation
	 */
	public boolean updatePerson(Person match_person, Person upd_person){
		PersonDb db = new PersonDb();
		return db.update_person(match_person, upd_person);
	}
	
	/**
	 * Deletes all the records identified by the search criteria in match_person
	 * @param match_person Bean containing the search criteria 
	 * @return success or failure of the operation
	 */
	public boolean deletePerson(Person match_person){
		PersonDb db = new PersonDb();
		return db.delete_person(match_person);
	}
	
	
	/**
	 * Creates a new person_label entry
	 * @param pers_label bean containing the values for the new record
	 * @return success or failure of the operation
	 */
	public boolean createPersonLabel(PersonLabel pers_label){
		PersonLabelDb db = new PersonLabelDb();
		return db.create_pl(pers_label);
	}
	
	/**
	 * Queries the person_label table in the database
	 * @param pers_label
	 * @return array of PersonLabel beans satisfying the search criteria
	 */
	public PersonLabel[] readPersonLabel(PersonLabel pers_label){
		PersonLabelDb db = new PersonLabelDb();
		return db.query_pl(pers_label);
	}
	
	/**
	 * Performs updates on the person_label table, overwriting the values in
	 * the records identified by match_pers_table with the not null values
	 * in upd_pers_label
	 * @param match_pers_label bean containing search criteria 
	 * @param upd_pers_label bean containing new values
	 * @return success or failure of the operation
	 */
	public boolean updatePersonLabel(PersonLabel match_pers_label, PersonLabel upd_pers_label){
		PersonLabelDb db = new PersonLabelDb();
		return db.update_pl(match_pers_label, upd_pers_label);
	}
	
	/**
	 * Deletes the entries in the table which satisfy the search criteria
	 * @param match_pers_label bean containing the search criteria
	 * @return success or failure of the operation
	 */
	public boolean deletePersonLabel(PersonLabel match_pers_label){
		PersonLabelDb db = new PersonLabelDb();
		return db.delete_pl(match_pers_label);
	}
}
