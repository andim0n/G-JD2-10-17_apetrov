package com.itacademy.jd2.apetrov.cms.dao.api;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.filter.SiteFilter;

public interface ISiteDao extends IDao<ISite, Integer> {

	List<ISite> find(SiteFilter filter);

	long getCount(SiteFilter filter);

}
