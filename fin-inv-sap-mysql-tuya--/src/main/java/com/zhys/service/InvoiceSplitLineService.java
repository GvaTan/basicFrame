package com.zhys.service;

import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceSplitLine;
import com.zhys.po.Express;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InvoiceSplitLineService extends BaseService<InvoiceSplitLine,InvoiceSplitLine> {
    /**
     * 取消提交
     * @param invoiceSplitLine
     * @return
     */
    Object cancelCommit(@RequestBody InvoiceSplitLine invoiceSplitLine);

    /**
     * 整单作废
     * @param invoiceSplitLine
     * @return
     */
    Object abolishAll(@RequestBody InvoiceSplitLine invoiceSplitLine);



    /**
     * 取消整单作废
     * @param invoiceSplitLine
     * @return
     */
    Object cancelAbolishAll(@RequestBody InvoiceSplitLine invoiceSplitLine);

    Object timerTask(String docNum);

    Object hc_express(List<Express> list);

    Object upload(MultipartFile file);

    /**
     * 恢复单据
     * @param doc_num
     * @param group_num
     * @return
     */
    Object hf(String doc_num, String group_num);
}