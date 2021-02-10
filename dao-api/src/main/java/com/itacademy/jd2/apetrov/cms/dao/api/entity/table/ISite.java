package com.itacademy.jd2.apetrov.cms.dao.api.entity.table;

public interface ISite extends IBaseEntity {

	void setName(String name);

	void setBaseTemplate(String baseTemplate);

	void setMenu(String menu);

	String getName();

	String getBaseTemplate();

	String getMenu();

}
