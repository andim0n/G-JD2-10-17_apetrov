package com.itacademy.jd2.apetrov.cms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IUserAccount;

public interface IUserAccountService {

    IUserAccount createEntity();

    IUserAccount get(Integer id);

    List<IUserAccount> getAll();

    void delete(Integer id);

    void deleteAll();

    void save(IUserAccount entity);
    
}
