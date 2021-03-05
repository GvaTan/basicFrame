package com.zhys.po;

import com.lycheeframework.core.model.IPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        value = "OriginalHead",
        description = ""
)
public class OriginalHeadDto implements IPO {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            value = "单据编码",
            name = "docNum"
    )
    private String docNum;
    @ApiModelProperty(
            value = "单据日期",
            name = "docDate"
    )
    private String docDate;
    @ApiModelProperty(
            value = "单据状态 ",
            name = "docStatus"
    )
    private String docStatus;

    //开票客户信息
    @ApiModelProperty(
            value = "开票客户编码",
            name = "custIdBill"
    )
    private String custIdBill;
    @ApiModelProperty(
            value = "开票客户全称 ",
            name = "custName"
    )
    private String custName;
    @ApiModelProperty(
            value = "开票客户税号 ",
            name = "custTaxcode"
    )
    private String custTaxcode;
    @ApiModelProperty(
            value = "开票客户地址",
            name = "custAddress"
    )
    private String custAddress;
    @ApiModelProperty(
            value = "开票客户电话 ",
            name = "custTelephone"
    )
    private String custTelephone;
    @ApiModelProperty(
            value = "开票客户银行 ",
            name = "custBankname"
    )
    private String custBankname;
    @ApiModelProperty(
            value = "开票客户账号 ",
            name = "custBankaccount"
    )
    private String custBankaccount;
    @ApiModelProperty(
            value = "开票客户邮箱 ",
            name = "custEmail"
    )
    private String custEmail;
    @ApiModelProperty(
            value = "开票客户手机号",
            name = "custMobile"
    )
    private String custMobile;
    @ApiModelProperty(
            value = "发票类型",
            name = "invoiceType"
    )
    private String invoiceType;
    @ApiModelProperty(
            value = "红字发票",
            name = "invoiceRed"
    )
    private String invoiceRed;
    @ApiModelProperty(
            value = "金税发票代码",
            name = "goldtaxCode"
    )
    private String goldtaxCode;
    @ApiModelProperty(
            value = "金税发票号码",
            name = "goldtaxNum"
    )
    private String goldtaxNum;
    @ApiModelProperty(
            value = "提交开票日期 ",
            name = "billDate"
    )
    private String billDate;
    @ApiModelProperty(
            value = "提交作废日期",
            name = "cancelDate"
    )
    private String cancelDate;
    @ApiModelProperty(
            value = "金税开票日期 ",
            name = "billGdate"
    )
    private String billGdate;
    @ApiModelProperty(
            value = "金税作废日期",
            name = "cancelGdate"
    )
    private String cancelGdate;
    @ApiModelProperty(
            value = "红字申请原因",
            name = "invoiceRedReqm"
    )
    private String invoiceRedReqm;
    @ApiModelProperty(
            value = "红字申请单号",
            name = "invoiceRedXxbm"
    )
    private String invoiceRedXxbm;
    @ApiModelProperty(
            value = "蓝字发票代码",
            name = "invoiceRedFpdm"
    )
    private String invoiceRedFpdm;
    @ApiModelProperty(
            value = "蓝字发票号码",
            name = "invoiceRedFphm"
    )
    private String invoiceRedFphm;


    @ApiModelProperty(
            value = "是否同步",
            name = "issync"
    )
    private String issync;
    @ApiModelProperty(
            value = "开票备注 ",
            name = "billRemark"
    )
    private String billRemark;
    @ApiModelProperty(
            value = "创建者ID",
            name = "createdBy"
    )
    private String createdBy;
    @ApiModelProperty(
            value = "创建时间",
            name = "creationDate"
    )
    private String creationDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getCustIdBill() {
        return custIdBill;
    }

    public void setCustIdBill(String custIdBill) {
        this.custIdBill = custIdBill;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustTaxcode() {
        return custTaxcode;
    }

    public void setCustTaxcode(String custTaxcode) {
        this.custTaxcode = custTaxcode;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustTelephone() {
        return custTelephone;
    }

    public void setCustTelephone(String custTelephone) {
        this.custTelephone = custTelephone;
    }

    public String getCustBankname() {
        return custBankname;
    }

    public void setCustBankname(String custBankname) {
        this.custBankname = custBankname;
    }

    public String getCustBankaccount() {
        return custBankaccount;
    }

    public void setCustBankaccount(String custBankaccount) {
        this.custBankaccount = custBankaccount;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceRed() {
        return invoiceRed;
    }

    public void setInvoiceRed(String invoiceRed) {
        this.invoiceRed = invoiceRed;
    }

    public String getGoldtaxNum() {
        return goldtaxNum;
    }

    public void setGoldtaxNum(String goldtaxNum) {
        this.goldtaxNum = goldtaxNum;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getBillGdate() {
        return billGdate;
    }

    public void setBillGdate(String billGdate) {
        this.billGdate = billGdate;
    }

    public String getCancelGdate() {
        return cancelGdate;
    }

    public void setCancelGdate(String cancelGdate) {
        this.cancelGdate = cancelGdate;
    }

    public String getInvoiceRedReqm() {
        return invoiceRedReqm;
    }

    public void setInvoiceRedReqm(String invoiceRedReqm) {
        this.invoiceRedReqm = invoiceRedReqm;
    }

    public String getInvoiceRedXxbm() {
        return invoiceRedXxbm;
    }

    public void setInvoiceRedXxbm(String invoiceRedXxbm) {
        this.invoiceRedXxbm = invoiceRedXxbm;
    }

    public String getInvoiceRedFpdm() {
        return invoiceRedFpdm;
    }

    public void setInvoiceRedFpdm(String invoiceRedFpdm) {
        this.invoiceRedFpdm = invoiceRedFpdm;
    }

    public String getInvoiceRedFphm() {
        return invoiceRedFphm;
    }

    public void setInvoiceRedFphm(String invoiceRedFphm) {
        this.invoiceRedFphm = invoiceRedFphm;
    }

    public String getIssync() {
        return issync;
    }

    public void setIssync(String issync) {
        this.issync = issync;
    }

    public String getBillRemark() {
        return billRemark;
    }

    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getGoldtaxCode() {
        return goldtaxCode;
    }

    public void setGoldtaxCode(String goldtaxCode) {
        this.goldtaxCode = goldtaxCode;
    }
}