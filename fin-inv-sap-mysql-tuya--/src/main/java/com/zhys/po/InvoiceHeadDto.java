/**
 * Copyright 2017-2018 1173499611@qq.com
 * All rights reserved.
 * 
 * @project
 * @author 11734
 * @version 1.0
 * @date 2018-12-24
 */
package com.zhys.po;

import com.zhys.EntityValidate.EntityValidate;
import com.zhys.excel.ExcelCell;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 
 * @author 11734
 *
 */
@Getter
@Setter
@ApiModel(value="InvoiceHeadDto",description="")
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceHeadDto  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 单据编码
	 */
	 @ApiModelProperty(value="单据编码",name="docNum")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="单据编码")
	 @XmlElement(name = "docNum")
	private String docNum;

	/**
	 * 开票客户全称 
	 */
	 @ApiModelProperty(value="开票客户全称 ",name="custName")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="开票客户全称")
	 @XmlElement(name = "custName")
	private String custName;

	/**
	 * 开票客户税号 
	 */
	 @ApiModelProperty(value="开票客户税号 ",name="custTaxcode")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="开票客户税号")
	 @XmlElement(name = "custTaxcode")
	private String custTaxcode;

	/**
	 * 开票客户地址
	 */
	 @ApiModelProperty(value="开票客户地址",name="custAddress")
	 @XmlElement(name = "custAddress")
	private String custAddress;

	/**
	 * 开票客户电话 
	 */
	 @ApiModelProperty(value="开票客户电话 ",name="custTelephone")
	 @XmlElement(name = "custTelephone")
	private String custTelephone;

	/**
	 * 开票客户银行 
	 */
	 @ApiModelProperty(value="开票客户银行 ",name="custBankname")
	 @XmlElement(name = "custBankname")
	private String custBankname;

	/**
	 * 开票客户账号 
	 */
	 @ApiModelProperty(value="开票客户账号 ",name="custBankaccount")
	 @XmlElement(name = "custBankaccount")
	private String custBankaccount;


	/**
	 * 发票类型
	 */
	 @ApiModelProperty(value="发票类型",name="invoiceType")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="发票类型")
	 @XmlElement(name = "invoiceType")
	private String invoiceType;


	/**
	 * 基准方式
	 */
	 @ApiModelProperty(value="基准方式",name="invoiceBase")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="基准方式")
	 @XmlElement(name = "invoiceBase")
	private String invoiceBase;


	/**
	 * 是否清单开票 
	 */
	 @ApiModelProperty(value="是否清单开票 ",name="invoiceList")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="是否清单开票")
	 @XmlElement(name = "invoiceList")
	private String invoiceList;

	/**
	 * 红字发票
	 */
	 @ApiModelProperty(value="红字发票",name="invoiceRed")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="红字发票")
	 @XmlElement(name = "invoiceRed")
	private String invoiceRed;


	/**
	 * 开票人姓名
	 */
	 @ApiModelProperty(value="开票人姓名",name="userName")
	 @XmlElement(name = "userName")
	private String userName;

	/**
	 * 复核人姓名 
	 */
	 @ApiModelProperty(value="复核人姓名 ",name="checkName")
	 @XmlElement(name = "checkName")
	private String checkName;

	/**
	 * 收款人姓名 
	 */
	 @ApiModelProperty(value="收款人姓名 ",name="payeeName")
	 @XmlElement(name = "payeeName")
	private String payeeName;

	/**
	 * 红字申请原因
	 */
	 @ApiModelProperty(value="红字申请原因",name="invoiceRedReqm")
	 @XmlElement(name = "invoiceRedReqm")
	private String invoiceRedReqm;

	/**
	 * 红字申请单号
	 */
	 @ApiModelProperty(value="红字申请单号",name="invoiceRedXxbm")
	 @XmlElement(name = "invoiceRedXxbm")
	private String invoiceRedXxbm;

	/**
	 * 蓝字发票代码
	 */
	 @ApiModelProperty(value="蓝字发票代码",name="invoiceRedFpdm")
	 @XmlElement(name = "invoiceRedFpdm")
	private String invoiceRedFpdm;

	/**
	 * 蓝字发票号码
	 */
	 @ApiModelProperty(value="蓝字发票号码",name="invoiceRedFphm")
	 @XmlElement(name = "invoiceRedFphm")
	private String invoiceRedFphm;

	/**
	 * 开票备注 
	 */
	 @ApiModelProperty(value="开票备注 ",name="billRemark")
	 @XmlElement(name = "billRemark")
	private String billRemark;


	@ApiModelProperty(value="分组明细",name="invoiceSplitLines")
	private List<InvoiceSplitLineDto> invoiceSplitLines;

	@ApiModelProperty(value="含税金额",name="hsje")
	@XmlElement(name = "hsje")
	private String hsje;

	/**
	 *
	 */
	@ApiModelProperty(value="未税金额",name="wsje")
	@XmlElement(name = "wsje")
	private String wsje;

	/**
	 *
	 */
	@ApiModelProperty(value="税额",name="se")
	@XmlElement(name = "se")
	private String se;

	@ApiModelProperty(value="成品油 1：成品油 0：其他",name="isOil")
	@XmlElement(name = "isOil")
	private String isOil;

	@ApiModelProperty(value="是否重开 1：重开 0：不重开",name="reopen")
	@EntityValidate(allowEmpty=false)
	@ExcelCell(name="是否重开")
	@XmlElement(name = "reopen")
	private String reopen;


	private String custProvEx;

	private String custCity;

	private String custDistrict;

	private String custAddrEx;

	private String exName;

	private String exTelephone;

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

}