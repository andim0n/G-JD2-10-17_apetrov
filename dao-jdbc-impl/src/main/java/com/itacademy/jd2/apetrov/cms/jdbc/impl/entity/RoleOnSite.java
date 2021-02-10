package com.itacademy.jd2.apetrov.cms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IRoleOnSite;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;

public class RoleOnSite extends BaseEntity implements IRoleOnSite {

	String role;
	IUserAccount userAccount;
	ISite site;

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public void setSite(ISite site) {
		this.site = site;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public ISite getSite() {
		return site;
	}

	@Override
	public void setRole(String userRole) {
		this.role = userRole;
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "RoleOnSite [userRole=" + role + ", userAccount=" + userAccount
				+ ", site=" + site + "]";
	}

}
