package com.itacademy.jd2.apetrov.wcms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.wcms.dao.api.entity.model.IProperty;

public interface IPropertyService {

	IProperty get(Integer id);

	List<IProperty> getAll();

	void save(IProperty entity);

	void delete(Integer id);

	void deleteAll();

	IProperty createEntity();

}
