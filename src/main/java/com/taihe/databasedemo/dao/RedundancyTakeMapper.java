package com.taihe.databasedemo.dao;

import com.taihe.databasedemo.entity.RedundancyTake;

public interface RedundancyTakeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedundancyTake record);

    int insertSelective(RedundancyTake record);

    RedundancyTake selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedundancyTake record);

    int updateByPrimaryKey(RedundancyTake record);
}