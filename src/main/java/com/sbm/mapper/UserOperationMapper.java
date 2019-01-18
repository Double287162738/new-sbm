package com.sbm.mapper;

import com.sbm.pojo.model.UserOperation;
import com.sbm.pojo.model.UserOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOperationMapper {
    UserOperation selectOneByExample(UserOperationExample example);

    int countByExample(UserOperationExample example);

    int deleteByExample(UserOperationExample example);

    int deleteByPrimaryKey(String operationId);

    int insert(UserOperation record);

    int insertSelective(UserOperation record);

    List<UserOperation> selectByExample(UserOperationExample example);

    UserOperation selectByPrimaryKey(String operationId);

    int updateByExampleSelective(@Param("record") UserOperation record, @Param("example") UserOperationExample example);

    int updateByExample(@Param("record") UserOperation record, @Param("example") UserOperationExample example);

    int updateByPrimaryKeySelective(UserOperation record);

    int updateByPrimaryKey(UserOperation record);
}