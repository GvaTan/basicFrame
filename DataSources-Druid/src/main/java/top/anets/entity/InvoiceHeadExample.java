package top.anets.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceHeadExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceHeadExample() {
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

        public Criteria andDocNumIsNull() {
            addCriterion("DOC_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDocNumIsNotNull() {
            addCriterion("DOC_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDocNumEqualTo(String value) {
            addCriterion("DOC_NUM =", value, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumNotEqualTo(String value) {
            addCriterion("DOC_NUM <>", value, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumGreaterThan(String value) {
            addCriterion("DOC_NUM >", value, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_NUM >=", value, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumLessThan(String value) {
            addCriterion("DOC_NUM <", value, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumLessThanOrEqualTo(String value) {
            addCriterion("DOC_NUM <=", value, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumLike(String value) {
            addCriterion("DOC_NUM like", value, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumNotLike(String value) {
            addCriterion("DOC_NUM not like", value, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumIn(List<String> values) {
            addCriterion("DOC_NUM in", values, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumNotIn(List<String> values) {
            addCriterion("DOC_NUM not in", values, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumBetween(String value1, String value2) {
            addCriterion("DOC_NUM between", value1, value2, "docNum");
            return (Criteria) this;
        }

        public Criteria andDocNumNotBetween(String value1, String value2) {
            addCriterion("DOC_NUM not between", value1, value2, "docNum");
            return (Criteria) this;
        }

        public Criteria andMandtIsNull() {
            addCriterion("MANDT is null");
            return (Criteria) this;
        }

        public Criteria andMandtIsNotNull() {
            addCriterion("MANDT is not null");
            return (Criteria) this;
        }

        public Criteria andMandtEqualTo(String value) {
            addCriterion("MANDT =", value, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtNotEqualTo(String value) {
            addCriterion("MANDT <>", value, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtGreaterThan(String value) {
            addCriterion("MANDT >", value, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtGreaterThanOrEqualTo(String value) {
            addCriterion("MANDT >=", value, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtLessThan(String value) {
            addCriterion("MANDT <", value, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtLessThanOrEqualTo(String value) {
            addCriterion("MANDT <=", value, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtLike(String value) {
            addCriterion("MANDT like", value, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtNotLike(String value) {
            addCriterion("MANDT not like", value, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtIn(List<String> values) {
            addCriterion("MANDT in", values, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtNotIn(List<String> values) {
            addCriterion("MANDT not in", values, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtBetween(String value1, String value2) {
            addCriterion("MANDT between", value1, value2, "mandt");
            return (Criteria) this;
        }

        public Criteria andMandtNotBetween(String value1, String value2) {
            addCriterion("MANDT not between", value1, value2, "mandt");
            return (Criteria) this;
        }

        public Criteria andDocDateIsNull() {
            addCriterion("DOC_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDocDateIsNotNull() {
            addCriterion("DOC_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDocDateEqualTo(String value) {
            addCriterion("DOC_DATE =", value, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateNotEqualTo(String value) {
            addCriterion("DOC_DATE <>", value, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateGreaterThan(String value) {
            addCriterion("DOC_DATE >", value, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_DATE >=", value, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateLessThan(String value) {
            addCriterion("DOC_DATE <", value, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateLessThanOrEqualTo(String value) {
            addCriterion("DOC_DATE <=", value, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateLike(String value) {
            addCriterion("DOC_DATE like", value, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateNotLike(String value) {
            addCriterion("DOC_DATE not like", value, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateIn(List<String> values) {
            addCriterion("DOC_DATE in", values, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateNotIn(List<String> values) {
            addCriterion("DOC_DATE not in", values, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateBetween(String value1, String value2) {
            addCriterion("DOC_DATE between", value1, value2, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocDateNotBetween(String value1, String value2) {
            addCriterion("DOC_DATE not between", value1, value2, "docDate");
            return (Criteria) this;
        }

        public Criteria andDocStatusIsNull() {
            addCriterion("DOC_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDocStatusIsNotNull() {
            addCriterion("DOC_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDocStatusEqualTo(String value) {
            addCriterion("DOC_STATUS =", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusNotEqualTo(String value) {
            addCriterion("DOC_STATUS <>", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusGreaterThan(String value) {
            addCriterion("DOC_STATUS >", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_STATUS >=", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusLessThan(String value) {
            addCriterion("DOC_STATUS <", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusLessThanOrEqualTo(String value) {
            addCriterion("DOC_STATUS <=", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusLike(String value) {
            addCriterion("DOC_STATUS like", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusNotLike(String value) {
            addCriterion("DOC_STATUS not like", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusIn(List<String> values) {
            addCriterion("DOC_STATUS in", values, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusNotIn(List<String> values) {
            addCriterion("DOC_STATUS not in", values, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusBetween(String value1, String value2) {
            addCriterion("DOC_STATUS between", value1, value2, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusNotBetween(String value1, String value2) {
            addCriterion("DOC_STATUS not between", value1, value2, "docStatus");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeIsNull() {
            addCriterion("ORG_TAXCODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeIsNotNull() {
            addCriterion("ORG_TAXCODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeEqualTo(String value) {
            addCriterion("ORG_TAXCODE =", value, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeNotEqualTo(String value) {
            addCriterion("ORG_TAXCODE <>", value, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeGreaterThan(String value) {
            addCriterion("ORG_TAXCODE >", value, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TAXCODE >=", value, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeLessThan(String value) {
            addCriterion("ORG_TAXCODE <", value, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_TAXCODE <=", value, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeLike(String value) {
            addCriterion("ORG_TAXCODE like", value, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeNotLike(String value) {
            addCriterion("ORG_TAXCODE not like", value, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeIn(List<String> values) {
            addCriterion("ORG_TAXCODE in", values, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeNotIn(List<String> values) {
            addCriterion("ORG_TAXCODE not in", values, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeBetween(String value1, String value2) {
            addCriterion("ORG_TAXCODE between", value1, value2, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgTaxcodeNotBetween(String value1, String value2) {
            addCriterion("ORG_TAXCODE not between", value1, value2, "orgTaxcode");
            return (Criteria) this;
        }

        public Criteria andOrgMachineIsNull() {
            addCriterion("ORG_MACHINE is null");
            return (Criteria) this;
        }

        public Criteria andOrgMachineIsNotNull() {
            addCriterion("ORG_MACHINE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgMachineEqualTo(String value) {
            addCriterion("ORG_MACHINE =", value, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineNotEqualTo(String value) {
            addCriterion("ORG_MACHINE <>", value, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineGreaterThan(String value) {
            addCriterion("ORG_MACHINE >", value, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_MACHINE >=", value, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineLessThan(String value) {
            addCriterion("ORG_MACHINE <", value, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineLessThanOrEqualTo(String value) {
            addCriterion("ORG_MACHINE <=", value, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineLike(String value) {
            addCriterion("ORG_MACHINE like", value, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineNotLike(String value) {
            addCriterion("ORG_MACHINE not like", value, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineIn(List<String> values) {
            addCriterion("ORG_MACHINE in", values, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineNotIn(List<String> values) {
            addCriterion("ORG_MACHINE not in", values, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineBetween(String value1, String value2) {
            addCriterion("ORG_MACHINE between", value1, value2, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgMachineNotBetween(String value1, String value2) {
            addCriterion("ORG_MACHINE not between", value1, value2, "orgMachine");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNull() {
            addCriterion("ORG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNotNull() {
            addCriterion("ORG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressEqualTo(String value) {
            addCriterion("ORG_ADDRESS =", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotEqualTo(String value) {
            addCriterion("ORG_ADDRESS <>", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThan(String value) {
            addCriterion("ORG_ADDRESS >", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS >=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThan(String value) {
            addCriterion("ORG_ADDRESS <", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS <=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLike(String value) {
            addCriterion("ORG_ADDRESS like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotLike(String value) {
            addCriterion("ORG_ADDRESS not like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIn(List<String> values) {
            addCriterion("ORG_ADDRESS in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotIn(List<String> values) {
            addCriterion("ORG_ADDRESS not in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS not between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneIsNull() {
            addCriterion("ORG_TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneIsNotNull() {
            addCriterion("ORG_TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneEqualTo(String value) {
            addCriterion("ORG_TELEPHONE =", value, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneNotEqualTo(String value) {
            addCriterion("ORG_TELEPHONE <>", value, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneGreaterThan(String value) {
            addCriterion("ORG_TELEPHONE >", value, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TELEPHONE >=", value, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneLessThan(String value) {
            addCriterion("ORG_TELEPHONE <", value, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneLessThanOrEqualTo(String value) {
            addCriterion("ORG_TELEPHONE <=", value, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneLike(String value) {
            addCriterion("ORG_TELEPHONE like", value, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneNotLike(String value) {
            addCriterion("ORG_TELEPHONE not like", value, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneIn(List<String> values) {
            addCriterion("ORG_TELEPHONE in", values, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneNotIn(List<String> values) {
            addCriterion("ORG_TELEPHONE not in", values, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneBetween(String value1, String value2) {
            addCriterion("ORG_TELEPHONE between", value1, value2, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgTelephoneNotBetween(String value1, String value2) {
            addCriterion("ORG_TELEPHONE not between", value1, value2, "orgTelephone");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameIsNull() {
            addCriterion("ORG_BANKNAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameIsNotNull() {
            addCriterion("ORG_BANKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameEqualTo(String value) {
            addCriterion("ORG_BANKNAME =", value, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameNotEqualTo(String value) {
            addCriterion("ORG_BANKNAME <>", value, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameGreaterThan(String value) {
            addCriterion("ORG_BANKNAME >", value, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_BANKNAME >=", value, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameLessThan(String value) {
            addCriterion("ORG_BANKNAME <", value, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameLessThanOrEqualTo(String value) {
            addCriterion("ORG_BANKNAME <=", value, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameLike(String value) {
            addCriterion("ORG_BANKNAME like", value, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameNotLike(String value) {
            addCriterion("ORG_BANKNAME not like", value, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameIn(List<String> values) {
            addCriterion("ORG_BANKNAME in", values, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameNotIn(List<String> values) {
            addCriterion("ORG_BANKNAME not in", values, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameBetween(String value1, String value2) {
            addCriterion("ORG_BANKNAME between", value1, value2, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBanknameNotBetween(String value1, String value2) {
            addCriterion("ORG_BANKNAME not between", value1, value2, "orgBankname");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountIsNull() {
            addCriterion("ORG_BANKACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountIsNotNull() {
            addCriterion("ORG_BANKACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountEqualTo(String value) {
            addCriterion("ORG_BANKACCOUNT =", value, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountNotEqualTo(String value) {
            addCriterion("ORG_BANKACCOUNT <>", value, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountGreaterThan(String value) {
            addCriterion("ORG_BANKACCOUNT >", value, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_BANKACCOUNT >=", value, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountLessThan(String value) {
            addCriterion("ORG_BANKACCOUNT <", value, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountLessThanOrEqualTo(String value) {
            addCriterion("ORG_BANKACCOUNT <=", value, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountLike(String value) {
            addCriterion("ORG_BANKACCOUNT like", value, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountNotLike(String value) {
            addCriterion("ORG_BANKACCOUNT not like", value, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountIn(List<String> values) {
            addCriterion("ORG_BANKACCOUNT in", values, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountNotIn(List<String> values) {
            addCriterion("ORG_BANKACCOUNT not in", values, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountBetween(String value1, String value2) {
            addCriterion("ORG_BANKACCOUNT between", value1, value2, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgBankaccountNotBetween(String value1, String value2) {
            addCriterion("ORG_BANKACCOUNT not between", value1, value2, "orgBankaccount");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedIsNull() {
            addCriterion("ORG_TAXEXCEED is null");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedIsNotNull() {
            addCriterion("ORG_TAXEXCEED is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedEqualTo(BigDecimal value) {
            addCriterion("ORG_TAXEXCEED =", value, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedNotEqualTo(BigDecimal value) {
            addCriterion("ORG_TAXEXCEED <>", value, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedGreaterThan(BigDecimal value) {
            addCriterion("ORG_TAXEXCEED >", value, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_TAXEXCEED >=", value, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedLessThan(BigDecimal value) {
            addCriterion("ORG_TAXEXCEED <", value, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORG_TAXEXCEED <=", value, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedIn(List<BigDecimal> values) {
            addCriterion("ORG_TAXEXCEED in", values, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedNotIn(List<BigDecimal> values) {
            addCriterion("ORG_TAXEXCEED not in", values, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_TAXEXCEED between", value1, value2, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgTaxexceedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORG_TAXEXCEED not between", value1, value2, "orgTaxexceed");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxIsNull() {
            addCriterion("ORG_CONTROLTAX is null");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxIsNotNull() {
            addCriterion("ORG_CONTROLTAX is not null");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxEqualTo(String value) {
            addCriterion("ORG_CONTROLTAX =", value, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxNotEqualTo(String value) {
            addCriterion("ORG_CONTROLTAX <>", value, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxGreaterThan(String value) {
            addCriterion("ORG_CONTROLTAX >", value, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CONTROLTAX >=", value, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxLessThan(String value) {
            addCriterion("ORG_CONTROLTAX <", value, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxLessThanOrEqualTo(String value) {
            addCriterion("ORG_CONTROLTAX <=", value, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxLike(String value) {
            addCriterion("ORG_CONTROLTAX like", value, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxNotLike(String value) {
            addCriterion("ORG_CONTROLTAX not like", value, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxIn(List<String> values) {
            addCriterion("ORG_CONTROLTAX in", values, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxNotIn(List<String> values) {
            addCriterion("ORG_CONTROLTAX not in", values, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxBetween(String value1, String value2) {
            addCriterion("ORG_CONTROLTAX between", value1, value2, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andOrgControltaxNotBetween(String value1, String value2) {
            addCriterion("ORG_CONTROLTAX not between", value1, value2, "orgControltax");
            return (Criteria) this;
        }

        public Criteria andCustIdArIsNull() {
            addCriterion("CUST_ID_AR is null");
            return (Criteria) this;
        }

        public Criteria andCustIdArIsNotNull() {
            addCriterion("CUST_ID_AR is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdArEqualTo(String value) {
            addCriterion("CUST_ID_AR =", value, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArNotEqualTo(String value) {
            addCriterion("CUST_ID_AR <>", value, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArGreaterThan(String value) {
            addCriterion("CUST_ID_AR >", value, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ID_AR >=", value, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArLessThan(String value) {
            addCriterion("CUST_ID_AR <", value, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArLessThanOrEqualTo(String value) {
            addCriterion("CUST_ID_AR <=", value, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArLike(String value) {
            addCriterion("CUST_ID_AR like", value, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArNotLike(String value) {
            addCriterion("CUST_ID_AR not like", value, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArIn(List<String> values) {
            addCriterion("CUST_ID_AR in", values, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArNotIn(List<String> values) {
            addCriterion("CUST_ID_AR not in", values, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArBetween(String value1, String value2) {
            addCriterion("CUST_ID_AR between", value1, value2, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustIdArNotBetween(String value1, String value2) {
            addCriterion("CUST_ID_AR not between", value1, value2, "custIdAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArIsNull() {
            addCriterion("CUST_NAME_AR is null");
            return (Criteria) this;
        }

        public Criteria andCustNameArIsNotNull() {
            addCriterion("CUST_NAME_AR is not null");
            return (Criteria) this;
        }

        public Criteria andCustNameArEqualTo(String value) {
            addCriterion("CUST_NAME_AR =", value, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArNotEqualTo(String value) {
            addCriterion("CUST_NAME_AR <>", value, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArGreaterThan(String value) {
            addCriterion("CUST_NAME_AR >", value, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_NAME_AR >=", value, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArLessThan(String value) {
            addCriterion("CUST_NAME_AR <", value, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArLessThanOrEqualTo(String value) {
            addCriterion("CUST_NAME_AR <=", value, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArLike(String value) {
            addCriterion("CUST_NAME_AR like", value, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArNotLike(String value) {
            addCriterion("CUST_NAME_AR not like", value, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArIn(List<String> values) {
            addCriterion("CUST_NAME_AR in", values, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArNotIn(List<String> values) {
            addCriterion("CUST_NAME_AR not in", values, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArBetween(String value1, String value2) {
            addCriterion("CUST_NAME_AR between", value1, value2, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustNameArNotBetween(String value1, String value2) {
            addCriterion("CUST_NAME_AR not between", value1, value2, "custNameAr");
            return (Criteria) this;
        }

        public Criteria andCustIdBillIsNull() {
            addCriterion("CUST_ID_BILL is null");
            return (Criteria) this;
        }

        public Criteria andCustIdBillIsNotNull() {
            addCriterion("CUST_ID_BILL is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdBillEqualTo(String value) {
            addCriterion("CUST_ID_BILL =", value, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillNotEqualTo(String value) {
            addCriterion("CUST_ID_BILL <>", value, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillGreaterThan(String value) {
            addCriterion("CUST_ID_BILL >", value, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ID_BILL >=", value, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillLessThan(String value) {
            addCriterion("CUST_ID_BILL <", value, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillLessThanOrEqualTo(String value) {
            addCriterion("CUST_ID_BILL <=", value, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillLike(String value) {
            addCriterion("CUST_ID_BILL like", value, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillNotLike(String value) {
            addCriterion("CUST_ID_BILL not like", value, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillIn(List<String> values) {
            addCriterion("CUST_ID_BILL in", values, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillNotIn(List<String> values) {
            addCriterion("CUST_ID_BILL not in", values, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillBetween(String value1, String value2) {
            addCriterion("CUST_ID_BILL between", value1, value2, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustIdBillNotBetween(String value1, String value2) {
            addCriterion("CUST_ID_BILL not between", value1, value2, "custIdBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillIsNull() {
            addCriterion("CUST_NAME_BILL is null");
            return (Criteria) this;
        }

        public Criteria andCustNameBillIsNotNull() {
            addCriterion("CUST_NAME_BILL is not null");
            return (Criteria) this;
        }

        public Criteria andCustNameBillEqualTo(String value) {
            addCriterion("CUST_NAME_BILL =", value, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillNotEqualTo(String value) {
            addCriterion("CUST_NAME_BILL <>", value, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillGreaterThan(String value) {
            addCriterion("CUST_NAME_BILL >", value, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_NAME_BILL >=", value, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillLessThan(String value) {
            addCriterion("CUST_NAME_BILL <", value, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillLessThanOrEqualTo(String value) {
            addCriterion("CUST_NAME_BILL <=", value, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillLike(String value) {
            addCriterion("CUST_NAME_BILL like", value, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillNotLike(String value) {
            addCriterion("CUST_NAME_BILL not like", value, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillIn(List<String> values) {
            addCriterion("CUST_NAME_BILL in", values, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillNotIn(List<String> values) {
            addCriterion("CUST_NAME_BILL not in", values, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillBetween(String value1, String value2) {
            addCriterion("CUST_NAME_BILL between", value1, value2, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameBillNotBetween(String value1, String value2) {
            addCriterion("CUST_NAME_BILL not between", value1, value2, "custNameBill");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNull() {
            addCriterion("CUST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNotNull() {
            addCriterion("CUST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustNameEqualTo(String value) {
            addCriterion("CUST_NAME =", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotEqualTo(String value) {
            addCriterion("CUST_NAME <>", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThan(String value) {
            addCriterion("CUST_NAME >", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_NAME >=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThan(String value) {
            addCriterion("CUST_NAME <", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThanOrEqualTo(String value) {
            addCriterion("CUST_NAME <=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLike(String value) {
            addCriterion("CUST_NAME like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotLike(String value) {
            addCriterion("CUST_NAME not like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameIn(List<String> values) {
            addCriterion("CUST_NAME in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotIn(List<String> values) {
            addCriterion("CUST_NAME not in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameBetween(String value1, String value2) {
            addCriterion("CUST_NAME between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotBetween(String value1, String value2) {
            addCriterion("CUST_NAME not between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeIsNull() {
            addCriterion("CUST_TAXCODE is null");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeIsNotNull() {
            addCriterion("CUST_TAXCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeEqualTo(String value) {
            addCriterion("CUST_TAXCODE =", value, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeNotEqualTo(String value) {
            addCriterion("CUST_TAXCODE <>", value, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeGreaterThan(String value) {
            addCriterion("CUST_TAXCODE >", value, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_TAXCODE >=", value, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeLessThan(String value) {
            addCriterion("CUST_TAXCODE <", value, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeLessThanOrEqualTo(String value) {
            addCriterion("CUST_TAXCODE <=", value, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeLike(String value) {
            addCriterion("CUST_TAXCODE like", value, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeNotLike(String value) {
            addCriterion("CUST_TAXCODE not like", value, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeIn(List<String> values) {
            addCriterion("CUST_TAXCODE in", values, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeNotIn(List<String> values) {
            addCriterion("CUST_TAXCODE not in", values, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeBetween(String value1, String value2) {
            addCriterion("CUST_TAXCODE between", value1, value2, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustTaxcodeNotBetween(String value1, String value2) {
            addCriterion("CUST_TAXCODE not between", value1, value2, "custTaxcode");
            return (Criteria) this;
        }

        public Criteria andCustAddressIsNull() {
            addCriterion("CUST_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andCustAddressIsNotNull() {
            addCriterion("CUST_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andCustAddressEqualTo(String value) {
            addCriterion("CUST_ADDRESS =", value, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressNotEqualTo(String value) {
            addCriterion("CUST_ADDRESS <>", value, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressGreaterThan(String value) {
            addCriterion("CUST_ADDRESS >", value, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ADDRESS >=", value, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressLessThan(String value) {
            addCriterion("CUST_ADDRESS <", value, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressLessThanOrEqualTo(String value) {
            addCriterion("CUST_ADDRESS <=", value, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressLike(String value) {
            addCriterion("CUST_ADDRESS like", value, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressNotLike(String value) {
            addCriterion("CUST_ADDRESS not like", value, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressIn(List<String> values) {
            addCriterion("CUST_ADDRESS in", values, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressNotIn(List<String> values) {
            addCriterion("CUST_ADDRESS not in", values, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressBetween(String value1, String value2) {
            addCriterion("CUST_ADDRESS between", value1, value2, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustAddressNotBetween(String value1, String value2) {
            addCriterion("CUST_ADDRESS not between", value1, value2, "custAddress");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneIsNull() {
            addCriterion("CUST_TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneIsNotNull() {
            addCriterion("CUST_TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneEqualTo(String value) {
            addCriterion("CUST_TELEPHONE =", value, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneNotEqualTo(String value) {
            addCriterion("CUST_TELEPHONE <>", value, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneGreaterThan(String value) {
            addCriterion("CUST_TELEPHONE >", value, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_TELEPHONE >=", value, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneLessThan(String value) {
            addCriterion("CUST_TELEPHONE <", value, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneLessThanOrEqualTo(String value) {
            addCriterion("CUST_TELEPHONE <=", value, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneLike(String value) {
            addCriterion("CUST_TELEPHONE like", value, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneNotLike(String value) {
            addCriterion("CUST_TELEPHONE not like", value, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneIn(List<String> values) {
            addCriterion("CUST_TELEPHONE in", values, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneNotIn(List<String> values) {
            addCriterion("CUST_TELEPHONE not in", values, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneBetween(String value1, String value2) {
            addCriterion("CUST_TELEPHONE between", value1, value2, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustTelephoneNotBetween(String value1, String value2) {
            addCriterion("CUST_TELEPHONE not between", value1, value2, "custTelephone");
            return (Criteria) this;
        }

        public Criteria andCustBanknameIsNull() {
            addCriterion("CUST_BANKNAME is null");
            return (Criteria) this;
        }

        public Criteria andCustBanknameIsNotNull() {
            addCriterion("CUST_BANKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustBanknameEqualTo(String value) {
            addCriterion("CUST_BANKNAME =", value, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameNotEqualTo(String value) {
            addCriterion("CUST_BANKNAME <>", value, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameGreaterThan(String value) {
            addCriterion("CUST_BANKNAME >", value, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_BANKNAME >=", value, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameLessThan(String value) {
            addCriterion("CUST_BANKNAME <", value, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameLessThanOrEqualTo(String value) {
            addCriterion("CUST_BANKNAME <=", value, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameLike(String value) {
            addCriterion("CUST_BANKNAME like", value, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameNotLike(String value) {
            addCriterion("CUST_BANKNAME not like", value, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameIn(List<String> values) {
            addCriterion("CUST_BANKNAME in", values, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameNotIn(List<String> values) {
            addCriterion("CUST_BANKNAME not in", values, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameBetween(String value1, String value2) {
            addCriterion("CUST_BANKNAME between", value1, value2, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBanknameNotBetween(String value1, String value2) {
            addCriterion("CUST_BANKNAME not between", value1, value2, "custBankname");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountIsNull() {
            addCriterion("CUST_BANKACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountIsNotNull() {
            addCriterion("CUST_BANKACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountEqualTo(String value) {
            addCriterion("CUST_BANKACCOUNT =", value, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountNotEqualTo(String value) {
            addCriterion("CUST_BANKACCOUNT <>", value, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountGreaterThan(String value) {
            addCriterion("CUST_BANKACCOUNT >", value, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_BANKACCOUNT >=", value, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountLessThan(String value) {
            addCriterion("CUST_BANKACCOUNT <", value, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountLessThanOrEqualTo(String value) {
            addCriterion("CUST_BANKACCOUNT <=", value, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountLike(String value) {
            addCriterion("CUST_BANKACCOUNT like", value, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountNotLike(String value) {
            addCriterion("CUST_BANKACCOUNT not like", value, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountIn(List<String> values) {
            addCriterion("CUST_BANKACCOUNT in", values, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountNotIn(List<String> values) {
            addCriterion("CUST_BANKACCOUNT not in", values, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountBetween(String value1, String value2) {
            addCriterion("CUST_BANKACCOUNT between", value1, value2, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustBankaccountNotBetween(String value1, String value2) {
            addCriterion("CUST_BANKACCOUNT not between", value1, value2, "custBankaccount");
            return (Criteria) this;
        }

        public Criteria andCustEmailIsNull() {
            addCriterion("CUST_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andCustEmailIsNotNull() {
            addCriterion("CUST_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andCustEmailEqualTo(String value) {
            addCriterion("CUST_EMAIL =", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailNotEqualTo(String value) {
            addCriterion("CUST_EMAIL <>", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailGreaterThan(String value) {
            addCriterion("CUST_EMAIL >", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_EMAIL >=", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailLessThan(String value) {
            addCriterion("CUST_EMAIL <", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailLessThanOrEqualTo(String value) {
            addCriterion("CUST_EMAIL <=", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailLike(String value) {
            addCriterion("CUST_EMAIL like", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailNotLike(String value) {
            addCriterion("CUST_EMAIL not like", value, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailIn(List<String> values) {
            addCriterion("CUST_EMAIL in", values, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailNotIn(List<String> values) {
            addCriterion("CUST_EMAIL not in", values, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailBetween(String value1, String value2) {
            addCriterion("CUST_EMAIL between", value1, value2, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustEmailNotBetween(String value1, String value2) {
            addCriterion("CUST_EMAIL not between", value1, value2, "custEmail");
            return (Criteria) this;
        }

        public Criteria andCustMobileIsNull() {
            addCriterion("CUST_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andCustMobileIsNotNull() {
            addCriterion("CUST_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andCustMobileEqualTo(String value) {
            addCriterion("CUST_MOBILE =", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotEqualTo(String value) {
            addCriterion("CUST_MOBILE <>", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileGreaterThan(String value) {
            addCriterion("CUST_MOBILE >", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_MOBILE >=", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileLessThan(String value) {
            addCriterion("CUST_MOBILE <", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileLessThanOrEqualTo(String value) {
            addCriterion("CUST_MOBILE <=", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileLike(String value) {
            addCriterion("CUST_MOBILE like", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotLike(String value) {
            addCriterion("CUST_MOBILE not like", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileIn(List<String> values) {
            addCriterion("CUST_MOBILE in", values, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotIn(List<String> values) {
            addCriterion("CUST_MOBILE not in", values, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileBetween(String value1, String value2) {
            addCriterion("CUST_MOBILE between", value1, value2, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotBetween(String value1, String value2) {
            addCriterion("CUST_MOBILE not between", value1, value2, "custMobile");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("INVOICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("INVOICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(String value) {
            addCriterion("INVOICE_TYPE =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(String value) {
            addCriterion("INVOICE_TYPE <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(String value) {
            addCriterion("INVOICE_TYPE >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPE >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(String value) {
            addCriterion("INVOICE_TYPE <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPE <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLike(String value) {
            addCriterion("INVOICE_TYPE like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotLike(String value) {
            addCriterion("INVOICE_TYPE not like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<String> values) {
            addCriterion("INVOICE_TYPE in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<String> values) {
            addCriterion("INVOICE_TYPE not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPE between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPE not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesIsNull() {
            addCriterion("INVOICE_TYPES is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesIsNotNull() {
            addCriterion("INVOICE_TYPES is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesEqualTo(String value) {
            addCriterion("INVOICE_TYPES =", value, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesNotEqualTo(String value) {
            addCriterion("INVOICE_TYPES <>", value, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesGreaterThan(String value) {
            addCriterion("INVOICE_TYPES >", value, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPES >=", value, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesLessThan(String value) {
            addCriterion("INVOICE_TYPES <", value, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPES <=", value, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesLike(String value) {
            addCriterion("INVOICE_TYPES like", value, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesNotLike(String value) {
            addCriterion("INVOICE_TYPES not like", value, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesIn(List<String> values) {
            addCriterion("INVOICE_TYPES in", values, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesNotIn(List<String> values) {
            addCriterion("INVOICE_TYPES not in", values, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPES between", value1, value2, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypesNotBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPES not between", value1, value2, "invoiceTypes");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIsNull() {
            addCriterion("INVOICE_BASE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIsNotNull() {
            addCriterion("INVOICE_BASE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseEqualTo(String value) {
            addCriterion("INVOICE_BASE =", value, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseNotEqualTo(String value) {
            addCriterion("INVOICE_BASE <>", value, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseGreaterThan(String value) {
            addCriterion("INVOICE_BASE >", value, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_BASE >=", value, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseLessThan(String value) {
            addCriterion("INVOICE_BASE <", value, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_BASE <=", value, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseLike(String value) {
            addCriterion("INVOICE_BASE like", value, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseNotLike(String value) {
            addCriterion("INVOICE_BASE not like", value, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseIn(List<String> values) {
            addCriterion("INVOICE_BASE in", values, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseNotIn(List<String> values) {
            addCriterion("INVOICE_BASE not in", values, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseBetween(String value1, String value2) {
            addCriterion("INVOICE_BASE between", value1, value2, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceBaseNotBetween(String value1, String value2) {
            addCriterion("INVOICE_BASE not between", value1, value2, "invoiceBase");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayIsNull() {
            addCriterion("INVOICE_WAY is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayIsNotNull() {
            addCriterion("INVOICE_WAY is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayEqualTo(String value) {
            addCriterion("INVOICE_WAY =", value, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayNotEqualTo(String value) {
            addCriterion("INVOICE_WAY <>", value, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayGreaterThan(String value) {
            addCriterion("INVOICE_WAY >", value, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_WAY >=", value, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayLessThan(String value) {
            addCriterion("INVOICE_WAY <", value, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_WAY <=", value, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayLike(String value) {
            addCriterion("INVOICE_WAY like", value, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayNotLike(String value) {
            addCriterion("INVOICE_WAY not like", value, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayIn(List<String> values) {
            addCriterion("INVOICE_WAY in", values, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayNotIn(List<String> values) {
            addCriterion("INVOICE_WAY not in", values, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayBetween(String value1, String value2) {
            addCriterion("INVOICE_WAY between", value1, value2, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andInvoiceWayNotBetween(String value1, String value2) {
            addCriterion("INVOICE_WAY not between", value1, value2, "invoiceWay");
            return (Criteria) this;
        }

        public Criteria andMergeAmtIsNull() {
            addCriterion("MERGE_AMT is null");
            return (Criteria) this;
        }

        public Criteria andMergeAmtIsNotNull() {
            addCriterion("MERGE_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andMergeAmtEqualTo(BigDecimal value) {
            addCriterion("MERGE_AMT =", value, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtNotEqualTo(BigDecimal value) {
            addCriterion("MERGE_AMT <>", value, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtGreaterThan(BigDecimal value) {
            addCriterion("MERGE_AMT >", value, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MERGE_AMT >=", value, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtLessThan(BigDecimal value) {
            addCriterion("MERGE_AMT <", value, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MERGE_AMT <=", value, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtIn(List<BigDecimal> values) {
            addCriterion("MERGE_AMT in", values, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtNotIn(List<BigDecimal> values) {
            addCriterion("MERGE_AMT not in", values, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MERGE_AMT between", value1, value2, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MERGE_AMT not between", value1, value2, "mergeAmt");
            return (Criteria) this;
        }

        public Criteria andMergeQtyIsNull() {
            addCriterion("MERGE_QTY is null");
            return (Criteria) this;
        }

        public Criteria andMergeQtyIsNotNull() {
            addCriterion("MERGE_QTY is not null");
            return (Criteria) this;
        }

        public Criteria andMergeQtyEqualTo(BigDecimal value) {
            addCriterion("MERGE_QTY =", value, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyNotEqualTo(BigDecimal value) {
            addCriterion("MERGE_QTY <>", value, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyGreaterThan(BigDecimal value) {
            addCriterion("MERGE_QTY >", value, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MERGE_QTY >=", value, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyLessThan(BigDecimal value) {
            addCriterion("MERGE_QTY <", value, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MERGE_QTY <=", value, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyIn(List<BigDecimal> values) {
            addCriterion("MERGE_QTY in", values, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyNotIn(List<BigDecimal> values) {
            addCriterion("MERGE_QTY not in", values, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MERGE_QTY between", value1, value2, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andMergeQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MERGE_QTY not between", value1, value2, "mergeQty");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNull() {
            addCriterion("TAX_RATE is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("TAX_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(String value) {
            addCriterion("TAX_RATE =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(String value) {
            addCriterion("TAX_RATE <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(String value) {
            addCriterion("TAX_RATE >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_RATE >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(String value) {
            addCriterion("TAX_RATE <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(String value) {
            addCriterion("TAX_RATE <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLike(String value) {
            addCriterion("TAX_RATE like", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotLike(String value) {
            addCriterion("TAX_RATE not like", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<String> values) {
            addCriterion("TAX_RATE in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<String> values) {
            addCriterion("TAX_RATE not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(String value1, String value2) {
            addCriterion("TAX_RATE between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(String value1, String value2) {
            addCriterion("TAX_RATE not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNull() {
            addCriterion("DISCOUNT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNotNull() {
            addCriterion("DISCOUNT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeEqualTo(String value) {
            addCriterion("DISCOUNT_TYPE =", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotEqualTo(String value) {
            addCriterion("DISCOUNT_TYPE <>", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThan(String value) {
            addCriterion("DISCOUNT_TYPE >", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DISCOUNT_TYPE >=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThan(String value) {
            addCriterion("DISCOUNT_TYPE <", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThanOrEqualTo(String value) {
            addCriterion("DISCOUNT_TYPE <=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLike(String value) {
            addCriterion("DISCOUNT_TYPE like", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotLike(String value) {
            addCriterion("DISCOUNT_TYPE not like", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIn(List<String> values) {
            addCriterion("DISCOUNT_TYPE in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotIn(List<String> values) {
            addCriterion("DISCOUNT_TYPE not in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeBetween(String value1, String value2) {
            addCriterion("DISCOUNT_TYPE between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotBetween(String value1, String value2) {
            addCriterion("DISCOUNT_TYPE not between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNull() {
            addCriterion("DISCOUNT_RATE is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNotNull() {
            addCriterion("DISCOUNT_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_RATE =", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_RATE <>", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThan(BigDecimal value) {
            addCriterion("DISCOUNT_RATE >", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_RATE >=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThan(BigDecimal value) {
            addCriterion("DISCOUNT_RATE <", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DISCOUNT_RATE <=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIn(List<BigDecimal> values) {
            addCriterion("DISCOUNT_RATE in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotIn(List<BigDecimal> values) {
            addCriterion("DISCOUNT_RATE not in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISCOUNT_RATE between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISCOUNT_RATE not between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andMergeGiftIsNull() {
            addCriterion("MERGE_GIFT is null");
            return (Criteria) this;
        }

        public Criteria andMergeGiftIsNotNull() {
            addCriterion("MERGE_GIFT is not null");
            return (Criteria) this;
        }

        public Criteria andMergeGiftEqualTo(String value) {
            addCriterion("MERGE_GIFT =", value, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftNotEqualTo(String value) {
            addCriterion("MERGE_GIFT <>", value, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftGreaterThan(String value) {
            addCriterion("MERGE_GIFT >", value, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftGreaterThanOrEqualTo(String value) {
            addCriterion("MERGE_GIFT >=", value, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftLessThan(String value) {
            addCriterion("MERGE_GIFT <", value, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftLessThanOrEqualTo(String value) {
            addCriterion("MERGE_GIFT <=", value, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftLike(String value) {
            addCriterion("MERGE_GIFT like", value, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftNotLike(String value) {
            addCriterion("MERGE_GIFT not like", value, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftIn(List<String> values) {
            addCriterion("MERGE_GIFT in", values, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftNotIn(List<String> values) {
            addCriterion("MERGE_GIFT not in", values, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftBetween(String value1, String value2) {
            addCriterion("MERGE_GIFT between", value1, value2, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andMergeGiftNotBetween(String value1, String value2) {
            addCriterion("MERGE_GIFT not between", value1, value2, "mergeGift");
            return (Criteria) this;
        }

        public Criteria andInvoiceListIsNull() {
            addCriterion("INVOICE_LIST is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceListIsNotNull() {
            addCriterion("INVOICE_LIST is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceListEqualTo(String value) {
            addCriterion("INVOICE_LIST =", value, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListNotEqualTo(String value) {
            addCriterion("INVOICE_LIST <>", value, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListGreaterThan(String value) {
            addCriterion("INVOICE_LIST >", value, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_LIST >=", value, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListLessThan(String value) {
            addCriterion("INVOICE_LIST <", value, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_LIST <=", value, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListLike(String value) {
            addCriterion("INVOICE_LIST like", value, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListNotLike(String value) {
            addCriterion("INVOICE_LIST not like", value, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListIn(List<String> values) {
            addCriterion("INVOICE_LIST in", values, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListNotIn(List<String> values) {
            addCriterion("INVOICE_LIST not in", values, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListBetween(String value1, String value2) {
            addCriterion("INVOICE_LIST between", value1, value2, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceListNotBetween(String value1, String value2) {
            addCriterion("INVOICE_LIST not between", value1, value2, "invoiceList");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedIsNull() {
            addCriterion("INVOICE_RED is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedIsNotNull() {
            addCriterion("INVOICE_RED is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedEqualTo(String value) {
            addCriterion("INVOICE_RED =", value, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedNotEqualTo(String value) {
            addCriterion("INVOICE_RED <>", value, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedGreaterThan(String value) {
            addCriterion("INVOICE_RED >", value, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED >=", value, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedLessThan(String value) {
            addCriterion("INVOICE_RED <", value, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED <=", value, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedLike(String value) {
            addCriterion("INVOICE_RED like", value, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedNotLike(String value) {
            addCriterion("INVOICE_RED not like", value, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedIn(List<String> values) {
            addCriterion("INVOICE_RED in", values, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedNotIn(List<String> values) {
            addCriterion("INVOICE_RED not in", values, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedBetween(String value1, String value2) {
            addCriterion("INVOICE_RED between", value1, value2, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedNotBetween(String value1, String value2) {
            addCriterion("INVOICE_RED not between", value1, value2, "invoiceRed");
            return (Criteria) this;
        }

        public Criteria andMergefTypeIsNull() {
            addCriterion("MERGEF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMergefTypeIsNotNull() {
            addCriterion("MERGEF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMergefTypeEqualTo(String value) {
            addCriterion("MERGEF_TYPE =", value, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeNotEqualTo(String value) {
            addCriterion("MERGEF_TYPE <>", value, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeGreaterThan(String value) {
            addCriterion("MERGEF_TYPE >", value, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MERGEF_TYPE >=", value, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeLessThan(String value) {
            addCriterion("MERGEF_TYPE <", value, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeLessThanOrEqualTo(String value) {
            addCriterion("MERGEF_TYPE <=", value, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeLike(String value) {
            addCriterion("MERGEF_TYPE like", value, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeNotLike(String value) {
            addCriterion("MERGEF_TYPE not like", value, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeIn(List<String> values) {
            addCriterion("MERGEF_TYPE in", values, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeNotIn(List<String> values) {
            addCriterion("MERGEF_TYPE not in", values, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeBetween(String value1, String value2) {
            addCriterion("MERGEF_TYPE between", value1, value2, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergefTypeNotBetween(String value1, String value2) {
            addCriterion("MERGEF_TYPE not between", value1, value2, "mergefType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeIsNull() {
            addCriterion("MERGES_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMergesTypeIsNotNull() {
            addCriterion("MERGES_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMergesTypeEqualTo(String value) {
            addCriterion("MERGES_TYPE =", value, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeNotEqualTo(String value) {
            addCriterion("MERGES_TYPE <>", value, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeGreaterThan(String value) {
            addCriterion("MERGES_TYPE >", value, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MERGES_TYPE >=", value, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeLessThan(String value) {
            addCriterion("MERGES_TYPE <", value, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeLessThanOrEqualTo(String value) {
            addCriterion("MERGES_TYPE <=", value, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeLike(String value) {
            addCriterion("MERGES_TYPE like", value, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeNotLike(String value) {
            addCriterion("MERGES_TYPE not like", value, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeIn(List<String> values) {
            addCriterion("MERGES_TYPE in", values, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeNotIn(List<String> values) {
            addCriterion("MERGES_TYPE not in", values, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeBetween(String value1, String value2) {
            addCriterion("MERGES_TYPE between", value1, value2, "mergesType");
            return (Criteria) this;
        }

        public Criteria andMergesTypeNotBetween(String value1, String value2) {
            addCriterion("MERGES_TYPE not between", value1, value2, "mergesType");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeIsNull() {
            addCriterion("GOLDTAX_CODE is null");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeIsNotNull() {
            addCriterion("GOLDTAX_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeEqualTo(String value) {
            addCriterion("GOLDTAX_CODE =", value, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeNotEqualTo(String value) {
            addCriterion("GOLDTAX_CODE <>", value, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeGreaterThan(String value) {
            addCriterion("GOLDTAX_CODE >", value, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeGreaterThanOrEqualTo(String value) {
            addCriterion("GOLDTAX_CODE >=", value, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeLessThan(String value) {
            addCriterion("GOLDTAX_CODE <", value, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeLessThanOrEqualTo(String value) {
            addCriterion("GOLDTAX_CODE <=", value, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeLike(String value) {
            addCriterion("GOLDTAX_CODE like", value, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeNotLike(String value) {
            addCriterion("GOLDTAX_CODE not like", value, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeIn(List<String> values) {
            addCriterion("GOLDTAX_CODE in", values, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeNotIn(List<String> values) {
            addCriterion("GOLDTAX_CODE not in", values, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeBetween(String value1, String value2) {
            addCriterion("GOLDTAX_CODE between", value1, value2, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxCodeNotBetween(String value1, String value2) {
            addCriterion("GOLDTAX_CODE not between", value1, value2, "goldtaxCode");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumIsNull() {
            addCriterion("GOLDTAX_NUM is null");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumIsNotNull() {
            addCriterion("GOLDTAX_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumEqualTo(String value) {
            addCriterion("GOLDTAX_NUM =", value, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumNotEqualTo(String value) {
            addCriterion("GOLDTAX_NUM <>", value, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumGreaterThan(String value) {
            addCriterion("GOLDTAX_NUM >", value, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumGreaterThanOrEqualTo(String value) {
            addCriterion("GOLDTAX_NUM >=", value, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumLessThan(String value) {
            addCriterion("GOLDTAX_NUM <", value, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumLessThanOrEqualTo(String value) {
            addCriterion("GOLDTAX_NUM <=", value, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumLike(String value) {
            addCriterion("GOLDTAX_NUM like", value, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumNotLike(String value) {
            addCriterion("GOLDTAX_NUM not like", value, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumIn(List<String> values) {
            addCriterion("GOLDTAX_NUM in", values, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumNotIn(List<String> values) {
            addCriterion("GOLDTAX_NUM not in", values, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumBetween(String value1, String value2) {
            addCriterion("GOLDTAX_NUM between", value1, value2, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andGoldtaxNumNotBetween(String value1, String value2) {
            addCriterion("GOLDTAX_NUM not between", value1, value2, "goldtaxNum");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andCheckNameIsNull() {
            addCriterion("CHECK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCheckNameIsNotNull() {
            addCriterion("CHECK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckNameEqualTo(String value) {
            addCriterion("CHECK_NAME =", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotEqualTo(String value) {
            addCriterion("CHECK_NAME <>", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameGreaterThan(String value) {
            addCriterion("CHECK_NAME >", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_NAME >=", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLessThan(String value) {
            addCriterion("CHECK_NAME <", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLessThanOrEqualTo(String value) {
            addCriterion("CHECK_NAME <=", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLike(String value) {
            addCriterion("CHECK_NAME like", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotLike(String value) {
            addCriterion("CHECK_NAME not like", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameIn(List<String> values) {
            addCriterion("CHECK_NAME in", values, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotIn(List<String> values) {
            addCriterion("CHECK_NAME not in", values, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameBetween(String value1, String value2) {
            addCriterion("CHECK_NAME between", value1, value2, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotBetween(String value1, String value2) {
            addCriterion("CHECK_NAME not between", value1, value2, "checkName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIsNull() {
            addCriterion("PAYEE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIsNotNull() {
            addCriterion("PAYEE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeNameEqualTo(String value) {
            addCriterion("PAYEE_NAME =", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotEqualTo(String value) {
            addCriterion("PAYEE_NAME <>", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameGreaterThan(String value) {
            addCriterion("PAYEE_NAME >", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PAYEE_NAME >=", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLessThan(String value) {
            addCriterion("PAYEE_NAME <", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLessThanOrEqualTo(String value) {
            addCriterion("PAYEE_NAME <=", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLike(String value) {
            addCriterion("PAYEE_NAME like", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotLike(String value) {
            addCriterion("PAYEE_NAME not like", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIn(List<String> values) {
            addCriterion("PAYEE_NAME in", values, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotIn(List<String> values) {
            addCriterion("PAYEE_NAME not in", values, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameBetween(String value1, String value2) {
            addCriterion("PAYEE_NAME between", value1, value2, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotBetween(String value1, String value2) {
            addCriterion("PAYEE_NAME not between", value1, value2, "payeeName");
            return (Criteria) this;
        }

        public Criteria andBillDateIsNull() {
            addCriterion("BILL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBillDateIsNotNull() {
            addCriterion("BILL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBillDateEqualTo(String value) {
            addCriterion("BILL_DATE =", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotEqualTo(String value) {
            addCriterion("BILL_DATE <>", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateGreaterThan(String value) {
            addCriterion("BILL_DATE >", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_DATE >=", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLessThan(String value) {
            addCriterion("BILL_DATE <", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLessThanOrEqualTo(String value) {
            addCriterion("BILL_DATE <=", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLike(String value) {
            addCriterion("BILL_DATE like", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotLike(String value) {
            addCriterion("BILL_DATE not like", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateIn(List<String> values) {
            addCriterion("BILL_DATE in", values, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotIn(List<String> values) {
            addCriterion("BILL_DATE not in", values, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateBetween(String value1, String value2) {
            addCriterion("BILL_DATE between", value1, value2, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotBetween(String value1, String value2) {
            addCriterion("BILL_DATE not between", value1, value2, "billDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateIsNull() {
            addCriterion("CANCEL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCancelDateIsNotNull() {
            addCriterion("CANCEL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCancelDateEqualTo(String value) {
            addCriterion("CANCEL_DATE =", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotEqualTo(String value) {
            addCriterion("CANCEL_DATE <>", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateGreaterThan(String value) {
            addCriterion("CANCEL_DATE >", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateGreaterThanOrEqualTo(String value) {
            addCriterion("CANCEL_DATE >=", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateLessThan(String value) {
            addCriterion("CANCEL_DATE <", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateLessThanOrEqualTo(String value) {
            addCriterion("CANCEL_DATE <=", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateLike(String value) {
            addCriterion("CANCEL_DATE like", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotLike(String value) {
            addCriterion("CANCEL_DATE not like", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateIn(List<String> values) {
            addCriterion("CANCEL_DATE in", values, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotIn(List<String> values) {
            addCriterion("CANCEL_DATE not in", values, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateBetween(String value1, String value2) {
            addCriterion("CANCEL_DATE between", value1, value2, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotBetween(String value1, String value2) {
            addCriterion("CANCEL_DATE not between", value1, value2, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andBillGdateIsNull() {
            addCriterion("BILL_GDATE is null");
            return (Criteria) this;
        }

        public Criteria andBillGdateIsNotNull() {
            addCriterion("BILL_GDATE is not null");
            return (Criteria) this;
        }

        public Criteria andBillGdateEqualTo(String value) {
            addCriterion("BILL_GDATE =", value, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateNotEqualTo(String value) {
            addCriterion("BILL_GDATE <>", value, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateGreaterThan(String value) {
            addCriterion("BILL_GDATE >", value, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_GDATE >=", value, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateLessThan(String value) {
            addCriterion("BILL_GDATE <", value, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateLessThanOrEqualTo(String value) {
            addCriterion("BILL_GDATE <=", value, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateLike(String value) {
            addCriterion("BILL_GDATE like", value, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateNotLike(String value) {
            addCriterion("BILL_GDATE not like", value, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateIn(List<String> values) {
            addCriterion("BILL_GDATE in", values, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateNotIn(List<String> values) {
            addCriterion("BILL_GDATE not in", values, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateBetween(String value1, String value2) {
            addCriterion("BILL_GDATE between", value1, value2, "billGdate");
            return (Criteria) this;
        }

        public Criteria andBillGdateNotBetween(String value1, String value2) {
            addCriterion("BILL_GDATE not between", value1, value2, "billGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateIsNull() {
            addCriterion("CANCEL_GDATE is null");
            return (Criteria) this;
        }

        public Criteria andCancelGdateIsNotNull() {
            addCriterion("CANCEL_GDATE is not null");
            return (Criteria) this;
        }

        public Criteria andCancelGdateEqualTo(String value) {
            addCriterion("CANCEL_GDATE =", value, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateNotEqualTo(String value) {
            addCriterion("CANCEL_GDATE <>", value, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateGreaterThan(String value) {
            addCriterion("CANCEL_GDATE >", value, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateGreaterThanOrEqualTo(String value) {
            addCriterion("CANCEL_GDATE >=", value, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateLessThan(String value) {
            addCriterion("CANCEL_GDATE <", value, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateLessThanOrEqualTo(String value) {
            addCriterion("CANCEL_GDATE <=", value, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateLike(String value) {
            addCriterion("CANCEL_GDATE like", value, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateNotLike(String value) {
            addCriterion("CANCEL_GDATE not like", value, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateIn(List<String> values) {
            addCriterion("CANCEL_GDATE in", values, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateNotIn(List<String> values) {
            addCriterion("CANCEL_GDATE not in", values, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateBetween(String value1, String value2) {
            addCriterion("CANCEL_GDATE between", value1, value2, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andCancelGdateNotBetween(String value1, String value2) {
            addCriterion("CANCEL_GDATE not between", value1, value2, "cancelGdate");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmIsNull() {
            addCriterion("INVOICE_RED_REQM is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmIsNotNull() {
            addCriterion("INVOICE_RED_REQM is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmEqualTo(String value) {
            addCriterion("INVOICE_RED_REQM =", value, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmNotEqualTo(String value) {
            addCriterion("INVOICE_RED_REQM <>", value, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmGreaterThan(String value) {
            addCriterion("INVOICE_RED_REQM >", value, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED_REQM >=", value, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmLessThan(String value) {
            addCriterion("INVOICE_RED_REQM <", value, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED_REQM <=", value, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmLike(String value) {
            addCriterion("INVOICE_RED_REQM like", value, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmNotLike(String value) {
            addCriterion("INVOICE_RED_REQM not like", value, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmIn(List<String> values) {
            addCriterion("INVOICE_RED_REQM in", values, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmNotIn(List<String> values) {
            addCriterion("INVOICE_RED_REQM not in", values, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmBetween(String value1, String value2) {
            addCriterion("INVOICE_RED_REQM between", value1, value2, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedReqmNotBetween(String value1, String value2) {
            addCriterion("INVOICE_RED_REQM not between", value1, value2, "invoiceRedReqm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmIsNull() {
            addCriterion("INVOICE_RED_XXBM is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmIsNotNull() {
            addCriterion("INVOICE_RED_XXBM is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmEqualTo(String value) {
            addCriterion("INVOICE_RED_XXBM =", value, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmNotEqualTo(String value) {
            addCriterion("INVOICE_RED_XXBM <>", value, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmGreaterThan(String value) {
            addCriterion("INVOICE_RED_XXBM >", value, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED_XXBM >=", value, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmLessThan(String value) {
            addCriterion("INVOICE_RED_XXBM <", value, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED_XXBM <=", value, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmLike(String value) {
            addCriterion("INVOICE_RED_XXBM like", value, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmNotLike(String value) {
            addCriterion("INVOICE_RED_XXBM not like", value, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmIn(List<String> values) {
            addCriterion("INVOICE_RED_XXBM in", values, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmNotIn(List<String> values) {
            addCriterion("INVOICE_RED_XXBM not in", values, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmBetween(String value1, String value2) {
            addCriterion("INVOICE_RED_XXBM between", value1, value2, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedXxbmNotBetween(String value1, String value2) {
            addCriterion("INVOICE_RED_XXBM not between", value1, value2, "invoiceRedXxbm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmIsNull() {
            addCriterion("INVOICE_RED_FPDM is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmIsNotNull() {
            addCriterion("INVOICE_RED_FPDM is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmEqualTo(String value) {
            addCriterion("INVOICE_RED_FPDM =", value, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmNotEqualTo(String value) {
            addCriterion("INVOICE_RED_FPDM <>", value, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmGreaterThan(String value) {
            addCriterion("INVOICE_RED_FPDM >", value, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED_FPDM >=", value, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmLessThan(String value) {
            addCriterion("INVOICE_RED_FPDM <", value, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED_FPDM <=", value, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmLike(String value) {
            addCriterion("INVOICE_RED_FPDM like", value, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmNotLike(String value) {
            addCriterion("INVOICE_RED_FPDM not like", value, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmIn(List<String> values) {
            addCriterion("INVOICE_RED_FPDM in", values, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmNotIn(List<String> values) {
            addCriterion("INVOICE_RED_FPDM not in", values, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmBetween(String value1, String value2) {
            addCriterion("INVOICE_RED_FPDM between", value1, value2, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFpdmNotBetween(String value1, String value2) {
            addCriterion("INVOICE_RED_FPDM not between", value1, value2, "invoiceRedFpdm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmIsNull() {
            addCriterion("INVOICE_RED_FPHM is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmIsNotNull() {
            addCriterion("INVOICE_RED_FPHM is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmEqualTo(String value) {
            addCriterion("INVOICE_RED_FPHM =", value, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmNotEqualTo(String value) {
            addCriterion("INVOICE_RED_FPHM <>", value, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmGreaterThan(String value) {
            addCriterion("INVOICE_RED_FPHM >", value, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED_FPHM >=", value, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmLessThan(String value) {
            addCriterion("INVOICE_RED_FPHM <", value, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_RED_FPHM <=", value, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmLike(String value) {
            addCriterion("INVOICE_RED_FPHM like", value, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmNotLike(String value) {
            addCriterion("INVOICE_RED_FPHM not like", value, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmIn(List<String> values) {
            addCriterion("INVOICE_RED_FPHM in", values, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmNotIn(List<String> values) {
            addCriterion("INVOICE_RED_FPHM not in", values, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmBetween(String value1, String value2) {
            addCriterion("INVOICE_RED_FPHM between", value1, value2, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andInvoiceRedFphmNotBetween(String value1, String value2) {
            addCriterion("INVOICE_RED_FPHM not between", value1, value2, "invoiceRedFphm");
            return (Criteria) this;
        }

        public Criteria andBillRemarkIsNull() {
            addCriterion("BILL_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andBillRemarkIsNotNull() {
            addCriterion("BILL_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andBillRemarkEqualTo(String value) {
            addCriterion("BILL_REMARK =", value, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkNotEqualTo(String value) {
            addCriterion("BILL_REMARK <>", value, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkGreaterThan(String value) {
            addCriterion("BILL_REMARK >", value, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_REMARK >=", value, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkLessThan(String value) {
            addCriterion("BILL_REMARK <", value, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkLessThanOrEqualTo(String value) {
            addCriterion("BILL_REMARK <=", value, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkLike(String value) {
            addCriterion("BILL_REMARK like", value, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkNotLike(String value) {
            addCriterion("BILL_REMARK not like", value, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkIn(List<String> values) {
            addCriterion("BILL_REMARK in", values, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkNotIn(List<String> values) {
            addCriterion("BILL_REMARK not in", values, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkBetween(String value1, String value2) {
            addCriterion("BILL_REMARK between", value1, value2, "billRemark");
            return (Criteria) this;
        }

        public Criteria andBillRemarkNotBetween(String value1, String value2) {
            addCriterion("BILL_REMARK not between", value1, value2, "billRemark");
            return (Criteria) this;
        }

        public Criteria andZamountHswcIsNull() {
            addCriterion("ZAMOUNT_HSWC is null");
            return (Criteria) this;
        }

        public Criteria andZamountHswcIsNotNull() {
            addCriterion("ZAMOUNT_HSWC is not null");
            return (Criteria) this;
        }

        public Criteria andZamountHswcEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_HSWC =", value, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcNotEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_HSWC <>", value, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcGreaterThan(BigDecimal value) {
            addCriterion("ZAMOUNT_HSWC >", value, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_HSWC >=", value, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcLessThan(BigDecimal value) {
            addCriterion("ZAMOUNT_HSWC <", value, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_HSWC <=", value, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcIn(List<BigDecimal> values) {
            addCriterion("ZAMOUNT_HSWC in", values, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcNotIn(List<BigDecimal> values) {
            addCriterion("ZAMOUNT_HSWC not in", values, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZAMOUNT_HSWC between", value1, value2, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountHswcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZAMOUNT_HSWC not between", value1, value2, "zamountHswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcIsNull() {
            addCriterion("ZAMOUNT_WSWC is null");
            return (Criteria) this;
        }

        public Criteria andZamountWswcIsNotNull() {
            addCriterion("ZAMOUNT_WSWC is not null");
            return (Criteria) this;
        }

        public Criteria andZamountWswcEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_WSWC =", value, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcNotEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_WSWC <>", value, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcGreaterThan(BigDecimal value) {
            addCriterion("ZAMOUNT_WSWC >", value, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_WSWC >=", value, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcLessThan(BigDecimal value) {
            addCriterion("ZAMOUNT_WSWC <", value, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_WSWC <=", value, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcIn(List<BigDecimal> values) {
            addCriterion("ZAMOUNT_WSWC in", values, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcNotIn(List<BigDecimal> values) {
            addCriterion("ZAMOUNT_WSWC not in", values, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZAMOUNT_WSWC between", value1, value2, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountWswcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZAMOUNT_WSWC not between", value1, value2, "zamountWswc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcIsNull() {
            addCriterion("ZAMOUNT_SEWC is null");
            return (Criteria) this;
        }

        public Criteria andZamountSewcIsNotNull() {
            addCriterion("ZAMOUNT_SEWC is not null");
            return (Criteria) this;
        }

        public Criteria andZamountSewcEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_SEWC =", value, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcNotEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_SEWC <>", value, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcGreaterThan(BigDecimal value) {
            addCriterion("ZAMOUNT_SEWC >", value, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_SEWC >=", value, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcLessThan(BigDecimal value) {
            addCriterion("ZAMOUNT_SEWC <", value, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ZAMOUNT_SEWC <=", value, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcIn(List<BigDecimal> values) {
            addCriterion("ZAMOUNT_SEWC in", values, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcNotIn(List<BigDecimal> values) {
            addCriterion("ZAMOUNT_SEWC not in", values, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZAMOUNT_SEWC between", value1, value2, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andZamountSewcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZAMOUNT_SEWC not between", value1, value2, "zamountSewc");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("CREATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("CREATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("CREATED_BY =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("CREATED_BY <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("CREATED_BY >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATED_BY >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("CREATED_BY <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("CREATED_BY <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("CREATED_BY like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("CREATED_BY not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("CREATED_BY in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("CREATED_BY not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("CREATED_BY between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("CREATED_BY not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreationDateIsNull() {
            addCriterion("CREATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreationDateIsNotNull() {
            addCriterion("CREATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreationDateEqualTo(Date value) {
            addCriterion("CREATION_DATE =", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotEqualTo(Date value) {
            addCriterion("CREATION_DATE <>", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateGreaterThan(Date value) {
            addCriterion("CREATION_DATE >", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATION_DATE >=", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateLessThan(Date value) {
            addCriterion("CREATION_DATE <", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATION_DATE <=", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateIn(List<Date> values) {
            addCriterion("CREATION_DATE in", values, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotIn(List<Date> values) {
            addCriterion("CREATION_DATE not in", values, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateBetween(Date value1, Date value2) {
            addCriterion("CREATION_DATE between", value1, value2, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATION_DATE not between", value1, value2, "creationDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByIsNull() {
            addCriterion("LAST_UPDATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByIsNotNull() {
            addCriterion("LAST_UPDATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByEqualTo(String value) {
            addCriterion("LAST_UPDATED_BY =", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByNotEqualTo(String value) {
            addCriterion("LAST_UPDATED_BY <>", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByGreaterThan(String value) {
            addCriterion("LAST_UPDATED_BY >", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_UPDATED_BY >=", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByLessThan(String value) {
            addCriterion("LAST_UPDATED_BY <", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByLessThanOrEqualTo(String value) {
            addCriterion("LAST_UPDATED_BY <=", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByLike(String value) {
            addCriterion("LAST_UPDATED_BY like", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByNotLike(String value) {
            addCriterion("LAST_UPDATED_BY not like", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByIn(List<String> values) {
            addCriterion("LAST_UPDATED_BY in", values, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByNotIn(List<String> values) {
            addCriterion("LAST_UPDATED_BY not in", values, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByBetween(String value1, String value2) {
            addCriterion("LAST_UPDATED_BY between", value1, value2, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByNotBetween(String value1, String value2) {
            addCriterion("LAST_UPDATED_BY not between", value1, value2, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatIsNull() {
            addCriterion("LAST_UPDATED_DAT is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatIsNotNull() {
            addCriterion("LAST_UPDATED_DAT is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatEqualTo(String value) {
            addCriterion("LAST_UPDATED_DAT =", value, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatNotEqualTo(String value) {
            addCriterion("LAST_UPDATED_DAT <>", value, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatGreaterThan(String value) {
            addCriterion("LAST_UPDATED_DAT >", value, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_UPDATED_DAT >=", value, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatLessThan(String value) {
            addCriterion("LAST_UPDATED_DAT <", value, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatLessThanOrEqualTo(String value) {
            addCriterion("LAST_UPDATED_DAT <=", value, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatLike(String value) {
            addCriterion("LAST_UPDATED_DAT like", value, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatNotLike(String value) {
            addCriterion("LAST_UPDATED_DAT not like", value, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatIn(List<String> values) {
            addCriterion("LAST_UPDATED_DAT in", values, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatNotIn(List<String> values) {
            addCriterion("LAST_UPDATED_DAT not in", values, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatBetween(String value1, String value2) {
            addCriterion("LAST_UPDATED_DAT between", value1, value2, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedDatNotBetween(String value1, String value2) {
            addCriterion("LAST_UPDATED_DAT not between", value1, value2, "lastUpdatedDat");
            return (Criteria) this;
        }

        public Criteria andAttribute1IsNull() {
            addCriterion("ATTRIBUTE1 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute1IsNotNull() {
            addCriterion("ATTRIBUTE1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute1EqualTo(String value) {
            addCriterion("ATTRIBUTE1 =", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotEqualTo(String value) {
            addCriterion("ATTRIBUTE1 <>", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1GreaterThan(String value) {
            addCriterion("ATTRIBUTE1 >", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1GreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE1 >=", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1LessThan(String value) {
            addCriterion("ATTRIBUTE1 <", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1LessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE1 <=", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1Like(String value) {
            addCriterion("ATTRIBUTE1 like", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotLike(String value) {
            addCriterion("ATTRIBUTE1 not like", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1In(List<String> values) {
            addCriterion("ATTRIBUTE1 in", values, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotIn(List<String> values) {
            addCriterion("ATTRIBUTE1 not in", values, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1Between(String value1, String value2) {
            addCriterion("ATTRIBUTE1 between", value1, value2, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE1 not between", value1, value2, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute2IsNull() {
            addCriterion("ATTRIBUTE2 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute2IsNotNull() {
            addCriterion("ATTRIBUTE2 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute2EqualTo(String value) {
            addCriterion("ATTRIBUTE2 =", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotEqualTo(String value) {
            addCriterion("ATTRIBUTE2 <>", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2GreaterThan(String value) {
            addCriterion("ATTRIBUTE2 >", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2GreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE2 >=", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2LessThan(String value) {
            addCriterion("ATTRIBUTE2 <", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2LessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE2 <=", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2Like(String value) {
            addCriterion("ATTRIBUTE2 like", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotLike(String value) {
            addCriterion("ATTRIBUTE2 not like", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2In(List<String> values) {
            addCriterion("ATTRIBUTE2 in", values, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotIn(List<String> values) {
            addCriterion("ATTRIBUTE2 not in", values, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2Between(String value1, String value2) {
            addCriterion("ATTRIBUTE2 between", value1, value2, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE2 not between", value1, value2, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute3IsNull() {
            addCriterion("ATTRIBUTE3 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute3IsNotNull() {
            addCriterion("ATTRIBUTE3 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute3EqualTo(String value) {
            addCriterion("ATTRIBUTE3 =", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotEqualTo(String value) {
            addCriterion("ATTRIBUTE3 <>", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3GreaterThan(String value) {
            addCriterion("ATTRIBUTE3 >", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3GreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE3 >=", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3LessThan(String value) {
            addCriterion("ATTRIBUTE3 <", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3LessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE3 <=", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3Like(String value) {
            addCriterion("ATTRIBUTE3 like", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotLike(String value) {
            addCriterion("ATTRIBUTE3 not like", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3In(List<String> values) {
            addCriterion("ATTRIBUTE3 in", values, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotIn(List<String> values) {
            addCriterion("ATTRIBUTE3 not in", values, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3Between(String value1, String value2) {
            addCriterion("ATTRIBUTE3 between", value1, value2, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE3 not between", value1, value2, "attribute3");
            return (Criteria) this;
        }

        public Criteria andIssyncIsNull() {
            addCriterion("ISSYNC is null");
            return (Criteria) this;
        }

        public Criteria andIssyncIsNotNull() {
            addCriterion("ISSYNC is not null");
            return (Criteria) this;
        }

        public Criteria andIssyncEqualTo(String value) {
            addCriterion("ISSYNC =", value, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncNotEqualTo(String value) {
            addCriterion("ISSYNC <>", value, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncGreaterThan(String value) {
            addCriterion("ISSYNC >", value, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncGreaterThanOrEqualTo(String value) {
            addCriterion("ISSYNC >=", value, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncLessThan(String value) {
            addCriterion("ISSYNC <", value, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncLessThanOrEqualTo(String value) {
            addCriterion("ISSYNC <=", value, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncLike(String value) {
            addCriterion("ISSYNC like", value, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncNotLike(String value) {
            addCriterion("ISSYNC not like", value, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncIn(List<String> values) {
            addCriterion("ISSYNC in", values, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncNotIn(List<String> values) {
            addCriterion("ISSYNC not in", values, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncBetween(String value1, String value2) {
            addCriterion("ISSYNC between", value1, value2, "issync");
            return (Criteria) this;
        }

        public Criteria andIssyncNotBetween(String value1, String value2) {
            addCriterion("ISSYNC not between", value1, value2, "issync");
            return (Criteria) this;
        }

        public Criteria andHsjeIsNull() {
            addCriterion("HSJE is null");
            return (Criteria) this;
        }

        public Criteria andHsjeIsNotNull() {
            addCriterion("HSJE is not null");
            return (Criteria) this;
        }

        public Criteria andHsjeEqualTo(String value) {
            addCriterion("HSJE =", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeNotEqualTo(String value) {
            addCriterion("HSJE <>", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeGreaterThan(String value) {
            addCriterion("HSJE >", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeGreaterThanOrEqualTo(String value) {
            addCriterion("HSJE >=", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeLessThan(String value) {
            addCriterion("HSJE <", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeLessThanOrEqualTo(String value) {
            addCriterion("HSJE <=", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeLike(String value) {
            addCriterion("HSJE like", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeNotLike(String value) {
            addCriterion("HSJE not like", value, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeIn(List<String> values) {
            addCriterion("HSJE in", values, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeNotIn(List<String> values) {
            addCriterion("HSJE not in", values, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeBetween(String value1, String value2) {
            addCriterion("HSJE between", value1, value2, "hsje");
            return (Criteria) this;
        }

        public Criteria andHsjeNotBetween(String value1, String value2) {
            addCriterion("HSJE not between", value1, value2, "hsje");
            return (Criteria) this;
        }

        public Criteria andWsjeIsNull() {
            addCriterion("WSJE is null");
            return (Criteria) this;
        }

        public Criteria andWsjeIsNotNull() {
            addCriterion("WSJE is not null");
            return (Criteria) this;
        }

        public Criteria andWsjeEqualTo(String value) {
            addCriterion("WSJE =", value, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeNotEqualTo(String value) {
            addCriterion("WSJE <>", value, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeGreaterThan(String value) {
            addCriterion("WSJE >", value, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeGreaterThanOrEqualTo(String value) {
            addCriterion("WSJE >=", value, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeLessThan(String value) {
            addCriterion("WSJE <", value, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeLessThanOrEqualTo(String value) {
            addCriterion("WSJE <=", value, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeLike(String value) {
            addCriterion("WSJE like", value, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeNotLike(String value) {
            addCriterion("WSJE not like", value, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeIn(List<String> values) {
            addCriterion("WSJE in", values, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeNotIn(List<String> values) {
            addCriterion("WSJE not in", values, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeBetween(String value1, String value2) {
            addCriterion("WSJE between", value1, value2, "wsje");
            return (Criteria) this;
        }

        public Criteria andWsjeNotBetween(String value1, String value2) {
            addCriterion("WSJE not between", value1, value2, "wsje");
            return (Criteria) this;
        }

        public Criteria andSeIsNull() {
            addCriterion("SE is null");
            return (Criteria) this;
        }

        public Criteria andSeIsNotNull() {
            addCriterion("SE is not null");
            return (Criteria) this;
        }

        public Criteria andSeEqualTo(String value) {
            addCriterion("SE =", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeNotEqualTo(String value) {
            addCriterion("SE <>", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeGreaterThan(String value) {
            addCriterion("SE >", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeGreaterThanOrEqualTo(String value) {
            addCriterion("SE >=", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeLessThan(String value) {
            addCriterion("SE <", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeLessThanOrEqualTo(String value) {
            addCriterion("SE <=", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeLike(String value) {
            addCriterion("SE like", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeNotLike(String value) {
            addCriterion("SE not like", value, "se");
            return (Criteria) this;
        }

        public Criteria andSeIn(List<String> values) {
            addCriterion("SE in", values, "se");
            return (Criteria) this;
        }

        public Criteria andSeNotIn(List<String> values) {
            addCriterion("SE not in", values, "se");
            return (Criteria) this;
        }

        public Criteria andSeBetween(String value1, String value2) {
            addCriterion("SE between", value1, value2, "se");
            return (Criteria) this;
        }

        public Criteria andSeNotBetween(String value1, String value2) {
            addCriterion("SE not between", value1, value2, "se");
            return (Criteria) this;
        }

        public Criteria andIsOilIsNull() {
            addCriterion("is_oil is null");
            return (Criteria) this;
        }

        public Criteria andIsOilIsNotNull() {
            addCriterion("is_oil is not null");
            return (Criteria) this;
        }

        public Criteria andIsOilEqualTo(String value) {
            addCriterion("is_oil =", value, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilNotEqualTo(String value) {
            addCriterion("is_oil <>", value, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilGreaterThan(String value) {
            addCriterion("is_oil >", value, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilGreaterThanOrEqualTo(String value) {
            addCriterion("is_oil >=", value, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilLessThan(String value) {
            addCriterion("is_oil <", value, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilLessThanOrEqualTo(String value) {
            addCriterion("is_oil <=", value, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilLike(String value) {
            addCriterion("is_oil like", value, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilNotLike(String value) {
            addCriterion("is_oil not like", value, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilIn(List<String> values) {
            addCriterion("is_oil in", values, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilNotIn(List<String> values) {
            addCriterion("is_oil not in", values, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilBetween(String value1, String value2) {
            addCriterion("is_oil between", value1, value2, "isOil");
            return (Criteria) this;
        }

        public Criteria andIsOilNotBetween(String value1, String value2) {
            addCriterion("is_oil not between", value1, value2, "isOil");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNull() {
            addCriterion("error_msg is null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNotNull() {
            addCriterion("error_msg is not null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgEqualTo(String value) {
            addCriterion("error_msg =", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotEqualTo(String value) {
            addCriterion("error_msg <>", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThan(String value) {
            addCriterion("error_msg >", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThanOrEqualTo(String value) {
            addCriterion("error_msg >=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThan(String value) {
            addCriterion("error_msg <", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThanOrEqualTo(String value) {
            addCriterion("error_msg <=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLike(String value) {
            addCriterion("error_msg like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotLike(String value) {
            addCriterion("error_msg not like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIn(List<String> values) {
            addCriterion("error_msg in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotIn(List<String> values) {
            addCriterion("error_msg not in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgBetween(String value1, String value2) {
            addCriterion("error_msg between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotBetween(String value1, String value2) {
            addCriterion("error_msg not between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andCustProvExIsNull() {
            addCriterion("CUST_PROV_EX is null");
            return (Criteria) this;
        }

        public Criteria andCustProvExIsNotNull() {
            addCriterion("CUST_PROV_EX is not null");
            return (Criteria) this;
        }

        public Criteria andCustProvExEqualTo(String value) {
            addCriterion("CUST_PROV_EX =", value, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExNotEqualTo(String value) {
            addCriterion("CUST_PROV_EX <>", value, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExGreaterThan(String value) {
            addCriterion("CUST_PROV_EX >", value, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_PROV_EX >=", value, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExLessThan(String value) {
            addCriterion("CUST_PROV_EX <", value, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExLessThanOrEqualTo(String value) {
            addCriterion("CUST_PROV_EX <=", value, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExLike(String value) {
            addCriterion("CUST_PROV_EX like", value, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExNotLike(String value) {
            addCriterion("CUST_PROV_EX not like", value, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExIn(List<String> values) {
            addCriterion("CUST_PROV_EX in", values, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExNotIn(List<String> values) {
            addCriterion("CUST_PROV_EX not in", values, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExBetween(String value1, String value2) {
            addCriterion("CUST_PROV_EX between", value1, value2, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustProvExNotBetween(String value1, String value2) {
            addCriterion("CUST_PROV_EX not between", value1, value2, "custProvEx");
            return (Criteria) this;
        }

        public Criteria andCustCityIsNull() {
            addCriterion("CUST_CITY is null");
            return (Criteria) this;
        }

        public Criteria andCustCityIsNotNull() {
            addCriterion("CUST_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andCustCityEqualTo(String value) {
            addCriterion("CUST_CITY =", value, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityNotEqualTo(String value) {
            addCriterion("CUST_CITY <>", value, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityGreaterThan(String value) {
            addCriterion("CUST_CITY >", value, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_CITY >=", value, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityLessThan(String value) {
            addCriterion("CUST_CITY <", value, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityLessThanOrEqualTo(String value) {
            addCriterion("CUST_CITY <=", value, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityLike(String value) {
            addCriterion("CUST_CITY like", value, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityNotLike(String value) {
            addCriterion("CUST_CITY not like", value, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityIn(List<String> values) {
            addCriterion("CUST_CITY in", values, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityNotIn(List<String> values) {
            addCriterion("CUST_CITY not in", values, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityBetween(String value1, String value2) {
            addCriterion("CUST_CITY between", value1, value2, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustCityNotBetween(String value1, String value2) {
            addCriterion("CUST_CITY not between", value1, value2, "custCity");
            return (Criteria) this;
        }

        public Criteria andCustDistrictIsNull() {
            addCriterion("CUST_DISTRICT is null");
            return (Criteria) this;
        }

        public Criteria andCustDistrictIsNotNull() {
            addCriterion("CUST_DISTRICT is not null");
            return (Criteria) this;
        }

        public Criteria andCustDistrictEqualTo(String value) {
            addCriterion("CUST_DISTRICT =", value, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictNotEqualTo(String value) {
            addCriterion("CUST_DISTRICT <>", value, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictGreaterThan(String value) {
            addCriterion("CUST_DISTRICT >", value, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_DISTRICT >=", value, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictLessThan(String value) {
            addCriterion("CUST_DISTRICT <", value, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictLessThanOrEqualTo(String value) {
            addCriterion("CUST_DISTRICT <=", value, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictLike(String value) {
            addCriterion("CUST_DISTRICT like", value, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictNotLike(String value) {
            addCriterion("CUST_DISTRICT not like", value, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictIn(List<String> values) {
            addCriterion("CUST_DISTRICT in", values, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictNotIn(List<String> values) {
            addCriterion("CUST_DISTRICT not in", values, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictBetween(String value1, String value2) {
            addCriterion("CUST_DISTRICT between", value1, value2, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustDistrictNotBetween(String value1, String value2) {
            addCriterion("CUST_DISTRICT not between", value1, value2, "custDistrict");
            return (Criteria) this;
        }

        public Criteria andCustAddrExIsNull() {
            addCriterion("CUST_ADDR_EX is null");
            return (Criteria) this;
        }

        public Criteria andCustAddrExIsNotNull() {
            addCriterion("CUST_ADDR_EX is not null");
            return (Criteria) this;
        }

        public Criteria andCustAddrExEqualTo(String value) {
            addCriterion("CUST_ADDR_EX =", value, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExNotEqualTo(String value) {
            addCriterion("CUST_ADDR_EX <>", value, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExGreaterThan(String value) {
            addCriterion("CUST_ADDR_EX >", value, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ADDR_EX >=", value, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExLessThan(String value) {
            addCriterion("CUST_ADDR_EX <", value, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExLessThanOrEqualTo(String value) {
            addCriterion("CUST_ADDR_EX <=", value, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExLike(String value) {
            addCriterion("CUST_ADDR_EX like", value, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExNotLike(String value) {
            addCriterion("CUST_ADDR_EX not like", value, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExIn(List<String> values) {
            addCriterion("CUST_ADDR_EX in", values, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExNotIn(List<String> values) {
            addCriterion("CUST_ADDR_EX not in", values, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExBetween(String value1, String value2) {
            addCriterion("CUST_ADDR_EX between", value1, value2, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andCustAddrExNotBetween(String value1, String value2) {
            addCriterion("CUST_ADDR_EX not between", value1, value2, "custAddrEx");
            return (Criteria) this;
        }

        public Criteria andExNameIsNull() {
            addCriterion("EX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExNameIsNotNull() {
            addCriterion("EX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExNameEqualTo(String value) {
            addCriterion("EX_NAME =", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameNotEqualTo(String value) {
            addCriterion("EX_NAME <>", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameGreaterThan(String value) {
            addCriterion("EX_NAME >", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameGreaterThanOrEqualTo(String value) {
            addCriterion("EX_NAME >=", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameLessThan(String value) {
            addCriterion("EX_NAME <", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameLessThanOrEqualTo(String value) {
            addCriterion("EX_NAME <=", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameLike(String value) {
            addCriterion("EX_NAME like", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameNotLike(String value) {
            addCriterion("EX_NAME not like", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameIn(List<String> values) {
            addCriterion("EX_NAME in", values, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameNotIn(List<String> values) {
            addCriterion("EX_NAME not in", values, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameBetween(String value1, String value2) {
            addCriterion("EX_NAME between", value1, value2, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameNotBetween(String value1, String value2) {
            addCriterion("EX_NAME not between", value1, value2, "exName");
            return (Criteria) this;
        }

        public Criteria andExTelephoneIsNull() {
            addCriterion("EX_TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andExTelephoneIsNotNull() {
            addCriterion("EX_TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andExTelephoneEqualTo(String value) {
            addCriterion("EX_TELEPHONE =", value, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneNotEqualTo(String value) {
            addCriterion("EX_TELEPHONE <>", value, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneGreaterThan(String value) {
            addCriterion("EX_TELEPHONE >", value, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("EX_TELEPHONE >=", value, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneLessThan(String value) {
            addCriterion("EX_TELEPHONE <", value, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneLessThanOrEqualTo(String value) {
            addCriterion("EX_TELEPHONE <=", value, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneLike(String value) {
            addCriterion("EX_TELEPHONE like", value, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneNotLike(String value) {
            addCriterion("EX_TELEPHONE not like", value, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneIn(List<String> values) {
            addCriterion("EX_TELEPHONE in", values, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneNotIn(List<String> values) {
            addCriterion("EX_TELEPHONE not in", values, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneBetween(String value1, String value2) {
            addCriterion("EX_TELEPHONE between", value1, value2, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andExTelephoneNotBetween(String value1, String value2) {
            addCriterion("EX_TELEPHONE not between", value1, value2, "exTelephone");
            return (Criteria) this;
        }

        public Criteria andAttributf1IsNull() {
            addCriterion("ATTRIBUTF1 is null");
            return (Criteria) this;
        }

        public Criteria andAttributf1IsNotNull() {
            addCriterion("ATTRIBUTF1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttributf1EqualTo(String value) {
            addCriterion("ATTRIBUTF1 =", value, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1NotEqualTo(String value) {
            addCriterion("ATTRIBUTF1 <>", value, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1GreaterThan(String value) {
            addCriterion("ATTRIBUTF1 >", value, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1GreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTF1 >=", value, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1LessThan(String value) {
            addCriterion("ATTRIBUTF1 <", value, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1LessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTF1 <=", value, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1Like(String value) {
            addCriterion("ATTRIBUTF1 like", value, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1NotLike(String value) {
            addCriterion("ATTRIBUTF1 not like", value, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1In(List<String> values) {
            addCriterion("ATTRIBUTF1 in", values, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1NotIn(List<String> values) {
            addCriterion("ATTRIBUTF1 not in", values, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1Between(String value1, String value2) {
            addCriterion("ATTRIBUTF1 between", value1, value2, "attributf1");
            return (Criteria) this;
        }

        public Criteria andAttributf1NotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTF1 not between", value1, value2, "attributf1");
            return (Criteria) this;
        }

        public Criteria andSendIsNull() {
            addCriterion("send is null");
            return (Criteria) this;
        }

        public Criteria andSendIsNotNull() {
            addCriterion("send is not null");
            return (Criteria) this;
        }

        public Criteria andSendEqualTo(String value) {
            addCriterion("send =", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendNotEqualTo(String value) {
            addCriterion("send <>", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendGreaterThan(String value) {
            addCriterion("send >", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendGreaterThanOrEqualTo(String value) {
            addCriterion("send >=", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendLessThan(String value) {
            addCriterion("send <", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendLessThanOrEqualTo(String value) {
            addCriterion("send <=", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendLike(String value) {
            addCriterion("send like", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendNotLike(String value) {
            addCriterion("send not like", value, "send");
            return (Criteria) this;
        }

        public Criteria andSendIn(List<String> values) {
            addCriterion("send in", values, "send");
            return (Criteria) this;
        }

        public Criteria andSendNotIn(List<String> values) {
            addCriterion("send not in", values, "send");
            return (Criteria) this;
        }

        public Criteria andSendBetween(String value1, String value2) {
            addCriterion("send between", value1, value2, "send");
            return (Criteria) this;
        }

        public Criteria andSendNotBetween(String value1, String value2) {
            addCriterion("send not between", value1, value2, "send");
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