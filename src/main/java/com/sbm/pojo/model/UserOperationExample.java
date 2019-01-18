package com.sbm.pojo.model;

import com.sbm.util.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserOperationExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    List<String> includeColumns = new ArrayList<String>();

    private static Map<String, String> MODEL_INFOS = new HashMap<String,String>();

    static {
        MODEL_INFOS.put("operationId","operation_id");
        MODEL_INFOS.put("operationUserId","operation_user_id");
        MODEL_INFOS.put("operationUserIphone","operation_user_iphone");
        MODEL_INFOS.put("operationDetail","operation_detail");
        MODEL_INFOS.put("operationRemark","operation_remark");
        MODEL_INFOS.put("operationGoodsId","operation_goods_id");
        MODEL_INFOS.put("createTime","create_time");
    }

    public UserOperationExample() {
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
     * 2019-01-15
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

        public Criteria andOperationIdIsNull() {
            addCriterion("operation_id is null");
            return (Criteria) this;
        }

        public Criteria andOperationIdIsNotNull() {
            addCriterion("operation_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperationIdEqualTo(String value) {
            addCriterion("operation_id =", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotEqualTo(String value) {
            addCriterion("operation_id <>", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdGreaterThan(String value) {
            addCriterion("operation_id >", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdGreaterThanOrEqualTo(String value) {
            addCriterion("operation_id >=", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdLessThan(String value) {
            addCriterion("operation_id <", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdLessThanOrEqualTo(String value) {
            addCriterion("operation_id <=", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdLike(String value) {
            addCriterion("operation_id like", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotLike(String value) {
            addCriterion("operation_id not like", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdIn(List<String> values) {
            addCriterion("operation_id in", values, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotIn(List<String> values) {
            addCriterion("operation_id not in", values, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdBetween(String value1, String value2) {
            addCriterion("operation_id between", value1, value2, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotBetween(String value1, String value2) {
            addCriterion("operation_id not between", value1, value2, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdIsNull() {
            addCriterion("operation_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdIsNotNull() {
            addCriterion("operation_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdEqualTo(String value) {
            addCriterion("operation_user_id =", value, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdNotEqualTo(String value) {
            addCriterion("operation_user_id <>", value, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdGreaterThan(String value) {
            addCriterion("operation_user_id >", value, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("operation_user_id >=", value, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdLessThan(String value) {
            addCriterion("operation_user_id <", value, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdLessThanOrEqualTo(String value) {
            addCriterion("operation_user_id <=", value, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdLike(String value) {
            addCriterion("operation_user_id like", value, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdNotLike(String value) {
            addCriterion("operation_user_id not like", value, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdIn(List<String> values) {
            addCriterion("operation_user_id in", values, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdNotIn(List<String> values) {
            addCriterion("operation_user_id not in", values, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdBetween(String value1, String value2) {
            addCriterion("operation_user_id between", value1, value2, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIdNotBetween(String value1, String value2) {
            addCriterion("operation_user_id not between", value1, value2, "operationUserId");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneIsNull() {
            addCriterion("operation_user_iphone is null");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneIsNotNull() {
            addCriterion("operation_user_iphone is not null");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneEqualTo(String value) {
            addCriterion("operation_user_iphone =", value, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneNotEqualTo(String value) {
            addCriterion("operation_user_iphone <>", value, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneGreaterThan(String value) {
            addCriterion("operation_user_iphone >", value, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneGreaterThanOrEqualTo(String value) {
            addCriterion("operation_user_iphone >=", value, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneLessThan(String value) {
            addCriterion("operation_user_iphone <", value, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneLessThanOrEqualTo(String value) {
            addCriterion("operation_user_iphone <=", value, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneLike(String value) {
            addCriterion("operation_user_iphone like", value, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneNotLike(String value) {
            addCriterion("operation_user_iphone not like", value, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneIn(List<String> values) {
            addCriterion("operation_user_iphone in", values, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneNotIn(List<String> values) {
            addCriterion("operation_user_iphone not in", values, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneBetween(String value1, String value2) {
            addCriterion("operation_user_iphone between", value1, value2, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationUserIphoneNotBetween(String value1, String value2) {
            addCriterion("operation_user_iphone not between", value1, value2, "operationUserIphone");
            return (Criteria) this;
        }

        public Criteria andOperationDetailIsNull() {
            addCriterion("operation_detail is null");
            return (Criteria) this;
        }

        public Criteria andOperationDetailIsNotNull() {
            addCriterion("operation_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOperationDetailEqualTo(String value) {
            addCriterion("operation_detail =", value, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailNotEqualTo(String value) {
            addCriterion("operation_detail <>", value, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailGreaterThan(String value) {
            addCriterion("operation_detail >", value, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailGreaterThanOrEqualTo(String value) {
            addCriterion("operation_detail >=", value, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailLessThan(String value) {
            addCriterion("operation_detail <", value, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailLessThanOrEqualTo(String value) {
            addCriterion("operation_detail <=", value, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailLike(String value) {
            addCriterion("operation_detail like", value, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailNotLike(String value) {
            addCriterion("operation_detail not like", value, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailIn(List<String> values) {
            addCriterion("operation_detail in", values, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailNotIn(List<String> values) {
            addCriterion("operation_detail not in", values, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailBetween(String value1, String value2) {
            addCriterion("operation_detail between", value1, value2, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationDetailNotBetween(String value1, String value2) {
            addCriterion("operation_detail not between", value1, value2, "operationDetail");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkIsNull() {
            addCriterion("operation_remark is null");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkIsNotNull() {
            addCriterion("operation_remark is not null");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkEqualTo(String value) {
            addCriterion("operation_remark =", value, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkNotEqualTo(String value) {
            addCriterion("operation_remark <>", value, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkGreaterThan(String value) {
            addCriterion("operation_remark >", value, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("operation_remark >=", value, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkLessThan(String value) {
            addCriterion("operation_remark <", value, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkLessThanOrEqualTo(String value) {
            addCriterion("operation_remark <=", value, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkLike(String value) {
            addCriterion("operation_remark like", value, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkNotLike(String value) {
            addCriterion("operation_remark not like", value, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkIn(List<String> values) {
            addCriterion("operation_remark in", values, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkNotIn(List<String> values) {
            addCriterion("operation_remark not in", values, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkBetween(String value1, String value2) {
            addCriterion("operation_remark between", value1, value2, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationRemarkNotBetween(String value1, String value2) {
            addCriterion("operation_remark not between", value1, value2, "operationRemark");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdIsNull() {
            addCriterion("operation_goods_id is null");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdIsNotNull() {
            addCriterion("operation_goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdEqualTo(String value) {
            addCriterion("operation_goods_id =", value, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdNotEqualTo(String value) {
            addCriterion("operation_goods_id <>", value, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdGreaterThan(String value) {
            addCriterion("operation_goods_id >", value, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdGreaterThanOrEqualTo(String value) {
            addCriterion("operation_goods_id >=", value, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdLessThan(String value) {
            addCriterion("operation_goods_id <", value, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdLessThanOrEqualTo(String value) {
            addCriterion("operation_goods_id <=", value, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdLike(String value) {
            addCriterion("operation_goods_id like", value, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdNotLike(String value) {
            addCriterion("operation_goods_id not like", value, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdIn(List<String> values) {
            addCriterion("operation_goods_id in", values, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdNotIn(List<String> values) {
            addCriterion("operation_goods_id not in", values, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdBetween(String value1, String value2) {
            addCriterion("operation_goods_id between", value1, value2, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andOperationGoodsIdNotBetween(String value1, String value2) {
            addCriterion("operation_goods_id not between", value1, value2, "operationGoodsId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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
     * 2019-01-15
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