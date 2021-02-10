package com.itacademy.jd2.apetrov.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;

public class PageServiceTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IPage entity = saveNewPage();
		final IPage entityFromDb = pageService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

//		assertEquals(entity.getSite().getId(), entityFromDb.getSite().getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getPath(), entityFromDb.getPath());
		assertEquals(entity.getTemplate(), entityFromDb.getTemplate());
		assertEquals(entity.getStatus(), entityFromDb.getStatus());

	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IPage entity = saveNewPage();

		final ISite newSite = saveNewSite();
		Integer newSiteId = newSite.getId();
		entity.setSite(newSite);

		String newName = entity.getName() + "_updated";
		entity.setName(newName);

		String newPath = entity.getPath() + "_updated";
		entity.setPath(newPath);

		String newTemplate = entity.getTemplate() + "_updated";
		entity.setTemplate(newTemplate);

		String newStatus = entity.getStatus() + "_updated";
		entity.setStatus(newStatus);

		Thread.sleep(2000);
		pageService.save(entity);

		final IPage entityFromDb = pageService.get(entity.getId());

		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());

		assertEquals(newSiteId, entityFromDb.getSite().getId()); // ??????
		assertEquals(newName, entityFromDb.getName());
		assertEquals(newPath, entityFromDb.getPath());
		assertEquals(newTemplate, entityFromDb.getTemplate());
		assertEquals(newStatus, entityFromDb.getStatus());
	}

	@Test
	public void testGetAll() {
		final int intialCount = pageService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPage();
		}

		final List<IPage> allEntities = pageService.getAll();

		for (final IPage entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getPath());
			assertNotNull(entityFromDb.getTemplate());
			assertNotNull(entityFromDb.getStatus());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IPage entity = saveNewPage();
		pageService.delete(entity.getId());
		assertNull(pageService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		for (int i = 0; i < 10; i++) {
			saveNewPage();
		}
		pageService.deleteAll();
		assertEquals(0, pageService.getAll().size());
	}

}
