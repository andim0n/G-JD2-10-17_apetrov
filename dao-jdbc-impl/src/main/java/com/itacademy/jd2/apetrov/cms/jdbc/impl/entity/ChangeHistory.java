package com.itacademy.jd2.apetrov.cms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IChangeHistory;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;

public class ChangeHistory extends BaseEntity implements IChangeHistory {

	IPage page;
	IUserAccount userAccount;
	String comment;

	@Override
	public void setPage(IPage page) {
		this.page = page;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public IPage getPage() {
		return page;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public String toString() {
		return "ChangeHistory [page=" + page + ", userAccount=" + userAccount
				+ ", comment=" + comment + "]";
	}

}
