package com.itacademy.jd2.apetrov.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.apetrov.cms.dao.api.IPageDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Page;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Site;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.util.PreparedStatementAction;

@Repository
public class PageDaoImpl extends AbstractDaoImpl<IPage, Integer> implements IPageDao {

	@Override
	protected IPage parseRow(final ResultSet resultSet) throws SQLException {
		final IPage entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setName(resultSet.getString("name"));
		entity.setPath(resultSet.getString("path"));
		entity.setTemplate(resultSet.getString("template"));
		entity.setStatus(resultSet.getString("status"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer siteId = resultSet.getInt("site_id");
		if (siteId != null) {
			final ISite site = new Site();
			site.setId(siteId);
			entity.setSite(site);
		}
		return entity;
	}

	@Override
	public IPage createEntity() {
		return new Page();
	}

	@Override
	public void update(IPage entity) {
		executeStatement(new PreparedStatementAction<IPage>(String.format(
				"update %s set site_id=?, name=?, path=?, template=?, status=?, updated=? where id=?",
				getTableName())) {

			@Override
			public IPage doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getSite().getId());
				pStmt.setString(2, entity.getName());
				pStmt.setString(3, entity.getPath());
				pStmt.setString(4, entity.getTemplate());
				pStmt.setString(5, entity.getStatus());
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(7, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}

		});
	}

	@Override
	public void insert(IPage entity) {
		executeStatement(new PreparedStatementAction<IPage>(String.format(
				"insert into %s (site_id, name, path, template, status, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IPage doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getSite().getId());
				pStmt.setString(2, entity.getName());
				pStmt.setString(3, entity.getPath());
				pStmt.setString(4, entity.getTemplate());
				pStmt.setString(5, entity.getStatus());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);
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
		return "page";
	}

}
