package com.zhys.po

;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "InvoiceReVo", description = "开票返回结果")
public class InvoiceReVo {
  private String msg;
  
  @ApiModelProperty(value = "单据编码", name = "docNum")
  private String docNum;
  
  @ApiModelProperty(value = "金税发票代码", name = "goldtaxCode")
  private String goldtaxCode;
  
  @ApiModelProperty(value = "金税发票号码", name = "goldtaxNum")
  private String goldtaxNum;
  
  @ApiModelProperty(value = "金税开票日期 ", name = "billGdate")
  private String billGdate;
  
  public void setMsg(String msg) {
    this.msg = msg;
  }
  
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  
  public void setGoldtaxCode(String goldtaxCode) {
    this.goldtaxCode = goldtaxCode;
  }
  
  public void setGoldtaxNum(String goldtaxNum) {
    this.goldtaxNum = goldtaxNum;
  }
  
  public void setBillGdate(String billGdate) {
    this.billGdate = billGdate;
  }
  
  public void setZamountHswc(BigDecimal zamountHswc) {
    this.zamountHswc = zamountHswc;
  }
  
  public void setZamountWswc(BigDecimal zamountWswc) {
    this.zamountWswc = zamountWswc;
  }
  
  public void setZamountSewc(BigDecimal zamountSewc) {
    this.zamountSewc = zamountSewc;
  }
  
  public void setHsje(String hsje) {
    this.hsje = hsje;
  }
  
  public void setWsje(String wsje) {
    this.wsje = wsje;
  }
  
  public void setSe(String se) {
    this.se = se;
  }
  
  public void setPdfUrl(String pdfUrl) {
    this.pdfUrl = pdfUrl;
  }
  
  public String getMsg() {
    return this.msg;
  }
  
  public String getDocNum() {
    return this.docNum;
  }
  
  public String getGoldtaxCode() {
    return this.goldtaxCode;
  }
  
  public String getGoldtaxNum() {
    return this.goldtaxNum;
  }
  
  public String getBillGdate() {
    return this.billGdate;
  }
  
  @ApiModelProperty(value = "含税净金额（尾差）", name = "zamountHswc")
  private BigDecimal zamountHswc = BigDecimal.ZERO;
  
  public BigDecimal getZamountHswc() {
    return this.zamountHswc;
  }
  
  @ApiModelProperty(value = "不含税净金额（尾差）", name = "zamountWswc")
  private BigDecimal zamountWswc = BigDecimal.ZERO;
  
  public BigDecimal getZamountWswc() {
    return this.zamountWswc;
  }
  
  @ApiModelProperty(value = "净税额（尾差）", name = "zamountSewc")
  private BigDecimal zamountSewc = BigDecimal.ZERO;
  
  @ApiModelProperty(value = "含税金额", name = "hsje")
  private String hsje;
  
  @ApiModelProperty(value = "未税金额", name = "wsje")
  private String wsje;
  
  @ApiModelProperty(value = "税额", name = "se")
  private String se;
  
  private String pdfUrl;
  
  public BigDecimal getZamountSewc() {
    return this.zamountSewc;
  }
  
  public String getHsje() {
    return this.hsje;
  }
  
  public String getWsje() {
    return this.wsje;
  }
  
  public String getSe() {
    return this.se;
  }
  
  public String getPdfUrl() {
    return this.pdfUrl;
  }
}
