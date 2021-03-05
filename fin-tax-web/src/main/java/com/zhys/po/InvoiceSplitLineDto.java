package com.zhys.po

;

import com.zhys.EntityValidate.EntityValidate;
import com.zhys.excel.ExcelCell;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@Getter
@Setter
@ApiModel(value = "InvoiceSplitLineDto", description = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceSplitLineDto {
  private static final long serialVersionUID = 1L;
  
  @ApiModelProperty(value = "单据编码", name = "docNum")
  @XmlElement(name = "docNum")
  private String docNum;
  
  @ApiModelProperty(value = "行号", name = "docLine")
//  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "行号")
  @XmlElement(name = "docLine")
  private String docLine;
  
  @ApiModelProperty(value = "开票产品名称 ", name = "itemName")
  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "开票产品名称")
  @XmlElement(name = "itemName")
  private String itemName;
  
  @ApiModelProperty(value = "开票规格型号 ", name = "itemSpec")
  @XmlElement(name = "itemSpec")
  private String itemSpec;
  
  @ApiModelProperty(value = "开票计量单位 ", name = "unitName")
  @XmlElement(name = "unitName")
  private String unitName;
  
  @ApiModelProperty(value = "税收分类编码", name = "taxCatecode")
  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "税收分类编码")
  @XmlElement(name = "taxCatecode")
  private String taxCatecode;
  
  @ApiModelProperty(value = "数量", name = "quantity")
  @XmlElement(name = "quantity")
  @EntityValidate(allowEmpty = false)
  private BigDecimal quantity;
  
  @ApiModelProperty(value = "税率 ", name = "taxRate")
  @EntityValidate(allowEmpty = false)
  @ExcelCell(name = "税率")
  @XmlElement(name = "taxRate")
  private BigDecimal taxRate;
  
  @ApiModelProperty(value = "含税净金额（净值）", name = "zamountHsj")
  @EntityValidate(allowEmpty = false)
  @XmlElement(name = "zamountHsj")
  private BigDecimal zamountHsj;
  
  @ApiModelProperty(value = "不含税净金额（净值）", name = "zamountWsj")
  @XmlElement(name = "zamountWsj")
  private BigDecimal zamountWsj;
  
  @ApiModelProperty(value = "净税额（净值）", name = "zamountSej")
  @XmlElement(name = "zamountSej")
  private BigDecimal zamountSej;
  
  @ApiModelProperty(value = "含税净单价", name = "zpriceHsj")
  @XmlElement(name = "zpriceHsj")
  private BigDecimal zpriceHsj;
  
  @ApiModelProperty(value = "不含税净单价", name = "zpriceWsj")
  @XmlElement(name = "zpriceWsj")
  @EntityValidate(allowEmpty = false)
  private BigDecimal zpriceWsj;


  @ApiModelProperty(
          value = "行折含税金额",
          name = "zamountHzhs"
  )
  private BigDecimal zamountHzhs;
  @ApiModelProperty(
          value = "行折未税金额",
          name = "zamountHzws"
  )
  private BigDecimal zamountHzws;
  @ApiModelProperty(
          value = "行折税额",
          name = "zamountHzse"
  )
  private BigDecimal zamountHzse;

  @ApiModelProperty(
          value = "含税原金额（原值）",
          name = "zamountHsy"
  )
  private BigDecimal zamountHsy;
  @ApiModelProperty(
          value = "不含税原金额（原值）",
          name = "zamountWsy"
  )
  private BigDecimal zamountWsy;
  @ApiModelProperty(
          value = "原税额（原值）",
          name = "zamountSey"
  )
  private BigDecimal zamountSey;

  @ApiModelProperty(
          value = "含税原单价 ",
          name = "zpriceHsy"
  )
  private BigDecimal zpriceHsy;
  @ApiModelProperty(
          value = "不含税原单价",
          name = "zpriceWsy"
  )
  private BigDecimal zpriceWsy;
  

}
