package com.itacademy.jd2.apetrov.wcms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.wcms.dao.api.entity.model.IProperty;

public class Property extends BaseEntity implements IProperty {

	private String name;
	private String value;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Property [name=" + getName() + ", value=" + getValue() + "]";
	}

}
