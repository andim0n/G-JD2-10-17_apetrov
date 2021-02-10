package com.itacademy.jd2.apetrov.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IContent;

public class ContentServiceTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IContent entity = saveNewContent();
		final IContent entityFromDb = contentService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertEquals(entityFromDb.getBlock().getId(), entity.getBlock().getId());
		assertEquals(entityFromDb.getNumber(), entity.getNumber());
		assertEquals(entityFromDb.getHtml(), entity.getHtml());

	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IContent entity = saveNewContent();
		final IBlock entityBlock = saveNewBlock();

		entity.setBlock(entityBlock);

		Integer newOrder = entity.getNumber() + getRANDOM().nextInt(10) + 5;
		entity.setNumber(newOrder);

		String newHtml = entity.getHtml() + getRandomPrefix();
		entity.setHtml(newHtml);

		Thread.sleep(2000);
		contentService.save(entity);

		final IContent entityFromDb = contentService.get(entity.getId());

		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());

		assertEquals(entityBlock.getId(), entity.getBlock().getId());
		assertEquals(newOrder, entityFromDb.getNumber());
		assertEquals(newHtml, entity.getHtml());

	}

	@Test
	public void testGetAll() {

		final int intialCount = contentService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewContent();
		}

		final List<IContent> allEntities = contentService.getAll();

		for (final IContent entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

			assertNotNull(entityFromDb.getBlock().getId());
			assertNotNull(entityFromDb.getNumber());
			assertNotNull(entityFromDb.getHtml());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());

	}

	@Test
	public void testDelete() {

		final IContent entity = saveNewContent();
		contentService.delete(entity.getId());
		assertNull(contentService.get(entity.getId()));

	}

	@Test
	public void testDeleteAll() {

		saveNewContent();
		contentService.deleteAll();
		assertEquals(0, contentService.getAll().size());

	}

}
