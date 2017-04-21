package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class MailDao {
	private DBHelper db=new DBHelper();
	//查询
	public List<Map<String,Object>> find(){
		String sql="select * from userinfo ";
		return db.find(sql, null);
	}
	//查询
	public List<Map<String,Object>> find(String userid){
		String sql="select * from userinfo  where userid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		return db.find(sql, params);
	}
	//查询条件
	public List<Map<String,Object>> find(String tiaojian ,String jieguo){
		
		String sql="select * from userinfo u where "+tiaojian+"=?";
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);
	}



}
