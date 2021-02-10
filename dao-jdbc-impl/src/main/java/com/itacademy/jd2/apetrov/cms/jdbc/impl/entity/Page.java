package com.itacademy.jd2.apetrov.cms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;

public class Page extends BaseEntity implements IPage {

	ISite site;
	String name;
	String path;
	String template;
	String status;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public String getTemplate() {
		return template;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setSite(ISite site) {
		this.site = site;
	}

	@Override
	public ISite getSite() {
		return site;
	}

	@Override
	public String toString() {
		return "Page [site=" + site + ", name=" + name + ", path=" + path + ", template="
				+ template + ", status=" + status + "]";
	}

}
