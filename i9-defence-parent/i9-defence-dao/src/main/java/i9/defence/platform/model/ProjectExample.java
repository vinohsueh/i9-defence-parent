package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectExample() {
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

        public Criteria andProjectNameIsNull() {
            addCriterion("projectName is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("projectName is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("projectName =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("projectName <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("projectName >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("projectName >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("projectName <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("projectName <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("projectName like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("projectName not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("projectName in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("projectName not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("projectName between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("projectName not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectAddressIsNull() {
            addCriterion("projectAddress is null");
            return (Criteria) this;
        }

        public Criteria andProjectAddressIsNotNull() {
            addCriterion("projectAddress is not null");
            return (Criteria) this;
        }

        public Criteria andProjectAddressEqualTo(String value) {
            addCriterion("projectAddress =", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotEqualTo(String value) {
            addCriterion("projectAddress <>", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressGreaterThan(String value) {
            addCriterion("projectAddress >", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressGreaterThanOrEqualTo(String value) {
            addCriterion("projectAddress >=", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLessThan(String value) {
            addCriterion("projectAddress <", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLessThanOrEqualTo(String value) {
            addCriterion("projectAddress <=", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLike(String value) {
            addCriterion("projectAddress like", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotLike(String value) {
            addCriterion("projectAddress not like", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressIn(List<String> values) {
            addCriterion("projectAddress in", values, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotIn(List<String> values) {
            addCriterion("projectAddress not in", values, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressBetween(String value1, String value2) {
            addCriterion("projectAddress between", value1, value2, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotBetween(String value1, String value2) {
            addCriterion("projectAddress not between", value1, value2, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeIsNull() {
            addCriterion("projectLongitude is null");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeIsNotNull() {
            addCriterion("projectLongitude is not null");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeEqualTo(Double value) {
            addCriterion("projectLongitude =", value, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeNotEqualTo(Double value) {
            addCriterion("projectLongitude <>", value, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeGreaterThan(Double value) {
            addCriterion("projectLongitude >", value, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("projectLongitude >=", value, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeLessThan(Double value) {
            addCriterion("projectLongitude <", value, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("projectLongitude <=", value, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeIn(List<Double> values) {
            addCriterion("projectLongitude in", values, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeNotIn(List<Double> values) {
            addCriterion("projectLongitude not in", values, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeBetween(Double value1, Double value2) {
            addCriterion("projectLongitude between", value1, value2, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("projectLongitude not between", value1, value2, "projectLongitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeIsNull() {
            addCriterion("projectLatitude is null");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeIsNotNull() {
            addCriterion("projectLatitude is not null");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeEqualTo(Double value) {
            addCriterion("projectLatitude =", value, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeNotEqualTo(Double value) {
            addCriterion("projectLatitude <>", value, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeGreaterThan(Double value) {
            addCriterion("projectLatitude >", value, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("projectLatitude >=", value, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeLessThan(Double value) {
            addCriterion("projectLatitude <", value, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("projectLatitude <=", value, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeIn(List<Double> values) {
            addCriterion("projectLatitude in", values, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeNotIn(List<Double> values) {
            addCriterion("projectLatitude not in", values, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeBetween(Double value1, Double value2) {
            addCriterion("projectLatitude between", value1, value2, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("projectLatitude not between", value1, value2, "projectLatitude");
            return (Criteria) this;
        }

        public Criteria andProjectDateIsNull() {
            addCriterion("projectDate is null");
            return (Criteria) this;
        }

        public Criteria andProjectDateIsNotNull() {
            addCriterion("projectDate is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDateEqualTo(Date value) {
            addCriterion("projectDate =", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateNotEqualTo(Date value) {
            addCriterion("projectDate <>", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateGreaterThan(Date value) {
            addCriterion("projectDate >", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateGreaterThanOrEqualTo(Date value) {
            addCriterion("projectDate >=", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateLessThan(Date value) {
            addCriterion("projectDate <", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateLessThanOrEqualTo(Date value) {
            addCriterion("projectDate <=", value, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateIn(List<Date> values) {
            addCriterion("projectDate in", values, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateNotIn(List<Date> values) {
            addCriterion("projectDate not in", values, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateBetween(Date value1, Date value2) {
            addCriterion("projectDate between", value1, value2, "projectDate");
            return (Criteria) this;
        }

        public Criteria andProjectDateNotBetween(Date value1, Date value2) {
            addCriterion("projectDate not between", value1, value2, "projectDate");
            return (Criteria) this;
        }
        
        
        public Criteria andProjectEndDateIsNull() {
            addCriterion("projectEndDate is null");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateIsNotNull() {
            addCriterion("projectEndDate is not null");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateEqualTo(Date value) {
            addCriterion("projectEndDate =", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateNotEqualTo(Date value) {
            addCriterion("projectEndDate <>", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateGreaterThan(Date value) {
            addCriterion("projectEndDate >", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("projectEndDate >=", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateLessThan(Date value) {
            addCriterion("projectEndDate <", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateLessThanOrEqualTo(Date value) {
            addCriterion("projectEndDate <=", value, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateIn(List<Date> values) {
            addCriterion("projectEndDate in", values, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateNotIn(List<Date> values) {
            addCriterion("projectEndDate not in", values, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateBetween(Date value1, Date value2) {
            addCriterion("projectEndDate between", value1, value2, "projectEndDate");
            return (Criteria) this;
        }

        public Criteria andProjectEndDateNotBetween(Date value1, Date value2) {
            addCriterion("projectEndDate not between", value1, value2, "projectEndDate");
            return (Criteria) this;
        }
        
        public Criteria andProjectStartDateIsNull() {
            addCriterion("projectStartDate is null");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateIsNotNull() {
            addCriterion("projectStartDate is not null");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateEqualTo(Date value) {
            addCriterion("projectStartDate =", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateNotEqualTo(Date value) {
            addCriterion("projectStartDate <>", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateGreaterThan(Date value) {
            addCriterion("projectStartDate >", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("projectStartDate >=", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateLessThan(Date value) {
            addCriterion("projectStartDate <", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateLessThanOrEqualTo(Date value) {
            addCriterion("projectStartDate <=", value, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateIn(List<Date> values) {
            addCriterion("projectStartDate in", values, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateNotIn(List<Date> values) {
            addCriterion("projectStartDate not in", values, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateBetween(Date value1, Date value2) {
            addCriterion("projectStartDate between", value1, value2, "projectStartDate");
            return (Criteria) this;
        }

        public Criteria andProjectStartDateNotBetween(Date value1, Date value2) {
            addCriterion("projectStartDate not between", value1, value2, "projectStartDate");
            return (Criteria) this;
        }
        
        
        

        public Criteria andProjectAreaIsNull() {
            addCriterion("projectArea is null");
            return (Criteria) this;
        }

        public Criteria andProjectAreaIsNotNull() {
            addCriterion("projectArea is not null");
            return (Criteria) this;
        }

        public Criteria andProjectAreaEqualTo(Double value) {
            addCriterion("projectArea =", value, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaNotEqualTo(Double value) {
            addCriterion("projectArea <>", value, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaGreaterThan(Double value) {
            addCriterion("projectArea >", value, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaGreaterThanOrEqualTo(Double value) {
            addCriterion("projectArea >=", value, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaLessThan(Double value) {
            addCriterion("projectArea <", value, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaLessThanOrEqualTo(Double value) {
            addCriterion("projectArea <=", value, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaIn(List<Double> values) {
            addCriterion("projectArea in", values, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaNotIn(List<Double> values) {
            addCriterion("projectArea not in", values, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaBetween(Double value1, Double value2) {
            addCriterion("projectArea between", value1, value2, "projectArea");
            return (Criteria) this;
        }

        public Criteria andProjectAreaNotBetween(Double value1, Double value2) {
            addCriterion("projectArea not between", value1, value2, "projectArea");
            return (Criteria) this;
        }

        public Criteria andDutyManIdIsNull() {
            addCriterion("dutyManId is null");
            return (Criteria) this;
        }

        public Criteria andDutyManIdIsNotNull() {
            addCriterion("dutyManId is not null");
            return (Criteria) this;
        }

        public Criteria andDutyManIdEqualTo(Integer value) {
            addCriterion("dutyManId =", value, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdNotEqualTo(Integer value) {
            addCriterion("dutyManId <>", value, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdGreaterThan(Integer value) {
            addCriterion("dutyManId >", value, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dutyManId >=", value, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdLessThan(Integer value) {
            addCriterion("dutyManId <", value, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdLessThanOrEqualTo(Integer value) {
            addCriterion("dutyManId <=", value, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdIn(List<Integer> values) {
            addCriterion("dutyManId in", values, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdNotIn(List<Integer> values) {
            addCriterion("dutyManId not in", values, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdBetween(Integer value1, Integer value2) {
            addCriterion("dutyManId between", value1, value2, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDutyManIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dutyManId not between", value1, value2, "dutyManId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdIsNull() {
            addCriterion("distributorId is null");
            return (Criteria) this;
        }

        public Criteria andDistributorIdIsNotNull() {
            addCriterion("distributorId is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorIdEqualTo(Integer value) {
            addCriterion("distributorId =", value, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdNotEqualTo(Integer value) {
            addCriterion("distributorId <>", value, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdGreaterThan(Integer value) {
            addCriterion("distributorId >", value, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("distributorId >=", value, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdLessThan(Integer value) {
            addCriterion("distributorId <", value, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdLessThanOrEqualTo(Integer value) {
            addCriterion("distributorId <=", value, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdIn(List<Integer> values) {
            addCriterion("distributorId in", values, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdNotIn(List<Integer> values) {
            addCriterion("distributorId not in", values, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdBetween(Integer value1, Integer value2) {
            addCriterion("distributorId between", value1, value2, "distributorId");
            return (Criteria) this;
        }

        public Criteria andDistributorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("distributorId not between", value1, value2, "distributorId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdIsNull() {
            addCriterion("safetyManId is null");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdIsNotNull() {
            addCriterion("safetyManId is not null");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdEqualTo(Integer value) {
            addCriterion("safetyManId =", value, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdNotEqualTo(Integer value) {
            addCriterion("safetyManId <>", value, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdGreaterThan(Integer value) {
            addCriterion("safetyManId >", value, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("safetyManId >=", value, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdLessThan(Integer value) {
            addCriterion("safetyManId <", value, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdLessThanOrEqualTo(Integer value) {
            addCriterion("safetyManId <=", value, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdIn(List<Integer> values) {
            addCriterion("safetyManId in", values, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdNotIn(List<Integer> values) {
            addCriterion("safetyManId not in", values, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdBetween(Integer value1, Integer value2) {
            addCriterion("safetyManId between", value1, value2, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andSafetyManIdNotBetween(Integer value1, Integer value2) {
            addCriterion("safetyManId not between", value1, value2, "safetyManId");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andProjectStateIsNull() {
            addCriterion("projectState is null");
            return (Criteria) this;
        }

        public Criteria andProjectStateIsNotNull() {
            addCriterion("projectState is not null");
            return (Criteria) this;
        }

        public Criteria andProjectStateEqualTo(Integer value) {
            addCriterion("projectState =", value, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateNotEqualTo(Integer value) {
            addCriterion("projectState <>", value, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateGreaterThan(Integer value) {
            addCriterion("projectState >", value, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("projectState >=", value, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateLessThan(Integer value) {
            addCriterion("projectState <", value, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateLessThanOrEqualTo(Integer value) {
            addCriterion("projectState <=", value, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateIn(List<Integer> values) {
            addCriterion("projectState in", values, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateNotIn(List<Integer> values) {
            addCriterion("projectState not in", values, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateBetween(Integer value1, Integer value2) {
            addCriterion("projectState between", value1, value2, "projectState");
            return (Criteria) this;
        }

        public Criteria andProjectStateNotBetween(Integer value1, Integer value2) {
            addCriterion("projectState not between", value1, value2, "projectState");
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