/**
 * Copyright 2017-2020 1173499611@qq.com
 * All rights reserved.
 * 
 * @project
 * @author 11734
 * @version 1.0
 * @date 2020-03-30
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
@ApiModel(value="RedXxbm",description="")
public class RedXxbm implements IPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="num")
	private String num;

	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="rq")
	private String rq;

	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="xfmc")
	private String xfmc;

	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="xfsh")
	private String xfsh;

	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="gfmc")
	private String gfmc;

	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="gfsh")
	private String gfsh;

	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="je")
	private String je;

	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="se")
	private String se;

	/**
	 * 
	 */
	 @ApiModelProperty(value="",name="zt")
	private String zt;


}