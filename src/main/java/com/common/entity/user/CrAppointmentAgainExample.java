package com.common.entity.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CrAppointmentAgainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public CrAppointmentAgainExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNull() {
            addCriterion("doctor_id is null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNotNull() {
            addCriterion("doctor_id is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdEqualTo(Long value) {
            addCriterion("doctor_id =", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotEqualTo(Long value) {
            addCriterion("doctor_id <>", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThan(Long value) {
            addCriterion("doctor_id >", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("doctor_id >=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThan(Long value) {
            addCriterion("doctor_id <", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThanOrEqualTo(Long value) {
            addCriterion("doctor_id <=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIn(List<Long> values) {
            addCriterion("doctor_id in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotIn(List<Long> values) {
            addCriterion("doctor_id not in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdBetween(Long value1, Long value2) {
            addCriterion("doctor_id between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotBetween(Long value1, Long value2) {
            addCriterion("doctor_id not between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNull() {
            addCriterion("house_id is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("house_id is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Long value) {
            addCriterion("house_id =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Long value) {
            addCriterion("house_id <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Long value) {
            addCriterion("house_id >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("house_id >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Long value) {
            addCriterion("house_id <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Long value) {
            addCriterion("house_id <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Long> values) {
            addCriterion("house_id in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Long> values) {
            addCriterion("house_id not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Long value1, Long value2) {
            addCriterion("house_id between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Long value1, Long value2) {
            addCriterion("house_id not between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdIsNull() {
            addCriterion("appointment_time_id is null");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdIsNotNull() {
            addCriterion("appointment_time_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdEqualTo(Long value) {
            addCriterion("appointment_time_id =", value, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdNotEqualTo(Long value) {
            addCriterion("appointment_time_id <>", value, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdGreaterThan(Long value) {
            addCriterion("appointment_time_id >", value, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("appointment_time_id >=", value, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdLessThan(Long value) {
            addCriterion("appointment_time_id <", value, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdLessThanOrEqualTo(Long value) {
            addCriterion("appointment_time_id <=", value, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdIn(List<Long> values) {
            addCriterion("appointment_time_id in", values, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdNotIn(List<Long> values) {
            addCriterion("appointment_time_id not in", values, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdBetween(Long value1, Long value2) {
            addCriterion("appointment_time_id between", value1, value2, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andAppointmentTimeIdNotBetween(Long value1, Long value2) {
            addCriterion("appointment_time_id not between", value1, value2, "appointmentTimeId");
            return (Criteria) this;
        }

        public Criteria andCauseIsNull() {
            addCriterion("cause is null");
            return (Criteria) this;
        }

        public Criteria andCauseIsNotNull() {
            addCriterion("cause is not null");
            return (Criteria) this;
        }

        public Criteria andCauseEqualTo(String value) {
            addCriterion("cause =", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotEqualTo(String value) {
            addCriterion("cause <>", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThan(String value) {
            addCriterion("cause >", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThanOrEqualTo(String value) {
            addCriterion("cause >=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThan(String value) {
            addCriterion("cause <", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThanOrEqualTo(String value) {
            addCriterion("cause <=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLike(String value) {
            addCriterion("cause like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotLike(String value) {
            addCriterion("cause not like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseIn(List<String> values) {
            addCriterion("cause in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotIn(List<String> values) {
            addCriterion("cause not in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseBetween(String value1, String value2) {
            addCriterion("cause between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotBetween(String value1, String value2) {
            addCriterion("cause not between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIsNull() {
            addCriterion("consult_price is null");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIsNotNull() {
            addCriterion("consult_price is not null");
            return (Criteria) this;
        }

        public Criteria andConsultPriceEqualTo(BigDecimal value) {
            addCriterion("consult_price =", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotEqualTo(BigDecimal value) {
            addCriterion("consult_price <>", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceGreaterThan(BigDecimal value) {
            addCriterion("consult_price >", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("consult_price >=", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceLessThan(BigDecimal value) {
            addCriterion("consult_price <", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("consult_price <=", value, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceIn(List<BigDecimal> values) {
            addCriterion("consult_price in", values, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotIn(List<BigDecimal> values) {
            addCriterion("consult_price not in", values, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("consult_price between", value1, value2, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andConsultPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("consult_price not between", value1, value2, "consultPrice");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNull() {
            addCriterion("express_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("express_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(Long value) {
            addCriterion("express_id =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(Long value) {
            addCriterion("express_id <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(Long value) {
            addCriterion("express_id >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(Long value) {
            addCriterion("express_id >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(Long value) {
            addCriterion("express_id <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(Long value) {
            addCriterion("express_id <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<Long> values) {
            addCriterion("express_id in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<Long> values) {
            addCriterion("express_id not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(Long value1, Long value2) {
            addCriterion("express_id between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(Long value1, Long value2) {
            addCriterion("express_id not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andSumPriceIsNull() {
            addCriterion("sum_price is null");
            return (Criteria) this;
        }

        public Criteria andSumPriceIsNotNull() {
            addCriterion("sum_price is not null");
            return (Criteria) this;
        }

        public Criteria andSumPriceEqualTo(BigDecimal value) {
            addCriterion("sum_price =", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotEqualTo(BigDecimal value) {
            addCriterion("sum_price <>", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceGreaterThan(BigDecimal value) {
            addCriterion("sum_price >", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_price >=", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceLessThan(BigDecimal value) {
            addCriterion("sum_price <", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_price <=", value, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceIn(List<BigDecimal> values) {
            addCriterion("sum_price in", values, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotIn(List<BigDecimal> values) {
            addCriterion("sum_price not in", values, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_price between", value1, value2, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andSumPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_price not between", value1, value2, "sumPrice");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andExStatusIsNull() {
            addCriterion("ex_status is null");
            return (Criteria) this;
        }

        public Criteria andExStatusIsNotNull() {
            addCriterion("ex_status is not null");
            return (Criteria) this;
        }

        public Criteria andExStatusEqualTo(Integer value) {
            addCriterion("ex_status =", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusNotEqualTo(Integer value) {
            addCriterion("ex_status <>", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusGreaterThan(Integer value) {
            addCriterion("ex_status >", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ex_status >=", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusLessThan(Integer value) {
            addCriterion("ex_status <", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusLessThanOrEqualTo(Integer value) {
            addCriterion("ex_status <=", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusIn(List<Integer> values) {
            addCriterion("ex_status in", values, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusNotIn(List<Integer> values) {
            addCriterion("ex_status not in", values, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusBetween(Integer value1, Integer value2) {
            addCriterion("ex_status between", value1, value2, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("ex_status not between", value1, value2, "exStatus");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdIsNull() {
            addCriterion("appointment_id is null");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdIsNotNull() {
            addCriterion("appointment_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdEqualTo(Long value) {
            addCriterion("appointment_id =", value, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdNotEqualTo(Long value) {
            addCriterion("appointment_id <>", value, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdGreaterThan(Long value) {
            addCriterion("appointment_id >", value, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("appointment_id >=", value, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdLessThan(Long value) {
            addCriterion("appointment_id <", value, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdLessThanOrEqualTo(Long value) {
            addCriterion("appointment_id <=", value, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdIn(List<Long> values) {
            addCriterion("appointment_id in", values, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdNotIn(List<Long> values) {
            addCriterion("appointment_id not in", values, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdBetween(Long value1, Long value2) {
            addCriterion("appointment_id between", value1, value2, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andAppointmentIdNotBetween(Long value1, Long value2) {
            addCriterion("appointment_id not between", value1, value2, "appointmentId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     */
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