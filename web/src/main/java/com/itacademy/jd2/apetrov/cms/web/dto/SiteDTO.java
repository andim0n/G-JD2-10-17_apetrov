package com.itacademy.jd2.apetrov.cms.web.dto;

import java.util.Date;

public class SiteDTO {

	private Integer id;
	private String name;
	private String baseTemplate;
	private String menu;
	private Date created;
	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseTemplate() {
		return baseTemplate;
	}

	public void setBaseTemplate(String baseTemplate) {
		this.baseTemplate = baseTemplate;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
