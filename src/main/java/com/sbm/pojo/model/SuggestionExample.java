package com.sbm.pojo.model;

import com.sbm.util.Page;

import java.util.*;

public class SuggestionExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    List<String> includeColumns = new ArrayList<String>();

    private static Map<String, String> MODEL_INFOS = new HashMap<String,String>();

    static {
        MODEL_INFOS.put("suggestionId","SUGGESTION_ID");
        MODEL_INFOS.put("suggestionScore","SUGGESTION_SCORE");
        MODEL_INFOS.put("suggestionDetail","SUGGESTION_DETAIL");
        MODEL_INFOS.put("createTime","CREATE_TIME");
        MODEL_INFOS.put("ifAudit","IF_AUDIT");
        MODEL_INFOS.put("updateTime","UPDATE_TIME");
        MODEL_INFOS.put("version","VERSION");
    }

    public SuggestionExample() {
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
     * 2018-08-01
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

        public Criteria andSuggestionIdIsNull() {
            addCriterion("SUGGESTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdIsNotNull() {
            addCriterion("SUGGESTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdEqualTo(String value) {
            addCriterion("SUGGESTION_ID =", value, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdNotEqualTo(String value) {
            addCriterion("SUGGESTION_ID <>", value, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdGreaterThan(String value) {
            addCriterion("SUGGESTION_ID >", value, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUGGESTION_ID >=", value, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdLessThan(String value) {
            addCriterion("SUGGESTION_ID <", value, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdLessThanOrEqualTo(String value) {
            addCriterion("SUGGESTION_ID <=", value, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdLike(String value) {
            addCriterion("SUGGESTION_ID like", value, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdNotLike(String value) {
            addCriterion("SUGGESTION_ID not like", value, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdIn(List<String> values) {
            addCriterion("SUGGESTION_ID in", values, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdNotIn(List<String> values) {
            addCriterion("SUGGESTION_ID not in", values, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdBetween(String value1, String value2) {
            addCriterion("SUGGESTION_ID between", value1, value2, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionIdNotBetween(String value1, String value2) {
            addCriterion("SUGGESTION_ID not between", value1, value2, "suggestionId");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreIsNull() {
            addCriterion("SUGGESTION_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreIsNotNull() {
            addCriterion("SUGGESTION_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreEqualTo(String value) {
            addCriterion("SUGGESTION_SCORE =", value, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreNotEqualTo(String value) {
            addCriterion("SUGGESTION_SCORE <>", value, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreGreaterThan(String value) {
            addCriterion("SUGGESTION_SCORE >", value, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SUGGESTION_SCORE >=", value, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreLessThan(String value) {
            addCriterion("SUGGESTION_SCORE <", value, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreLessThanOrEqualTo(String value) {
            addCriterion("SUGGESTION_SCORE <=", value, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreLike(String value) {
            addCriterion("SUGGESTION_SCORE like", value, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreNotLike(String value) {
            addCriterion("SUGGESTION_SCORE not like", value, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreIn(List<String> values) {
            addCriterion("SUGGESTION_SCORE in", values, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreNotIn(List<String> values) {
            addCriterion("SUGGESTION_SCORE not in", values, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreBetween(String value1, String value2) {
            addCriterion("SUGGESTION_SCORE between", value1, value2, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionScoreNotBetween(String value1, String value2) {
            addCriterion("SUGGESTION_SCORE not between", value1, value2, "suggestionScore");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailIsNull() {
            addCriterion("SUGGESTION_DETAIL is null");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailIsNotNull() {
            addCriterion("SUGGESTION_DETAIL is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailEqualTo(String value) {
            addCriterion("SUGGESTION_DETAIL =", value, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailNotEqualTo(String value) {
            addCriterion("SUGGESTION_DETAIL <>", value, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailGreaterThan(String value) {
            addCriterion("SUGGESTION_DETAIL >", value, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailGreaterThanOrEqualTo(String value) {
            addCriterion("SUGGESTION_DETAIL >=", value, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailLessThan(String value) {
            addCriterion("SUGGESTION_DETAIL <", value, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailLessThanOrEqualTo(String value) {
            addCriterion("SUGGESTION_DETAIL <=", value, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailLike(String value) {
            addCriterion("SUGGESTION_DETAIL like", value, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailNotLike(String value) {
            addCriterion("SUGGESTION_DETAIL not like", value, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailIn(List<String> values) {
            addCriterion("SUGGESTION_DETAIL in", values, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailNotIn(List<String> values) {
            addCriterion("SUGGESTION_DETAIL not in", values, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailBetween(String value1, String value2) {
            addCriterion("SUGGESTION_DETAIL between", value1, value2, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andSuggestionDetailNotBetween(String value1, String value2) {
            addCriterion("SUGGESTION_DETAIL not between", value1, value2, "suggestionDetail");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andIfAuditIsNull() {
            addCriterion("IF_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andIfAuditIsNotNull() {
            addCriterion("IF_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andIfAuditEqualTo(String value) {
            addCriterion("IF_AUDIT =", value, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditNotEqualTo(String value) {
            addCriterion("IF_AUDIT <>", value, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditGreaterThan(String value) {
            addCriterion("IF_AUDIT >", value, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditGreaterThanOrEqualTo(String value) {
            addCriterion("IF_AUDIT >=", value, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditLessThan(String value) {
            addCriterion("IF_AUDIT <", value, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditLessThanOrEqualTo(String value) {
            addCriterion("IF_AUDIT <=", value, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditLike(String value) {
            addCriterion("IF_AUDIT like", value, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditNotLike(String value) {
            addCriterion("IF_AUDIT not like", value, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditIn(List<String> values) {
            addCriterion("IF_AUDIT in", values, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditNotIn(List<String> values) {
            addCriterion("IF_AUDIT not in", values, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditBetween(String value1, String value2) {
            addCriterion("IF_AUDIT between", value1, value2, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andIfAuditNotBetween(String value1, String value2) {
            addCriterion("IF_AUDIT not between", value1, value2, "ifAudit");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("VERSION not between", value1, value2, "version");
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
     * 2018-08-01
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