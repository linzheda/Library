package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;
import com.zd.book.util.Data;

public class ReaderInfoDao {
	private DBHelper db=new DBHelper();
	/**
	 * 查询用户
	 * @return
	 */
	public List<Map<String,Object>> find(String username){
		String sql="select u.*,r.* from userinfo u,rank r where u.rankid=r.rankid and userid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(username);
		return db.find(sql,params);
	}
	//
	public int update(String sname,String sex,String useridentity,String mail,String usertel){
		List<Object> params=new ArrayList<Object>();
		String userid=Data.username;
		String sql="update userinfo set sname=?,sex=?,useridentity=?,mail=?,usertel=? where userid=?";
		params.add(sname);
		params.add(sex);
		params.add(useridentity);
		params.add(mail);
		params.add(usertel);
		params.add(userid);
		return db.update(sql, params);
		
	}
	public int update(String pwad){
		List<Object> params=new ArrayList<Object>();
		String userid=Data.username;
		String sql="update userinfo set pwad=? where userid=?";
		params.add(pwad);
		params.add(userid);
		return db.update(sql, params);
		
	}
	
	

}
