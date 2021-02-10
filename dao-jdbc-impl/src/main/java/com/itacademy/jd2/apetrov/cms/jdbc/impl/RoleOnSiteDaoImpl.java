package com.itacademy.jd2.apetrov.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.apetrov.cms.dao.api.IRoleOnSiteDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IRoleOnSite;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.RoleOnSite;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Site;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.util.PreparedStatementAction;

@Repository
public class RoleOnSiteDaoImpl extends AbstractDaoImpl<IRoleOnSite, Integer>
		implements IRoleOnSiteDao {

	@Override
	protected IRoleOnSite parseRow(final ResultSet resultSet) throws SQLException {
		final IRoleOnSite entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		entity.setRole(resultSet.getString("role"));

		final IUserAccount userAccount = new UserAccount();
		userAccount.setId(resultSet.getInt("user_account_id"));
		entity.setUserAccount(userAccount);

		final ISite site = new Site();
		site.setId(resultSet.getInt("site_id"));
		entity.setSite(site);

		return entity;
	}

	@Override
	public IRoleOnSite createEntity() {
		return new RoleOnSite();
	}

	@Override
	public void update(IRoleOnSite entity) {
		executeStatement(new PreparedStatementAction<IRoleOnSite>(String.format(
				"update %s set role=?, user_account_id=?, site_id=?, updated=? where id=?",
				getTableName())) {

			@Override
			public IRoleOnSite doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setString(1, entity.getRole());
				pStmt.setInt(2, entity.getUserAccount().getId());
				pStmt.setInt(3, entity.getSite().getId());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}

		});
	}

	@Override
	public void insert(IRoleOnSite entity) {
		executeStatement(new PreparedStatementAction<IRoleOnSite>(String.format(
				"insert into %s (role, user_account_id, site_id, created, updated) values(?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IRoleOnSite doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setString(1, entity.getRole());
				pStmt.setInt(2, entity.getUserAccount().getId());
				pStmt.setInt(3, entity.getSite().getId());
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
		return "role_on_site";
	}

}
