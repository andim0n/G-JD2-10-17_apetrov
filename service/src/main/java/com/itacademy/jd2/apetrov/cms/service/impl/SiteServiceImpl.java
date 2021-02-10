package com.itacademy.jd2.apetrov.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.apetrov.cms.dao.api.ISiteDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.apetrov.cms.service.ISiteService;

@Service
public class SiteServiceImpl implements ISiteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class);
	private ISiteDao dao;

	@Autowired
	public SiteServiceImpl(ISiteDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ISite createEntity() {
		ISite entity = dao.createEntity();
		LOGGER.info("Site created: {}", entity);
		return entity;
	}

	@Override
	public ISite get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<ISite> getAll() {
		List<ISite> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("Site deleted: {}", dao.get(id));
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("All sites deleted");
		dao.deleteAll();
	}

	@Override
	public void save(ISite entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			LOGGER.info("Site created: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.info("Site updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public List<ISite> find(SiteFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(SiteFilter filter) {
		return dao.getCount(filter);
	}

}
