package com.sbm.pojo.model;

import java.util.Date;

public class GoodsSpec {

    private String goodId;

    /**
     * 特殊商品:1-推荐,2-人气
     */
    private String type;

    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}