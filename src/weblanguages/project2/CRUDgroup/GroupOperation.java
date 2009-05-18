package weblanguages.project2.CRUDgroup;

public class GroupOperation {

	public boolean createGroup(Group group){
		return true;
	}
	
	public Group[] readGroup(Group group){
		return new Group[1];
	}
	
	public boolean updateGroup(Group match_group, Group upd_group){
		return true;
	}
	
	public boolean deleteGroup(Group match_group){
		return true;
	}
	
	
	
	public boolean createGroupLabel(GroupLabel group_label){
		return true;
	}
	
	public GroupLabel[] readGroupLabel(GroupLabel group_label){
		return new GroupLabel[1];
	}
	
	public boolean updateGroupLabel(GroupLabel match_group_label, GroupLabel upd_group_label){
		return true;
	}
	
	public boolean deleteGroupLabel(GroupLabel match_group_label){
		return true;
	}
}
