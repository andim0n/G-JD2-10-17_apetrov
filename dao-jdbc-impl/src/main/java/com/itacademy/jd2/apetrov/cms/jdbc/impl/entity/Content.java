package com.itacademy.jd2.apetrov.cms.jdbc.impl.entity;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;
import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IContent;

public class Content extends BaseEntity implements IContent {

	IBlock block;
	Integer order;
	String html;

	@Override
	public void setNumber(Integer order) {
		this.order = order;
	}

	@Override
	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public Integer getNumber() {
		return order;
	}

	@Override
	public String getHtml() {
		return html;
	}

	@Override
	public void setBlock(IBlock block) {
		this.block = block;
	}

	@Override
	public IBlock getBlock() {
		return block;
	}

	@Override
	public String toString() {
		return "Content [block=" + block + ", order=" + order + ", html=" + html + "]";
	}

}
