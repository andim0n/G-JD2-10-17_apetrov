package com.itacademy.jd2.apetrov.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;

public class UserAccountServiceTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IUserAccount entity = saveNewUserAccount();
		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getMail(), entityFromDb.getMail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());

	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IUserAccount entity = saveNewUserAccount();

		String newName = entity.getName() + "_updated";
		String newMail = entity.getMail() + "_updated";
		String newPassword = entity.getPassword() + "_updated";

		entity.setName(newName);
		entity.setMail(newMail);
		entity.setPassword(newPassword);

		Thread.sleep(2000);
		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());

		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getMail(), entityFromDb.getMail());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());

	}

	@Test
	public void testGetAll() {
		final int intialCount = userAccountService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserAccount();
		}

		final List<IUserAccount> allEntities = userAccountService.getAll();

		for (final IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getMail());
			assertNotNull(entityFromDb.getPassword());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());

	}

	@Test
	public void testDelete() {

		final IUserAccount entity = saveNewUserAccount();
		userAccountService.delete(entity.getId());
		assertNull(userAccountService.get(entity.getId()));

	}

	@Test
	public void testDeleteAll() {

		saveNewUserAccount();
		userAccountService.deleteAll();
		assertEquals(0, userAccountService.getAll().size());

	}

}
