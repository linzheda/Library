package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class OrderReportDao {
	private DBHelper db=new DBHelper();
	//查询
	public  List<Map<String,Object>> find(){
		String sql="select b.*,bo.*,bo.price*bo.booknum g from book b,bookorder bo where b.bookid=bo.bookid";
		return db.find(sql, null);
	}
	//查询按条件
	public  List<Map<String,Object>> finds(String tiaojian,String jieguo ){
		String sql="select b.*,bo.*,bo.price*bo.booknum g from book b,bookorder bo "
				+ " where b.bookid=bo.bookid and "+tiaojian+"=?";
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);
	}
	//
	//查询按条件
	public  List<Map<String,Object>> findDate(String orderdate,String orderdates ){
		String sql="select b.*,bo.*,bo.price*bo.booknum g from book b,bookorder bo "
				+ " where b.bookid=bo.bookid and "
				+ " bo.orderdate>=to_date('"+orderdate+"','yyyy-MM-dd') and bo.orderdate<=to_date('"+orderdates+"','yyyy-MM-dd')";

		return db.find(sql, null);
	}

}
