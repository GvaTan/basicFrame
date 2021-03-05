/**
 * 
 */
package top.anets.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.anets.dataSource.config.DataSource;
import top.anets.dataSource.config.DataSourceType;
import top.anets.entity.InvoiceHead;
import top.anets.service.UserService;

/**
 * @author Administrator
 *
 */
@Controller
@ResponseBody
public class UserController {
//	@Autowired
//    JdbcTemplate jdbcTemplate;
//    
//    
//    
//    @GetMapping("/query")
//    public Map<String,Object> map(){
//        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM user");
//        return list.get(0);
//    }
    
	@Autowired
	private UserService userService;
    
    @RequestMapping("/get")
    public List<InvoiceHead> get(){
    	List<InvoiceHead> get1 = userService.getHeads();
    	System.out.println(get1.size());
    	System.out.println("============="); 
    	List<InvoiceHead> get2 = userService.getHeads1();
    	System.out.println(get2.size());
    	System.out.println("============="); 
    	List<InvoiceHead> get3 = userService.getHeads2();
    	System.out.println(get3.size());
    	return get1;
    }
     
    @DataSource(value = DataSourceType.MASTER)
    @RequestMapping("/get1")
    public List<InvoiceHead> get1(){
    	return userService.getHeads();
    } 
    @DataSource(value = DataSourceType.SLAVE)
    @RequestMapping("/get2")
    public List<InvoiceHead> get2(){
    	return userService.getHeads(); 
    }
    
}
