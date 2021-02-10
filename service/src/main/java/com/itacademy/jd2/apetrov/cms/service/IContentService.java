package com.itacademy.jd2.apetrov.cms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IContent;

public interface IContentService {

    IContent createEntity();

    IContent get(Integer id);

    List<IContent> getAll();

    void delete(Integer id);

    void deleteAll();

    void save(IContent entity);
}
