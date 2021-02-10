package com.itacademy.jd2.apetrov.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.apetrov.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.apetrov.cms.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserAccountServiceImpl.class);
	private IUserAccountDao dao;

	@Autowired
	public UserAccountServiceImpl(IUserAccountDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IUserAccount createEntity() {
		IUserAccount entity = dao.createEntity();
		LOGGER.info("User account created: {}", entity);
		return entity;
	}

	@Override
	public IUserAccount get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<IUserAccount> getAll() {
		List<IUserAccount> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("User account deleted: {}", dao.get(id));
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("All user accounts deleted");
		dao.deleteAll();
	}

	@Override
	public void save(IUserAccount entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			LOGGER.info("User account created: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.info("User account updated: {}", entity);
			dao.update(entity);
		}
	}

}
