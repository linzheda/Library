package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class BookClassDao {
	private DBHelper db=new DBHelper();
	
	
	//查询
	public List<Map<String,Object>> find(){
		String sql="select classid,classname,cstate from bookclass";
		return db.find(sql, null);
	}
	//名称查询
	public List<Map<String,Object>> findName(String classname){
		String sql="select classid,classname,cstate from bookclass where classname=?";
		List<Object> params =new ArrayList<Object>();
		params.add(classname);
		return db.find(sql, params);
	}
	//编号查询
	public List<Map<String,Object>> findId(String classid){
		String sql="select classid,classname,cstate from bookclass where classid=?";
		List<Object> params =new ArrayList<Object>();
		params.add(classid);
		return db.find(sql, params);
	}
	//添加
	public int add(String classname,String cstate){
		String sql="insert into bookclass values(seq_classid.nextval,?,?,'','')";
		List<Object> params =new ArrayList<Object>();
		params.add(classname);
		params.add(cstate);
		return db.update(sql, params);
	}
	//修改
	public int update(String classid,String classname,String cstate){
		String sql="update bookclass set cstate=?,classname=? where classid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(cstate);
		params.add(classname);
		params.add(classid);
		return db.update(sql, params);
		
	}

}
