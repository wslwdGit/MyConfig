package com.sxgg.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sxgg.demo.pojo.Myuser;


@Mapper
public interface TestDao {

	List<Myuser> myUserList();
}
