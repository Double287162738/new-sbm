package com.sbm.pojo.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * Created by zhangxz on 2018/6/15
 */
@Valid
public class RegisteredRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private  String passWord;
    @NotBlank
    private  String phoneVerified;
    @NotBlank
    private  String phoneCode;
    @NotBlank
    private  String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(String phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}
