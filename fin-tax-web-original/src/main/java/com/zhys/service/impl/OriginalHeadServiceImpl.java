package com.zhys.service.impl;

import com.lycheeframework.core.cmp.kit.EasyPage;
import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.base.BaseApiService;
import com.zhys.invoice.po.InvoiceHead;
import com.zhys.invoice.po.InvoiceMergefLine;
import com.zhys.invoice.po.InvoiceSplitLine;
import com.zhys.service.InvoiceHeadService;
import com.zhys.service.OriginalHeadService;
import com.zhys.service.SQLManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class OriginalHeadServiceImpl extends BaseApiService implements OriginalHeadService {
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody InvoiceHead invoiceHead){

		if(invoiceHead.getDocNum() != null ){//修改
			manager.update("invoice_head.update", invoiceHead);
			manager.delete("invoice_mergef_line.del", invoiceHead);
//			manager.delete("invoice_merges_line.del", invoiceHead);
			manager.delete("invoice_split_line.del", invoiceHead);
		}else{//插入
			manager.insert("invoice_head.create", invoiceHead);
		}
		

		List<InvoiceMergefLine> mergefLines = invoiceHead.getInvoiceMergefLines();
		if(mergefLines!=null&&mergefLines.size()>0) {
			for(InvoiceMergefLine type:mergefLines) {
				type.setDocNum(invoiceHead.getDocNum());
				manager.insert("invoice_mergef_line.create", type);
			}
		}

//		List<InvoiceMergesLine> mergesLines = invoiceHead.getInvoiceMergesLines();
//		if(mergesLines!=null&&mergesLines.size()>0) {
//			for(InvoiceMergesLine img:mergesLines) {
//				img.setDocNum(invoiceHead.getDocNum());
//				manager.insert("invoice_merges_line.create", img);
//			}
//		}

		List<InvoiceSplitLine> splitLines = invoiceHead.getInvoiceSplitLines();
		if(splitLines!=null&&splitLines.size()>0) {
			for(InvoiceSplitLine news:splitLines) {
				news.setDocNum(invoiceHead.getDocNum());
				manager.insert("invoice_split_line.create", news);
			}
		}
		return 1;
		
	}
	
	
	@Override
	public InvoiceHead queryByEntity(@RequestBody InvoiceHead invoiceHead){
	             return (InvoiceHead)manager.query("invoice_head.query", invoiceHead);
	}
	@Override
	public List<InvoiceHead> queryList(@RequestBody InvoiceHead invoiceHead){
		List<InvoiceHead> list =  (List<InvoiceHead>)manager.list("invoice_head.queryList", invoiceHead);
		return list;
	}

	@Override
    public Pages<List<InvoiceHead>> pages(@RequestBody InvoiceHead invoiceHead, Integer pageSize, Integer pageNum){
        EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    
	    return (Pages<List<InvoiceHead>>) manager.pages("original_head.page", invoiceHead, page);
	
	}

	@Override
	public Integer changeDelStateById(@RequestBody InvoiceHead invoiceHead) {
		return manager.update("invoice_head.changeDelStateById", invoiceHead);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody InvoiceHead invoiceHeadPojo) {
		return manager.update("invoice_head.changeDelStateByIds", invoiceHeadPojo);
	}


	@Override
	public Integer delByIds(InvoiceHead invoiceHead) {
		return manager.delete("original_head.delByIds",invoiceHead);
	}
}