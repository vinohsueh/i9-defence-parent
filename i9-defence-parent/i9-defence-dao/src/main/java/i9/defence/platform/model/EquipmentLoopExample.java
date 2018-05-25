package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.List;

public class EquipmentLoopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentLoopExample() {
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

        public Criteria andLooIdIsNull() {
            addCriterion("looId is null");
            return (Criteria) this;
        }

        public Criteria andLooIdIsNotNull() {
            addCriterion("looId is not null");
            return (Criteria) this;
        }

        public Criteria andLooIdEqualTo(Integer value) {
            addCriterion("looId =", value, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdNotEqualTo(Integer value) {
            addCriterion("looId <>", value, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdGreaterThan(Integer value) {
            addCriterion("looId >", value, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("looId >=", value, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdLessThan(Integer value) {
            addCriterion("looId <", value, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdLessThanOrEqualTo(Integer value) {
            addCriterion("looId <=", value, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdIn(List<Integer> values) {
            addCriterion("looId in", values, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdNotIn(List<Integer> values) {
            addCriterion("looId not in", values, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdBetween(Integer value1, Integer value2) {
            addCriterion("looId between", value1, value2, "looId");
            return (Criteria) this;
        }

        public Criteria andLooIdNotBetween(Integer value1, Integer value2) {
            addCriterion("looId not between", value1, value2, "looId");
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