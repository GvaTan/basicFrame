package com.zhys.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
public interface TestService  {
	@GetMapping("/index")
    String index();

//    @GetMapping("/index1")
//    Object index1();

}