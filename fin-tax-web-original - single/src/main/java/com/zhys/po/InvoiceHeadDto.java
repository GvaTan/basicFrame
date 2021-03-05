package com.zhys.po

;

//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
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

@ApiModel(value = "InvoiceHeadDto", description = "")
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class InvoiceHeadDto {
  private static final long serialVersionUID = 1L;
  
  @ApiModelProperty(value = "单据编码", name = "docNum")
  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "单据编码")
  @XmlElement(name = "docNum")
  private String docNum;
  
  @ApiModelProperty(value = "开票客户全称 ", name = "custName")
  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "开票客户全称")
  @XmlElement(name = "custName")
  private String custName;
  
  @ApiModelProperty(value = "开票客户税号 ", name = "custTaxcode")
//  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "开票客户税号")
  @XmlElement(name = "custTaxcode")
  private String custTaxcode;
  
  @ApiModelProperty(value = "开票客户地址", name = "custAddress")
  @XmlElement(name = "custAddress")
  private String custAddress;
  
  @ApiModelProperty(value = "开票客户电话 ", name = "custTelephone")
  @XmlElement(name = "custTelephone")
  private String custTelephone;
  
  @ApiModelProperty(value = "开票客户银行 ", name = "custBankname")
  @XmlElement(name = "custBankname")
  private String custBankname;
  
  @ApiModelProperty(value = "开票客户账号 ", name = "custBankaccount")
  @XmlElement(name = "custBankaccount")
  private String custBankaccount;
  
  @ApiModelProperty(value = "发票类型", name = "invoiceType")
//  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "发票类型")
  @XmlElement(name = "invoiceType")
  private String invoiceType;
  
  @ApiModelProperty(value = "基准方式", name = "invoiceBase")
//  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "基准方式")
  @XmlElement(name = "invoiceBase")
  private String invoiceBase;
  
  @ApiModelProperty(value = "是否清单开票 ", name = "invoiceList")
//  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "是否清单开票")
  @XmlElement(name = "invoiceList")
  private String invoiceList;
  
  @ApiModelProperty(value = "红字发票", name = "invoiceRed")
//  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "红字发票")
  @XmlElement(name = "invoiceRed")
  private String invoiceRed;
  
  @ApiModelProperty(value = "开票人姓名", name = "userName")
  @XmlElement(name = "userName")
  private String userName;
  
  @ApiModelProperty(value = "复核人姓名 ", name = "checkName")
  @XmlElement(name = "checkName")
  private String checkName;
  
  @ApiModelProperty(value = "收款人姓名 ", name = "payeeName")
  @XmlElement(name = "payeeName")
  private String payeeName;
  
  @ApiModelProperty(value = "红字申请原因", name = "invoiceRedReqm")
  @XmlElement(name = "invoiceRedReqm")
  private String invoiceRedReqm;
  
  @ApiModelProperty(value = "红字申请单号", name = "invoiceRedXxbm")
  @XmlElement(name = "invoiceRedXxbm")
  private String invoiceRedXxbm;
  
  @ApiModelProperty(value = "蓝字发票代码", name = "invoiceRedFpdm")
  @XmlElement(name = "invoiceRedFpdm")
  private String invoiceRedFpdm;
  
  @ApiModelProperty(value = "蓝字发票号码", name = "invoiceRedFphm")
  @XmlElement(name = "invoiceRedFphm")
  private String invoiceRedFphm;
  
  @ApiModelProperty(value = "开票备注 ", name = "billRemark")
  @XmlElement(name = "billRemark")
  private String billRemark;
  
  @ApiModelProperty(value = "分组明细", name = "invoiceSplitLines")
//  @JacksonXmlElementWrapper(localName = "details")
//  @JacksonXmlProperty(localName = "detail")
  private List<InvoiceSplitLineDto> invoiceSplitLines;
  
  @ApiModelProperty(value = "含税金额", name = "hsje")
  @XmlElement(name = "hsje")
  private String hsje;
  
  @ApiModelProperty(value = "未税金额", name = "wsje")
  @XmlElement(name = "wsje")
  private String wsje;
  
  @ApiModelProperty(value = "税额", name = "se")
  @XmlElement(name = "se")
  private String se;
  
  @ApiModelProperty(value = "成品油 1：成品油 0：其他", name = "isOil")
  @XmlElement(name = "isOil")
  private String isOil;
  
  @ApiModelProperty(value = "是否重开 1：重开 0：不重开", name = "reopen")
//  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "是否重开")
  @XmlElement(name = "reopen")
  private String reopen;
  
  private String custProvEx;
  
  private String custCity;
  
  private String custDistrict;
  
  private String custAddrEx;
  
  private String exName;
  
  private String exTelephone;
  
  @ApiModelProperty(value = "受票组织名称", name = "orgName")
  private String orgName;
  
  @ApiModelProperty(value = "开票公司税号", name = "orgTaxcode")
  private String orgTaxcode;
  
  @ApiModelProperty(value = "税控机号", name = "orgMachine")
  private String orgMachine;
  
  @ApiModelProperty(value = "开票公司地址", name = "orgAddress")
  private String orgAddress;
  
  @ApiModelProperty(value = "开票公司电话", name = "orgTelephone")
  private String orgTelephone;
  
  @ApiModelProperty(value = "开票公司银行", name = "orgBankname")
  private String orgBankname;
  
  @ApiModelProperty(value = "开票公司账号 ", name = "orgBankaccount")
  private String orgBankaccount;
  

}
