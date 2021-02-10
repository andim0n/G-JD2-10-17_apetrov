package com.itacademy.jd2.apetrov.cms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;

public class Block extends BaseEntity implements IBlock {

	IPage page;
	Integer number;

	@Override
	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public Integer getNumber() {
		return number;
	}

	@Override
	public void setPage(IPage page) {
		this.page = page;
	}

	@Override
	public IPage getPage() {
		return page;
	}

	@Override
	public String toString() {
		return "Block [page=" + page + ", number=" + number + "]";
	}

}
