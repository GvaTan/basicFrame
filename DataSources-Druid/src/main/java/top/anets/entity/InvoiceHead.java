package top.anets.entity;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceHead {
    private String docNum;

    private String mandt;

    private String docDate;

    private String docStatus;

    private String orgId;

    private String orgName;

    private String orgTaxcode;

    private String orgMachine;

    private String orgAddress;

    private String orgTelephone;

    private String orgBankname;

    private String orgBankaccount;

    private BigDecimal orgTaxexceed;

    private String orgControltax;

    private String custIdAr;

    private String custNameAr;

    private String custIdBill;

    private String custNameBill;

    private String custName;

    private String custTaxcode;

    private String custAddress;

    private String custTelephone;

    private String custBankname;

    private String custBankaccount;

    private String custEmail;

    private String custMobile;

    private String invoiceType;

    private String invoiceTypes;

    private String invoiceBase;

    private String invoiceWay;

    private BigDecimal mergeAmt;

    private BigDecimal mergeQty;

    private String taxRate;

    private String discountType;

    private BigDecimal discountRate;

    private String mergeGift;

    private String invoiceList;

    private String invoiceRed;

    private String mergefType;

    private String mergesType;

    private String goldtaxCode;

    private String goldtaxNum;

    private String userId;

    private String userName;

    private String checkName;

    private String payeeName;

    private String billDate;

    private String cancelDate;

    private String billGdate;

    private String cancelGdate;

    private String invoiceRedReqm;

    private String invoiceRedXxbm;

    private String invoiceRedFpdm;

    private String invoiceRedFphm;

    private String billRemark;

    private BigDecimal zamountHswc;

    private BigDecimal zamountWswc;

    private BigDecimal zamountSewc;

    private String createdBy;

    private Date creationDate;

    private String lastUpdatedBy;

    private String lastUpdatedDat;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String issync;

    private String hsje;

    private String wsje;

    private String se;

    private String isOil;

    private String errorMsg;

    private String custProvEx;

    private String custCity;

    private String custDistrict;

    private String custAddrEx;

    private String exName;

    private String exTelephone;

    private String attributf1;

    private String send;

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum == null ? null : docNum.trim();
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt == null ? null : mandt.trim();
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate == null ? null : docDate.trim();
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus == null ? null : docStatus.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgTaxcode() {
        return orgTaxcode;
    }

    public void setOrgTaxcode(String orgTaxcode) {
        this.orgTaxcode = orgTaxcode == null ? null : orgTaxcode.trim();
    }

    public String getOrgMachine() {
        return orgMachine;
    }

    public void setOrgMachine(String orgMachine) {
        this.orgMachine = orgMachine == null ? null : orgMachine.trim();
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    public String getOrgTelephone() {
        return orgTelephone;
    }

    public void setOrgTelephone(String orgTelephone) {
        this.orgTelephone = orgTelephone == null ? null : orgTelephone.trim();
    }

    public String getOrgBankname() {
        return orgBankname;
    }

    public void setOrgBankname(String orgBankname) {
        this.orgBankname = orgBankname == null ? null : orgBankname.trim();
    }

    public String getOrgBankaccount() {
        return orgBankaccount;
    }

    public void setOrgBankaccount(String orgBankaccount) {
        this.orgBankaccount = orgBankaccount == null ? null : orgBankaccount.trim();
    }

    public BigDecimal getOrgTaxexceed() {
        return orgTaxexceed;
    }

    public void setOrgTaxexceed(BigDecimal orgTaxexceed) {
        this.orgTaxexceed = orgTaxexceed;
    }

    public String getOrgControltax() {
        return orgControltax;
    }

    public void setOrgControltax(String orgControltax) {
        this.orgControltax = orgControltax == null ? null : orgControltax.trim();
    }

    public String getCustIdAr() {
        return custIdAr;
    }

    public void setCustIdAr(String custIdAr) {
        this.custIdAr = custIdAr == null ? null : custIdAr.trim();
    }

    public String getCustNameAr() {
        return custNameAr;
    }

    public void setCustNameAr(String custNameAr) {
        this.custNameAr = custNameAr == null ? null : custNameAr.trim();
    }

    public String getCustIdBill() {
        return custIdBill;
    }

    public void setCustIdBill(String custIdBill) {
        this.custIdBill = custIdBill == null ? null : custIdBill.trim();
    }

    public String getCustNameBill() {
        return custNameBill;
    }

    public void setCustNameBill(String custNameBill) {
        this.custNameBill = custNameBill == null ? null : custNameBill.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCustTaxcode() {
        return custTaxcode;
    }

    public void setCustTaxcode(String custTaxcode) {
        this.custTaxcode = custTaxcode == null ? null : custTaxcode.trim();
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress == null ? null : custAddress.trim();
    }

    public String getCustTelephone() {
        return custTelephone;
    }

    public void setCustTelephone(String custTelephone) {
        this.custTelephone = custTelephone == null ? null : custTelephone.trim();
    }

    public String getCustBankname() {
        return custBankname;
    }

    public void setCustBankname(String custBankname) {
        this.custBankname = custBankname == null ? null : custBankname.trim();
    }

    public String getCustBankaccount() {
        return custBankaccount;
    }

    public void setCustBankaccount(String custBankaccount) {
        this.custBankaccount = custBankaccount == null ? null : custBankaccount.trim();
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail == null ? null : custEmail.trim();
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile == null ? null : custMobile.trim();
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(String invoiceTypes) {
        this.invoiceTypes = invoiceTypes == null ? null : invoiceTypes.trim();
    }

    public String getInvoiceBase() {
        return invoiceBase;
    }

    public void setInvoiceBase(String invoiceBase) {
        this.invoiceBase = invoiceBase == null ? null : invoiceBase.trim();
    }

    public String getInvoiceWay() {
        return invoiceWay;
    }

    public void setInvoiceWay(String invoiceWay) {
        this.invoiceWay = invoiceWay == null ? null : invoiceWay.trim();
    }

    public BigDecimal getMergeAmt() {
        return mergeAmt;
    }

    public void setMergeAmt(BigDecimal mergeAmt) {
        this.mergeAmt = mergeAmt;
    }

    public BigDecimal getMergeQty() {
        return mergeQty;
    }

    public void setMergeQty(BigDecimal mergeQty) {
        this.mergeQty = mergeQty;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate == null ? null : taxRate.trim();
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType == null ? null : discountType.trim();
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getMergeGift() {
        return mergeGift;
    }

    public void setMergeGift(String mergeGift) {
        this.mergeGift = mergeGift == null ? null : mergeGift.trim();
    }

    public String getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(String invoiceList) {
        this.invoiceList = invoiceList == null ? null : invoiceList.trim();
    }

    public String getInvoiceRed() {
        return invoiceRed;
    }

    public void setInvoiceRed(String invoiceRed) {
        this.invoiceRed = invoiceRed == null ? null : invoiceRed.trim();
    }

    public String getMergefType() {
        return mergefType;
    }

    public void setMergefType(String mergefType) {
        this.mergefType = mergefType == null ? null : mergefType.trim();
    }

    public String getMergesType() {
        return mergesType;
    }

    public void setMergesType(String mergesType) {
        this.mergesType = mergesType == null ? null : mergesType.trim();
    }

    public String getGoldtaxCode() {
        return goldtaxCode;
    }

    public void setGoldtaxCode(String goldtaxCode) {
        this.goldtaxCode = goldtaxCode == null ? null : goldtaxCode.trim();
    }

    public String getGoldtaxNum() {
        return goldtaxNum;
    }

    public void setGoldtaxNum(String goldtaxNum) {
        this.goldtaxNum = goldtaxNum == null ? null : goldtaxNum.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName == null ? null : checkName.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate == null ? null : billDate.trim();
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate == null ? null : cancelDate.trim();
    }

    public String getBillGdate() {
        return billGdate;
    }

    public void setBillGdate(String billGdate) {
        this.billGdate = billGdate == null ? null : billGdate.trim();
    }

    public String getCancelGdate() {
        return cancelGdate;
    }

    public void setCancelGdate(String cancelGdate) {
        this.cancelGdate = cancelGdate == null ? null : cancelGdate.trim();
    }

    public String getInvoiceRedReqm() {
        return invoiceRedReqm;
    }

    public void setInvoiceRedReqm(String invoiceRedReqm) {
        this.invoiceRedReqm = invoiceRedReqm == null ? null : invoiceRedReqm.trim();
    }

    public String getInvoiceRedXxbm() {
        return invoiceRedXxbm;
    }

    public void setInvoiceRedXxbm(String invoiceRedXxbm) {
        this.invoiceRedXxbm = invoiceRedXxbm == null ? null : invoiceRedXxbm.trim();
    }

    public String getInvoiceRedFpdm() {
        return invoiceRedFpdm;
    }

    public void setInvoiceRedFpdm(String invoiceRedFpdm) {
        this.invoiceRedFpdm = invoiceRedFpdm == null ? null : invoiceRedFpdm.trim();
    }

    public String getInvoiceRedFphm() {
        return invoiceRedFphm;
    }

    public void setInvoiceRedFphm(String invoiceRedFphm) {
        this.invoiceRedFphm = invoiceRedFphm == null ? null : invoiceRedFphm.trim();
    }

    public String getBillRemark() {
        return billRemark;
    }

    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark == null ? null : billRemark.trim();
    }

    public BigDecimal getZamountHswc() {
        return zamountHswc;
    }

    public void setZamountHswc(BigDecimal zamountHswc) {
        this.zamountHswc = zamountHswc;
    }

    public BigDecimal getZamountWswc() {
        return zamountWswc;
    }

    public void setZamountWswc(BigDecimal zamountWswc) {
        this.zamountWswc = zamountWswc;
    }

    public BigDecimal getZamountSewc() {
        return zamountSewc;
    }

    public void setZamountSewc(BigDecimal zamountSewc) {
        this.zamountSewc = zamountSewc;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy == null ? null : lastUpdatedBy.trim();
    }

    public String getLastUpdatedDat() {
        return lastUpdatedDat;
    }

    public void setLastUpdatedDat(String lastUpdatedDat) {
        this.lastUpdatedDat = lastUpdatedDat == null ? null : lastUpdatedDat.trim();
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1 == null ? null : attribute1.trim();
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2 == null ? null : attribute2.trim();
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3 == null ? null : attribute3.trim();
    }

    public String getIssync() {
        return issync;
    }

    public void setIssync(String issync) {
        this.issync = issync == null ? null : issync.trim();
    }

    public String getHsje() {
        return hsje;
    }

    public void setHsje(String hsje) {
        this.hsje = hsje == null ? null : hsje.trim();
    }

    public String getWsje() {
        return wsje;
    }

    public void setWsje(String wsje) {
        this.wsje = wsje == null ? null : wsje.trim();
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se == null ? null : se.trim();
    }

    public String getIsOil() {
        return isOil;
    }

    public void setIsOil(String isOil) {
        this.isOil = isOil == null ? null : isOil.trim();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public String getCustProvEx() {
        return custProvEx;
    }

    public void setCustProvEx(String custProvEx) {
        this.custProvEx = custProvEx == null ? null : custProvEx.trim();
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity == null ? null : custCity.trim();
    }

    public String getCustDistrict() {
        return custDistrict;
    }

    public void setCustDistrict(String custDistrict) {
        this.custDistrict = custDistrict == null ? null : custDistrict.trim();
    }

    public String getCustAddrEx() {
        return custAddrEx;
    }

    public void setCustAddrEx(String custAddrEx) {
        this.custAddrEx = custAddrEx == null ? null : custAddrEx.trim();
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName == null ? null : exName.trim();
    }

    public String getExTelephone() {
        return exTelephone;
    }

    public void setExTelephone(String exTelephone) {
        this.exTelephone = exTelephone == null ? null : exTelephone.trim();
    }

    public String getAttributf1() {
        return attributf1;
    }

    public void setAttributf1(String attributf1) {
        this.attributf1 = attributf1 == null ? null : attributf1.trim();
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send == null ? null : send.trim();
    }
}