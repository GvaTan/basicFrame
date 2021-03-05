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
import java.math.BigDecimal;

/**
 * 
 * @author 11734
 *
 */
@Getter
@Setter
@ApiModel(value="InvoiceSplitLineDto",description="")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceSplitLineDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 单据编码
	 */
	 @ApiModelProperty(value="单据编码",name="docNum")
	 @XmlElement(name = "docNum")
	private String docNum;

	/**
	 * 行号
	 */
	 @ApiModelProperty(value="行号",name="docLine")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="行号")
	 @XmlElement(name = "docLine")
	private String docLine;

	/**
	 * 开票产品名称 
	 */
	 @ApiModelProperty(value="开票产品名称 ",name="itemName")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="开票产品名称")
	 @XmlElement(name = "itemName")
	private String itemName;

	/**
	 * 开票规格型号 
	 */
	 @ApiModelProperty(value="开票规格型号 ",name="itemSpec")
	 @XmlElement(name = "itemSpec")
	private String itemSpec;

	/**
	 * 开票计量单位 
	 */
	 @ApiModelProperty(value="开票计量单位 ",name="unitName")
	 @XmlElement(name = "unitName")
	private String unitName;

	/**
	 * 税收分类编码
	 */
	 @ApiModelProperty(value="税收分类编码",name="taxCatecode")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="税收分类编码")
	 @XmlElement(name = "taxCatecode")
	private String taxCatecode;

	/**
	 * 数量
	 */
	 @ApiModelProperty(value="数量",name="quantity")
	 @XmlElement(name = "quantity")
	private BigDecimal quantity;

	/**
	 * 税率 
	 */
	 @ApiModelProperty(value="税率 ",name="taxRate")
	 @EntityValidate(allowEmpty=false)
	 @ExcelCell(name="税率")
	 @XmlElement(name = "taxRate")
	private BigDecimal taxRate;


	/**
	 * 含税净金额（净值）
	 */
	 @ApiModelProperty(value="含税净金额（净值）",name="zamountHsj")
	 @XmlElement(name = "zamountHsj")
	private BigDecimal zamountHsj;

	/**
	 * 不含税净金额（净值）
	 */
	 @ApiModelProperty(value="不含税净金额（净值）",name="zamountWsj")
	 @XmlElement(name = "zamountWsj")
	private BigDecimal zamountWsj;

	/**
	 * 净税额（净值）
	 */
	 @ApiModelProperty(value="净税额（净值）",name="zamountSej")
	 @XmlElement(name = "zamountSej")
	private BigDecimal zamountSej;


	/**
	 * 含税净单价
	 */
	 @ApiModelProperty(value="含税净单价",name="zpriceHsj")
	 @XmlElement(name = "zpriceHsj")
	private BigDecimal zpriceHsj;

	/**
	 * 不含税净单价
	 */
	 @ApiModelProperty(value="不含税净单价",name="zpriceWsj")
	 @XmlElement(name = "zpriceWsj")
	private BigDecimal zpriceWsj;


}