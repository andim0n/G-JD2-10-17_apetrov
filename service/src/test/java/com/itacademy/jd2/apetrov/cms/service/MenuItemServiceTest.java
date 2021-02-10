package com.itacademy.jd2.apetrov.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IMenuItem;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;

public class MenuItemServiceTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IMenuItem entity = saveNewMenuItem(saveNewPage(), saveNewSite());
		final IMenuItem entityFromDb = menuItemService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertEquals(entity.getPage().getId(), entityFromDb.getPage().getId());
		assertEquals(entity.getSite().getId(), entityFromDb.getSite().getId());
		assertEquals(entity.getName(), entityFromDb.getName());

	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IMenuItem entity = saveNewMenuItem(saveNewPage(), saveNewSite());
		final ISite newSite = saveNewSite();
		final IPage newPage = saveNewPage();
		final String newName = entity.getName() + getRandomPrefix() + "_updated";

		entity.setSite(newSite);
		entity.setPage(newPage);
		entity.setName(newName);

		Thread.sleep(2000);
		menuItemService.save(entity);

		final IMenuItem entityFromDb = menuItemService.get(entity.getId());

		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());

		assertEquals(newSite.getId(), entityFromDb.getSite().getId());
		assertEquals(newPage.getId(), entityFromDb.getPage().getId());
		assertEquals(newName, entityFromDb.getName());

	}

	@Test
	public void testGetAll() {
		final int intialCount = menuItemService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewMenuItem(saveNewPage(), saveNewSite());
		}

		final List<IMenuItem> allEntities = menuItemService.getAll();

		for (final IMenuItem entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

			assertNotNull(entityFromDb.getSite());
			assertNotNull(entityFromDb.getPage());
			assertNotNull(entityFromDb.getName());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());

	}

	@Test
	public void testDelete() {

		final IMenuItem entity = saveNewMenuItem(saveNewPage(), saveNewSite());
		menuItemService.delete(entity.getId());
		assertNull(menuItemService.get(entity.getId()));

	}

	@Test
	public void testDeleteAll() {

		saveNewMenuItem(saveNewPage(), saveNewSite());
		menuItemService.deleteAll();
		assertEquals(0, menuItemService.getAll().size());

	}

}
