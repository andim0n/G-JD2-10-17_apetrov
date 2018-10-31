package com.itacademy.jd2.apetrov.wcms.service.impl;

import java.util.Date;
import java.util.List;

import com.itacademy.jd2.apetrov.wcms.dao.api.IPropertyDao;
import com.itacademy.jd2.apetrov.wcms.dao.api.entity.model.IProperty;
import com.itacademy.jd2.apetrov.wcms.jdbc.impl.PropertyDaoImpl;
import com.itacademy.jd2.apetrov.wcms.service.IPropertyService;

public class PropertiesServiceImpl implements IPropertyService {

	private IPropertyDao dao = new PropertyDaoImpl();

	@Override
	public IProperty get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<IProperty> getAll() {
		final List<IProperty> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(IProperty entity) {
		Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);

	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public IProperty createEntity() {
		return dao.createEntity();
	}

}
