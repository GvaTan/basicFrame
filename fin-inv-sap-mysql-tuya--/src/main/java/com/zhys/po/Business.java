package com.zhys.po;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter

@XmlRootElement(name = "business")
@XmlAccessorType(XmlAccessType.FIELD)
public class Business {
    @XmlAttribute
    public String id ;

     @XmlAttribute
    public String comment ;
    @XmlElement(name="body")
    public Body body;




}
