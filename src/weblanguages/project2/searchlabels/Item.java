package weblanguages.project2.searchlabels;

public class Item {
	private int id_item;
	private String item_type;
	private String item_name;
	private String item_uri;
	
	public Item(){}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_uri() {
		return item_uri;
	}

	public void setItem_uri(String item_uri) {
		this.item_uri = item_uri;
	}
}
