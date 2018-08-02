package com.sbm.pojo.model;


import com.sbm.util.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 嗖嗖入参
 */
public class SouSouInparameterDTO implements Serializable{
    protected String keyWord;//搜索关键字
    protected String souType;//搜索类型
    protected List<String> souArea;//搜索区域
    protected int currentPage;//当前页
    protected Page page;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getSouType() {
        return souType;
    }

    public void setSouType(String souType) {
        this.souType = souType;
    }

    public List<String> getSouArea() {
        return souArea;
    }

    public void setSouArea(List<String> souArea) {
        this.souArea = souArea;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}