package com.sbm.service;


import com.sbm.pojo.response.ACommentResponse;

import java.util.List;

public interface CommentService {

    /**
     * 根据商品ID获取评论
     * @param goodId
     * @return
     */
    List<ACommentResponse> goodsInitComment(Integer   goodId);

}
