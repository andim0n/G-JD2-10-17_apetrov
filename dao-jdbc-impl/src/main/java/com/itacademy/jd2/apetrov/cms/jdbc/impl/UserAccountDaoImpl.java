package com.itacademy.jd2.apetrov.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.apetrov.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.util.PreparedStatementAction;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer>
		implements IUserAccountDao {

	@Override
	protected IUserAccount parseRow(final ResultSet resultSet) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setName(resultSet.getString("name"));
		entity.setMail(resultSet.getString("mail"));
		entity.setPassword(resultSet.getString("password"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void update(IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"update %s set name=?, mail=?, password=?, updated=? where id=?",
				getTableName())) {

			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getMail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}

		});
	}

	@Override
	public void insert(IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"insert into %s (name, mail, password, created, updated) values(?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getMail());
				pStmt.setString(3, entity.getPassword());
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
		return "user_account";
	}

}
