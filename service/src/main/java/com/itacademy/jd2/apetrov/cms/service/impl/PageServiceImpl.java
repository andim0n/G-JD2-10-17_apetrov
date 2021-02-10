package com.itacademy.jd2.apetrov.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.apetrov.cms.dao.api.IPageDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.service.IPageService;

@Service
public class PageServiceImpl implements IPageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PageServiceImpl.class);
	private IPageDao dao;

	@Autowired
	public PageServiceImpl(IPageDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPage createEntity() {
		IPage entity = dao.createEntity();
		LOGGER.info("Page created: {}", entity);
		return entity;
	}

	@Override
	public IPage get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<IPage> getAll() {
		final List<IPage> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("Page deleted: {}", dao.get(id));
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("All pages deleted");
		dao.deleteAll();
	}

	@Override
	public void save(IPage entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			LOGGER.info("Page created: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.info("Page updated: {}", entity);
			dao.update(entity);
		}
	}

}
