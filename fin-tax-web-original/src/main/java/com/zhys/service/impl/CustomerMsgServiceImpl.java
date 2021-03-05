package com.zhys.service.impl;

import com.zhys.po.CustomerMsg;
import com.zhys.service.SQLManager;
import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import com.lycheeframework.core.cmp.kit.EasyPage;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhys.base.BaseApiService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import com.zhys.service.CustomerMsgService;

@Slf4j
@RestController
public class CustomerMsgServiceImpl extends BaseApiService implements CustomerMsgService{
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody CustomerMsg customerMsg){
	  CustomerMsg c = (CustomerMsg) manager.query("customer_msg.query",customerMsg);
		if(c != null ){//修改
		  return	manager.update("customer_msg.update", customerMsg);
		}else{//插入
		  return    manager.insert("customer_msg.create", customerMsg);
		}
		
	}
	
	
	@Override
	public CustomerMsg queryByEntity(@RequestBody CustomerMsg customerMsg){
	             return (CustomerMsg)manager.query("customer_msg.query", customerMsg);
	}
	@Override
	public List<CustomerMsg> queryList(@RequestBody CustomerMsg customerMsg){
	          return (List<CustomerMsg>)manager.list("customer_msg.queryList", customerMsg);
	}
	@Override
    public Pages<List<CustomerMsg>> pages(@RequestBody CustomerMsg customerMsg, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<CustomerMsg>>) manager.pages("customer_msg.page", customerMsg, page);
	
	}
	/**
    @Override
    public Pages<List<CustomerMsg>> pages(@RequestBody CustomerMsgPojo customerMsgPojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<CustomerMsg>>) manager.pages("customer_msg.page", customerMsgPojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody CustomerMsg customerMsg) {
		return manager.delete("customer_msg.changeDelStateById", customerMsg);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody CustomerMsg customerMsg) {
		return manager.update("customer_msg.changeDelStateByIds", customerMsg);
	}
	
    
}