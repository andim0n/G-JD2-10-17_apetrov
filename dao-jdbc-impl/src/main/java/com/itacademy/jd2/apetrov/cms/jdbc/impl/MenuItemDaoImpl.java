package com.itacademy.jd2.apetrov.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.apetrov.cms.dao.api.IMenuItemDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IMenuItem;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.MenuItem;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Page;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Site;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.util.PreparedStatementAction;

@Repository
public class MenuItemDaoImpl extends AbstractDaoImpl<IMenuItem, Integer>
		implements IMenuItemDao {

	@Override
	protected IMenuItem parseRow(final ResultSet resultSet) throws SQLException {
		final IMenuItem entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final ISite site = new Site();
		site.setId(resultSet.getInt("site_id"));
		entity.setSite(site);

		final IPage page = new Page();
		page.setId(resultSet.getInt("page_id"));
		entity.setPage(page);

		return entity;
	}

	@Override
	public IMenuItem createEntity() {
		return new MenuItem();
	}

	@Override
	public void update(IMenuItem entity) {
		executeStatement(new PreparedStatementAction<IMenuItem>(String.format(
				"update %s set site_id=?, page_id=?, name=?, updated=? where id=?",
				getTableName())) {

			@Override
			public IMenuItem doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getSite().getId());
				pStmt.setInt(2, entity.getPage().getId());
				pStmt.setString(3, entity.getName());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}

		});
	}

	@Override
	public void insert(IMenuItem entity) {
		executeStatement(new PreparedStatementAction<IMenuItem>(String.format(
				"insert into %s (site_id, page_id, name, created, updated) values(?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IMenuItem doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getSite().getId());
				pStmt.setInt(2, entity.getPage().getId());
				pStmt.setString(3, entity.getName());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");
				rs.close();
				entity.setId(id);

				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "menu_item";
	}

}
