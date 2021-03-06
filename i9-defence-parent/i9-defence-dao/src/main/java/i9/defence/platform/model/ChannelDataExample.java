package i9.defence.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChannelDataExample {
    protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ChannelDataExample() {
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

		public Criteria andChannelIsNull() {
			addCriterion("channel is null");
			return (Criteria) this;
		}

		public Criteria andChannelIsNotNull() {
			addCriterion("channel is not null");
			return (Criteria) this;
		}

		public Criteria andChannelEqualTo(Integer value) {
			addCriterion("channel =", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotEqualTo(Integer value) {
			addCriterion("channel <>", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelGreaterThan(Integer value) {
			addCriterion("channel >", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelGreaterThanOrEqualTo(Integer value) {
			addCriterion("channel >=", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelLessThan(Integer value) {
			addCriterion("channel <", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelLessThanOrEqualTo(Integer value) {
			addCriterion("channel <=", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelIn(List<Integer> values) {
			addCriterion("channel in", values, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotIn(List<Integer> values) {
			addCriterion("channel not in", values, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelBetween(Integer value1, Integer value2) {
			addCriterion("channel between", value1, value2, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotBetween(Integer value1, Integer value2) {
			addCriterion("channel not between", value1, value2, "channel");
			return (Criteria) this;
		}

		public Criteria andDateTimeIsNull() {
			addCriterion("dateTime is null");
			return (Criteria) this;
		}

		public Criteria andDateTimeIsNotNull() {
			addCriterion("dateTime is not null");
			return (Criteria) this;
		}

		public Criteria andDateTimeEqualTo(Date value) {
			addCriterion("dateTime =", value, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeNotEqualTo(Date value) {
			addCriterion("dateTime <>", value, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeGreaterThan(Date value) {
			addCriterion("dateTime >", value, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("dateTime >=", value, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeLessThan(Date value) {
			addCriterion("dateTime <", value, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeLessThanOrEqualTo(Date value) {
			addCriterion("dateTime <=", value, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeIn(List<Date> values) {
			addCriterion("dateTime in", values, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeNotIn(List<Date> values) {
			addCriterion("dateTime not in", values, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeBetween(Date value1, Date value2) {
			addCriterion("dateTime between", value1, value2, "dateTime");
			return (Criteria) this;
		}

		public Criteria andDateTimeNotBetween(Date value1, Date value2) {
			addCriterion("dateTime not between", value1, value2, "dateTime");
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

		public Criteria andValueIsNull() {
			addCriterion("value is null");
			return (Criteria) this;
		}

		public Criteria andValueIsNotNull() {
			addCriterion("value is not null");
			return (Criteria) this;
		}

		public Criteria andValueEqualTo(String value) {
			addCriterion("value =", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueNotEqualTo(String value) {
			addCriterion("value <>", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueGreaterThan(String value) {
			addCriterion("value >", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueGreaterThanOrEqualTo(String value) {
			addCriterion("value >=", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueLessThan(String value) {
			addCriterion("value <", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueLessThanOrEqualTo(String value) {
			addCriterion("value <=", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueLike(String value) {
			addCriterion("value like", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueNotLike(String value) {
			addCriterion("value not like", value, "value");
			return (Criteria) this;
		}

		public Criteria andValueIn(List<String> values) {
			addCriterion("value in", values, "value");
			return (Criteria) this;
		}

		public Criteria andValueNotIn(List<String> values) {
			addCriterion("value not in", values, "value");
			return (Criteria) this;
		}

		public Criteria andValueBetween(String value1, String value2) {
			addCriterion("value between", value1, value2, "value");
			return (Criteria) this;
		}

		public Criteria andValueNotBetween(String value1, String value2) {
			addCriterion("value not between", value1, value2, "value");
			return (Criteria) this;
		}

		public Criteria andSystemTypeIsNull() {
			addCriterion("systemType is null");
			return (Criteria) this;
		}

		public Criteria andSystemTypeIsNotNull() {
			addCriterion("systemType is not null");
			return (Criteria) this;
		}

		public Criteria andSystemTypeEqualTo(String value) {
			addCriterion("systemType =", value, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeNotEqualTo(String value) {
			addCriterion("systemType <>", value, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeGreaterThan(String value) {
			addCriterion("systemType >", value, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeGreaterThanOrEqualTo(String value) {
			addCriterion("systemType >=", value, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeLessThan(String value) {
			addCriterion("systemType <", value, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeLessThanOrEqualTo(String value) {
			addCriterion("systemType <=", value, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeLike(String value) {
			addCriterion("systemType like", value, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeNotLike(String value) {
			addCriterion("systemType not like", value, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeIn(List<String> values) {
			addCriterion("systemType in", values, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeNotIn(List<String> values) {
			addCriterion("systemType not in", values, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeBetween(String value1, String value2) {
			addCriterion("systemType between", value1, value2, "systemType");
			return (Criteria) this;
		}

		public Criteria andSystemTypeNotBetween(String value1, String value2) {
			addCriterion("systemType not between", value1, value2, "systemType");
			return (Criteria) this;
		}

		public Criteria andDealStatusIsNull() {
			addCriterion("dealStatus is null");
			return (Criteria) this;
		}

		public Criteria andDealStatusIsNotNull() {
			addCriterion("dealStatus is not null");
			return (Criteria) this;
		}

		public Criteria andDealStatusEqualTo(Integer value) {
			addCriterion("dealStatus =", value, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusNotEqualTo(Integer value) {
			addCriterion("dealStatus <>", value, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusGreaterThan(Integer value) {
			addCriterion("dealStatus >", value, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("dealStatus >=", value, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusLessThan(Integer value) {
			addCriterion("dealStatus <", value, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusLessThanOrEqualTo(Integer value) {
			addCriterion("dealStatus <=", value, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusIn(List<Integer> values) {
			addCriterion("dealStatus in", values, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusNotIn(List<Integer> values) {
			addCriterion("dealStatus not in", values, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusBetween(Integer value1, Integer value2) {
			addCriterion("dealStatus between", value1, value2, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("dealStatus not between", value1, value2, "dealStatus");
			return (Criteria) this;
		}

		public Criteria andDealDetailIsNull() {
			addCriterion("dealDetail is null");
			return (Criteria) this;
		}

		public Criteria andDealDetailIsNotNull() {
			addCriterion("dealDetail is not null");
			return (Criteria) this;
		}

		public Criteria andDealDetailEqualTo(String value) {
			addCriterion("dealDetail =", value, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailNotEqualTo(String value) {
			addCriterion("dealDetail <>", value, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailGreaterThan(String value) {
			addCriterion("dealDetail >", value, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailGreaterThanOrEqualTo(String value) {
			addCriterion("dealDetail >=", value, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailLessThan(String value) {
			addCriterion("dealDetail <", value, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailLessThanOrEqualTo(String value) {
			addCriterion("dealDetail <=", value, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailLike(String value) {
			addCriterion("dealDetail like", value, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailNotLike(String value) {
			addCriterion("dealDetail not like", value, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailIn(List<String> values) {
			addCriterion("dealDetail in", values, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailNotIn(List<String> values) {
			addCriterion("dealDetail not in", values, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailBetween(String value1, String value2) {
			addCriterion("dealDetail between", value1, value2, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDealDetailNotBetween(String value1, String value2) {
			addCriterion("dealDetail not between", value1, value2, "dealDetail");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressIsNull() {
			addCriterion("deviceAddress is null");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressIsNotNull() {
			addCriterion("deviceAddress is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressEqualTo(String value) {
			addCriterion("deviceAddress =", value, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressNotEqualTo(String value) {
			addCriterion("deviceAddress <>", value, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressGreaterThan(String value) {
			addCriterion("deviceAddress >", value, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressGreaterThanOrEqualTo(String value) {
			addCriterion("deviceAddress >=", value, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressLessThan(String value) {
			addCriterion("deviceAddress <", value, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressLessThanOrEqualTo(String value) {
			addCriterion("deviceAddress <=", value, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressLike(String value) {
			addCriterion("deviceAddress like", value, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressNotLike(String value) {
			addCriterion("deviceAddress not like", value, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressIn(List<String> values) {
			addCriterion("deviceAddress in", values, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressNotIn(List<String> values) {
			addCriterion("deviceAddress not in", values, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressBetween(String value1, String value2) {
			addCriterion("deviceAddress between", value1, value2, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceAddressNotBetween(String value1, String value2) {
			addCriterion("deviceAddress not between", value1, value2, "deviceAddress");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIsNull() {
			addCriterion("deviceId is null");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIsNotNull() {
			addCriterion("deviceId is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceIdEqualTo(String value) {
			addCriterion("deviceId =", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotEqualTo(String value) {
			addCriterion("deviceId <>", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdGreaterThan(String value) {
			addCriterion("deviceId >", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdGreaterThanOrEqualTo(String value) {
			addCriterion("deviceId >=", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLessThan(String value) {
			addCriterion("deviceId <", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLessThanOrEqualTo(String value) {
			addCriterion("deviceId <=", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLike(String value) {
			addCriterion("deviceId like", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotLike(String value) {
			addCriterion("deviceId not like", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIn(List<String> values) {
			addCriterion("deviceId in", values, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotIn(List<String> values) {
			addCriterion("deviceId not in", values, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdBetween(String value1, String value2) {
			addCriterion("deviceId between", value1, value2, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotBetween(String value1, String value2) {
			addCriterion("deviceId not between", value1, value2, "deviceId");
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