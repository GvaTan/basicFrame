/**
 * Copyright 2017-2020 1173499611@qq.com
 * All rights reserved.
 * 
 * @project
 * @author 11734
 * @version 1.0
 * @date 2020-06-02
 */
package com.zhys.po;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import com.lycheeframework.core.model.IPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author 11734
 *
 */
@Getter
@Setter
@ApiModel(value="CustomerMsg",description="")
public class CustomerMsg implements IPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  主键 id
	 */
	 @ApiModelProperty(value=" 主键 id",name="id")
	private Long id;

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
	 * 
	 */
	 @ApiModelProperty(value="",name="createTime")
	private Date createTime;


}