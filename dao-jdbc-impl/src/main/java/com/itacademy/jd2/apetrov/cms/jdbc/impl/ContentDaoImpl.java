package com.itacademy.jd2.apetrov.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.apetrov.cms.dao.api.IContentDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IContent;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Block;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Content;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.util.PreparedStatementAction;

@Repository
public class ContentDaoImpl extends AbstractDaoImpl<IContent, Integer>
		implements IContentDao {

	@Override
	protected IContent parseRow(final ResultSet resultSet) throws SQLException {
		final IContent entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setNumber(resultSet.getInt("number"));
		entity.setHtml(resultSet.getString("html"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IBlock block = new Block();
		block.setId(resultSet.getInt("block_id"));
		entity.setBlock(block);

		return entity;
	}

	@Override
	public IContent createEntity() {
		return new Content();
	}

	@Override
	public void update(IContent entity) {
		executeStatement(new PreparedStatementAction<IContent>(String.format(
				"update %s set block_id=?, number=?, html=?, updated=? where id=?",
				getTableName())) {

			@Override
			public IContent doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getBlock().getId());
				pStmt.setInt(2, entity.getNumber());
				pStmt.setString(3, entity.getHtml());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}

		});
	}

	@Override
	public void insert(IContent entity) {
		executeStatement(new PreparedStatementAction<IContent>(String.format(
				"insert into %s (block_id, number, html, created, updated) values(?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IContent doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getBlock().getId());
				pStmt.setInt(2, entity.getNumber());
				pStmt.setString(3, entity.getHtml());
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
		return "content";
	}

}
