package com.itacademy.jd2.apetrov.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.apetrov.cms.dao.api.IContentDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IContent;
import com.itacademy.jd2.apetrov.cms.service.IContentService;

@Service
public class ContentServiceImpl implements IContentService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContentServiceImpl.class);
	private IContentDao dao;

	@Autowired
	public ContentServiceImpl(IContentDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IContent createEntity() {
		IContent entity = dao.createEntity();
		LOGGER.info("Content created: {}", entity);
		return entity;
	}

	@Override
	public IContent get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<IContent> getAll() {
		List<IContent> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("Content deleted: {}", dao.get(id));
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("All content deleted");
		dao.deleteAll();
	}

	@Override
	public void save(IContent entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			LOGGER.info("Content created: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.info("Content updated: {}", entity);
			dao.update(entity);
		}
	}

}