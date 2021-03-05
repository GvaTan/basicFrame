package com.zhys.po;

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
public class Fyxm {
    @XmlAttribute
    public String count ;

//    @XmlElementWrapper(name="groups")
    @XmlElement(name="group")
    public List<Group> groups ;
}
