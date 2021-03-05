/**
 * 
 */
package top.anets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 *
 */
@Controller
public class TestController {
    @RequestMapping("/get")
    @ResponseBody
    public String get() {
    	return "嘿嘿";
    }
}
