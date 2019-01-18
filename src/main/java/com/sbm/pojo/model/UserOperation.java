package com.sbm.pojo.model;

import java.util.Date;

public class UserOperation {

    private String operationId;

    private String operationUserId;

    private String operationUserIphone;

    private String operationDetail;

    private String operationRemark;

    private String operationGoodsId;

    private Date createTime;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(String operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getOperationUserIphone() {
        return operationUserIphone;
    }

    public void setOperationUserIphone(String operationUserIphone) {
        this.operationUserIphone = operationUserIphone;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }

    public String getOperationRemark() {
        return operationRemark;
    }

    public void setOperationRemark(String operationRemark) {
        this.operationRemark = operationRemark;
    }

    public String getOperationGoodsId() {
        return operationGoodsId;
    }

    public void setOperationGoodsId(String operationGoodsId) {
        this.operationGoodsId = operationGoodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}