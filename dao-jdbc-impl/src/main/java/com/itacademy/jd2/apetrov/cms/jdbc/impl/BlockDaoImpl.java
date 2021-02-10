package com.itacademy.jd2.apetrov.cms.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.apetrov.cms.dao.api.IBlockDao;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Block;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.entity.Page;
import com.itacademy.jd2.apetrov.cms.jdbc.impl.util.PreparedStatementAction;

@Repository
public class BlockDaoImpl extends AbstractDaoImpl<IBlock, Integer> implements IBlockDao {

	@Override
	protected IBlock parseRow(final ResultSet resultSet) throws SQLException {
		final IBlock entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setNumber(resultSet.getInt("number"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		Integer pageId = resultSet.getInt("page_id");
		if (pageId != null) {
			final IPage page = new Page();
			page.setId(pageId);
			// TODO parse other columns
			entity.setPage(page);
		}

		return entity;
	}

	@Override
	public IBlock createEntity() {
		return new Block();
	}

	@Override
	public void update(IBlock entity) {
		executeStatement(new PreparedStatementAction<IBlock>(
				String.format("update %s set page_id=?, number=?, updated=? where id=?",
						getTableName())) {

			@Override
			public IBlock doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getPage().getId());
				pStmt.setInt(2, entity.getNumber());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}

		});
	}

	@Override
	public void insert(IBlock entity) {
		executeStatement(new PreparedStatementAction<IBlock>(String.format(
				"insert into %s (page_id, number, created, updated) values(?,?,?,?)",
				getTableName()), true) {

			@Override
			public IBlock doWithPreparedStatement(final PreparedStatement pStmt)
					throws SQLException {
				pStmt.setInt(1, entity.getPage().getId());
				pStmt.setInt(2, entity.getNumber());
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
		return "block";
	}

}
