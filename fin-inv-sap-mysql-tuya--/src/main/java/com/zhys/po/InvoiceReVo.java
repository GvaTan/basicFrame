package com.zhys.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@ApiModel(value="InvoiceReVo",description="开票返回结果")
@Getter
@Setter
public class InvoiceReVo {
    private String msg;

    /**
     * 单据编码
     */
    @ApiModelProperty(value="单据编码",name="docNum")
    private String docNum;

//    /**
//     * 单据日期
//     */
//    @ApiModelProperty(value="单据日期",name="docDate")
//    private String docDate;

//    /**
//     * 单据状态
//     */
//    @ApiModelProperty(value="单据状态 ",name="docStatus")
//    private String docStatus;


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

//    /**
//     * 提交开票日期
//     */
//    @ApiModelProperty(value="提交开票日期 ",name="billDate")
//    private String billDate;

    /**
     * 金税开票日期
     */
    @ApiModelProperty(value="金税开票日期 ",name="billGdate")
    private String billGdate;

    /**
     * 含税净金额（尾差）
     */
    @ApiModelProperty(value="含税净金额（尾差）",name="zamountHswc")
    private BigDecimal zamountHswc=BigDecimal.ZERO;

    /**
     * 不含税净金额（尾差）
     */
    @ApiModelProperty(value="不含税净金额（尾差）",name="zamountWswc")
    private BigDecimal zamountWswc=BigDecimal.ZERO;

    /**
     * 净税额（尾差）
     */
    @ApiModelProperty(value="净税额（尾差）",name="zamountSewc")
    private BigDecimal zamountSewc=BigDecimal.ZERO;

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


}
