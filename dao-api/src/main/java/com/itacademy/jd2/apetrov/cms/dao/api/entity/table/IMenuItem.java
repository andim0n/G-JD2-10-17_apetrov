package com.itacademy.jd2.apetrov.cms.dao.api.entity.table;

public interface IMenuItem extends IBaseEntity {

    void setSite(ISite site);

    void setPage(IPage page);

    void setName(String name);

    ISite getSite();

    IPage getPage();

    String getName();

}
