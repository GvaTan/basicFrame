package com.zhys.po;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {
    @XmlAttribute
    public String yylxdm ;
    @XmlElement
    public String kpzdbs ;
    @XmlElement
    public String fplxdm ;
    public String fpqqlsh ;
    public String kplx ;
    public String tspz ;

    public String xhdwsbh ;
    public String xhdwmc ;
    public String xhdwdzdh ;
    public String xhdwyhzh ;
    public String ghdwsbh ;
    public String ghdwmc ;
    public String ghdwdzdh ;
    public String ghdwyhzh ;
    public String qdbz ;
    public String zsfs ;
    public Fyxm fyxm ;
    public String hjje ;
    public String hjse ;
    public String jshj ;
    public String kce ;
    public String bz ;
    public String skr ;
    public String fhr ;

    public String kpr ;
    public String tzdbh ;
    public String yfpdm ;
    public String yfphm ;

    public String qmcs ;
}
