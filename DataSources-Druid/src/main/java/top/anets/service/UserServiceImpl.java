/**
 * 
 */
package top.anets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.anets.dataSource.config.DataSource;
import top.anets.dataSource.config.DataSourceType;
import top.anets.entity.InvoiceHead;
import top.anets.entity.InvoiceHeadExample;
import top.anets.mapper.InvoiceHeadMapper;

/**
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private InvoiceHeadMapper  invoiceHeadMapper;
	
	@DataSource(value = DataSourceType.MASTER)
	@Override
	public List<InvoiceHead> getHeads() {
		InvoiceHeadExample example = new InvoiceHeadExample();
		List<InvoiceHead> list = invoiceHeadMapper.selectByExample(example );
		System.out.println(list==null?0:list.size());
		return list;
	}
	
	@DataSource(value = DataSourceType.SLAVE)
	@Override
	public List<InvoiceHead> getHeads1() {
		InvoiceHeadExample example = new InvoiceHeadExample();
		List<InvoiceHead> list = invoiceHeadMapper.selectByExample(example );
		System.out.println(list==null?0:list.size());
		return list;
	}

	@Override
	public List<InvoiceHead> getHeads2() {
		InvoiceHeadExample example = new InvoiceHeadExample();
		List<InvoiceHead> list = invoiceHeadMapper.selectByExample(example );
		System.out.println(list==null?0:list.size());
		return list;
	}

}
