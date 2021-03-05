/**
 * 
 */
package top.anets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.anets.service.LogService;
import top.anets.vo.Page;
import top.anets.vo.Result;

@RestController
@RequestMapping("/log/")
public class LogController {
    @Autowired
    private LogService logService;
    
    @RequestMapping("/getLogs")
    public Result getLogs(@RequestParam(defaultValue = "1")Integer start,@RequestParam(defaultValue = "2")Integer limit) {
    	Page logs = logService.getLogs(start, limit);
    	return new Result(logs);
    }
}
