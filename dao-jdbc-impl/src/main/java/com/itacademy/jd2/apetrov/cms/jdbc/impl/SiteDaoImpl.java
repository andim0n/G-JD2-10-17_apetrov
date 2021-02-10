package com.itacademy.jd2.apetrov.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.apetrov.cms.dao.api.ISiteDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Site;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.util.PreparedStatementAction;

@Repository
public class SiteDaoImpl extends AbstractDaoImpl<ISite, Integer> implements ISiteDao {

	@Override
	protected ISite parseRow(final ResultSet resultSet) throws SQLException {
		final ISite entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setName(resultSet.getString("name"));
		entity.setBaseTemplate(resultSet.getString("base_template"));
		entity.setMenu(resultSet.getString("menu"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public ISite createEntity() {
		return new Site();
	}

	@Override
	public void update(ISite entity) {
		executeStatement(new PreparedStatementAction<ISite>(String.format(
				"update %s set name=?, base_template=?, menu=?, updated=? where id=?",
				getTableName())) {

			@Override
			public ISite doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getBaseTemplate());
				pStmt.setString(3, entity.getMenu());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}

		});
	}

	@Override
	public void insert(ISite entity) {
		executeStatement(new PreparedStatementAction<ISite>(String.format(
				"insert into %s (name, base_template, menu, created, updated) values(?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public ISite doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getBaseTemplate());
				pStmt.setString(3, entity.getMenu());
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
		return "site";
	}

	@Override
	public List<ISite> find(SiteFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(SiteFilter filter) {
		return executeCountQuery("");
	}

}
