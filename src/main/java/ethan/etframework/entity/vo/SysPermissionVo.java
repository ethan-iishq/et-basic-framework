package ethan.etframework.entity.vo;

import java.util.List;

import ethan.etframework.entity.SysPermission;
import ethan.etframework.entity.SysRole;

public class SysPermissionVo extends SysPermission{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SysRole> roleList;

	/**
	 * @return the roleList
	 */
	public List<SysRole> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}


	@Override
	public String toString() {
		return "SysPermissionVo{" +
				", id=" + getId() +
				", name=" + getName() +
				", description=" + getDescription() +
				", url=" + getUrl() +
				", pid=" + getPid() +
				", [roleList=" + roleList.toString() + "]"
				+"}";
	}
	
	
}
