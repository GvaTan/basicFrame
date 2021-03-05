package com.zhys.po;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VInvoiceHead {
    public String is_oil;
    public String MANDT;
    public String ORG_ID;
    public String ORG_TAXCODE;
    public String ORG_NAME;
    public String ORG_ADDRESS;
    public String ORG_TELEPHONE;
    public String ORG_BANKNAME;
    public String ORG_BANKACCOUNT;
    public String ORG_MACHINE;
    public String DOC_NUM;
    public String GROUP_NUM;
    public String INVOICE_TYPE;
    public String INVOICE_TYPES;
    public String ORG_CONTROLTAX;
    public String INVOICE_BASE;
    public String INVOICE_WAY;
    public String DISCOUNT_TYPE;
    public String DISCOUNT_RATE;
    public String INVOICE_LIST;
    public String INVOICE_LIST_QZ;
    public String INVOICE_RED;
    public String INVOICE_RED_REQM;
    public String INVOICE_RED_XXBM;
    public String INVOICE_RED_FPDM;
    public String INVOICE_RED_FPHM;
    public String CUST_ID_BILL;
    public String CUST_NAME;
    public String CUST_TAXCODE;
    public String CUST_ADDRESS;
    public String CUST_TELEPHONE;
    public String CUST_BANKNAME;
    public String CUST_BANKACCOUNT;
    public String CUST_EMAIL;
    public String CUST_MOBILE;
    public String BILL_REMARK;
    public String USER_NAME;
    public String CHECK_NAME;
    public String PAYEE_NAME;
    public String DOC_DATE;
    public String BILL_GDATE;
    public String kpy;
    public String bmbbh;
    public String sslb;
    public String GROUP_STATUS;
    public String GOLDTAX_NUM;
    public String GOLDTAX_CODE;
    public String TAX_RATE;
    public String ylzd1;
    public String ylzd2;
    public String ylzd3;
    public String ylzd4;
    public String ylzd5;
    public String zkhs;
    public String ZAMOUNT_HSY;
    public String ZAMOUNT_WSY;
    public String ZAMOUNT_SEY;
    public String ZAMOUNT_HSJ;
    public String ZAMOUNT_WSJ;
    public String ZAMOUNT_SEJ;
    public String HSJEC;
    public String WSJEC;
    public String SEC;
    public String dayin;
    public String JIANYAN;
    public String E_INV_ID;
    public String sjs;
    public String HSJEC0;
    public String WSJEC0;
    public String ZAMOUNT_HSWC;
    public String ZAMOUNT_WSWC;
    public String ZAMOUNT_SEWC;
    public List<VInvoiceDetail> detailList;

    private String billGDateStart;

    private String billGDateEnd;

    private String CUST_PROV_EX;

    private String CUST_CITY;

    private String CUST_DISTRICT;

    private String CUST_ADDR_EX;

    private String EX_NAME;

    private String EX_TELEPHONE;

    private String EXID;

    private String EXNUM;

    private String exbz;

    private String HSJE;

    private String WSJE;

    private String SE;

    private String fphmStart;

    private String fphmEnd;

    private String fpzt;



}
