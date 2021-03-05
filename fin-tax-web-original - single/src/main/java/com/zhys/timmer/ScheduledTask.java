package com.zhys.timmer;

import com.alibaba.fastjson.JSON;
import com.lycheeframework.core.model.IPO;
import com.zhys.invoice.po.InvoiceHead;
import com.zhys.invoice.po.InvoiceSplitLine;
import com.zhys.redis.RedisUtils;
import com.zhys.service.SQLManager;
import com.zhys.util.HttpPostUtil;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Log
public class ScheduledTask {
    @Autowired
    private RedisUtils redis;


    @Autowired
    private SQLManager manager;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");





    
//    检测空白发票
    @Scheduled(fixedRate = 12345)
    @Transactional
    public void    notifys() throws InterruptedException {
    	log.info("检测空白发票...");
//    1从 数据库查询 待同步的未同步成功的数据 -1  和未同步的数据 1 。
    	InvoiceHead invoiceHead = new InvoiceHead();
        invoiceHead.setInvoiceType("9");
        //查询是否需要同步 中 是 的数据，取1条
        List<InvoiceHead> list =  (List<InvoiceHead>)manager.list("invoice_head.queryList", (IPO)invoiceHead);
        if(list==null||list.size()<=0) {
        	Thread.sleep(60000);
        	return ;
        }
        for (InvoiceHead item : list) {
        	log.info("检测到空白发票："+item.getDocNum());
        	item.setDocStatus("2");
        	this.manager.update("invoice_head.changeDocStatusById", (IPO)item);
        	List<InvoiceSplitLine> splitLines = item.getInvoiceSplitLines();
        	if(splitLines==null||splitLines.size()<=0)  continue;
        	
        	for (InvoiceSplitLine splitLine : splitLines) {
				splitLine.setGroupStatus("2");
        		this.manager.update("invoice_split_line.changeDelStateById", (IPO)splitLine);
			}
		}
        log.info("空白发票处理完成");
        
    }
    
    

    

}
