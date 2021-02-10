package com.itacademy.jd2.apetrov.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IChangeHistory;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;

public class ChangeHistoryServiceTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IChangeHistory entity = saveNewChangeHistory(saveNewPage());
		final IChangeHistory entityFromDb = changeHistoryService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertEquals(entityFromDb.getPage().getId(), entity.getPage().getId());
		assertEquals(entityFromDb.getUserAccount().getId(),
				entity.getUserAccount().getId());
		assertEquals(entityFromDb.getComment(), entity.getComment());

	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IChangeHistory entity = saveNewChangeHistory(saveNewPage());
		final IPage newPage = saveNewPage();
		final IUserAccount newUserAccount = saveNewUserAccount();
		final String newComment = entity.getComment() + "_updated";

		entity.setPage(newPage);
		entity.setUserAccount(newUserAccount);
		entity.setComment(newComment);

		Thread.sleep(2000);
		changeHistoryService.save(entity);

		final IChangeHistory entityFromDb = changeHistoryService.get(entity.getId());

		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());

		assertEquals(newPage.getId(), entityFromDb.getPage().getId());
		assertEquals(newUserAccount.getId(), entityFromDb.getUserAccount().getId());
		assertEquals(newComment, entityFromDb.getComment());

	}

	@Test
	public void testGetAll() {
		final int intialCount = changeHistoryService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewChangeHistory(saveNewPage());
		}

		final List<IChangeHistory> allEntities = changeHistoryService.getAll();

		for (final IChangeHistory entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

			assertNotNull(entityFromDb.getPage());
			assertNotNull(entityFromDb.getUserAccount());
			assertNotNull(entityFromDb.getComment());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());

	}

	@Test
	public void testDelete() {

		final IChangeHistory entity = saveNewChangeHistory(saveNewPage());
		changeHistoryService.delete(entity.getId());
		assertNull(changeHistoryService.get(entity.getId()));

	}

	@Test
	public void testDeleteAll() {

		saveNewChangeHistory(saveNewPage());
		changeHistoryService.deleteAll();
		assertEquals(0, changeHistoryService.getAll().size());

	}

}
