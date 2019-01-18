package com.sbm.pojo.model;

import com.sbm.util.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    List<String> includeColumns = new ArrayList<String>();

    private static Map<String, String> MODEL_INFOS = new HashMap<String,String>();

    static {
        MODEL_INFOS.put("goodsId","GOODS_ID");
        MODEL_INFOS.put("userId","USER_ID");
        MODEL_INFOS.put("userName","USER_NAME");
        MODEL_INFOS.put("goodsName","GOODS_NAME");
        MODEL_INFOS.put("goodsSpecificType","GOODS_SPECIFIC_TYPE");
        MODEL_INFOS.put("goodsType","GOODS_TYPE");
        MODEL_INFOS.put("goodsArea","GOODS_AREA");
        MODEL_INFOS.put("goodsPrice","GOODS_PRICE");
        MODEL_INFOS.put("goodsWords","GOODS_WORDS");
        MODEL_INFOS.put("goodsQq","GOODS_QQ");
        MODEL_INFOS.put("goodsWx","GOODS_WX");
        MODEL_INFOS.put("goodsOther","GOODS_OTHER");
        MODEL_INFOS.put("goodsClickAmount","GOODS_CLICK_AMOUNT");
        MODEL_INFOS.put("goodsCollectionAmount","GOODS_COLLECTION_AMOUNT");
        MODEL_INFOS.put("goodsPic1","GOODS_PIC1");
        MODEL_INFOS.put("goodsPic2","GOODS_PIC2");
        MODEL_INFOS.put("goodsPic3","GOODS_PIC3");
        MODEL_INFOS.put("goodsPic4","GOODS_PIC4");
        MODEL_INFOS.put("goodsPic5","GOODS_PIC5");
        MODEL_INFOS.put("goodsPic6","GOODS_PIC6");
        MODEL_INFOS.put("goodsPic7","GOODS_PIC7");
        MODEL_INFOS.put("goodsPic8","GOODS_PIC8");
        MODEL_INFOS.put("goodsPic9","GOODS_PIC9");
        MODEL_INFOS.put("goodsGmtCreate","GOODS_GMT_CREATE");
        MODEL_INFOS.put("goodsLastMod","GOODS_LAST_MOD");
        MODEL_INFOS.put("goodsNo","GOODS_NO");
        MODEL_INFOS.put("goodsStatus","GOODS_STATUS");
    }

    public GoodsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
    }

    public List<String> getIncludeColumns() {
        return this.includeColumns;
    }

    public void addSelectiveField(String field) {
        String columnName = MODEL_INFOS.get(field);
        includeColumns.add(columnName);
    }

    public void addSelectiveFields(String ... fields) {
        for(String field:fields){
            addSelectiveField(field);
        }
    }

    public void addSelectiveFields(List<String> fields) {
        for(String field:fields){
            addSelectiveField(field);
        }
    }

    /**
     * 
     * 2019-01-13
     */
    protected abstract static class GeneratedCriteria {

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("GOODS_ID is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("GOODS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(String value) {
            addCriterion("GOODS_ID =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(String value) {
            addCriterion("GOODS_ID <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(String value) {
            addCriterion("GOODS_ID >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_ID >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(String value) {
            addCriterion("GOODS_ID <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(String value) {
            addCriterion("GOODS_ID <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLike(String value) {
            addCriterion("GOODS_ID like", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotLike(String value) {
            addCriterion("GOODS_ID not like", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<String> values) {
            addCriterion("GOODS_ID in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<String> values) {
            addCriterion("GOODS_ID not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(String value1, String value2) {
            addCriterion("GOODS_ID between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(String value1, String value2) {
            addCriterion("GOODS_ID not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("GOODS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("GOODS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("GOODS_NAME =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("GOODS_NAME <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("GOODS_NAME >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_NAME >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("GOODS_NAME <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("GOODS_NAME <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("GOODS_NAME like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("GOODS_NAME not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("GOODS_NAME in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("GOODS_NAME not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("GOODS_NAME between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("GOODS_NAME not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeIsNull() {
            addCriterion("GOODS_SPECIFIC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeIsNotNull() {
            addCriterion("GOODS_SPECIFIC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeEqualTo(String value) {
            addCriterion("GOODS_SPECIFIC_TYPE =", value, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeNotEqualTo(String value) {
            addCriterion("GOODS_SPECIFIC_TYPE <>", value, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeGreaterThan(String value) {
            addCriterion("GOODS_SPECIFIC_TYPE >", value, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_SPECIFIC_TYPE >=", value, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeLessThan(String value) {
            addCriterion("GOODS_SPECIFIC_TYPE <", value, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeLessThanOrEqualTo(String value) {
            addCriterion("GOODS_SPECIFIC_TYPE <=", value, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeLike(String value) {
            addCriterion("GOODS_SPECIFIC_TYPE like", value, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeNotLike(String value) {
            addCriterion("GOODS_SPECIFIC_TYPE not like", value, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeIn(List<String> values) {
            addCriterion("GOODS_SPECIFIC_TYPE in", values, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeNotIn(List<String> values) {
            addCriterion("GOODS_SPECIFIC_TYPE not in", values, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeBetween(String value1, String value2) {
            addCriterion("GOODS_SPECIFIC_TYPE between", value1, value2, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecificTypeNotBetween(String value1, String value2) {
            addCriterion("GOODS_SPECIFIC_TYPE not between", value1, value2, "goodsSpecificType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNull() {
            addCriterion("GOODS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNotNull() {
            addCriterion("GOODS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeEqualTo(String value) {
            addCriterion("GOODS_TYPE =", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotEqualTo(String value) {
            addCriterion("GOODS_TYPE <>", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThan(String value) {
            addCriterion("GOODS_TYPE >", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_TYPE >=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThan(String value) {
            addCriterion("GOODS_TYPE <", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThanOrEqualTo(String value) {
            addCriterion("GOODS_TYPE <=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLike(String value) {
            addCriterion("GOODS_TYPE like", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotLike(String value) {
            addCriterion("GOODS_TYPE not like", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIn(List<String> values) {
            addCriterion("GOODS_TYPE in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotIn(List<String> values) {
            addCriterion("GOODS_TYPE not in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeBetween(String value1, String value2) {
            addCriterion("GOODS_TYPE between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotBetween(String value1, String value2) {
            addCriterion("GOODS_TYPE not between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaIsNull() {
            addCriterion("GOODS_AREA is null");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaIsNotNull() {
            addCriterion("GOODS_AREA is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaEqualTo(String value) {
            addCriterion("GOODS_AREA =", value, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaNotEqualTo(String value) {
            addCriterion("GOODS_AREA <>", value, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaGreaterThan(String value) {
            addCriterion("GOODS_AREA >", value, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_AREA >=", value, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaLessThan(String value) {
            addCriterion("GOODS_AREA <", value, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaLessThanOrEqualTo(String value) {
            addCriterion("GOODS_AREA <=", value, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaLike(String value) {
            addCriterion("GOODS_AREA like", value, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaNotLike(String value) {
            addCriterion("GOODS_AREA not like", value, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaIn(List<String> values) {
            addCriterion("GOODS_AREA in", values, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaNotIn(List<String> values) {
            addCriterion("GOODS_AREA not in", values, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaBetween(String value1, String value2) {
            addCriterion("GOODS_AREA between", value1, value2, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsAreaNotBetween(String value1, String value2) {
            addCriterion("GOODS_AREA not between", value1, value2, "goodsArea");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNull() {
            addCriterion("GOODS_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNotNull() {
            addCriterion("GOODS_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceEqualTo(String value) {
            addCriterion("GOODS_PRICE =", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotEqualTo(String value) {
            addCriterion("GOODS_PRICE <>", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThan(String value) {
            addCriterion("GOODS_PRICE >", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PRICE >=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThan(String value) {
            addCriterion("GOODS_PRICE <", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThanOrEqualTo(String value) {
            addCriterion("GOODS_PRICE <=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLike(String value) {
            addCriterion("GOODS_PRICE like", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotLike(String value) {
            addCriterion("GOODS_PRICE not like", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIn(List<String> values) {
            addCriterion("GOODS_PRICE in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotIn(List<String> values) {
            addCriterion("GOODS_PRICE not in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceBetween(String value1, String value2) {
            addCriterion("GOODS_PRICE between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotBetween(String value1, String value2) {
            addCriterion("GOODS_PRICE not between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsIsNull() {
            addCriterion("GOODS_WORDS is null");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsIsNotNull() {
            addCriterion("GOODS_WORDS is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsEqualTo(String value) {
            addCriterion("GOODS_WORDS =", value, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsNotEqualTo(String value) {
            addCriterion("GOODS_WORDS <>", value, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsGreaterThan(String value) {
            addCriterion("GOODS_WORDS >", value, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_WORDS >=", value, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsLessThan(String value) {
            addCriterion("GOODS_WORDS <", value, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsLessThanOrEqualTo(String value) {
            addCriterion("GOODS_WORDS <=", value, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsLike(String value) {
            addCriterion("GOODS_WORDS like", value, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsNotLike(String value) {
            addCriterion("GOODS_WORDS not like", value, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsIn(List<String> values) {
            addCriterion("GOODS_WORDS in", values, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsNotIn(List<String> values) {
            addCriterion("GOODS_WORDS not in", values, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsBetween(String value1, String value2) {
            addCriterion("GOODS_WORDS between", value1, value2, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsWordsNotBetween(String value1, String value2) {
            addCriterion("GOODS_WORDS not between", value1, value2, "goodsWords");
            return (Criteria) this;
        }

        public Criteria andGoodsQqIsNull() {
            addCriterion("GOODS_QQ is null");
            return (Criteria) this;
        }

        public Criteria andGoodsQqIsNotNull() {
            addCriterion("GOODS_QQ is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsQqEqualTo(String value) {
            addCriterion("GOODS_QQ =", value, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqNotEqualTo(String value) {
            addCriterion("GOODS_QQ <>", value, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqGreaterThan(String value) {
            addCriterion("GOODS_QQ >", value, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_QQ >=", value, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqLessThan(String value) {
            addCriterion("GOODS_QQ <", value, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqLessThanOrEqualTo(String value) {
            addCriterion("GOODS_QQ <=", value, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqLike(String value) {
            addCriterion("GOODS_QQ like", value, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqNotLike(String value) {
            addCriterion("GOODS_QQ not like", value, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqIn(List<String> values) {
            addCriterion("GOODS_QQ in", values, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqNotIn(List<String> values) {
            addCriterion("GOODS_QQ not in", values, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqBetween(String value1, String value2) {
            addCriterion("GOODS_QQ between", value1, value2, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsQqNotBetween(String value1, String value2) {
            addCriterion("GOODS_QQ not between", value1, value2, "goodsQq");
            return (Criteria) this;
        }

        public Criteria andGoodsWxIsNull() {
            addCriterion("GOODS_WX is null");
            return (Criteria) this;
        }

        public Criteria andGoodsWxIsNotNull() {
            addCriterion("GOODS_WX is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsWxEqualTo(String value) {
            addCriterion("GOODS_WX =", value, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxNotEqualTo(String value) {
            addCriterion("GOODS_WX <>", value, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxGreaterThan(String value) {
            addCriterion("GOODS_WX >", value, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_WX >=", value, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxLessThan(String value) {
            addCriterion("GOODS_WX <", value, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxLessThanOrEqualTo(String value) {
            addCriterion("GOODS_WX <=", value, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxLike(String value) {
            addCriterion("GOODS_WX like", value, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxNotLike(String value) {
            addCriterion("GOODS_WX not like", value, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxIn(List<String> values) {
            addCriterion("GOODS_WX in", values, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxNotIn(List<String> values) {
            addCriterion("GOODS_WX not in", values, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxBetween(String value1, String value2) {
            addCriterion("GOODS_WX between", value1, value2, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsWxNotBetween(String value1, String value2) {
            addCriterion("GOODS_WX not between", value1, value2, "goodsWx");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherIsNull() {
            addCriterion("GOODS_OTHER is null");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherIsNotNull() {
            addCriterion("GOODS_OTHER is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherEqualTo(String value) {
            addCriterion("GOODS_OTHER =", value, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherNotEqualTo(String value) {
            addCriterion("GOODS_OTHER <>", value, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherGreaterThan(String value) {
            addCriterion("GOODS_OTHER >", value, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_OTHER >=", value, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherLessThan(String value) {
            addCriterion("GOODS_OTHER <", value, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherLessThanOrEqualTo(String value) {
            addCriterion("GOODS_OTHER <=", value, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherLike(String value) {
            addCriterion("GOODS_OTHER like", value, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherNotLike(String value) {
            addCriterion("GOODS_OTHER not like", value, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherIn(List<String> values) {
            addCriterion("GOODS_OTHER in", values, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherNotIn(List<String> values) {
            addCriterion("GOODS_OTHER not in", values, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherBetween(String value1, String value2) {
            addCriterion("GOODS_OTHER between", value1, value2, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsOtherNotBetween(String value1, String value2) {
            addCriterion("GOODS_OTHER not between", value1, value2, "goodsOther");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountIsNull() {
            addCriterion("GOODS_CLICK_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountIsNotNull() {
            addCriterion("GOODS_CLICK_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountEqualTo(String value) {
            addCriterion("GOODS_CLICK_AMOUNT =", value, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountNotEqualTo(String value) {
            addCriterion("GOODS_CLICK_AMOUNT <>", value, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountGreaterThan(String value) {
            addCriterion("GOODS_CLICK_AMOUNT >", value, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_CLICK_AMOUNT >=", value, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountLessThan(String value) {
            addCriterion("GOODS_CLICK_AMOUNT <", value, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountLessThanOrEqualTo(String value) {
            addCriterion("GOODS_CLICK_AMOUNT <=", value, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountLike(String value) {
            addCriterion("GOODS_CLICK_AMOUNT like", value, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountNotLike(String value) {
            addCriterion("GOODS_CLICK_AMOUNT not like", value, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountIn(List<String> values) {
            addCriterion("GOODS_CLICK_AMOUNT in", values, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountNotIn(List<String> values) {
            addCriterion("GOODS_CLICK_AMOUNT not in", values, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountBetween(String value1, String value2) {
            addCriterion("GOODS_CLICK_AMOUNT between", value1, value2, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsClickAmountNotBetween(String value1, String value2) {
            addCriterion("GOODS_CLICK_AMOUNT not between", value1, value2, "goodsClickAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountIsNull() {
            addCriterion("GOODS_COLLECTION_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountIsNotNull() {
            addCriterion("GOODS_COLLECTION_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountEqualTo(String value) {
            addCriterion("GOODS_COLLECTION_AMOUNT =", value, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountNotEqualTo(String value) {
            addCriterion("GOODS_COLLECTION_AMOUNT <>", value, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountGreaterThan(String value) {
            addCriterion("GOODS_COLLECTION_AMOUNT >", value, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_COLLECTION_AMOUNT >=", value, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountLessThan(String value) {
            addCriterion("GOODS_COLLECTION_AMOUNT <", value, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountLessThanOrEqualTo(String value) {
            addCriterion("GOODS_COLLECTION_AMOUNT <=", value, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountLike(String value) {
            addCriterion("GOODS_COLLECTION_AMOUNT like", value, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountNotLike(String value) {
            addCriterion("GOODS_COLLECTION_AMOUNT not like", value, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountIn(List<String> values) {
            addCriterion("GOODS_COLLECTION_AMOUNT in", values, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountNotIn(List<String> values) {
            addCriterion("GOODS_COLLECTION_AMOUNT not in", values, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountBetween(String value1, String value2) {
            addCriterion("GOODS_COLLECTION_AMOUNT between", value1, value2, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCollectionAmountNotBetween(String value1, String value2) {
            addCriterion("GOODS_COLLECTION_AMOUNT not between", value1, value2, "goodsCollectionAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1IsNull() {
            addCriterion("GOODS_PIC1 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1IsNotNull() {
            addCriterion("GOODS_PIC1 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1EqualTo(String value) {
            addCriterion("GOODS_PIC1 =", value, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1NotEqualTo(String value) {
            addCriterion("GOODS_PIC1 <>", value, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1GreaterThan(String value) {
            addCriterion("GOODS_PIC1 >", value, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC1 >=", value, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1LessThan(String value) {
            addCriterion("GOODS_PIC1 <", value, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC1 <=", value, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1Like(String value) {
            addCriterion("GOODS_PIC1 like", value, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1NotLike(String value) {
            addCriterion("GOODS_PIC1 not like", value, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1In(List<String> values) {
            addCriterion("GOODS_PIC1 in", values, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1NotIn(List<String> values) {
            addCriterion("GOODS_PIC1 not in", values, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1Between(String value1, String value2) {
            addCriterion("GOODS_PIC1 between", value1, value2, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic1NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC1 not between", value1, value2, "goodsPic1");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2IsNull() {
            addCriterion("GOODS_PIC2 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2IsNotNull() {
            addCriterion("GOODS_PIC2 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2EqualTo(String value) {
            addCriterion("GOODS_PIC2 =", value, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2NotEqualTo(String value) {
            addCriterion("GOODS_PIC2 <>", value, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2GreaterThan(String value) {
            addCriterion("GOODS_PIC2 >", value, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC2 >=", value, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2LessThan(String value) {
            addCriterion("GOODS_PIC2 <", value, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC2 <=", value, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2Like(String value) {
            addCriterion("GOODS_PIC2 like", value, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2NotLike(String value) {
            addCriterion("GOODS_PIC2 not like", value, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2In(List<String> values) {
            addCriterion("GOODS_PIC2 in", values, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2NotIn(List<String> values) {
            addCriterion("GOODS_PIC2 not in", values, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2Between(String value1, String value2) {
            addCriterion("GOODS_PIC2 between", value1, value2, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic2NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC2 not between", value1, value2, "goodsPic2");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3IsNull() {
            addCriterion("GOODS_PIC3 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3IsNotNull() {
            addCriterion("GOODS_PIC3 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3EqualTo(String value) {
            addCriterion("GOODS_PIC3 =", value, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3NotEqualTo(String value) {
            addCriterion("GOODS_PIC3 <>", value, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3GreaterThan(String value) {
            addCriterion("GOODS_PIC3 >", value, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC3 >=", value, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3LessThan(String value) {
            addCriterion("GOODS_PIC3 <", value, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC3 <=", value, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3Like(String value) {
            addCriterion("GOODS_PIC3 like", value, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3NotLike(String value) {
            addCriterion("GOODS_PIC3 not like", value, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3In(List<String> values) {
            addCriterion("GOODS_PIC3 in", values, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3NotIn(List<String> values) {
            addCriterion("GOODS_PIC3 not in", values, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3Between(String value1, String value2) {
            addCriterion("GOODS_PIC3 between", value1, value2, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic3NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC3 not between", value1, value2, "goodsPic3");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4IsNull() {
            addCriterion("GOODS_PIC4 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4IsNotNull() {
            addCriterion("GOODS_PIC4 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4EqualTo(String value) {
            addCriterion("GOODS_PIC4 =", value, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4NotEqualTo(String value) {
            addCriterion("GOODS_PIC4 <>", value, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4GreaterThan(String value) {
            addCriterion("GOODS_PIC4 >", value, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC4 >=", value, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4LessThan(String value) {
            addCriterion("GOODS_PIC4 <", value, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC4 <=", value, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4Like(String value) {
            addCriterion("GOODS_PIC4 like", value, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4NotLike(String value) {
            addCriterion("GOODS_PIC4 not like", value, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4In(List<String> values) {
            addCriterion("GOODS_PIC4 in", values, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4NotIn(List<String> values) {
            addCriterion("GOODS_PIC4 not in", values, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4Between(String value1, String value2) {
            addCriterion("GOODS_PIC4 between", value1, value2, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic4NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC4 not between", value1, value2, "goodsPic4");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5IsNull() {
            addCriterion("GOODS_PIC5 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5IsNotNull() {
            addCriterion("GOODS_PIC5 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5EqualTo(String value) {
            addCriterion("GOODS_PIC5 =", value, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5NotEqualTo(String value) {
            addCriterion("GOODS_PIC5 <>", value, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5GreaterThan(String value) {
            addCriterion("GOODS_PIC5 >", value, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC5 >=", value, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5LessThan(String value) {
            addCriterion("GOODS_PIC5 <", value, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC5 <=", value, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5Like(String value) {
            addCriterion("GOODS_PIC5 like", value, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5NotLike(String value) {
            addCriterion("GOODS_PIC5 not like", value, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5In(List<String> values) {
            addCriterion("GOODS_PIC5 in", values, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5NotIn(List<String> values) {
            addCriterion("GOODS_PIC5 not in", values, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5Between(String value1, String value2) {
            addCriterion("GOODS_PIC5 between", value1, value2, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic5NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC5 not between", value1, value2, "goodsPic5");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6IsNull() {
            addCriterion("GOODS_PIC6 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6IsNotNull() {
            addCriterion("GOODS_PIC6 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6EqualTo(String value) {
            addCriterion("GOODS_PIC6 =", value, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6NotEqualTo(String value) {
            addCriterion("GOODS_PIC6 <>", value, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6GreaterThan(String value) {
            addCriterion("GOODS_PIC6 >", value, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC6 >=", value, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6LessThan(String value) {
            addCriterion("GOODS_PIC6 <", value, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC6 <=", value, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6Like(String value) {
            addCriterion("GOODS_PIC6 like", value, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6NotLike(String value) {
            addCriterion("GOODS_PIC6 not like", value, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6In(List<String> values) {
            addCriterion("GOODS_PIC6 in", values, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6NotIn(List<String> values) {
            addCriterion("GOODS_PIC6 not in", values, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6Between(String value1, String value2) {
            addCriterion("GOODS_PIC6 between", value1, value2, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic6NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC6 not between", value1, value2, "goodsPic6");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7IsNull() {
            addCriterion("GOODS_PIC7 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7IsNotNull() {
            addCriterion("GOODS_PIC7 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7EqualTo(String value) {
            addCriterion("GOODS_PIC7 =", value, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7NotEqualTo(String value) {
            addCriterion("GOODS_PIC7 <>", value, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7GreaterThan(String value) {
            addCriterion("GOODS_PIC7 >", value, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC7 >=", value, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7LessThan(String value) {
            addCriterion("GOODS_PIC7 <", value, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC7 <=", value, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7Like(String value) {
            addCriterion("GOODS_PIC7 like", value, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7NotLike(String value) {
            addCriterion("GOODS_PIC7 not like", value, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7In(List<String> values) {
            addCriterion("GOODS_PIC7 in", values, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7NotIn(List<String> values) {
            addCriterion("GOODS_PIC7 not in", values, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7Between(String value1, String value2) {
            addCriterion("GOODS_PIC7 between", value1, value2, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic7NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC7 not between", value1, value2, "goodsPic7");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8IsNull() {
            addCriterion("GOODS_PIC8 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8IsNotNull() {
            addCriterion("GOODS_PIC8 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8EqualTo(String value) {
            addCriterion("GOODS_PIC8 =", value, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8NotEqualTo(String value) {
            addCriterion("GOODS_PIC8 <>", value, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8GreaterThan(String value) {
            addCriterion("GOODS_PIC8 >", value, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC8 >=", value, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8LessThan(String value) {
            addCriterion("GOODS_PIC8 <", value, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC8 <=", value, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8Like(String value) {
            addCriterion("GOODS_PIC8 like", value, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8NotLike(String value) {
            addCriterion("GOODS_PIC8 not like", value, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8In(List<String> values) {
            addCriterion("GOODS_PIC8 in", values, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8NotIn(List<String> values) {
            addCriterion("GOODS_PIC8 not in", values, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8Between(String value1, String value2) {
            addCriterion("GOODS_PIC8 between", value1, value2, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic8NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC8 not between", value1, value2, "goodsPic8");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9IsNull() {
            addCriterion("GOODS_PIC9 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9IsNotNull() {
            addCriterion("GOODS_PIC9 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9EqualTo(String value) {
            addCriterion("GOODS_PIC9 =", value, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9NotEqualTo(String value) {
            addCriterion("GOODS_PIC9 <>", value, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9GreaterThan(String value) {
            addCriterion("GOODS_PIC9 >", value, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9GreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC9 >=", value, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9LessThan(String value) {
            addCriterion("GOODS_PIC9 <", value, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9LessThanOrEqualTo(String value) {
            addCriterion("GOODS_PIC9 <=", value, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9Like(String value) {
            addCriterion("GOODS_PIC9 like", value, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9NotLike(String value) {
            addCriterion("GOODS_PIC9 not like", value, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9In(List<String> values) {
            addCriterion("GOODS_PIC9 in", values, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9NotIn(List<String> values) {
            addCriterion("GOODS_PIC9 not in", values, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9Between(String value1, String value2) {
            addCriterion("GOODS_PIC9 between", value1, value2, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsPic9NotBetween(String value1, String value2) {
            addCriterion("GOODS_PIC9 not between", value1, value2, "goodsPic9");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateIsNull() {
            addCriterion("GOODS_GMT_CREATE is null");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateIsNotNull() {
            addCriterion("GOODS_GMT_CREATE is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateEqualTo(Date value) {
            addCriterion("GOODS_GMT_CREATE =", value, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateNotEqualTo(Date value) {
            addCriterion("GOODS_GMT_CREATE <>", value, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateGreaterThan(Date value) {
            addCriterion("GOODS_GMT_CREATE >", value, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("GOODS_GMT_CREATE >=", value, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateLessThan(Date value) {
            addCriterion("GOODS_GMT_CREATE <", value, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("GOODS_GMT_CREATE <=", value, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateIn(List<Date> values) {
            addCriterion("GOODS_GMT_CREATE in", values, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateNotIn(List<Date> values) {
            addCriterion("GOODS_GMT_CREATE not in", values, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateBetween(Date value1, Date value2) {
            addCriterion("GOODS_GMT_CREATE between", value1, value2, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("GOODS_GMT_CREATE not between", value1, value2, "goodsGmtCreate");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModIsNull() {
            addCriterion("GOODS_LAST_MOD is null");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModIsNotNull() {
            addCriterion("GOODS_LAST_MOD is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModEqualTo(Date value) {
            addCriterion("GOODS_LAST_MOD =", value, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModNotEqualTo(Date value) {
            addCriterion("GOODS_LAST_MOD <>", value, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModGreaterThan(Date value) {
            addCriterion("GOODS_LAST_MOD >", value, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModGreaterThanOrEqualTo(Date value) {
            addCriterion("GOODS_LAST_MOD >=", value, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModLessThan(Date value) {
            addCriterion("GOODS_LAST_MOD <", value, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModLessThanOrEqualTo(Date value) {
            addCriterion("GOODS_LAST_MOD <=", value, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModIn(List<Date> values) {
            addCriterion("GOODS_LAST_MOD in", values, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModNotIn(List<Date> values) {
            addCriterion("GOODS_LAST_MOD not in", values, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModBetween(Date value1, Date value2) {
            addCriterion("GOODS_LAST_MOD between", value1, value2, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsLastModNotBetween(Date value1, Date value2) {
            addCriterion("GOODS_LAST_MOD not between", value1, value2, "goodsLastMod");
            return (Criteria) this;
        }

        public Criteria andGoodsNoIsNull() {
            addCriterion("GOODS_NO is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNoIsNotNull() {
            addCriterion("GOODS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNoEqualTo(String value) {
            addCriterion("GOODS_NO =", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotEqualTo(String value) {
            addCriterion("GOODS_NO <>", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoGreaterThan(String value) {
            addCriterion("GOODS_NO >", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_NO >=", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLessThan(String value) {
            addCriterion("GOODS_NO <", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLessThanOrEqualTo(String value) {
            addCriterion("GOODS_NO <=", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLike(String value) {
            addCriterion("GOODS_NO like", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotLike(String value) {
            addCriterion("GOODS_NO not like", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoIn(List<String> values) {
            addCriterion("GOODS_NO in", values, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotIn(List<String> values) {
            addCriterion("GOODS_NO not in", values, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoBetween(String value1, String value2) {
            addCriterion("GOODS_NO between", value1, value2, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotBetween(String value1, String value2) {
            addCriterion("GOODS_NO not between", value1, value2, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIsNull() {
            addCriterion("GOODS_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIsNotNull() {
            addCriterion("GOODS_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusEqualTo(String value) {
            addCriterion("GOODS_STATUS =", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotEqualTo(String value) {
            addCriterion("GOODS_STATUS <>", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusGreaterThan(String value) {
            addCriterion("GOODS_STATUS >", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_STATUS >=", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusLessThan(String value) {
            addCriterion("GOODS_STATUS <", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusLessThanOrEqualTo(String value) {
            addCriterion("GOODS_STATUS <=", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusLike(String value) {
            addCriterion("GOODS_STATUS like", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotLike(String value) {
            addCriterion("GOODS_STATUS not like", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIn(List<String> values) {
            addCriterion("GOODS_STATUS in", values, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotIn(List<String> values) {
            addCriterion("GOODS_STATUS not in", values, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusBetween(String value1, String value2) {
            addCriterion("GOODS_STATUS between", value1, value2, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotBetween(String value1, String value2) {
            addCriterion("GOODS_STATUS not between", value1, value2, "goodsStatus");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
     * 
     * 2019-01-13
     */
    public static class Criterion {

        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}