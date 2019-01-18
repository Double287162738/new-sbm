package com.sbm.mapper;

import com.sbm.pojo.model.Recycle;
import com.sbm.pojo.model.RecycleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecycleMapper {
    Recycle selectOneByExample(RecycleExample example);

    int countByExample(RecycleExample example);

    int deleteByExample(RecycleExample example);

    int deleteByPrimaryKey(String recycleId);

    int insert(Recycle record);

    int insertSelective(Recycle record);

    List<Recycle> selectByExample(RecycleExample example);

    Recycle selectByPrimaryKey(String recycleId);

    int updateByExampleSelective(@Param("record") Recycle record, @Param("example") RecycleExample example);

    int updateByExample(@Param("record") Recycle record, @Param("example") RecycleExample example);

    int updateByPrimaryKeySelective(Recycle record);

    int updateByPrimaryKey(Recycle record);
}