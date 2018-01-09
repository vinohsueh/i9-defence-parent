package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipmentMonitorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentMonitorExample() {
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

        public Criteria andVoltageIsNull() {
            addCriterion("voltage is null");
            return (Criteria) this;
        }

        public Criteria andVoltageIsNotNull() {
            addCriterion("voltage is not null");
            return (Criteria) this;
        }

        public Criteria andVoltageEqualTo(Double value) {
            addCriterion("voltage =", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotEqualTo(Double value) {
            addCriterion("voltage <>", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageGreaterThan(Double value) {
            addCriterion("voltage >", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageGreaterThanOrEqualTo(Double value) {
            addCriterion("voltage >=", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLessThan(Double value) {
            addCriterion("voltage <", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageLessThanOrEqualTo(Double value) {
            addCriterion("voltage <=", value, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageIn(List<Double> values) {
            addCriterion("voltage in", values, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotIn(List<Double> values) {
            addCriterion("voltage not in", values, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageBetween(Double value1, Double value2) {
            addCriterion("voltage between", value1, value2, "voltage");
            return (Criteria) this;
        }

        public Criteria andVoltageNotBetween(Double value1, Double value2) {
            addCriterion("voltage not between", value1, value2, "voltage");
            return (Criteria) this;
        }

        public Criteria andElectricityIsNull() {
            addCriterion("electricity is null");
            return (Criteria) this;
        }

        public Criteria andElectricityIsNotNull() {
            addCriterion("electricity is not null");
            return (Criteria) this;
        }

        public Criteria andElectricityEqualTo(Double value) {
            addCriterion("electricity =", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityNotEqualTo(Double value) {
            addCriterion("electricity <>", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityGreaterThan(Double value) {
            addCriterion("electricity >", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityGreaterThanOrEqualTo(Double value) {
            addCriterion("electricity >=", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityLessThan(Double value) {
            addCriterion("electricity <", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityLessThanOrEqualTo(Double value) {
            addCriterion("electricity <=", value, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityIn(List<Double> values) {
            addCriterion("electricity in", values, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityNotIn(List<Double> values) {
            addCriterion("electricity not in", values, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityBetween(Double value1, Double value2) {
            addCriterion("electricity between", value1, value2, "electricity");
            return (Criteria) this;
        }

        public Criteria andElectricityNotBetween(Double value1, Double value2) {
            addCriterion("electricity not between", value1, value2, "electricity");
            return (Criteria) this;
        }

        public Criteria andRealtimeIsNull() {
            addCriterion("realtime is null");
            return (Criteria) this;
        }

        public Criteria andRealtimeIsNotNull() {
            addCriterion("realtime is not null");
            return (Criteria) this;
        }

        public Criteria andRealtimeEqualTo(Date value) {
            addCriterion("realtime =", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotEqualTo(Date value) {
            addCriterion("realtime <>", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeGreaterThan(Date value) {
            addCriterion("realtime >", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("realtime >=", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeLessThan(Date value) {
            addCriterion("realtime <", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeLessThanOrEqualTo(Date value) {
            addCriterion("realtime <=", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeIn(List<Date> values) {
            addCriterion("realtime in", values, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotIn(List<Date> values) {
            addCriterion("realtime not in", values, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeBetween(Date value1, Date value2) {
            addCriterion("realtime between", value1, value2, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotBetween(Date value1, Date value2) {
            addCriterion("realtime not between", value1, value2, "realtime");
            return (Criteria) this;
        }

        public Criteria andEqStateIsNull() {
            addCriterion("eqState is null");
            return (Criteria) this;
        }

        public Criteria andEqStateIsNotNull() {
            addCriterion("eqState is not null");
            return (Criteria) this;
        }

        public Criteria andEqStateEqualTo(String value) {
            addCriterion("eqState =", value, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateNotEqualTo(String value) {
            addCriterion("eqState <>", value, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateGreaterThan(String value) {
            addCriterion("eqState >", value, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateGreaterThanOrEqualTo(String value) {
            addCriterion("eqState >=", value, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateLessThan(String value) {
            addCriterion("eqState <", value, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateLessThanOrEqualTo(String value) {
            addCriterion("eqState <=", value, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateLike(String value) {
            addCriterion("eqState like", value, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateNotLike(String value) {
            addCriterion("eqState not like", value, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateIn(List<String> values) {
            addCriterion("eqState in", values, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateNotIn(List<String> values) {
            addCriterion("eqState not in", values, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateBetween(String value1, String value2) {
            addCriterion("eqState between", value1, value2, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqStateNotBetween(String value1, String value2) {
            addCriterion("eqState not between", value1, value2, "eqState");
            return (Criteria) this;
        }

        public Criteria andEqIdIsNull() {
            addCriterion("eqId is null");
            return (Criteria) this;
        }

        public Criteria andEqIdIsNotNull() {
            addCriterion("eqId is not null");
            return (Criteria) this;
        }

        public Criteria andEqIdEqualTo(Integer value) {
            addCriterion("eqId =", value, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdNotEqualTo(Integer value) {
            addCriterion("eqId <>", value, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdGreaterThan(Integer value) {
            addCriterion("eqId >", value, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eqId >=", value, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdLessThan(Integer value) {
            addCriterion("eqId <", value, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdLessThanOrEqualTo(Integer value) {
            addCriterion("eqId <=", value, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdIn(List<Integer> values) {
            addCriterion("eqId in", values, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdNotIn(List<Integer> values) {
            addCriterion("eqId not in", values, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdBetween(Integer value1, Integer value2) {
            addCriterion("eqId between", value1, value2, "eqId");
            return (Criteria) this;
        }

        public Criteria andEqIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eqId not between", value1, value2, "eqId");
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