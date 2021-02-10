package com.itacademy.jd2.apetrov.cms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IMenuItem;

public interface IMenuItemService {
    
    IMenuItem createEntity();

    IMenuItem get(Integer id);

    List<IMenuItem> getAll();

    void delete(Integer id);

    void deleteAll();

    void save(IMenuItem entity);
}
