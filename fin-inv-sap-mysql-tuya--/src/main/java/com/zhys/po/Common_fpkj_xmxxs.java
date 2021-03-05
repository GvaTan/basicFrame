package com.zhys.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;


@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Common_fpkj_xmxxs {
    @JSONField(name="COMMON_FPKJ_XMXX")
    @XmlElement
    public List<COMMON_FPKJ_XMXX> COMMON_FPKJ_XMXX ;
    @XmlAttribute
    private String size;
}
