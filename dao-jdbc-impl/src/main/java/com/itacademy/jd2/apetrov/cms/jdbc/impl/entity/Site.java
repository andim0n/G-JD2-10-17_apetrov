package com.itacademy.jd2.apetrov.cms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;

public class Site extends BaseEntity implements ISite {

	private String name;
	private String baseTemplate;
	private String menu;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setBaseTemplate(String baseTemplate) {
		this.baseTemplate = baseTemplate;
	}

	@Override
	public void setMenu(String menu) {
		this.menu = menu;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getBaseTemplate() {
		return baseTemplate;
	}

	@Override
	public String getMenu() {
		return menu;
	}

	@Override
	public String toString() {
		return "Site [name=" + name + ", baseTemplate=" + baseTemplate + ", menu=" + menu
				+ "]";
	}

}
