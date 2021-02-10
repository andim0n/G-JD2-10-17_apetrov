package com.itacademy.jd2.apetrov.cms.dao.api.entity.table;

public interface IPage extends IBaseEntity {

    void setSite(ISite site);

    void setName(String name);

    void setPath(String path);

    void setTemplate(String template);

    void setStatus(String status);

    ISite getSite();

    String getName();

    String getPath();

    String getTemplate();

    String getStatus();

}
