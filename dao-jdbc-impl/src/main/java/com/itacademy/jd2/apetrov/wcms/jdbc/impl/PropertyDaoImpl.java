package com.itacademy.jd2.apetrov.wcms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.itacademy.jd2.apetrov.wcms.dao.api.IPropertyDao;
import com.itacademy.jd2.apetrov.wcms.dao.api.entity.model.IProperty;
import com.itacademy.jd2.apetrov.wcms.jdbc.impl.entity.Property;
import com.itacademy.jd2.apetrov.wcms.jdbc.impl.util.PreparedStatementAction;

public class PropertyDaoImpl extends AbstractDaoImpl<IProperty, Integer> implements IPropertyDao {

	protected IProperty parseRow(final ResultSet resultSet) throws SQLException {
		final IProperty entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setName(resultSet.getString("value"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public IProperty createEntity() {
		return new Property();
	}

	@Override
	public void update(IProperty entity) {
		executeStatement(new PreparedStatementAction<IProperty>(
				String.format("update %s set name=?, value=?, updated=? where id=?", getTableName())) {
			@Override
			public IProperty doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getValue());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IProperty entity) {
		executeStatement(new PreparedStatementAction<IProperty>(
				String.format("insert into %s (name, value, created, updated) values(?,?,?,?)", getTableName()), true) {
			@Override
			public IProperty doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getValue());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

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
		return "property";
	}

}
