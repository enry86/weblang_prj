package weblanguages.project2.searchlabels;

/**
 * This class performs queries over the various tables of the 
 * database considering the labels as search criteria
 * @author enry
 *
 */
public class SearchLabel {
	/**
	 * Search all the items which are associated with the input label
	 * @param match_label the label used as search criteria, null values
	 * won't be considered as search criteria
	 * @return an array of Item beans satisfying the search criteria
	 */
	public Item[] searchLabel(Label match_label){
		return new Item[1];
	}
	
	/**
	 * Queries the database for all the items associated with at least one
	 * of the labels passed as input
	 * @param match_labels search criteria as an array of label beans 
	 * @return array of Item beans satisfying the search criteria
	 */
	public Item[] searchAnyLabel(Label[] match_labels){
		return new Item[1];
	}
	
	/**
	 * Queries the database for all the items associated with at all
	 * the labels passed as input
	 * @param match_labels search criteria as an array of label beans 
	 * @return array of Item beans satisfying the search criteria
	 */
	public Item[] searchAllLabel(Label[] match_labels){
		return new Item[1];
	}	
}
