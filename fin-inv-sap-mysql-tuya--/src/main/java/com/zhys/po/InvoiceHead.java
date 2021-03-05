package com.zhys.po;


import com.lycheeframework.core.model.IPO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class InvoiceHead  implements IPO {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户端
     */
    @ApiModelProperty(value="客户端",name="mandt")
    private String mandt;

    /**
     * 单据编码
     */
    @ApiModelProperty(value="单据编码",name="docNum")
    private String docNum;

    /**
     * 单据日期
     */
    @ApiModelProperty(value="单据日期",name="docDate")
    private String docDate;

    /**
     * 单据状态
     */
    @ApiModelProperty(value="单据状态 ",name="docStatus")
    private String docStatus;

    /**
     * 受票组织id
     */
    @ApiModelProperty(value="受票组织id",name="orgId")
    private String orgId;

    /**
     * 受票组织名称
     */
    @ApiModelProperty(value="受票组织名称",name="orgName")
    private String orgName;

    /**
     * 开票公司税号
     */
    @ApiModelProperty(value="开票公司税号",name="orgTaxcode")
    private String orgTaxcode;

    /**
     * 税控机号
     */
    @ApiModelProperty(value="税控机号",name="orgMachine")
    private String orgMachine;

    /**
     * 开票公司地址
     */
    @ApiModelProperty(value="开票公司地址",name="orgAddress")
    private String orgAddress;

    /**
     * 开票公司电话
     */
    @ApiModelProperty(value="开票公司电话",name="orgTelephone")
    private String orgTelephone;

    /**
     * 开票公司银行
     */
    @ApiModelProperty(value="开票公司银行",name="orgBankname")
    private String orgBankname;

    /**
     * 开票公司账号
     */
    @ApiModelProperty(value="开票公司账号 ",name="orgBankaccount")
    private String orgBankaccount;

    /**
     * 开票公司发票限额
     */
    @ApiModelProperty(value="开票公司发票限额 ",name="orgTaxexceed")
    private BigDecimal orgTaxexceed;

    /**
     * 开票公司选项
     */
    @ApiModelProperty(value="开票公司选项",name="orgControltax")
    private String orgControltax;

    /**
     * 应收客户编码
     */
    @ApiModelProperty(value="应收客户编码",name="custIdAr")
    private String custIdAr;

    /**
     * 应收客户名称
     */
    @ApiModelProperty(value="应收客户名称",name="custNameAr")
    private String custNameAr;

    /**
     * 开票客户编码
     */
    @ApiModelProperty(value="开票客户编码",name="custIdBill")
    private String custIdBill;

    /**
     * 开票客户名称
     */
    @ApiModelProperty(value="开票客户名称 ",name="custNameBill")
    private String custNameBill;

    /**
     * 开票客户全称
     */
    @ApiModelProperty(value="开票客户全称 ",name="custName")
    private String custName;

    /**
     * 开票客户税号
     */
    @ApiModelProperty(value="开票客户税号 ",name="custTaxcode")
    private String custTaxcode;

    /**
     * 开票客户地址
     */
    @ApiModelProperty(value="开票客户地址",name="custAddress")
    private String custAddress;

    /**
     * 开票客户电话
     */
    @ApiModelProperty(value="开票客户电话 ",name="custTelephone")
    private String custTelephone;

    /**
     * 开票客户银行
     */
    @ApiModelProperty(value="开票客户银行 ",name="custBankname")
    private String custBankname;

    /**
     * 开票客户账号
     */
    @ApiModelProperty(value="开票客户账号 ",name="custBankaccount")
    private String custBankaccount;

    /**
     * 开票客户邮箱
     */
    @ApiModelProperty(value="开票客户邮箱 ",name="custEmail")
    private String custEmail;

    /**
     * 开票客户手机号
     */
    @ApiModelProperty(value="开票客户手机号",name="custMobile")
    private String custMobile;

    /**
     * 发票类型
     */
    @ApiModelProperty(value="发票类型",name="invoiceType")
    private String invoiceType;

    /**
     * 发票子类型
     */
    @ApiModelProperty(value="发票子类型 ",name="invoiceTypes")
    private String invoiceTypes;

    /**
     * 基准方式
     */
    @ApiModelProperty(value="基准方式",name="invoiceBase")
    private String invoiceBase;

    /**
     * 开票方式
     */
    @ApiModelProperty(value="开票方式",name="invoiceWay")
    private String invoiceWay;

    /**
     * 拼单金额（万元）
     */
    @ApiModelProperty(value="拼单金额（万元）",name="mergeAmt")
    private BigDecimal mergeAmt;

    /**
     * 拼单数量
     */
    @ApiModelProperty(value="拼单数量",name="mergeQty")
    private BigDecimal mergeQty;

    /**
     * 税率
     */
    @ApiModelProperty(value="税率",name="taxRate")
    private String taxRate;

    /**
     * 折扣方式
     */
    @ApiModelProperty(value="折扣方式",name="discountType")
    private String discountType;

    /**
     * 折扣率
     */
    @ApiModelProperty(value="折扣率 ",name="discountRate")
    private BigDecimal discountRate;

    /**
     * 是否合并赠品行
     */
    @ApiModelProperty(value="是否合并赠品行 ",name="mergeGift")
    private String mergeGift;

    /**
     * 是否清单开票
     */
    @ApiModelProperty(value="是否清单开票 ",name="invoiceList")
    private String invoiceList;

    /**
     * 红字发票
     */
    @ApiModelProperty(value="红字发票",name="invoiceRed")
    private String invoiceRed;

    /**
     * 一次合并方式  相同物料合并：1
     * 相同物料+单价合并：2
     * 不合并：3
     */
    @ApiModelProperty(value="一次合并方式 ",name="mergefType")
    private String mergefType;

    /**
     * 二次合并方式
     */
    @ApiModelProperty(value="二次合并方式",name="mergesType")
    private String mergesType;

    /**
     * 金税发票代码
     */
    @ApiModelProperty(value="金税发票代码",name="goldtaxCode")
    private String goldtaxCode;

    /**
     * 金税发票号码
     */
    @ApiModelProperty(value="金税发票号码",name="goldtaxNum")
    private String goldtaxNum;

    /**
     * 开票人编码
     */
    @ApiModelProperty(value="开票人编码",name="userId")
    private String userId;

    /**
     * 开票人姓名
     */
    @ApiModelProperty(value="开票人姓名",name="userName")
    private String userName;

    /**
     * 复核人姓名
     */
    @ApiModelProperty(value="复核人姓名 ",name="checkName")
    private String checkName;

    /**
     * 收款人姓名
     */
    @ApiModelProperty(value="收款人姓名 ",name="payeeName")
    private String payeeName;

    /**
     * 提交开票日期
     */
    @ApiModelProperty(value="提交开票日期 ",name="billDate")
    private String billDate;

    /**
     * 提交作废日期
     */
    @ApiModelProperty(value="提交作废日期",name="cancelDate")
    private String cancelDate;

    /**
     * 金税开票日期
     */
    @ApiModelProperty(value="金税开票日期 ",name="billGdate")
    private String billGdate;

    /**
     * 金税作废日期
     */
    @ApiModelProperty(value="金税作废日期",name="cancelGdate")
    private String cancelGdate;

    /**
     * 红字申请原因
     */
    @ApiModelProperty(value="红字申请原因",name="invoiceRedReqm")
    private String invoiceRedReqm;

    /**
     * 红字申请单号
     */
    @ApiModelProperty(value="红字申请单号",name="invoiceRedXxbm")
    private String invoiceRedXxbm;

    /**
     * 蓝字发票代码
     */
    @ApiModelProperty(value="蓝字发票代码",name="invoiceRedFpdm")
    private String invoiceRedFpdm;

    /**
     * 蓝字发票号码
     */
    @ApiModelProperty(value="蓝字发票号码",name="invoiceRedFphm")
    private String invoiceRedFphm;

    /**
     * 开票备注
     */
    @ApiModelProperty(value="开票备注 ",name="billRemark")
    private String billRemark;

    /**
     * 含税净金额（尾差）
     */
    @ApiModelProperty(value="含税净金额（尾差）",name="zamountHswc")
    private BigDecimal zamountHswc;

    /**
     * 不含税净金额（尾差）
     */
    @ApiModelProperty(value="不含税净金额（尾差）",name="zamountWswc")
    private BigDecimal zamountWswc;

    /**
     * 净税额（尾差）
     */
    @ApiModelProperty(value="净税额（尾差）",name="zamountSewc")
    private BigDecimal zamountSewc;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value="创建者ID",name="createdBy")
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="creationDate")
    private String creationDate;

    /**
     * 最后修改者ID
     */
    @ApiModelProperty(value="最后修改者ID",name="lastUpdatedBy")
    private String lastUpdatedBy;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value="最后修改时间 ",name="lastUpdatedDat")
    private String lastUpdatedDat;

    /**
     * 自定义字段1
     */
    @ApiModelProperty(value="自定义字段1",name="attribute1")
    private String attribute1;

    /**
     * 自定义字段2
     */
    @ApiModelProperty(value="自定义字段2",name="attribute2")
    private String attribute2;

    /**
     * 自定义字段3
     */
    @ApiModelProperty(value="自定义字段3",name="attribute3")
    private String attribute3;

    /**
     * 是否同步
     */
    @ApiModelProperty(value="是否同步",name="issync")
    private String issync;

    /**
     * 含税净金额（净值）
     */
    @ApiModelProperty(value="含税净金额（净值）",name="zamountHsj")
    private BigDecimal zamountHsj=BigDecimal.ZERO;

    /**
     * 不含税净金额（净值）
     */
    @ApiModelProperty(value="不含税净金额（净值）",name="zamountWsj")
    private BigDecimal zamountWsj=BigDecimal.ZERO;

    /**
     * 净税额（净值）
     */
    @ApiModelProperty(value="净税额（净值）",name="zamountSej")
    private BigDecimal zamountSej=BigDecimal.ZERO;



    @ApiModelProperty(value="单据起始日期",name="docDateStart")
    private String docDateStart;

    @ApiModelProperty(value="单据截至日期",name="docDateEnd")
    private String docDateEnd;



    @ApiModelProperty(value="分组明细",name="invoiceSplitLines")
    private List<InvoiceSplitLine> invoiceSplitLines;

    /**
     * 原始单据号 逗号分隔
     */
    @ApiModelProperty(value="原始单据号",name="originalNos")
    private String originalNos;

    @ApiModelProperty(value="含税金额",name="hsje")
    private String hsje;

    /**
     *
     */
    @ApiModelProperty(value="未税金额",name="wsje")
    private String wsje;

    /**
     *
     */
    @ApiModelProperty(value="税额",name="se")
    private String se;

    @ApiModelProperty(value="成品油 1：成品油 0：其他",name="isOil")
    private String isOil;

    @ApiModelProperty(value="是否重开 1：重开 0：不重开",name="reopen")
    private String reopen;

    private String custProvEx;

    private String custCity;

    private String custDistrict;

    private String custAddrEx;

    private String exName;

    private String exTelephone;

    private String attributf1;

    private String send;







}
