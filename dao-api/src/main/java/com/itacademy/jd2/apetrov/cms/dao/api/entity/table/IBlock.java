package com.itacademy.jd2.apetrov.cms.dao.api.entity.table;

public interface IBlock extends IBaseEntity {

    void setPage(IPage page);

    void setNumber(Integer number);

    IPage getPage();

    Integer getNumber();

}
