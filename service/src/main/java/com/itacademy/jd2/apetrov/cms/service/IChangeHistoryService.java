package com.itacademy.jd2.apetrov.cms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IChangeHistory;

public interface IChangeHistoryService {

    IChangeHistory createEntity();

    IChangeHistory get(Integer id);

    List<IChangeHistory> getAll();

    void delete(Integer id);

    void deleteAll();

    void save(IChangeHistory entity);
    
}
