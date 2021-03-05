package com.zhys.service;


import com.lycheeframework.core.cmp.kit.Pages;
import com.sap.conn.jco.JCoException;
import com.zhys.po.InvoiceHeadDto;
import com.zhys.po.SendMsg;
import com.zhys.po.VInvoiceHead;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InvoiceHeadService {
/**
 * 
* revoke是撤销的意思
*@param orgId    这个是组织id
*@param docStatus   这个是单据状态
*@param IV_N        
*@return
 */
    Object revoke(String orgId, String docStatus, String IV_N);

/**
 * 
* 解锁sap用的   ， 解锁后sap才能做下一步操作
*@param orgId
*@return
*@throws JCoException
 */
    Object unlock(String orgId)throws JCoException;


    
    Object waitSend();


    Pages<List<SendMsg>> pages_send_msg(@RequestBody SendMsg sendMsg, Integer pageSize, Integer pageNum);


    Object exWaitSend();

}