package weblanguages.project2.CRUDpers;

public class Person {
	private int id_person;
	private String first_name;
	private String last_name;
	private String citizenship;
	private String title;
	private String email;
	private String person_uri;
	
	public Person(){}

	public int getId_person() {
		return id_person;
	}

	public void setId_person(int id_person) {
		this.id_person = id_person;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerson_uri() {
		return person_uri;
	}

	public void setPerson_uri(String person_uri) {
		this.person_uri = person_uri;
	}
	
	
}
