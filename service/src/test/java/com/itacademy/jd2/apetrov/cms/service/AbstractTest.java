package com.itacademy.jd2.apetrov.cms.service;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IChangeHistory;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IContent;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IMenuItem;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IRoleOnSite;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.apetrov.cms.service.IBlockService;
import com.itacademy.jd2.apetrov.cms.service.IChangeHistoryService;
import com.itacademy.jd2.apetrov.cms.service.IContentService;
import com.itacademy.jd2.apetrov.cms.service.IMenuItemService;
import com.itacademy.jd2.apetrov.cms.service.IPageService;
import com.itacademy.jd2.apetrov.cms.service.IRoleOnSiteService;
import com.itacademy.jd2.apetrov.cms.service.ISiteService;
import com.itacademy.jd2.apetrov.cms.service.IUserAccountService;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {

	@Autowired
	protected ISiteService siteService;

	@Autowired
	protected IPageService pageService;

	@Autowired
	protected IBlockService blockService;

	@Autowired
	protected IContentService contentService;

	@Autowired
	protected IMenuItemService menuItemService;

	@Autowired
	protected IChangeHistoryService changeHistoryService;

	@Autowired
	protected IUserAccountService userAccountService;

	@Autowired
	protected IRoleOnSiteService roleOnSiteService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive
		changeHistoryService.deleteAll();
		menuItemService.deleteAll();
		contentService.deleteAll();
		blockService.deleteAll();
		roleOnSiteService.deleteAll();
		userAccountService.deleteAll();
		pageService.deleteAll();
		siteService.deleteAll();

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

	// site
	protected ISite saveNewSite() {
		final ISite entity = siteService.createEntity();
		entity.setName("site-" + getRandomPrefix());
		entity.setBaseTemplate("template-" + getRandomPrefix());
		entity.setMenu("menu-" + getRandomPrefix());
		siteService.save(entity);
		return entity;
	}

	// page
	protected IPage saveNewPage() {
		final IPage entity = pageService.createEntity();
		entity.setSite(saveNewSite());
		entity.setName("namepage-" + getRandomPrefix());
		entity.setPath("path-" + getRandomPrefix());
		entity.setTemplate("pagetemplate-" + getRandomPrefix());
		entity.setStatus("status-" + getRandomPrefix());
		pageService.save(entity);
		return entity;
	}

	// block
	protected IBlock saveNewBlock() {
		IBlock entity = blockService.createEntity();
		entity.setPage(saveNewPage());
		entity.setNumber(getRANDOM().nextInt(100) + 50); // ?
		blockService.save(entity);
		return entity;
	}

	// content
	protected IContent saveNewContent() {
		IContent entity = contentService.createEntity();
		entity.setBlock(saveNewBlock());
		entity.setNumber(getRANDOM().nextInt(50) + 100); // ?
		entity.setHtml("html-" + getRandomPrefix());
		contentService.save(entity);
		return entity;
	}

	// menu item
	protected IMenuItem saveNewMenuItem(IPage page, ISite site) {
		IMenuItem entity = menuItemService.createEntity();
		entity.setPage(page);
		entity.setSite(site);
		entity.setName("menuitem-" + getRandomPrefix());
		menuItemService.save(entity);
		return entity;
	}

	// change history
	protected IChangeHistory saveNewChangeHistory(IPage page) {
		IChangeHistory entity = changeHistoryService.createEntity();
		entity.setPage(page);
		entity.setUserAccount(saveNewUserAccount());
		entity.setComment("comment-" + getRandomPrefix());
		changeHistoryService.save(entity);
		return entity;
	}

	// user account
	protected IUserAccount saveNewUserAccount() {
		IUserAccount entity = userAccountService.createEntity();
		entity.setName("user-" + getRandomPrefix());
		entity.setMail("mail-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		userAccountService.save(entity);
		return entity;
	}

	// role on site
	protected IRoleOnSite saveNewRoleOnSite() {
		IRoleOnSite entity = roleOnSiteService.createEntity();
		entity.setRole("role-" + getRandomPrefix());
		entity.setUserAccount(saveNewUserAccount());
		entity.setSite(saveNewSite());
		roleOnSiteService.save(entity);
		return entity;
	}

}
