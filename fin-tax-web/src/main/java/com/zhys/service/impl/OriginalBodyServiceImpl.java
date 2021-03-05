package com.zhys.service.impl;

import com.lycheeframework.core.cmp.kit.EasyPage;
import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.base.BaseApiService;
import com.zhys.invoice.po.InvoiceOriginalLine;
import com.zhys.po.OriginalBodyDto;
import com.zhys.service.InvoiceOriginalLineService;
import com.zhys.service.OriginalBodyService;
import com.zhys.service.SQLManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class OriginalBodyServiceImpl extends BaseApiService implements OriginalBodyService {
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody OriginalBodyDto originalBodyDto){
		OriginalBodyDto c = (OriginalBodyDto) manager.query("original_body.query",originalBodyDto);
		if(c != null ){//修改
		  return	manager.update("original_body.update", originalBodyDto);
		}else{//插入
		  return    manager.insert("original_body.create", originalBodyDto);
		}
		
	}
	
	
	@Override
	public OriginalBodyDto  queryByEntity(@RequestBody OriginalBodyDto originalBodyDto){
	             return (OriginalBodyDto)manager.query("original_body.query", originalBodyDto);
	}
	@Override
	public List<OriginalBodyDto> queryList(@RequestBody OriginalBodyDto originalBodyDto){
		List<OriginalBodyDto> list =   (List<OriginalBodyDto>)manager.list("original_body.queryList", originalBodyDto);
			if(list!=null&&list.size()>0){
				list.forEach(invoiceOriginalLine1 -> {
					int i = list.indexOf(invoiceOriginalLine1);
					invoiceOriginalLine1.setDocOrigline(i+1001+"");
				});
			}
		return list;
	}
	@Override
    public Pages<List<OriginalBodyDto>> pages(@RequestBody OriginalBodyDto originalBodyDto, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<OriginalBodyDto>>) manager.pages("original_body.page", originalBodyDto, page);
	
	}
	/**
    @Override
    public Pages<List<InvoiceOriginalLine>> pages(@RequestBody InvoiceOriginalLinePojo invoiceOriginalLinePojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceOriginalLine>>) manager.pages("original_body.page", invoiceOriginalLinePojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody OriginalBodyDto originalBodyDto) {
		return manager.update("original_body.changeDelStateById", originalBodyDto);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody OriginalBodyDto originalBodyDto) {
		return manager.update("original_body.changeDelStateByIds", originalBodyDto);
	}
	
    
}