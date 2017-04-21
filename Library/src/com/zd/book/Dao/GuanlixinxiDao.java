package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;
import com.zd.book.util.Data;





public class GuanlixinxiDao {
	private DBHelper db=new DBHelper();
	
	/**
	 * 修改密码
	 * @param userstate
	 * @param userid
	 * @return
	 */
	public int update(String pwad){
		String sql="update manageinfo set pwad=? where mid =?";
		List<Object> params=new ArrayList<Object>();
		params.add(pwad);
		
		
		String manageid=Data.manageid;
		params.add(manageid);
		return db.update(sql, params);
		
	}
	
	/**
	 * 修改
	 * @param userstate
	 * @param userid
	 * @return
	 */
	public int update(String mname,String tel,String manageidentity,String sex){
		String sql="update manageinfo set mname=?,tel=?,manageidentity=?,sex=? where mid =?";
		List<Object> params=new ArrayList<Object>();
		params.add(mname);
		params.add(tel);
		params.add(manageidentity);
		params.add(sex);
		
		String manageid=Data.manageid;
		params.add(manageid);
		return db.update(sql, params);
		
	}
	/**
	 * 查询原密码
	 */
	public List<Map<String,Object>> find(){
		String sql="select pwad from manageinfo where mid=?";
		List<Object> params=new ArrayList<Object>();
		String manageid=Data.manageid;
		params.add(manageid);
		return db.find(sql, params);
	}
	/**
	 * 查询信息
	 */
	public List<Map<String,Object>> find1(){
		String sql="select mname,tel,manageidentity,sex from manageinfo where mid=?";
		List<Object> params=new ArrayList<Object>();
		String manageid=Data.manageid;
		params.add(manageid);
		return db.find(sql, params);
	}
}
