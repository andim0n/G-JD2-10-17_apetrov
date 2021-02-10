package com.itacademy.jd2.apetrov.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.apetrov.cms.dao.api.IMenuItemDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IMenuItem;
import com.itacademy.jd2.apetrov.cms.service.IMenuItemService;

@Service
public class MenuItemServiceImpl implements IMenuItemService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MenuItemServiceImpl.class);
	private IMenuItemDao dao;

	@Autowired
	public MenuItemServiceImpl(IMenuItemDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IMenuItem createEntity() {
		IMenuItem entity = dao.createEntity();
		LOGGER.info("Menu item created: {}", entity);
		return entity;
	}

	@Override
	public IMenuItem get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<IMenuItem> getAll() {
		List<IMenuItem> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("Menu item delted: {}", dao.get(id));
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("All menu items deleted");
		dao.deleteAll();
	}

	@Override
	public void save(IMenuItem entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			LOGGER.info("Menu item created: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.info("Menu item updated: {}", entity);
			dao.update(entity);
		}
	}

}
