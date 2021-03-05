package com.zhys.service.impl;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    //简单邮件测试
    public void sendSimple(String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("公牛发票推送");
        message.setText(content);
        message.setTo("1173499611@qq.com");
        message.setFrom("liwx@gongniu.cn");
        javaMailSender.send(message);
    }
    //复杂邮件测试
    public Map<String,String> sendComplicated(String content, String cust_email)  {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //用MimeMessageHelper来包装MimeMessage
        MimeMessageHelper mimeMessageHelper = null;
        Map<String,String> map = new HashMap<String,String>();
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,false);
            mimeMessageHelper.setSubject("公牛发票信息");
            mimeMessageHelper.setText( content,true);
            mimeMessageHelper.setTo(cust_email);
            mimeMessageHelper.setFrom("liwx@gongniu.cn");
            javaMailSender.send(mimeMessage);
            log.info(cust_email+"：发送邮件成功:");
            map.put("success","true");
            map.put("msg","邮件发送成功");
            return map;
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("发送邮件异常:"+e.getMessage());
            map.put("success","true");
            map.put("msg","邮件发送失败："+e.getMessage());
            return map;
        }


    }
}
