package com.sxgg.demo.pojo;

import java.util.Date;

/**
    * 邮箱接收人实体
 * @author Administrator
 *
 */
public class EmailReceive {
	private String to;                 //收件人
    private String subject;            //主题
    private String content;            //内容key
    private String contentValue;       //内容模板
    private Date   emailTime;
    private String fileStr;            //附件路径
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentValue() {
		return contentValue;
	}
	public void setContentValue(String contentValue) {
		this.contentValue = contentValue;
	}
	public Date getEmailTime() {
		return emailTime;
	}
	public void setEmailTime(Date emailTime) {
		this.emailTime = emailTime;
	}
	public String getFileStr() {
		return fileStr;
	}
	public void setFileStr(String fileStr) {
		this.fileStr = fileStr;
	}
}
