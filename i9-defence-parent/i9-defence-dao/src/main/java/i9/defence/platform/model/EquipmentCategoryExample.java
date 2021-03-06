package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.List;

public class EquipmentCategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentCategoryExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameIsNull() {
            addCriterion("eqCategoryName is null");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameIsNotNull() {
            addCriterion("eqCategoryName is not null");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameEqualTo(String value) {
            addCriterion("eqCategoryName =", value, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameNotEqualTo(String value) {
            addCriterion("eqCategoryName <>", value, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameGreaterThan(String value) {
            addCriterion("eqCategoryName >", value, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("eqCategoryName >=", value, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameLessThan(String value) {
            addCriterion("eqCategoryName <", value, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("eqCategoryName <=", value, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameLike(String value) {
            addCriterion("eqCategoryName like", value, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameNotLike(String value) {
            addCriterion("eqCategoryName not like", value, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameIn(List<String> values) {
            addCriterion("eqCategoryName in", values, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameNotIn(List<String> values) {
            addCriterion("eqCategoryName not in", values, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameBetween(String value1, String value2) {
            addCriterion("eqCategoryName between", value1, value2, "eqCategoryName");
            return (Criteria) this;
        }

        public Criteria andEqCategoryNameNotBetween(String value1, String value2) {
            addCriterion("eqCategoryName not between", value1, value2, "eqCategoryName");
            return (Criteria) this;
        }
        
        public Criteria andEqCategoryIdIsNull() {
            addCriterion("eqCategoryId is null");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdIsNotNull() {
            addCriterion("eqCategoryId is not null");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdEqualTo(String value) {
            addCriterion("eqCategoryId =", value, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdNotEqualTo(String value) {
            addCriterion("eqCategoryName <>", value, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdGreaterThan(String value) {
            addCriterion("eqCategoryId >", value, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("eqCategoryId >=", value, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdLessThan(String value) {
            addCriterion("eqCategoryId <", value, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("eqCategoryId <=", value, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdLike(String value) {
            addCriterion("eqCategoryId like", value, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdNotLike(String value) {
            addCriterion("eqCategoryId not like", value, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdIn(List<String> values) {
            addCriterion("eqCategoryId in", values, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdNotIn(List<String> values) {
            addCriterion("eqCategoryId not in", values, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdBetween(String value1, String value2) {
            addCriterion("eqCategoryId between", value1, value2, "eqCategoryId");
            return (Criteria) this;
        }

        public Criteria andEqCategoryIdNotBetween(String value1, String value2) {
            addCriterion("eqCategoryId not between", value1, value2, "eqCategoryId");
            return (Criteria) this;
        }
        
        public Criteria andEqCategoryExplainIsNull() {
            addCriterion("eqCategoryExplain is null");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainIsNotNull() {
            addCriterion("eqCategoryExplain is not null");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainEqualTo(String value) {
            addCriterion("eqCategoryExplain =", value, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainNotEqualTo(String value) {
            addCriterion("eqCategoryExplain <>", value, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainGreaterThan(String value) {
            addCriterion("eqCategoryExplain >", value, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainGreaterThanOrEqualTo(String value) {
            addCriterion("eqCategoryExplain >=", value, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainLessThan(String value) {
            addCriterion("eqCategoryExplain <", value, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainLessThanOrEqualTo(String value) {
            addCriterion("eqCategoryExplain <=", value, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainLike(String value) {
            addCriterion("eqCategoryExplain like", value, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainNotLike(String value) {
            addCriterion("eqCategoryExplain not like", value, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainIn(List<String> values) {
            addCriterion("eqCategoryExplain in", values, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainNotIn(List<String> values) {
            addCriterion("eqCategoryExplain not in", values, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainBetween(String value1, String value2) {
            addCriterion("eqCategoryExplain between", value1, value2, "eqCategoryExplain");
            return (Criteria) this;
        }

        public Criteria andEqCategoryExplainNotBetween(String value1, String value2) {
            addCriterion("eqCategoryExplain not between", value1, value2, "eqCategoryExplain");
            return (Criteria) this;
        }
        
        public Criteria andEqSystemtypeIdIsNull() {
            addCriterion("eqSystemtypeId is null");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdIsNotNull() {
            addCriterion("eqSystemtypeId is not null");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdEqualTo(Integer value) {
            addCriterion("eqSystemtypeId =", value, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdNotEqualTo(Integer value) {
            addCriterion("eqSystemtypeId <>", value, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdGreaterThan(Integer value) {
            addCriterion("eqSystemtypeId >", value, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eqSystemtypeId >=", value, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdLessThan(Integer value) {
            addCriterion("eqSystemtypeId <", value, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("eqSystemtypeId <=", value, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdIn(List<Integer> values) {
            addCriterion("eqSystemtypeId in", values, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdNotIn(List<Integer> values) {
            addCriterion("eqSystemtypeId not in", values, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdBetween(Integer value1, Integer value2) {
            addCriterion("eqSystemtypeId between", value1, value2, "eqSystemtypeId");
            return (Criteria) this;
        }

        public Criteria andEqSystemtypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eqSystemtypeId not between", value1, value2, "eqSystemtypeId");
            return (Criteria) this;
        }

        
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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