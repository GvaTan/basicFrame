package top.anets.controller;

import org.apache.catalina.startup.HomesUserDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	  @RequestMapping("/admin")
      public String Homes() {
    	  return "starter";
      }
	  
	  @RequestMapping("/{module}/{page}")
      public String showPage(@PathVariable String module, @PathVariable String page) {
    	  return "sys/"+page+".html";
      }
	  
	  @RequestMapping("/system/login")
	  public String login() {
		  return "login";
	  }
}
