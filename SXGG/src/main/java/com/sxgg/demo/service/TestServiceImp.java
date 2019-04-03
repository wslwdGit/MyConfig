package com.sxgg.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxgg.demo.dao.TestDao;
import com.sxgg.demo.pojo.Myuser;

@Service("testservice")
public class TestServiceImp {
	
	@Autowired
    private TestDao dao;
	
	 
	public List<Myuser> myUserListService(){
		return dao.myUserList();
	}
	
	public Myuser getMyusernameService(String name) {
		return null;
	}
	
	
}
