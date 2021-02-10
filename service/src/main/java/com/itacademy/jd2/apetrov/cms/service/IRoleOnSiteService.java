package com.itacademy.jd2.apetrov.cms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IRoleOnSite;

public interface IRoleOnSiteService {
    
    IRoleOnSite createEntity();

    IRoleOnSite get(Integer id);

    List<IRoleOnSite> getAll();

    void delete(Integer id);

    void deleteAll();

    void save(IRoleOnSite entity);
}
