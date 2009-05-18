package weblanguages.project2.CRUDpers;

public class PersonOperation {
	public boolean createPerson(Person person){
		return true;
	}
	
	public Person[] readPerson(Person person){
		return new Person[1];
	}
	
	public boolean updatePerson(Person match_person, Person upd_person){
		return true;
	}
	
	public boolean deletePerson(Person match_person){
		return true;
	}
	
	
	
	public boolean createPersonLabel(PersonLabel pers_label){
		return true;
	}
	
	public PersonLabel[] readPersonLabel(PersonLabel pers_label){
		return new PersonLabel[1];
	}
	
	public boolean updatePersonLabel(PersonLabel match_pers_label, PersonLabel upd_pers_label){
		return true;
	}
	
	public boolean deletePersonLabel(PersonLabel match_pers_label){
		return true;
	}
}
