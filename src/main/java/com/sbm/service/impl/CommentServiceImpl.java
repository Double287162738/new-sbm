package com.sbm.service.impl;

import com.sbm.mapper.CommentMapper;
import com.sbm.mapper.CommentReplyMapper;
import com.sbm.pojo.model.Comment;
import com.sbm.pojo.model.CommentExample;
import com.sbm.pojo.model.CommentReply;
import com.sbm.pojo.model.CommentReplyExample;
import com.sbm.pojo.response.ACommentResponse;
import com.sbm.pojo.response.CommentReplyResponse;
import com.sbm.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxz on 2018/6/21
 */

@Service
public class CommentServiceImpl  implements CommentService {

    @Resource
    private CommentMapper  commentMapper;
    @Resource
    private CommentReplyMapper  commentReplyMapper;

    /**
     * 商品查询评论
     * @param goodId
     * @return
     */
    @Override
    public List<ACommentResponse> goodsInitComment(Integer goodId) {
        //获取全部的主评论
        CommentExample  commentExample= new CommentExample();
        commentExample.createCriteria().andGoodsIdEqualTo(goodId);
        List<Comment> comments= commentMapper.selectByExample(commentExample);
        //获取副评论等待提出
        List<ACommentResponse> list= new ArrayList<ACommentResponse>();

        for (Comment comment : comments) {
            //设置主评论属性
            ACommentResponse commentResponse= new ACommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setContent(comment.getContent());



            //BeanUtil.copyBean();
//            PropertyUtils
            //查询回复
            CommentReplyExample  commentReplyExample= new CommentReplyExample();
            commentReplyExample.createCriteria().andCommentIdEqualTo(comment.getId());
            List<CommentReply> commentReplys=  commentReplyMapper.selectByExample(commentReplyExample);
          //  commentResponse.setCommentReplies(commentReplys);
            //commentResponse.setCommentReplies(commentReplys);


            ACommentResponse commentResponseRe= goodsComment(commentReplys,commentResponse);
            list.add(commentResponseRe);
        }


        return list;
    }

    /**
     * 解析最外层封装遍历
     * @param commentReplys
     * @param AcommentResponse
     * @return
     */
    public ACommentResponse goodsComment(List<CommentReply> commentReplys, ACommentResponse AcommentResponse){
        //次回复
        List<CommentReplyResponse> BcommentReplies= new ArrayList<>();


        for (CommentReply commentReply : commentReplys) {
            CommentReplyResponse BcommentReplyResponse=new  CommentReplyResponse();
            BcommentReplyResponse.setId(commentReply.getId());
            BcommentReplyResponse.setContent(commentReply.getContent());


            CommentReplyExample  commentReplyExample= new CommentReplyExample();
            commentReplyExample.createCriteria().andReplyIdEqualTo(commentReply.getId());
            List<CommentReply> commentReplys1=  commentReplyMapper.selectByExample(commentReplyExample);
            if(commentReplys1!=null&&commentReplys1.size()>0){
                BcommentReplyResponse= goodsComment(commentReplys1,BcommentReplyResponse);
            }
            BcommentReplies.add(BcommentReplyResponse);

        }
        AcommentResponse.setCommentReplies(BcommentReplies);
        return AcommentResponse;
    }

    /**
     * 递归回复查询
     * @param commentReplys
     * @param BcommentReply
     * @return
     */
    public CommentReplyResponse goodsComment(List<CommentReply> commentReplys,CommentReplyResponse  BcommentReply){
        List<CommentReplyResponse> BcommentReplies= new ArrayList<>();


        for (CommentReply commentReply : commentReplys) {
            CommentReplyResponse BcommentReplyResponse=new  CommentReplyResponse();
            BcommentReplyResponse.setId(commentReply.getId());
            BcommentReplyResponse.setContent(commentReply.getContent());


            CommentReplyExample  commentReplyExample= new CommentReplyExample();
            commentReplyExample.createCriteria().andReplyIdEqualTo(commentReply.getId());
            List<CommentReply> commentReplys1=  commentReplyMapper.selectByExample(commentReplyExample);
            if(commentReplys1!=null&&commentReplys1.size()>0){
                BcommentReplyResponse= goodsComment(commentReplys1,BcommentReplyResponse);
            }
            BcommentReplies.add(BcommentReplyResponse);

        }
        BcommentReply.setCommentReplies(BcommentReplies);
        return BcommentReply;
    }






}
