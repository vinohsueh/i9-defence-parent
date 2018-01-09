package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EquipmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EquipmentExample() {
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

        public Criteria andEquipmentNameIsNull() {
            addCriterion("equipmentName is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameIsNotNull() {
            addCriterion("equipmentName is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameEqualTo(String value) {
            addCriterion("equipmentName =", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotEqualTo(String value) {
            addCriterion("equipmentName <>", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameGreaterThan(String value) {
            addCriterion("equipmentName >", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("equipmentName >=", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLessThan(String value) {
            addCriterion("equipmentName <", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLessThanOrEqualTo(String value) {
            addCriterion("equipmentName <=", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLike(String value) {
            addCriterion("equipmentName like", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotLike(String value) {
            addCriterion("equipmentName not like", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameIn(List<String> values) {
            addCriterion("equipmentName in", values, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotIn(List<String> values) {
            addCriterion("equipmentName not in", values, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameBetween(String value1, String value2) {
            addCriterion("equipmentName between", value1, value2, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotBetween(String value1, String value2) {
            addCriterion("equipmentName not between", value1, value2, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumIsNull() {
            addCriterion("equipmentNum is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumIsNotNull() {
            addCriterion("equipmentNum is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumEqualTo(Integer value) {
            addCriterion("equipmentNum =", value, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumNotEqualTo(Integer value) {
            addCriterion("equipmentNum <>", value, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumGreaterThan(Integer value) {
            addCriterion("equipmentNum >", value, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipmentNum >=", value, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumLessThan(Integer value) {
            addCriterion("equipmentNum <", value, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumLessThanOrEqualTo(Integer value) {
            addCriterion("equipmentNum <=", value, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumIn(List<Integer> values) {
            addCriterion("equipmentNum in", values, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumNotIn(List<Integer> values) {
            addCriterion("equipmentNum not in", values, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumBetween(Integer value1, Integer value2) {
            addCriterion("equipmentNum between", value1, value2, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("equipmentNum not between", value1, value2, "equipmentNum");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierIsNull() {
            addCriterion("equipmentIdentifier is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierIsNotNull() {
            addCriterion("equipmentIdentifier is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierEqualTo(Integer value) {
            addCriterion("equipmentIdentifier =", value, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierNotEqualTo(Integer value) {
            addCriterion("equipmentIdentifier <>", value, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierGreaterThan(Integer value) {
            addCriterion("equipmentIdentifier >", value, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipmentIdentifier >=", value, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierLessThan(Integer value) {
            addCriterion("equipmentIdentifier <", value, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierLessThanOrEqualTo(Integer value) {
            addCriterion("equipmentIdentifier <=", value, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierIn(List<Integer> values) {
            addCriterion("equipmentIdentifier in", values, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierNotIn(List<Integer> values) {
            addCriterion("equipmentIdentifier not in", values, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierBetween(Integer value1, Integer value2) {
            addCriterion("equipmentIdentifier between", value1, value2, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdentifierNotBetween(Integer value1, Integer value2) {
            addCriterion("equipmentIdentifier not between", value1, value2, "equipmentIdentifier");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionIsNull() {
            addCriterion("equipmentPosition is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionIsNotNull() {
            addCriterion("equipmentPosition is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionEqualTo(String value) {
            addCriterion("equipmentPosition =", value, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionNotEqualTo(String value) {
            addCriterion("equipmentPosition <>", value, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionGreaterThan(String value) {
            addCriterion("equipmentPosition >", value, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionGreaterThanOrEqualTo(String value) {
            addCriterion("equipmentPosition >=", value, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionLessThan(String value) {
            addCriterion("equipmentPosition <", value, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionLessThanOrEqualTo(String value) {
            addCriterion("equipmentPosition <=", value, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionLike(String value) {
            addCriterion("equipmentPosition like", value, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionNotLike(String value) {
            addCriterion("equipmentPosition not like", value, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionIn(List<String> values) {
            addCriterion("equipmentPosition in", values, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionNotIn(List<String> values) {
            addCriterion("equipmentPosition not in", values, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionBetween(String value1, String value2) {
            addCriterion("equipmentPosition between", value1, value2, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentPositionNotBetween(String value1, String value2) {
            addCriterion("equipmentPosition not between", value1, value2, "equipmentPosition");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateIsNull() {
            addCriterion("equipmentDate is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateIsNotNull() {
            addCriterion("equipmentDate is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateEqualTo(Date value) {
            addCriterion("equipmentDate =", value, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateNotEqualTo(Date value) {
            addCriterion("equipmentDate <>", value, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateGreaterThan(Date value) {
            addCriterion("equipmentDate >", value, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateGreaterThanOrEqualTo(Date value) {
            addCriterion("equipmentDate >=", value, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateLessThan(Date value) {
            addCriterion("equipmentDate <", value, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateLessThanOrEqualTo(Date value) {
            addCriterion("equipmentDate <=", value, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateIn(List<Date> values) {
            addCriterion("equipmentDate in", values, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateNotIn(List<Date> values) {
            addCriterion("equipmentDate not in", values, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateBetween(Date value1, Date value2) {
            addCriterion("equipmentDate between", value1, value2, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentDateNotBetween(Date value1, Date value2) {
            addCriterion("equipmentDate not between", value1, value2, "equipmentDate");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksIsNull() {
            addCriterion("equipmentRemarks is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksIsNotNull() {
            addCriterion("equipmentRemarks is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksEqualTo(String value) {
            addCriterion("equipmentRemarks =", value, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksNotEqualTo(String value) {
            addCriterion("equipmentRemarks <>", value, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksGreaterThan(String value) {
            addCriterion("equipmentRemarks >", value, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("equipmentRemarks >=", value, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksLessThan(String value) {
            addCriterion("equipmentRemarks <", value, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksLessThanOrEqualTo(String value) {
            addCriterion("equipmentRemarks <=", value, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksLike(String value) {
            addCriterion("equipmentRemarks like", value, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksNotLike(String value) {
            addCriterion("equipmentRemarks not like", value, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksIn(List<String> values) {
            addCriterion("equipmentRemarks in", values, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksNotIn(List<String> values) {
            addCriterion("equipmentRemarks not in", values, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksBetween(String value1, String value2) {
            addCriterion("equipmentRemarks between", value1, value2, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentRemarksNotBetween(String value1, String value2) {
            addCriterion("equipmentRemarks not between", value1, value2, "equipmentRemarks");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIsNull() {
            addCriterion("equipmentCategoryId is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIsNotNull() {
            addCriterion("equipmentCategoryId is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdEqualTo(Integer value) {
            addCriterion("equipmentCategoryId =", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotEqualTo(Integer value) {
            addCriterion("equipmentCategoryId <>", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdGreaterThan(Integer value) {
            addCriterion("equipmentCategoryId >", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipmentCategoryId >=", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdLessThan(Integer value) {
            addCriterion("equipmentCategoryId <", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("equipmentCategoryId <=", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIn(List<Integer> values) {
            addCriterion("equipmentCategoryId in", values, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotIn(List<Integer> values) {
            addCriterion("equipmentCategoryId not in", values, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("equipmentCategoryId between", value1, value2, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("equipmentCategoryId not between", value1, value2, "equipmentCategoryId");
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