package com.taihe.databasedemo.dao;

import com.taihe.databasedemo.entity.Take;
import org.apache.ibatis.annotations.Param;

public interface TakeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBySidAndCid(@Param("sid") Integer sid, @Param("cid") Integer cid);

    int insert(Take record);

    int insertSelective(Take record);

    Take selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Take record);

    int updateByPrimaryKey(Take record);
}