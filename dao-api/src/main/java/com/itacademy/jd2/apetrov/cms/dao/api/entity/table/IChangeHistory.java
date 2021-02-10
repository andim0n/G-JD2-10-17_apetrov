package com.itacademy.jd2.apetrov.cms.dao.api.entity.table;

public interface IChangeHistory extends IBaseEntity {

	void setPage(IPage page);

	void setUserAccount(IUserAccount userAccount);

	void setComment(String comment);

	IPage getPage();

	IUserAccount getUserAccount();

	String getComment();

}
