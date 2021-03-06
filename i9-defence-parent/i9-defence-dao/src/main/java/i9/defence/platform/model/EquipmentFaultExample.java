package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.List;

public class EquipmentFaultExample {
    protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public EquipmentFaultExample() {
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

		public Criteria andCodeIsNull() {
			addCriterion("code is null");
			return (Criteria) this;
		}

		public Criteria andCodeIsNotNull() {
			addCriterion("code is not null");
			return (Criteria) this;
		}

		public Criteria andCodeEqualTo(String value) {
			addCriterion("code =", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotEqualTo(String value) {
			addCriterion("code <>", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThan(String value) {
			addCriterion("code >", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThanOrEqualTo(String value) {
			addCriterion("code >=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThan(String value) {
			addCriterion("code <", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThanOrEqualTo(String value) {
			addCriterion("code <=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLike(String value) {
			addCriterion("code like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotLike(String value) {
			addCriterion("code not like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeIn(List<String> values) {
			addCriterion("code in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotIn(List<String> values) {
			addCriterion("code not in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeBetween(String value1, String value2) {
			addCriterion("code between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotBetween(String value1, String value2) {
			addCriterion("code not between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdIsNull() {
			addCriterion("equipmentId is null");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdIsNotNull() {
			addCriterion("equipmentId is not null");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdEqualTo(Integer value) {
			addCriterion("equipmentId =", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdNotEqualTo(Integer value) {
			addCriterion("equipmentId <>", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdGreaterThan(Integer value) {
			addCriterion("equipmentId >", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("equipmentId >=", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdLessThan(Integer value) {
			addCriterion("equipmentId <", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdLessThanOrEqualTo(Integer value) {
			addCriterion("equipmentId <=", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdIn(List<Integer> values) {
			addCriterion("equipmentId in", values, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdNotIn(List<Integer> values) {
			addCriterion("equipmentId not in", values, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdBetween(Integer value1, Integer value2) {
			addCriterion("equipmentId between", value1, value2, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("equipmentId not between", value1, value2, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}
		
		public Criteria andJhTypeIsNull() {
			addCriterion("jhType is null");
			return (Criteria) this;
		}

		public Criteria andJhTypeIsNotNull() {
			addCriterion("jhType is not null");
			return (Criteria) this;
		}

		public Criteria andJhTypeEqualTo(Integer value) {
			addCriterion("jhType =", value, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeNotEqualTo(Integer value) {
			addCriterion("jhType <>", value, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeGreaterThan(Integer value) {
			addCriterion("jhType >", value, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("jhType >=", value, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeLessThan(Integer value) {
			addCriterion("jhType <", value, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeLessThanOrEqualTo(Integer value) {
			addCriterion("jhType <=", value, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeIn(List<Integer> values) {
			addCriterion("jhType in", values, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeNotIn(List<Integer> values) {
			addCriterion("jhType not in", values, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeBetween(Integer value1, Integer value2) {
			addCriterion("jhType between", value1, value2, "jhType");
			return (Criteria) this;
		}

		public Criteria andJhTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("jhType not between", value1, value2, "jhType");
			return (Criteria) this;
		}
		
		public Criteria andActivationOneIsNull() {
			addCriterion("activationOne is null");
			return (Criteria) this;
		}

		public Criteria andActivationOneIsNotNull() {
			addCriterion("activationOne is not null");
			return (Criteria) this;
		}

		public Criteria andActivationOneEqualTo(String value) {
			addCriterion("activationOne =", value, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneNotEqualTo(String value) {
			addCriterion("activationOne <>", value, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneGreaterThan(String value) {
			addCriterion("activationOne >", value, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneGreaterThanOrEqualTo(String value) {
			addCriterion("activationOne >=", value, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneLessThan(String value) {
			addCriterion("activationOne <", value, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneLessThanOrEqualTo(String value) {
			addCriterion("activationOne <=", value, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneIn(List<String> values) {
			addCriterion("activationOne in", values, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneNotIn(List<String> values) {
			addCriterion("activationOne not in", values, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneBetween(String value1, String value2) {
			addCriterion("activationOne between", value1, value2, "activationOne");
			return (Criteria) this;
		}

		public Criteria andActivationOneNotBetween(String value1, String value2) {
			addCriterion("activationOne not between", value1, value2, "activationOne");
			return (Criteria) this;
		}
		
		public Criteria andActivationTwoIsNull() {
			addCriterion("activationTwo is null");
			return (Criteria) this;
		}

		public Criteria andActivationTwoIsNotNull() {
			addCriterion("activationTwo is not null");
			return (Criteria) this;
		}

		public Criteria andActivationTwoEqualTo(String value) {
			addCriterion("activationTwo =", value, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoNotEqualTo(String value) {
			addCriterion("activationTwo <>", value, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoGreaterThan(String value) {
			addCriterion("activationTwo >", value, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoGreaterThanOrEqualTo(String value) {
			addCriterion("activationTwo >=", value, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoLessThan(String value) {
			addCriterion("activationTwo <", value, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoLessThanOrEqualTo(String value) {
			addCriterion("activationTwo <=", value, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoIn(List<String> values) {
			addCriterion("activationTwo in", values, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoNotIn(List<String> values) {
			addCriterion("activationTwo not in", values, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoBetween(String value1, String value2) {
			addCriterion("activationTwo between", value1, value2, "activationTwo");
			return (Criteria) this;
		}

		public Criteria andActivationTwoNotBetween(String value1, String value2) {
			addCriterion("activationTwo not between", value1, value2, "activationTwo");
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