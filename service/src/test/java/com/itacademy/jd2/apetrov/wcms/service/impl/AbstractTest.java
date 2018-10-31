package com.itacademy.jd2.apetrov.wcms.service.impl;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import com.itacademy.jd2.apetrov.wcms.dao.api.entity.model.IProperty;
import com.itacademy.jd2.apetrov.wcms.service.IPropertyService;

public abstract class AbstractTest {

	protected IPropertyService propertyService = new PropertiesServiceImpl();

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive
		propertyService.deleteAll();

	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected IProperty saveNewProperty() {
		final IProperty entity = propertyService.createEntity();
		entity.setName("key_" + getRandomPrefix());
		entity.setValue("value_" + getRandomPrefix());
		propertyService.save(entity);
		return entity;
	}
}
