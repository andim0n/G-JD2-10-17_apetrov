package com.itacademy.jd2.apetrov.cms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IMenuItem;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;

public class MenuItem extends BaseEntity implements IMenuItem {

	ISite site;
	IPage page;
	String name;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setSite(ISite site) {
		this.site = site;
	}

	@Override
	public void setPage(IPage page) {
		this.page = page;
	}

	@Override
	public ISite getSite() {
		return site;
	}

	@Override
	public IPage getPage() {
		return page;
	}

	@Override
	public String toString() {
		return "MenuItem [site=" + site + ", page=" + page + ", name=" + name + "]";
	}

}
