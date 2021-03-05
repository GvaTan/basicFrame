package com.zhys.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "REQUEST_COMMON_FPKJ")
@XmlAccessorType(XmlAccessType.FIELD)
public class REQUEST_COMMON_FPKJ {
    @XmlElement
    @JSONField(name="FPQQLSH")
    public String FPQQLSH ;
    @XmlElement
    @JSONField(name="BMB_BBH")
    public String BMB_BBH ;
    @XmlElement
    @JSONField(name="ZSFS")
    public String ZSFS ;
    @XmlElement
    @JSONField(name="KPLX")
    public String KPLX ;
    @XmlElement
    @JSONField(name="XSF_NSRSBH")
    public String XSF_NSRSBH ;
    @XmlElement
    @JSONField(name="XSF_MC")
    public String XSF_MC ;
    @XmlElement
    @JSONField(name="XSF_DZDH")
    public String XSF_DZDH ;
    @XmlElement
    @JSONField(name="XSF_YHZH")
    public String XSF_YHZH ;
    @XmlElement
    @JSONField(name="GMF_NSRSBH")
    public String GMF_NSRSBH ;
    @XmlElement
    @JSONField(name="GMF_MC")
    public String GMF_MC ;
    @XmlElement
    @JSONField(name="GMF_DZDH")
    public String GMF_DZDH ;
    @XmlElement
    @JSONField(name="GMF_YHZH")
    public String GMF_YHZH ;
    @XmlElement
    @JSONField(name="GMF_SJH")
    public String GMF_SJH ;
    @XmlElement
    @JSONField(name="GMF_DZYX")
    public String GMF_DZYX ;
    @XmlElement
    @JSONField(name="FPT_ZH")
    public String FPT_ZH ;
    @XmlElement
    @JSONField(name="WX_OPENID")
    public String WX_OPENID ;
    @XmlElement
    @JSONField(name="KPR")
    public String KPR ;
    @XmlElement
    @JSONField(name="SKR")
    public String SKR ;
    @XmlElement
    @JSONField(name="FHR")
    public String FHR ;
    @JSONField(name="YFP_DM")
    public String YFP_DM ;
    @JSONField(name="YFP_HM")
    public String YFP_HM ;
    @XmlElement
    @JSONField(name="JSHJ")
    public String JSHJ ;
    @XmlElement
    @JSONField(name="HJJE")
    public String HJJE ;
    @XmlElement
    @JSONField(name="HJSE")
    public String HJSE ;
    @XmlElement
    @JSONField(name="KCE")
    public String KCE ;
    @XmlElement
    @JSONField(name="BZ")
    public String BZ ;
    @XmlElement
    @JSONField(name="HYLX")
    public String HYLX ;
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
    @JSONField(name="BY6")
    public String BY6 ;
    @JSONField(name="BY7")
    public String BY7 ;
    @JSONField(name="BY8")
    public String BY8 ;
    @JSONField(name="BY9")
    public String BY9 ;
    @JSONField(name="BY10")
    public String BY10 ;
    @JSONField(name="COMMON_FPKJ_XMXXS")
    @XmlElement
    public Common_fpkj_xmxxs COMMON_FPKJ_XMXXS ;
    @XmlElement
    public String YFPDM ;
    @XmlElement
    public String YFPHM ;
    @XmlElement
    public String TZDBH ;
    @XmlElement
    public String SKM ;
    @XmlElement
    public String JYM ;
    @XmlElement
    public String KPRQ ;
    @XmlElement
    public String SKP_NO ;
    @XmlElement
    public String TSPZ;
}
