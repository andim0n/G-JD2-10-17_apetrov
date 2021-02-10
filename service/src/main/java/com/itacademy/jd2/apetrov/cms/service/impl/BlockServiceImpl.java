package com.itacademy.jd2.apetrov.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.apetrov.cms.dao.api.IBlockDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;
import com.itacademy.jd2.apetrov.cms.service.IBlockService;

@Service
public class BlockServiceImpl implements IBlockService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BlockServiceImpl.class);
	private IBlockDao dao;

	@Autowired
	public BlockServiceImpl(IBlockDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IBlock createEntity() {
		IBlock entity = dao.createEntity();
		LOGGER.info("Block created: {}", entity);
		return entity;
	}

	@Override
	public IBlock get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<IBlock> getAll() {
		List<IBlock> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		LOGGER.info("Block deleted: {}", dao.get(id));
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("All blocks deleted");
		dao.deleteAll();
	}

	@Override
	public void save(IBlock entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			LOGGER.info("Block created: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.info("Block updated: {}", entity);
			dao.update(entity);
		}
	}

}
