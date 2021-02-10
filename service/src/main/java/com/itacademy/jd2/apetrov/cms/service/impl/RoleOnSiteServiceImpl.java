package com.itacademy.jd2.apetrov.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.apetrov.cms.dao.api.IRoleOnSiteDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IRoleOnSite;
import com.itacademy.jd2.apetrov.cms.service.IRoleOnSiteService;

@Service
public class RoleOnSiteServiceImpl implements IRoleOnSiteService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RoleOnSiteServiceImpl.class);
	private IRoleOnSiteDao dao;

	@Autowired
	public RoleOnSiteServiceImpl(IRoleOnSiteDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IRoleOnSite createEntity() {
		IRoleOnSite entity = dao.createEntity();
		LOGGER.info("Role on site created: {}", entity);
		return entity;
	}

	@Override
	public IRoleOnSite get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<IRoleOnSite> getAll() {
		List<IRoleOnSite> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("Role on site deleted: {}", dao.get(id));
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("All roles on site deleted");
		dao.deleteAll();
	}

	@Override
	public void save(IRoleOnSite entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			LOGGER.info("Role on site created: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.info("Role on site updated: {}", entity);
			dao.update(entity);
		}
	}

}
