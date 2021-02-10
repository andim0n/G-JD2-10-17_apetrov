package com.itacademy.jd2.apetrov.cms.dao.api.entity.table;

public interface IRoleOnSite extends IBaseEntity {

	void setRole(String userRole);

	void setUserAccount(IUserAccount userAccount);

	void setSite(ISite site);

	String getRole();

	IUserAccount getUserAccount();

	ISite getSite();

}
