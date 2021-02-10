package com.itacademy.jd2.apetrov.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;

public class BlockServiceTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IBlock entity = saveNewBlock();
		final IBlock entityFromDb = blockService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertEquals(entity.getPage().getId(), entityFromDb.getPage().getId()); // ???
		assertEquals(entity.getNumber(), entityFromDb.getNumber());

	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IBlock entity = saveNewBlock();
		final IPage entityPage = saveNewPage();

		entity.setPage(entityPage);

		Integer newNumber = entity.getNumber() + getRANDOM().nextInt(10) + 5;
		entity.setNumber(newNumber);

		Thread.sleep(2000);
		blockService.save(entity);

		final IBlock entityFromDb = blockService.get(entity.getId());

		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());

		assertEquals(newNumber, entityFromDb.getNumber());
		assertEquals(entityPage.getId(), entityFromDb.getPage().getId());

	}

	@Test
	public void testGetAll() {
		final int intialCount = blockService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewBlock();
		}

		final List<IBlock> allEntities = blockService.getAll();

		for (final IBlock entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

			assertNotNull(entityFromDb.getPage());
			assertNotNull(entityFromDb.getNumber());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());

	}

	@Test
	public void testDelete() {

		final IBlock entity = saveNewBlock();
		blockService.delete(entity.getId());
		assertNull(blockService.get(entity.getId()));

	}

	@Test
	public void testDeleteAll() {

		saveNewBlock();
		blockService.deleteAll();
		assertEquals(0, blockService.getAll().size());

	}

}
