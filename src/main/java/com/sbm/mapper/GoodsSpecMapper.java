package com.sbm.mapper;

import com.sbm.pojo.model.GoodsSpec;
import com.sbm.pojo.model.GoodsSpecExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsSpecMapper {
    GoodsSpec selectOneByExample(GoodsSpecExample example);

    int countByExample(GoodsSpecExample example);

    int deleteByExample(GoodsSpecExample example);

    int deleteByPrimaryKey(String goodId);

    int insert(GoodsSpec record);

    int insertSelective(GoodsSpec record);

    List<GoodsSpec> selectByExample(GoodsSpecExample example);

    GoodsSpec selectByPrimaryKey(String goodId);

    int updateByExampleSelective(@Param("record") GoodsSpec record, @Param("example") GoodsSpecExample example);

    int updateByExample(@Param("record") GoodsSpec record, @Param("example") GoodsSpecExample example);

    int updateByPrimaryKeySelective(GoodsSpec record);

    int updateByPrimaryKey(GoodsSpec record);
}