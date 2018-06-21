package com.sbm.controller;
import com.sbm.pojo.response.ACommentResponse;
import com.sbm.service.CommentService;
import com.sbm.util.ExecuteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangxz on 2018/6/19
 *
 * 评论系统  //商品评论  学校论坛统一使用  请认真查看接口参数
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {


    @Resource
    private CommentService  commentService;
    /**
     * 获取商品评论
     * @param goodId
     * @return
     */
    @RequestMapping("/goodComment")
    @ResponseBody
    public ExecuteResult<List<ACommentResponse>> goodsInitComment(Integer   goodId){

        List<ACommentResponse> list= commentService.goodsInitComment(goodId);

        return  new ExecuteResult<>(list);
    }




}
