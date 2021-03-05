package com.zhys.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceSplitLine;

@RequestMapping("invoiceSplitLine")
public interface InvoiceSplitLineService extends BaseService<InvoiceSplitLine,InvoiceSplitLine>{
    /**
     * 取消提交
     * @param invoiceSplitLine
     * @return
     */
    @GetMapping("/cancelCommit")
    Integer cancelCommit(@RequestBody InvoiceSplitLine invoiceSplitLine);

    /**
     * 整单作废
     * @param invoiceSplitLine
     * @return
     */
    @GetMapping("/abolishAll")
    Integer abolishAll(@RequestBody InvoiceSplitLine invoiceSplitLine);

    /**
     * 分组作废
     * @param invoiceSplitLine
     * @return
     */
    @GetMapping("/abolishOne")
    Integer abolishOne(@RequestBody InvoiceSplitLine invoiceSplitLine);

    /**
     * 取消整单作废
     * @param invoiceSplitLine
     * @return
     */
    @GetMapping("/cancelAbolishAll")
    Integer cancelAbolishAll(@RequestBody InvoiceSplitLine invoiceSplitLine);

    /**
     * 取消分组作废
     * @param invoiceSplitLine
     * @return
     */
    @GetMapping("/cancelAbolishOne")
    Integer cancelAbolishOne(@RequestBody InvoiceSplitLine invoiceSplitLine);
}