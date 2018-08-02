package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MessageLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageLogExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andTemplateNumIsNull() {
            addCriterion("templateNum is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNumIsNotNull() {
            addCriterion("templateNum is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNumEqualTo(String value) {
            addCriterion("templateNum =", value, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumNotEqualTo(String value) {
            addCriterion("templateNum <>", value, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumGreaterThan(String value) {
            addCriterion("templateNum >", value, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumGreaterThanOrEqualTo(String value) {
            addCriterion("templateNum >=", value, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumLessThan(String value) {
            addCriterion("templateNum <", value, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumLessThanOrEqualTo(String value) {
            addCriterion("templateNum <=", value, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumLike(String value) {
            addCriterion("templateNum like", value, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumNotLike(String value) {
            addCriterion("templateNum not like", value, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumIn(List<String> values) {
            addCriterion("templateNum in", values, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumNotIn(List<String> values) {
            addCriterion("templateNum not in", values, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumBetween(String value1, String value2) {
            addCriterion("templateNum between", value1, value2, "templateNum");
            return (Criteria) this;
        }

        public Criteria andTemplateNumNotBetween(String value1, String value2) {
            addCriterion("templateNum not between", value1, value2, "templateNum");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNull() {
            addCriterion("sendStatus is null");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNotNull() {
            addCriterion("sendStatus is not null");
            return (Criteria) this;
        }

        public Criteria andSendStatusEqualTo(Integer value) {
            addCriterion("sendStatus =", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotEqualTo(Integer value) {
            addCriterion("sendStatus <>", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThan(Integer value) {
            addCriterion("sendStatus >", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sendStatus >=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThan(Integer value) {
            addCriterion("sendStatus <", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThanOrEqualTo(Integer value) {
            addCriterion("sendStatus <=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIn(List<Integer> values) {
            addCriterion("sendStatus in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotIn(List<Integer> values) {
            addCriterion("sendStatus not in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusBetween(Integer value1, Integer value2) {
            addCriterion("sendStatus between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("sendStatus not between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("sendTime is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("sendTime is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterionForJDBCDate("sendTime =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("sendTime <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("sendTime >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sendTime >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterionForJDBCDate("sendTime <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sendTime <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterionForJDBCDate("sendTime in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("sendTime not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sendTime between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sendTime not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andPhonesIsNull() {
            addCriterion("phones is null");
            return (Criteria) this;
        }

        public Criteria andPhonesIsNotNull() {
            addCriterion("phones is not null");
            return (Criteria) this;
        }

        public Criteria andPhonesEqualTo(String value) {
            addCriterion("phones =", value, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesNotEqualTo(String value) {
            addCriterion("phones <>", value, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesGreaterThan(String value) {
            addCriterion("phones >", value, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesGreaterThanOrEqualTo(String value) {
            addCriterion("phones >=", value, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesLessThan(String value) {
            addCriterion("phones <", value, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesLessThanOrEqualTo(String value) {
            addCriterion("phones <=", value, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesLike(String value) {
            addCriterion("phones like", value, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesNotLike(String value) {
            addCriterion("phones not like", value, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesIn(List<String> values) {
            addCriterion("phones in", values, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesNotIn(List<String> values) {
            addCriterion("phones not in", values, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesBetween(String value1, String value2) {
            addCriterion("phones between", value1, value2, "phones");
            return (Criteria) this;
        }

        public Criteria andPhonesNotBetween(String value1, String value2) {
            addCriterion("phones not between", value1, value2, "phones");
            return (Criteria) this;
        }

        public Criteria andClientNamesIsNull() {
            addCriterion("clientNames is null");
            return (Criteria) this;
        }

        public Criteria andClientNamesIsNotNull() {
            addCriterion("clientNames is not null");
            return (Criteria) this;
        }

        public Criteria andClientNamesEqualTo(String value) {
            addCriterion("clientNames =", value, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesNotEqualTo(String value) {
            addCriterion("clientNames <>", value, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesGreaterThan(String value) {
            addCriterion("clientNames >", value, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesGreaterThanOrEqualTo(String value) {
            addCriterion("clientNames >=", value, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesLessThan(String value) {
            addCriterion("clientNames <", value, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesLessThanOrEqualTo(String value) {
            addCriterion("clientNames <=", value, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesLike(String value) {
            addCriterion("clientNames like", value, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesNotLike(String value) {
            addCriterion("clientNames not like", value, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesIn(List<String> values) {
            addCriterion("clientNames in", values, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesNotIn(List<String> values) {
            addCriterion("clientNames not in", values, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesBetween(String value1, String value2) {
            addCriterion("clientNames between", value1, value2, "clientNames");
            return (Criteria) this;
        }

        public Criteria andClientNamesNotBetween(String value1, String value2) {
            addCriterion("clientNames not between", value1, value2, "clientNames");
            return (Criteria) this;
        }

        public Criteria andSignNameIsNull() {
            addCriterion("signName is null");
            return (Criteria) this;
        }

        public Criteria andSignNameIsNotNull() {
            addCriterion("signName is not null");
            return (Criteria) this;
        }

        public Criteria andSignNameEqualTo(String value) {
            addCriterion("signName =", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotEqualTo(String value) {
            addCriterion("signName <>", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameGreaterThan(String value) {
            addCriterion("signName >", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameGreaterThanOrEqualTo(String value) {
            addCriterion("signName >=", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLessThan(String value) {
            addCriterion("signName <", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLessThanOrEqualTo(String value) {
            addCriterion("signName <=", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLike(String value) {
            addCriterion("signName like", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotLike(String value) {
            addCriterion("signName not like", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameIn(List<String> values) {
            addCriterion("signName in", values, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotIn(List<String> values) {
            addCriterion("signName not in", values, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameBetween(String value1, String value2) {
            addCriterion("signName between", value1, value2, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotBetween(String value1, String value2) {
            addCriterion("signName not between", value1, value2, "signName");
            return (Criteria) this;
        }

        public Criteria andSendResultIsNull() {
            addCriterion("sendResult is null");
            return (Criteria) this;
        }

        public Criteria andSendResultIsNotNull() {
            addCriterion("sendResult is not null");
            return (Criteria) this;
        }

        public Criteria andSendResultEqualTo(String value) {
            addCriterion("sendResult =", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotEqualTo(String value) {
            addCriterion("sendResult <>", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultGreaterThan(String value) {
            addCriterion("sendResult >", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultGreaterThanOrEqualTo(String value) {
            addCriterion("sendResult >=", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLessThan(String value) {
            addCriterion("sendResult <", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLessThanOrEqualTo(String value) {
            addCriterion("sendResult <=", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLike(String value) {
            addCriterion("sendResult like", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotLike(String value) {
            addCriterion("sendResult not like", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultIn(List<String> values) {
            addCriterion("sendResult in", values, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotIn(List<String> values) {
            addCriterion("sendResult not in", values, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultBetween(String value1, String value2) {
            addCriterion("sendResult between", value1, value2, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotBetween(String value1, String value2) {
            addCriterion("sendResult not between", value1, value2, "sendResult");
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