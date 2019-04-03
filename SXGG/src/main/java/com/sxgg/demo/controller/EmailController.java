package com.sxgg.demo.controller;

 

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.sxgg.demo.pojo.EmailReceive;
import com.sxgg.demo.util.EmailSendUtil;

@RestController
public class EmailController {
          
	
	public String sendEmail(List<EmailReceive> emaillist) {
		EmailReceive em=new EmailReceive();
		em.setTo("1175056766@qq.com");
		em.setSubject("主题");
		em.setContentValue("内容");
		Thread t_emailsend=new Thread(new EmailSendUtil(emaillist));
		t_emailsend.start();
		return "";
	}
}
