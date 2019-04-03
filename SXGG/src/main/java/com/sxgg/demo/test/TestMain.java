package com.sxgg.demo.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.sxgg.demo.pojo.EmailReceive;
import com.sxgg.demo.util.DateConvertHelperUtil;
import com.sxgg.demo.util.EmailSendUtil;
import com.sxgg.demo.util.MD5Util;
import com.sxgg.demo.util.RegexExample;

public class TestMain {
		   public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
//				List<EmailReceive> emaillist = new ArrayList<EmailReceive>();
//				EmailReceive em = new EmailReceive();
//				em.setTo("1175056766@qq.com");
//				em.setSubject("主题0");
//				em.setContentValue("内容0");
//				EmailReceive ems = new EmailReceive();
//				ems.setTo("wslwdljh@163.com");
//				ems.setSubject("主题1");
//				ems.setContentValue("内容1");
//				emaillist.add(em);
//				emaillist.add(ems);
		
//				for (EmailReceive emailobject : emaillist) {
//					if (!RegexExample.isEmail(emailobject.getTo())) {
//						System.out.println("邮箱[" + emailobject.getTo() + "]格式错误！");
//					}
//				}
//		 
//				Thread t_emailsend = new Thread(new EmailSendUtil(emaillist));
//				t_emailsend.start();
			   //4QrcOUm6Wau+VuBX8g+IPg
			   System.out.println(MD5Util.encodeByMd5("123456"));
			   System.out.println(DateConvertHelperUtil.getNowDateNumberStr());
		}
} 
