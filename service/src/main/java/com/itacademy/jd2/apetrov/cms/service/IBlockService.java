package com.itacademy.jd2.apetrov.cms.service;

import java.util.List;

import com.itacademy.jd2.apetrov.cms.dao.api.entity.table.IBlock;

public interface IBlockService {

    IBlock createEntity();

    IBlock get(Integer id);

    List<IBlock> getAll();

    void delete(Integer id);

    void deleteAll();

    void save(IBlock entity);
}
