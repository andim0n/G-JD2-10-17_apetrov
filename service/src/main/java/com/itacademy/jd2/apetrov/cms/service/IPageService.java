package com.itacademy.jd2.apetrov.cms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IPage;

public interface IPageService {

    IPage createEntity();

    IPage get(Integer id);

    List<IPage> getAll();

    void delete(Integer id);

    void deleteAll();

    void save(IPage entity);

}
