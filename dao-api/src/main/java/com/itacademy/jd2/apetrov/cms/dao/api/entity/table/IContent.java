package com.itacademy.jd2.apetrov.cms.dao.api.entity.table;

public interface IContent extends IBaseEntity {

    void setBlock(IBlock block);

    void setNumber(Integer number);

    void setHtml(String html);

    IBlock getBlock();

    Integer getNumber();

    String getHtml();

}
