package com.sxgg.demo.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sxgg.demo.pojo.EmailReceive;
import com.sun.mail.util.MailSSLSocketFactory;

/**
   *   邮箱发送
 * @author Administrator
 *
 */
public class EmailSendUtil implements Runnable{
	
	private static String account;     //登录用户名
    private static String pass;        //登录密码
    private static String host;        //服务器地址（邮件服务器）
    private static String port;        //端口
    private static String protocol;    //协议
    private static String emailname;   //发件人别名
    
    private  List<EmailReceive> Receive; //发送者信息

    public EmailSendUtil(List<EmailReceive> Receive) {
    	//接收者信息
    	this.Receive=Receive;
    	
    	
    	//发送者信息
    	Properties prop = new Properties();
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("email.properties");
        } catch (IOException e) {
            System.out.println("加载属性文件失败");
        }
	        account = prop.getProperty("e.account");
	        pass = prop.getProperty("e.pass");
	        host = prop.getProperty("e.host");
	        port = prop.getProperty("e.port");
	        protocol = prop.getProperty("e.protocol");
        try {
			emailname=new String(prop.getProperty("e.emailname").getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
    
     
     
    
    /**
             * 用户名密码验证  继承Authenticator抽象类 重写抽象方法
     * @author Administrator
     *
     */
    static class MyAuthenricator extends Authenticator{  
        String u = null;  
        String p = null;  
        public MyAuthenricator(String u,String p){  
            this.u=u;  
            this.p=p;  
        }  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(u,p);  
        }  
    }
    
    
    /**
     * email发送
     */
    @Override
    public void run() {
    	 //创建临时文件
    	 Properties prop = new Properties();
         //协议
         prop.setProperty("mail.transport.protocol", protocol);
         //服务器
         prop.setProperty("mail.smtp.host", host);
         //端口
         prop.setProperty("mail.smtp.port", port);
         //使用smtp身份验证
         prop.setProperty("mail.smtp.auth", "true");
         //使用SSL验证
         //开启安全协议
         MailSSLSocketFactory sf = null;
         try {
             sf = new MailSSLSocketFactory();
             sf.setTrustAllHosts(true);
         } catch (GeneralSecurityException e1) {
             e1.printStackTrace();
         }
         prop.put("mail.smtp.ssl.enable", "true");
         prop.put("mail.smtp.ssl.socketFactory", sf);
         
         //循环发送
         for (int i = 0; i < Receive.size(); i++) {
         Session session = Session.getDefaultInstance(prop, new MyAuthenricator(account, pass));
         //session.setDebug(true);
         MimeMessage mimeMessage = new MimeMessage(session);
         try {
             //发件人
           if(emailname.length()==0 && emailname.equals("")) {
         		mimeMessage.setFrom(new InternetAddress(account));
         	 }else {
         		mimeMessage.setFrom(new InternetAddress(account,emailname));
         	}
             
 	            //收件人
 	            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Receive.get(i).getTo()));
 	            //主题
 	            mimeMessage.setSubject(Receive.get(i).getSubject());
 	            //时间
 	            mimeMessage.setSentDate(new Date());
 	            //容器类，可以包含多个MimeBodyPart对象
 	            Multipart mp = new MimeMultipart();
 	            
 	            //MimeBodyPart可以包装文本，图片，附件
 	            MimeBodyPart body = new MimeBodyPart();
 	            //HTML正文
 	            body.setContent(Receive.get(i).getContentValue(), "text/html; charset=UTF-8");
 	            mp.addBodyPart(body);
 	            
 				//添加图片&附件 
 	            body = new MimeBodyPart();
 	            body.attachFile("D:\\muban\\img\\dog.png");
 	            mp.addBodyPart(body);
 				 
 	            //设置邮件内容
 	            mimeMessage.setContent(mp);
 	            //仅仅发送文本
 	            //mimeMessage.setText(content);
 	            mimeMessage.saveChanges();
 	            //发送
 	            Transport.send(mimeMessage);
 	            
         
         } catch (MessagingException e) {
             e.printStackTrace();
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         } 
    }
    
    /**
             *  多个邮箱发送
     * @throws InterruptedException 
     *
     */
    public static void sendAll(List<EmailReceive> Receive) throws InterruptedException{
    	 
       
      
    }

    
    /**
             * 单个邮件发送
     * @param Receive
     */
    public static void send(EmailReceive Receive){
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", protocol);
        //服务器
        prop.setProperty("mail.smtp.host", host);
        //端口
        prop.setProperty("mail.smtp.port", port);
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        
        //使用SSL验证
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        
        Session session = Session.getDefaultInstance(prop, new MyAuthenricator(account, pass));
        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            //发件人
        	if(emailname.length()==0 && emailname.equals("")) {
        		mimeMessage.setFrom(new InternetAddress(account));
        	}else {
        		mimeMessage.setFrom(new InternetAddress(account,emailname));
        	}
            //收件人
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(Receive.getTo()));
            //主题
            mimeMessage.setSubject(Receive.getSubject());
            //时间
            mimeMessage.setSentDate(new Date());
            //容器类，可以包含多个MimeBodyPart对象
            Multipart mp = new MimeMultipart();
            
            //MimeBodyPart可以包装文本，图片，附件
            MimeBodyPart body = new MimeBodyPart();
            //HTML正文
            body.setContent(Receive.getContent(), "text/html; charset=UTF-8");
            mp.addBodyPart(body);
            
			//添加图片&附件 
            //body = new MimeBodyPart(); 
            // body.attachFile(fileStr);
			//mp.addBodyPart(body);
			 
            //设置邮件内容
            mimeMessage.setContent(mp);
            //仅仅发送文本
            //mimeMessage.setText(content);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
              * 更具接收者信息集合中的  content(内容  key) 加载对应properties文件中的value
     * @param Receive 接收者信息集合 
     * @return
     */
    public List<EmailReceive> modeCount(List<EmailReceive> Receive) {
    	String key="";  //具体模板标识 key
    	String value="";//具体模板内容          value
    	String valueAll=""; //所以模板内容总和
    	Properties prop = new Properties();
    	for (int i = 0; i < Receive.size(); i++) {
    		key=Receive.get(i).getContent();
               try {
                   prop = PropertiesLoaderUtils.loadAllProperties("modelist.properties");
               } catch (IOException e) {
                   System.out.println("加载属性文件失败");
               }
               valueAll = prop.getProperty("m.modejson");
		}
    	return null;
    }
}
