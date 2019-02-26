package com.taihe.databasedemo.dao;

import com.taihe.databasedemo.entity.RedundancyTake;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RedundancyTakeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBySidAndCid(@Param("sid") Integer sid, @Param("cid") Integer cid);

    int insert(RedundancyTake record);

    int insertBySidAndCid(@Param("sid") Integer sid, @Param("cid") Integer cid);

    int insertSelective(RedundancyTake record);

    RedundancyTake selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedundancyTake record);

    int updateByPrimaryKey(RedundancyTake record);

    List<RedundancyTake> selectCourseListBySid(Integer sid);
}