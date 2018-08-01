package com.sbm.pojo.model;


import java.util.Date;

public class Suggestion {

    private String suggestionId;

    private String suggestionScore;

    private String suggestionDetail;

    private Date createTime;

    private String ifAudit;

    private Date updateTime;

    private Integer version;

    public String getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(String suggestionId) {
        this.suggestionId = suggestionId;
    }

    public String getSuggestionScore() {
        return suggestionScore;
    }

    public void setSuggestionScore(String suggestionScore) {
        this.suggestionScore = suggestionScore;
    }

    public String getSuggestionDetail() {
        return suggestionDetail;
    }

    public void setSuggestionDetail(String suggestionDetail) {
        this.suggestionDetail = suggestionDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIfAudit() {
        return ifAudit;
    }

    public void setIfAudit(String ifAudit) {
        this.ifAudit = ifAudit;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}