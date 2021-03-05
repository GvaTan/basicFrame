package com.zhys.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lycheeframework.core.model.IPO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class SendMsg implements IPO {
    private String gfmc;//购方名称
    private String content;//发送内容
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sendtime; //发送时间
    private String success; //1：成功 2：失败
    private String sendtype;//1：邮箱 2：短信
    private String sendno;//手机号/邮箱号
}
