/**
 * Copyright 2017-2020 1173499611@qq.com
 * All rights reserved.
 * 
 * @project
 * @author 11734
 * @version 1.0
 * @date 2020-02-18
 */
package com.zhys.po;

import com.lycheeframework.core.model.IPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author 11734
 *
 */
@Getter
@Setter
@ApiModel(value="PrintMsg",description="")
public class PrintMsg implements IPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * $pro.annotation
	 */
	 @ApiModelProperty(value="$pro.annotation",name="CODE")
	private String CODE;

	/**
	 * $pro.annotation
	 */
	 @ApiModelProperty(value="$pro.annotation",name="NUM")
	private String NUM;

	/**
	 * $pro.annotation
	 */
	 @ApiModelProperty(value="$pro.annotation",name="SKM")
	private String SKM;

	/**
	 * $pro.annotation
	 */
	 @ApiModelProperty(value="$pro.annotation",name="JYM")
	private String JYM;

	/**
	 * $pro.annotation
	 */
	 @ApiModelProperty(value="$pro.annotation",name="MSG")
	private String MSG;

	private String id;

	private String KPRQ;

//	private String JQBH;



}