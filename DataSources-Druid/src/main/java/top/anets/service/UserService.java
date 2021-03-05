/**
 * 
 */
package top.anets.service;

import java.util.List;

import top.anets.dataSource.config.DataSource;
import top.anets.dataSource.config.DataSourceType;
import top.anets.entity.InvoiceHead;

/**
 * @author Administrator
 *
 */
public interface UserService {
	 
      List<InvoiceHead> getHeads();
	  
	  
      @DataSource(value = DataSourceType.MASTER)
      List<InvoiceHead> getHeads1();
	  
	  @DataSource(value = DataSourceType.SLAVE)
      List<InvoiceHead> getHeads2();
}
