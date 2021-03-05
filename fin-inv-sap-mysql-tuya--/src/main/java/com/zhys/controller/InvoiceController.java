package com.zhys.controller;

import com.alibaba.fastjson.JSONObject;
import com.sap.conn.jco.JCoException;
import com.zhys.result.ResponseResult;
import com.zhys.service.InvoiceHeadService;
import com.zhys.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"invoice-api"})
@ResponseResult
@CrossOrigin
@Slf4j
public class InvoiceController {

    @Autowired
    private InvoiceHeadService invoiceHeadService;

    @GetMapping(value = {"/unlock/{taxcode}"}, produces = {"application/json"})
    @ResponseBody
    public Object unlock(@PathVariable String taxcode) {
        try {
            return this.invoiceHeadService.unlock(taxcode);
        } catch (JCoException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    /**
    *通过组织id和单据状态 得到无效的发票
    *@param orgId
    *@return
    *@throws JCoException
    */
    @GetMapping(value="/getInvalidInvoice/{orgId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getInvalidInvoice(@PathVariable  String orgId) throws JCoException {
        return invoiceHeadService.revoke(orgId,"3","1");
    }


    @GetMapping(value = {"/unlock/{taxcode}"}, produces = {"application/json"})
    @ResponseBody
    public Object unlock(@PathVariable String taxcode) {
        try {
            return this.invoiceHeadService.unlock(taxcode);
        } catch (JCoException e) {
            e.printStackTrace();
            return null;
        }
    }


}
