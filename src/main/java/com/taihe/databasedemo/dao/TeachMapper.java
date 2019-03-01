package com.taihe.databasedemo.dao;

import com.taihe.databasedemo.entity.Teach;
import org.apache.ibatis.annotations.Param;

public interface TeachMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teach record);

    int insertSelective(Teach record);

    Teach selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teach record);

    int updateByPrimaryKey(Teach record);

    int deleteByTidAndCid(@Param("tid") Integer tid,@Param("cid") Integer cid);
}