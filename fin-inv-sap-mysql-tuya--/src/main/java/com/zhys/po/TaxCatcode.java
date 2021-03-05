package com.zhys.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TaxCatcode {
    private String MAKTX;
    private BigDecimal TAX_RATE;
    private String MATNR;
    private String ZMATNR_TAX;
    private String MANDT;
    private String ZDATE;
    private String FLAG;

}
