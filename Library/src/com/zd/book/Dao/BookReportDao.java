package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;
import com.zd.book.util.Data;

public class BookReportDao {
	private DBHelper db=new DBHelper();
	//查询借阅表
	public List<Map<String,Object>> find(){
		String sql="Select b.bookid,b.bookname,br.* from borrow br,book b,bookdetailed bd "
				+ " where b.bookid=bd.bookid and bd.bid=br.bid";
		List<Object> params=new ArrayList<Object>();
		return db.find(sql,null);
	}
	//查询借阅表(条件)
	public List<Map<String,Object>> find(String tiaojian,String jieguo){
		String sql="Select b.bookid,b.bookname,br.* from borrow br,book b,bookdetailed bd where b.bookid=bd.bookid and bd.bid=br.bid and "+tiaojian+"=?";
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql,params);
	}
	//查询借阅表(条件)riqi
	public List<Map<String,Object>> findDate(String hdate,String bdate){
		String sql="Select b.bookid,b.bookname,br.* from borrow br,book b,bookdetailed bd "
				+ "where b.bookid=bd.bookid and bd.bid=br.bid and( bdate>=to_date(?,'yyyy-mm-dd') and (hdate<=to_date(?,'yyyy-mm-dd') or hdate is null) )";
		List<Object> params=new ArrayList<Object>();
		params.add(bdate);
		params.add(hdate);
		return db.find(sql,params);
	}
	//查询预约表
	public List<Map<String,Object>> finds(){
		String sql="select * from bespeak bs,book b where  b.bookid=bs.bookid ";	
		return db.find(sql,null);
	}
	//查询预约表 (条件)
	public List<Map<String,Object>> finds(String tiaojian,String jieguo){
		String sql="select * from bespeak bs,book b where  b.bookid=bs.bookid and "+tiaojian+"=?";

		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql,params);
	}
	//查询预约表 (条件)riqi
	public List<Map<String,Object>> findsDate(String hdate,String bdate){
		String sql="select * from bespeak bs,book b where  b.bookid=bs.bookid  and bs.bespeakdate>=to_date(?,'yyyy-mm-dd') and bs.bespeakdate<=to_date(?,'yyyy-mm-dd') ";
		List<Object> params=new ArrayList<Object>();
		params.add(bdate);
		params.add(hdate);
		return db.find(sql,params);
	}
	//查询罚单
	public List<Map<String,Object>> findT(boolean flag){
		String sql;
		if(flag){
			sql="Select b.bookid,b.bookname,br.* from borrow br,book b,bookdetailed bd "
					+ " where b.bookid=bd.bookid and bd.bid=br.bid and br.ticket is not null";
		}else{
			sql="Select b.bookid,b.bookname,br.* from borrow br,book b,bookdetailed bd "
					+ " where b.bookid=bd.bookid and bd.bid=br.bid  and br.ticket is null";
		}
		return db.find(sql,null);
	}
	
	
	
}
