package com.itacademy.jd2.apetrov.cms.dao.api.entity.table;

public interface IUserAccount extends IBaseEntity {

    void setName(String name);

    void setMail(String mail);

    void setPassword(String password);

    String getName();

    String getMail();

    String getPassword();

}
