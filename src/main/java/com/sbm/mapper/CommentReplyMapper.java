package com.sbm.mapper;

import com.sbm.pojo.model.CommentReply;
import com.sbm.pojo.model.CommentReplyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReplyMapper {
    CommentReply selectOneByExample(CommentReplyExample example);

    int countByExample(CommentReplyExample example);

    int deleteByExample(CommentReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentReply record);

    int insertSelective(CommentReply record);

    List<CommentReply> selectByExample(CommentReplyExample example);

    CommentReply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentReply record, @Param("example") CommentReplyExample example);

    int updateByExample(@Param("record") CommentReply record, @Param("example") CommentReplyExample example);

    int updateByPrimaryKeySelective(CommentReply record);

    int updateByPrimaryKey(CommentReply record);
}