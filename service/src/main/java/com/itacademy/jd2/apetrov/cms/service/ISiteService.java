package com.itacademy.jd2.apetrov.cms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.filter.SiteFilter;

public interface ISiteService {

	ISite createEntity();

	ISite get(Integer id);

	List<ISite> getAll();

	void delete(Integer id);

	void deleteAll();

	void save(ISite entity);

	List<ISite> find(SiteFilter filter);

	long getCount(SiteFilter filter);

}
