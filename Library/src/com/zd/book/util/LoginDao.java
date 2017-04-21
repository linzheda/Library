package com.zd.book.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginDao {
	private DBHelper db=new DBHelper();
	/**
	 * 查询用户
	 * @return
	 */
	public List<Map<String,Object>> findUser(String userid,String pwad){
		String sql="select * from userinfo where userid=? and pwad=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		params.add(pwad);
		return db.find(sql,params);
	}
	/**
	 * 查询管理员
	 * @return
	 */
	public List<Map<String,Object>> findManage(String mid,String pwad){
		String sql="select * from manageinfo where mid=? and pwad=?";
		List<Object> params=new ArrayList<Object>();
		params.add(mid);
		params.add(pwad);
		return db.find(sql,params);
	}
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<Map<String,Object>> findUser(){
		String sql="select * from userinfo";
		
		return db.find(sql, null);
	}
	/**
	 * 查询所有管理员
	 * @return
	 */
	
	public List<Map<String,Object>> findManage(){
		String sql="select * from manageinfo ";
		
		return db.find(sql, null);
	}
}
