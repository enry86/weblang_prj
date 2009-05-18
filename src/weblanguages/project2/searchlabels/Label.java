package weblanguages.project2.searchlabels;

public class Label {
	private int id_label;
	private String Label_name;
	private String label_value;
	
	public Label(){}

	public int getId_label() {
		return id_label;
	}

	public void setId_label(int id_label) {
		this.id_label = id_label;
	}

	public String getLabel_name() {
		return Label_name;
	}

	public void setLabel_name(String label_name) {
		Label_name = label_name;
	}

	public String getLabel_value() {
		return label_value;
	}

	public void setLabel_value(String label_value) {
		this.label_value = label_value;
	}
}
