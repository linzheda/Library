package com.zd.book.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class FadanDao {
	private DBHelper db=new DBHelper();
	/**
	 * 查询
	 */
	public List<Map<String,Object>> find(){
		String sql="select b.brid,b.stuid,u.sname,b.bid,b.ticket,u.userstate,b.state from borrow b inner join userinfo u on u.userid=b.stuid where b.ticket is  not null";		
		return db.find(sql, null);
	}
	/**
	 * 按条件查询
	 */
	public List<Map<String,Object>> find1(String tiaojian,String jieguo){
		String sql="select b.brid,b.stuid,u.sname,b.bid,b.ticket,u.userstate,b.state from borrow b inner join userinfo u on u.userid=b.stuid where "+tiaojian+"=? and b.ticket is  not null";
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);
	}
	/**
	 * 修改状态
	 * @param userstate
	 * @param userid
	 * @return
	 */
	public int update(String userstate,int brid){
		String sql="update userinfo u set userstate=? where userid in (select b.stuid from borrow b where b.brid=?)";
		List<Object> params=new ArrayList<Object>();
		params.add(userstate);
		params.add(brid);
		return db.update(sql, params);
		
	}
}
