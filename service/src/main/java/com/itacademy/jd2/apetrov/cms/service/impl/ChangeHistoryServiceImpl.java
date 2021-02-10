package com.itacademy.jd2.apetrov.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.apetrov.cms.dao.api.IChangeHistoryDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IChangeHistory;
import com.itacademy.jd2.apetrov.cms.service.IChangeHistoryService;

@Service
public class ChangeHistoryServiceImpl implements IChangeHistoryService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChangeHistoryServiceImpl.class);
	private IChangeHistoryDao dao;

	@Autowired
	public ChangeHistoryServiceImpl(IChangeHistoryDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IChangeHistory createEntity() {
		IChangeHistory entity = dao.createEntity();
		LOGGER.info("Change history created: {}", entity);
		return entity;
	}

	@Override
	public IChangeHistory get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<IChangeHistory> getAll() {
		List<IChangeHistory> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("Change history deleted: {}", dao.get(id));
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("All Change history deleted");
		dao.deleteAll();
	}

	@Override
	public void save(IChangeHistory entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			LOGGER.info("Change history created: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.info("Change history updated: {}", entity);
			dao.update(entity);
		}
	}

}
