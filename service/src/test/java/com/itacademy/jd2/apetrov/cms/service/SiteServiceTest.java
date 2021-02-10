package com.itacademy.jd2.apetrov.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;

public class SiteServiceTest extends AbstractTest {

	@Test
	public void testCreate() {

		final ISite entity = saveNewSite();
		final ISite entityFromDb = siteService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getBaseTemplate(), entityFromDb.getBaseTemplate());
		assertEquals(entity.getMenu(), entityFromDb.getMenu());
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ISite entity = saveNewSite();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);

		String newBaseTemplate = entity.getBaseTemplate() + "_updated";
		entity.setBaseTemplate(newBaseTemplate);

		String newMenu = entity.getMenu() + "_updated";
		entity.setMenu(newMenu);

		Thread.sleep(2000);
		siteService.save(entity);

		final ISite entityFromDb = siteService.get(entity.getId());

		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());

		assertEquals(newName, entityFromDb.getName());
		assertEquals(newBaseTemplate, entityFromDb.getBaseTemplate());
		assertEquals(newMenu, entityFromDb.getMenu());

		assertEquals(entity.getCreated(), entityFromDb.getCreated());

	}

	@Test
	public void testGetAll() {
		final int intialCount = siteService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewSite();
		}

		final List<ISite> allEntities = siteService.getAll();

		for (final ISite entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getBaseTemplate());
			assertNotNull(entityFromDb.getMenu());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ISite entity = saveNewSite();
		siteService.delete(entity.getId());
		assertNull(siteService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewSite();
		siteService.deleteAll();
		assertEquals(0, siteService.getAll().size());
	}

}
