package com.zhys.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class COMMON_FPKJ_XMXX {
    @XmlElement
    @JSONField(name="FPHXZ")
    public String FPHXZ ;
    @XmlElement
    @JSONField(name="SPBM")
    public String SPBM ;
    @XmlElement
    @JSONField(name="ZXBM")
    public String ZXBM ;
    @XmlElement
    @JSONField(name="YHZCBS")
    public String YHZCBS ;
    @XmlElement
    @JSONField(name="LSLBS")
    public String LSLBS ;
    @XmlElement
    @JSONField(name="ZZSTSGL")
    public String ZZSTSGL ;
    @XmlElement
    @JSONField(name="XMMC")
    public String XMMC ;
    @XmlElement
    @JSONField(name="GGXH")
    public String GGXH ;
    @XmlElement
    @JSONField(name="DW")
    public String DW ;
    @XmlElement
    @JSONField(name="XMSL")
    public String XMSL ;
    @XmlElement
    @JSONField(name="XMDJ")
    public String XMDJ ;
    @XmlElement
    @JSONField(name="XMJE")
    public String XMJE ;
    @XmlElement
    @JSONField(name="SL")
    public String SL ;
    @XmlElement
    @JSONField(name="SE")
    public String SE ;
    @XmlElement
    @JSONField(name="BY1")
    public String BY1 ;
    @JSONField(name="BY2")
    public String BY2 ;
    @JSONField(name="BY3")
    public String BY3 ;
    @JSONField(name="BY4")
    public String BY4 ;
    @JSONField(name="BY5")
    public String BY5 ;


//    public static void main(String[] args) {
//        REQUEST_COMMON_FPKJ request_common_fpkj = new REQUEST_COMMON_FPKJ();
//
//        Common_fpkj_xmxxs  common_fpkj_xmxxs = new Common_fpkj_xmxxs();
//        List<COMMON_FPKJ_XMXX>  common_fpkj_xmxxes = new ArrayList<>();
//
//        request_common_fpkj.COMMON_FPKJ_XMXXS=common_fpkj_xmxxs;
//        COMMON_FPKJ_XMXX common_fpkj_xmxx = new COMMON_FPKJ_XMXX();
//        common_fpkj_xmxx.FPHXZ = "1";
//        common_fpkj_xmxxes.add(common_fpkj_xmxx);
//        common_fpkj_xmxxs.COMMON_FPKJ_XMXX = common_fpkj_xmxxes;
//        System.out.println(JSONObject.toJSONString(request_common_fpkj, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect));
//    }
}
