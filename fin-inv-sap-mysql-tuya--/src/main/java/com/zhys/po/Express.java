package com.zhys.po;

import com.lycheeframework.core.model.IPO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Express implements IPO {
    private String exid;
    private String exnum;
    private String fphm;
    private String fpdm;
}
