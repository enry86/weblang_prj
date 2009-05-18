package weblanguages.project2.CRUDgroup;

/**
 * This class performes CRUD operations for the group and group_label tables
 * in the database
 * @author enry
 *
 */
public class GroupOperation {
	
	/**
	 * Creates a new group in the table
	 * @param group A Group Bean containing the values for the new group
	 * @return success or failure of the operation
	 */
	public boolean createGroup(Group group){
		return true;
	}
	
	/**
	 * Performs a search operation in the group table. The variables in the
	 * input group Bean which are unset will not be considered for the search
	 * criteria.
	 * @param group Group Bean containing the search criteria
	 * @return an array of groups satisfying the search criteria 
	 */
	public Group[] readGroup(Group group){
		return new Group[1];
	}
	
	/**
	 * Updates the records identified by match_group object with the not null
	 * values of upd_group
	 * @param match_group Bean containing the search criteria for record to be
	 * updated
	 * @param upd_group Bean containing the new values for the records 
	 * @return success or failure of the operation
	 */
	public boolean updateGroup(Group match_group, Group upd_group){
		return true;
	}
	
	/**
	 * Deletes all the groups identified by the search criteria in match_group
	 * @param match_group Bean containing the search criteria 
	 * @return success or failure of the operation
	 */
	public boolean deleteGroup(Group match_group){
		return true;
	}
	
	
	/**
	 * Creates a new group_label entry
	 * @param group_label bean containing the values for the new record
	 * @return sucess or failure of the operation
	 */
	public boolean createGroupLabel(GroupLabel group_label){
		return true;
	}
	
	/**
	 * Queries the group_label table in the database
	 * @param group_label
	 * @return array of GroupLabel beans satisfying the search criteria
	 */
	public GroupLabel[] readGroupLabel(GroupLabel group_label){
		return new GroupLabel[1];
	}
	
	/**
	 * Performs updates on the group_label table, overwriting the values in
	 * the records identified by match_group_table with the not null values
	 * in upd_group_label
	 * @param match_group_label bean containing search criteria 
	 * @param upd_group_label bean containing new values
	 * @return success or failure of the operation
	 */
	public boolean updateGroupLabel(GroupLabel match_group_label, GroupLabel upd_group_label){
		return true;
	}
	
	/**
	 * Deletes the entries in the table which satisfy the search criteria
	 * @param match_group_label bean containing the seearch criteria
	 * @return success or failure of the operation
	 */
	public boolean deleteGroupLabel(GroupLabel match_group_label){
		return true;
	}
}
