package com.itacademy.jd2.apetrov.cms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IRoleOnSite;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;

public class RoleOnSiteServiceTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IRoleOnSite entity = saveNewRoleOnSite();
		final IRoleOnSite entityFromDb = roleOnSiteService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertEquals(entityFromDb.getRole(), entity.getRole());
		assertEquals(entityFromDb.getUserAccount().getId(),
				entity.getUserAccount().getId());
		assertEquals(entityFromDb.getSite().getId(), entity.getSite().getId());
	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IRoleOnSite entity = saveNewRoleOnSite();
		final IUserAccount newUserAccount = saveNewUserAccount();
		final ISite newSite = saveNewSite();
		final String newRole = entity.getRole() + "_updated";

		entity.setRole(newRole);
		entity.setUserAccount(newUserAccount);
		entity.setSite(newSite);

		Thread.sleep(2000);
		roleOnSiteService.save(entity);

		final IRoleOnSite entityFromDb = roleOnSiteService.get(entity.getId());

		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());

		assertEquals(entityFromDb.getRole(), newRole);
		assertEquals(entityFromDb.getUserAccount().getId(), newUserAccount.getId());
		assertEquals(entityFromDb.getSite().getId(), newSite.getId());

	}

	@Test
	public void testGetAll() {
		final int intialCount = roleOnSiteService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewRoleOnSite();
		}

		final List<IRoleOnSite> allEntities = roleOnSiteService.getAll();

		for (final IRoleOnSite entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

			assertNotNull(entityFromDb.getRole());
			assertNotNull(entityFromDb.getUserAccount().getId());
			assertNotNull(entityFromDb.getSite().getId());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());

	}

	@Test
	public void testDelete() {

		final IRoleOnSite entity = saveNewRoleOnSite();
		roleOnSiteService.delete(entity.getId());
		assertNull(roleOnSiteService.get(entity.getId()));

	}

	@Test
	public void testDeleteAll() {

		saveNewRoleOnSite();
		roleOnSiteService.deleteAll();
		assertEquals(0, roleOnSiteService.getAll().size());

	}

}
