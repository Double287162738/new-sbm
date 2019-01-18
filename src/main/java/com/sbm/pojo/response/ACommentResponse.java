package com.sbm.pojo.response;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangxz on 2018/6/19
 */
public class ACommentResponse {


    /**
     * 主键
     */
    private Integer id;

    /**
     * 主题id
     */
    private Integer topicId;

    /**
     * 主题类型
     */
    private String topicType;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论用户id
     */
    private Integer fromUid;

    /**
     * 图片路径
     */
    private String picturePath;

    /**
     * 视频路径
     */
    private String videoPath;
    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 作废标记
     */
    private Boolean invalid;

    /**
     * 作废原因
     */
    private String invalidBe;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private String add1;

    private String add2;

    private String add3;

    /**
     * 点赞次数
     */
    private Integer likeCount;

    /**
     * 排名
     */
    private Integer rank;

    //回复
    private List<CommentReplyResponse> commentReplies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public String getInvalidBe() {
        return invalidBe;
    }

    public void setInvalidBe(String invalidBe) {
        this.invalidBe = invalidBe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getAdd3() {
        return add3;
    }

    public void setAdd3(String add3) {
        this.add3 = add3;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<CommentReplyResponse> getCommentReplies() {
        return commentReplies;
    }

    public void setCommentReplies(List<CommentReplyResponse> commentReplies) {
        this.commentReplies = commentReplies;
    }
}
