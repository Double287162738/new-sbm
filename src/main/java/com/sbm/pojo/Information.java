package com.sbm.pojo;

/**
 * Created by zhangxz on 2018/6/15
 * 短信回传类
 */
public class Information {

    //{"respCode":"00000","respDesc":"请求成功。","failCount":"0","failList":[],"smsId":"a7f33d0a56cd40dda80e8695044aeeeb"}

    //状态码
    private  String respCode;
    //描述
    private  String respDesc;
    private  String failCount;
    private  String failList;
    private  String smsId;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getFailCount() {
        return failCount;
    }

    public void setFailCount(String failCount) {
        this.failCount = failCount;
    }

    public String getFailList() {
        return failList;
    }

    public void setFailList(String failList) {
        this.failList = failList;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }
}
