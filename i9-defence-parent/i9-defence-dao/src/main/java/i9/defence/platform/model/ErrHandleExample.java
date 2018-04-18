package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrHandleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErrHandleExample() {
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

        public Criteria andHandleConIsNull() {
            addCriterion("handleCon is null");
            return (Criteria) this;
        }

        public Criteria andHandleConIsNotNull() {
            addCriterion("handleCon is not null");
            return (Criteria) this;
        }

        public Criteria andHandleConEqualTo(String value) {
            addCriterion("handleCon =", value, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConNotEqualTo(String value) {
            addCriterion("handleCon <>", value, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConGreaterThan(String value) {
            addCriterion("handleCon >", value, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConGreaterThanOrEqualTo(String value) {
            addCriterion("handleCon >=", value, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConLessThan(String value) {
            addCriterion("handleCon <", value, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConLessThanOrEqualTo(String value) {
            addCriterion("handleCon <=", value, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConLike(String value) {
            addCriterion("handleCon like", value, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConNotLike(String value) {
            addCriterion("handleCon not like", value, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConIn(List<String> values) {
            addCriterion("handleCon in", values, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConNotIn(List<String> values) {
            addCriterion("handleCon not in", values, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConBetween(String value1, String value2) {
            addCriterion("handleCon between", value1, value2, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleConNotBetween(String value1, String value2) {
            addCriterion("handleCon not between", value1, value2, "handleCon");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdIsNull() {
            addCriterion("handleManagerId is null");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdIsNotNull() {
            addCriterion("handleManagerId is not null");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdEqualTo(Integer value) {
            addCriterion("handleManagerId =", value, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdNotEqualTo(Integer value) {
            addCriterion("handleManagerId <>", value, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdGreaterThan(Integer value) {
            addCriterion("handleManagerId >", value, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("handleManagerId >=", value, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdLessThan(Integer value) {
            addCriterion("handleManagerId <", value, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("handleManagerId <=", value, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdIn(List<Integer> values) {
            addCriterion("handleManagerId in", values, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdNotIn(List<Integer> values) {
            addCriterion("handleManagerId not in", values, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("handleManagerId between", value1, value2, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleManagerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("handleManagerId not between", value1, value2, "handleManagerId");
            return (Criteria) this;
        }

        public Criteria andHandleDateIsNull() {
            addCriterion("handleDate is null");
            return (Criteria) this;
        }

        public Criteria andHandleDateIsNotNull() {
            addCriterion("handleDate is not null");
            return (Criteria) this;
        }

        public Criteria andHandleDateEqualTo(Date value) {
            addCriterion("handleDate =", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateNotEqualTo(Date value) {
            addCriterion("handleDate <>", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateGreaterThan(Date value) {
            addCriterion("handleDate >", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateGreaterThanOrEqualTo(Date value) {
            addCriterion("handleDate >=", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateLessThan(Date value) {
            addCriterion("handleDate <", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateLessThanOrEqualTo(Date value) {
            addCriterion("handleDate <=", value, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateIn(List<Date> values) {
            addCriterion("handleDate in", values, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateNotIn(List<Date> values) {
            addCriterion("handleDate not in", values, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateBetween(Date value1, Date value2) {
            addCriterion("handleDate between", value1, value2, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleDateNotBetween(Date value1, Date value2) {
            addCriterion("handleDate not between", value1, value2, "handleDate");
            return (Criteria) this;
        }

        public Criteria andHandleStateIsNull() {
            addCriterion("handleState is null");
            return (Criteria) this;
        }

        public Criteria andHandleStateIsNotNull() {
            addCriterion("handleState is not null");
            return (Criteria) this;
        }

        public Criteria andHandleStateEqualTo(Integer value) {
            addCriterion("handleState =", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateNotEqualTo(Integer value) {
            addCriterion("handleState <>", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateGreaterThan(Integer value) {
            addCriterion("handleState >", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("handleState >=", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateLessThan(Integer value) {
            addCriterion("handleState <", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateLessThanOrEqualTo(Integer value) {
            addCriterion("handleState <=", value, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateIn(List<Integer> values) {
            addCriterion("handleState in", values, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateNotIn(List<Integer> values) {
            addCriterion("handleState not in", values, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateBetween(Integer value1, Integer value2) {
            addCriterion("handleState between", value1, value2, "handleState");
            return (Criteria) this;
        }

        public Criteria andHandleStateNotBetween(Integer value1, Integer value2) {
            addCriterion("handleState not between", value1, value2, "handleState");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdIsNull() {
            addCriterion("eqDeviceId is null");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdIsNotNull() {
            addCriterion("eqDeviceId is not null");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdEqualTo(String value) {
            addCriterion("eqDeviceId =", value, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdNotEqualTo(String value) {
            addCriterion("eqDeviceId <>", value, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdGreaterThan(String value) {
            addCriterion("eqDeviceId >", value, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdGreaterThanOrEqualTo(String value) {
            addCriterion("eqDeviceId >=", value, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdLessThan(String value) {
            addCriterion("eqDeviceId <", value, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdLessThanOrEqualTo(String value) {
            addCriterion("eqDeviceId <=", value, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdLike(String value) {
            addCriterion("eqDeviceId like", value, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdNotLike(String value) {
            addCriterion("eqDeviceId not like", value, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdIn(List<String> values) {
            addCriterion("eqDeviceId in", values, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdNotIn(List<String> values) {
            addCriterion("eqDeviceId not in", values, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdBetween(String value1, String value2) {
            addCriterion("eqDeviceId between", value1, value2, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqDeviceIdNotBetween(String value1, String value2) {
            addCriterion("eqDeviceId not between", value1, value2, "eqDeviceId");
            return (Criteria) this;
        }

        public Criteria andEqAddRessIsNull() {
            addCriterion("eqAddRess is null");
            return (Criteria) this;
        }

        public Criteria andEqAddRessIsNotNull() {
            addCriterion("eqAddRess is not null");
            return (Criteria) this;
        }

        public Criteria andEqAddRessEqualTo(String value) {
            addCriterion("eqAddRess =", value, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessNotEqualTo(String value) {
            addCriterion("eqAddRess <>", value, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessGreaterThan(String value) {
            addCriterion("eqAddRess >", value, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessGreaterThanOrEqualTo(String value) {
            addCriterion("eqAddRess >=", value, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessLessThan(String value) {
            addCriterion("eqAddRess <", value, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessLessThanOrEqualTo(String value) {
            addCriterion("eqAddRess <=", value, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessLike(String value) {
            addCriterion("eqAddRess like", value, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessNotLike(String value) {
            addCriterion("eqAddRess not like", value, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessIn(List<String> values) {
            addCriterion("eqAddRess in", values, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessNotIn(List<String> values) {
            addCriterion("eqAddRess not in", values, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessBetween(String value1, String value2) {
            addCriterion("eqAddRess between", value1, value2, "eqAddRess");
            return (Criteria) this;
        }

        public Criteria andEqAddRessNotBetween(String value1, String value2) {
            addCriterion("eqAddRess not between", value1, value2, "eqAddRess");
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