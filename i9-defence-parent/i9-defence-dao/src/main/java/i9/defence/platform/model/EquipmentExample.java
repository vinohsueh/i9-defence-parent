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

		public Criteria andEquipmentIdentifierEqualTo(String value) {
			addCriterion("equipmentIdentifier =", value, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierNotEqualTo(String value) {
			addCriterion("equipmentIdentifier <>", value, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierGreaterThan(String value) {
			addCriterion("equipmentIdentifier >", value, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierGreaterThanOrEqualTo(String value) {
			addCriterion("equipmentIdentifier >=", value, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierLessThan(String value) {
			addCriterion("equipmentIdentifier <", value, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierLessThanOrEqualTo(String value) {
			addCriterion("equipmentIdentifier <=", value, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierLike(String value) {
			addCriterion("equipmentIdentifier like", value, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierNotLike(String value) {
			addCriterion("equipmentIdentifier not like", value, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierIn(List<String> values) {
			addCriterion("equipmentIdentifier in", values, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierNotIn(List<String> values) {
			addCriterion("equipmentIdentifier not in", values, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierBetween(String value1, String value2) {
			addCriterion("equipmentIdentifier between", value1, value2, "equipmentIdentifier");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdentifierNotBetween(String value1, String value2) {
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
		public Criteria andProjectIdIsNull() {
			addCriterion("projectId is null");
			return (Criteria) this;
		}

		public Criteria andProjectIdIsNotNull() {
			addCriterion("projectId is not null");
			return (Criteria) this;
		}

		public Criteria andProjectIdEqualTo(Integer value) {
			addCriterion("projectId =", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdNotEqualTo(Integer value) {
			addCriterion("projectId <>", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdGreaterThan(Integer value) {
			addCriterion("projectId >", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("projectId >=", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdLessThan(Integer value) {
			addCriterion("projectId <", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
			addCriterion("projectId <=", value, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdIn(List<Integer> values) {
			addCriterion("projectId in", values, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdNotIn(List<Integer> values) {
			addCriterion("projectId not in", values, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdBetween(Integer value1, Integer value2) {
			addCriterion("projectId between", value1, value2, "projectId");
			return (Criteria) this;
		}

		public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
			addCriterion("projectId not between", value1, value2, "projectId");
			return (Criteria) this;
		}
		
		public Criteria andUnitIsNull() {
			addCriterion("unit is null");
			return (Criteria) this;
		}

		public Criteria andUnitIsNotNull() {
			addCriterion("unit is not null");
			return (Criteria) this;
		}

		public Criteria andUnitEqualTo(Integer value) {
			addCriterion("unit =", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitNotEqualTo(Integer value) {
			addCriterion("unit <>", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitGreaterThan(Integer value) {
			addCriterion("unit >", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitGreaterThanOrEqualTo(Integer value) {
			addCriterion("unit >=", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitLessThan(Integer value) {
			addCriterion("unit <", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitLessThanOrEqualTo(Integer value) {
			addCriterion("unit <=", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitIn(List<Integer> values) {
			addCriterion("unit in", values, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitNotIn(List<Integer> values) {
			addCriterion("unit not in", values, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitBetween(Integer value1, Integer value2) {
			addCriterion("unit between", value1, value2, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitNotBetween(Integer value1, Integer value2) {
			addCriterion("unit not between", value1, value2, "unit");
			return (Criteria) this;
		}
		public Criteria andLooplIsNull() {
			addCriterion("loopl is null");
			return (Criteria) this;
		}

		public Criteria andLooplIsNotNull() {
			addCriterion("loopl is not null");
			return (Criteria) this;
		}

		public Criteria andLooplEqualTo(Integer value) {
			addCriterion("loopl =", value, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplNotEqualTo(Integer value) {
			addCriterion("loopl <>", value, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplGreaterThan(Integer value) {
			addCriterion("loopl >", value, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplGreaterThanOrEqualTo(Integer value) {
			addCriterion("loopl >=", value, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplLessThan(Integer value) {
			addCriterion("loopl <", value, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplLessThanOrEqualTo(Integer value) {
			addCriterion("loopl <=", value, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplIn(List<Integer> values) {
			addCriterion("loopl in", values, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplNotIn(List<Integer> values) {
			addCriterion("loopl not in", values, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplBetween(Integer value1, Integer value2) {
			addCriterion("loopl between", value1, value2, "loopl");
			return (Criteria) this;
		}

		public Criteria andLooplNotBetween(Integer value1, Integer value2) {
			addCriterion("loopl not between", value1, value2, "loopl");
			return (Criteria) this;
		}
	
		
		public Criteria andSystemIdIsNull() {
			addCriterion("systemId is null");
			return (Criteria) this;
		}

		public Criteria andSystemIdIsNotNull() {
			addCriterion("systemId is not null");
			return (Criteria) this;
		}

		public Criteria andSystemIdEqualTo(String value) {
			addCriterion("systemId =", value, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdNotEqualTo(String value) {
			addCriterion("systemId <>", value, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdGreaterThan(String value) {
			addCriterion("systemId >", value, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdGreaterThanOrEqualTo(String value) {
			addCriterion("systemId >=", value, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdLessThan(String value) {
			addCriterion("systemId <", value, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdLessThanOrEqualTo(String value) {
			addCriterion("systemId <=", value, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdLike(String value) {
			addCriterion("systemId like", value, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdNotLike(String value) {
			addCriterion("systemId not like", value, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdIn(List<String> values) {
			addCriterion("systemId in", values, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdNotIn(List<String> values) {
			addCriterion("systemId not in", values, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdBetween(String value1, String value2) {
			addCriterion("systemId between", value1, value2, "systemId");
			return (Criteria) this;
		}

		public Criteria andSystemIdNotBetween(String value1, String value2) {
			addCriterion("systemId not between", value1, value2, "systemId");
			return (Criteria) this;
		}
		public Criteria andDelStatusIsNull() {
			addCriterion("delStatus is null");
			return (Criteria) this;
		}

		public Criteria andDelStatusIsNotNull() {
			addCriterion("delStatus is not null");
			return (Criteria) this;
		}

		public Criteria andDelStatusEqualTo(Integer value) {
			addCriterion("delStatus =", value, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusNotEqualTo(Integer value) {
			addCriterion("delStatus <>", value, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusGreaterThan(Integer value) {
			addCriterion("delStatus >", value, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("delStatus >=", value, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusLessThan(Integer value) {
			addCriterion("delStatus <", value, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusLessThanOrEqualTo(Integer value) {
			addCriterion("delStatus <=", value, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusIn(List<Integer> values) {
			addCriterion("delStatus in", values, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusNotIn(List<Integer> values) {
			addCriterion("delStatus not in", values, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusBetween(Integer value1, Integer value2) {
			addCriterion("delStatus between", value1, value2, "delStatus");
			return (Criteria) this;
		}

		public Criteria andDelStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("delStatus not between", value1, value2, "delStatus");
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