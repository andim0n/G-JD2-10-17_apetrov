package com.itacademy.jd2.apetrov.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.apetrov.cms.dao.api.IChangeHistoryDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IChangeHistory;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.ChangeHistory;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Page;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.util.PreparedStatementAction;

@Repository
public class ChangeHistoryDaoImpl extends AbstractDaoImpl<IChangeHistory, Integer>
		implements IChangeHistoryDao {

	@Override
	protected IChangeHistory parseRow(final ResultSet resultSet) throws SQLException {
		final IChangeHistory entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setComment(resultSet.getString("comment"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IPage page = new Page();
		page.setId(resultSet.getInt("page_id"));
		entity.setPage(page);

		final IUserAccount userAccount = new UserAccount();
		userAccount.setId(resultSet.getInt("user_account_id"));
		entity.setUserAccount(userAccount);

		return entity;
	}

	@Override
	public IChangeHistory createEntity() {
		return new ChangeHistory();
	}

	@Override
	public void update(IChangeHistory entity) {
		executeStatement(new PreparedStatementAction<IChangeHistory>(String.format(
				"update %s set page_id=?, user_account_id=?, comment=?, updated=? where id=?",
				getTableName())) {

			@Override
			public IChangeHistory doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getPage().getId());
				pStmt.setInt(2, entity.getUserAccount().getId());
				pStmt.setString(3, entity.getComment());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}

		});
	}

	@Override
	public void insert(IChangeHistory entity) {
		executeStatement(new PreparedStatementAction<IChangeHistory>(String.format(
				"insert into %s (page_id, user_account_id, comment, created, updated) values(?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IChangeHistory doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {

				pStmt.setInt(1, entity.getPage().getId());
				pStmt.setInt(2, entity.getUserAccount().getId());
				pStmt.setString(3, entity.getComment());
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
		return "change_history";
	}

}
