package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.List;

public class HiddenDangerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HiddenDangerExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxIsNull() {
            addCriterion("HiddenMax is null");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxIsNotNull() {
            addCriterion("HiddenMax is not null");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxEqualTo(Double value) {
            addCriterion("HiddenMax =", value, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxNotEqualTo(Double value) {
            addCriterion("HiddenMax <>", value, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxGreaterThan(Double value) {
            addCriterion("HiddenMax >", value, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxGreaterThanOrEqualTo(Double value) {
            addCriterion("HiddenMax >=", value, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxLessThan(Double value) {
            addCriterion("HiddenMax <", value, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxLessThanOrEqualTo(Double value) {
            addCriterion("HiddenMax <=", value, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxIn(List<Double> values) {
            addCriterion("HiddenMax in", values, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxNotIn(List<Double> values) {
            addCriterion("HiddenMax not in", values, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxBetween(Double value1, Double value2) {
            addCriterion("HiddenMax between", value1, value2, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMaxNotBetween(Double value1, Double value2) {
            addCriterion("HiddenMax not between", value1, value2, "hiddenMax");
            return (Criteria) this;
        }

        public Criteria andHiddenMinIsNull() {
            addCriterion("HiddenMin is null");
            return (Criteria) this;
        }

        public Criteria andHiddenMinIsNotNull() {
            addCriterion("HiddenMin is not null");
            return (Criteria) this;
        }

        public Criteria andHiddenMinEqualTo(Double value) {
            addCriterion("HiddenMin =", value, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinNotEqualTo(Double value) {
            addCriterion("HiddenMin <>", value, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinGreaterThan(Double value) {
            addCriterion("HiddenMin >", value, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinGreaterThanOrEqualTo(Double value) {
            addCriterion("HiddenMin >=", value, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinLessThan(Double value) {
            addCriterion("HiddenMin <", value, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinLessThanOrEqualTo(Double value) {
            addCriterion("HiddenMin <=", value, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinIn(List<Double> values) {
            addCriterion("HiddenMin in", values, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinNotIn(List<Double> values) {
            addCriterion("HiddenMin not in", values, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinBetween(Double value1, Double value2) {
            addCriterion("HiddenMin between", value1, value2, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andHiddenMinNotBetween(Double value1, Double value2) {
            addCriterion("HiddenMin not between", value1, value2, "hiddenMin");
            return (Criteria) this;
        }

        public Criteria andWarningMaxIsNull() {
            addCriterion("WarningMax is null");
            return (Criteria) this;
        }

        public Criteria andWarningMaxIsNotNull() {
            addCriterion("WarningMax is not null");
            return (Criteria) this;
        }

        public Criteria andWarningMaxEqualTo(Double value) {
            addCriterion("WarningMax =", value, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxNotEqualTo(Double value) {
            addCriterion("WarningMax <>", value, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxGreaterThan(Double value) {
            addCriterion("WarningMax >", value, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxGreaterThanOrEqualTo(Double value) {
            addCriterion("WarningMax >=", value, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxLessThan(Double value) {
            addCriterion("WarningMax <", value, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxLessThanOrEqualTo(Double value) {
            addCriterion("WarningMax <=", value, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxIn(List<Double> values) {
            addCriterion("WarningMax in", values, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxNotIn(List<Double> values) {
            addCriterion("WarningMax not in", values, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxBetween(Double value1, Double value2) {
            addCriterion("WarningMax between", value1, value2, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMaxNotBetween(Double value1, Double value2) {
            addCriterion("WarningMax not between", value1, value2, "warningMax");
            return (Criteria) this;
        }

        public Criteria andWarningMinIsNull() {
            addCriterion("WarningMin is null");
            return (Criteria) this;
        }

        public Criteria andWarningMinIsNotNull() {
            addCriterion("WarningMin is not null");
            return (Criteria) this;
        }

        public Criteria andWarningMinEqualTo(Double value) {
            addCriterion("WarningMin =", value, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinNotEqualTo(Double value) {
            addCriterion("WarningMin <>", value, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinGreaterThan(Double value) {
            addCriterion("WarningMin >", value, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinGreaterThanOrEqualTo(Double value) {
            addCriterion("WarningMin >=", value, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinLessThan(Double value) {
            addCriterion("WarningMin <", value, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinLessThanOrEqualTo(Double value) {
            addCriterion("WarningMin <=", value, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinIn(List<Double> values) {
            addCriterion("WarningMin in", values, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinNotIn(List<Double> values) {
            addCriterion("WarningMin not in", values, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinBetween(Double value1, Double value2) {
            addCriterion("WarningMin between", value1, value2, "warningMin");
            return (Criteria) this;
        }

        public Criteria andWarningMinNotBetween(Double value1, Double value2) {
            addCriterion("WarningMin not between", value1, value2, "warningMin");
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