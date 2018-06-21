package com.sbm.service;


import com.sbm.pojo.model.Comment;
import com.sbm.pojo.model.CommentReply;
import com.sbm.pojo.response.ACommentResponse;

import java.util.List;

public interface CommentService {

    /**
     * 根据商品ID获取评论
     * @param goodId
     * @return
     */
    List<ACommentResponse> goodsInitComment(Integer   goodId);

    /**
     *新增主评论
     * @param comment
     * @return
     */
    Boolean createComment(Comment comment);
    /**
     * 回复评论
     * @param comment
     * @return
     */
    Boolean createCommentReply(CommentReply commentReply);

}