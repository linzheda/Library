package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;



public class AcceptanceDao {
	private DBHelper db=new DBHelper();
	
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Map<String,Object>> find(){
		String sql="select bo.orderid,b.bookname,bc.classname,b.bookph,b.bookprice,bo.booknum,bo.orderdate,b.bookprice*bo.booknum sum"
			       +" from bookorder bo,book b,bookclass bc"
			       +" where bo.bookid=b.bookid and b.classid=bc.classid and bo.accept is null"
			       +" order by bo.orderid";
		return db.find(sql, null);				
	}
	
	
	
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Map<String,Object>> find(String tiaojian,String jieguo){
		String sql="select bo.orderid,b.bookname,bc.classname,b.bookph,b.bookprice,bo.booknum,bo.orderdate,b.bookprice*bo.booknum sum"
			       +" from bookorder bo,book b,bookclass bc"
			       +" where bo.bookid=b.bookid and b.classid=bc.classid and bo.accept is null and "+tiaojian+"=?"
			       +" order by bo.orderid";
		
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);				
	}
	
	
	/**
	 * 通过编号修改这个订单的验收时间
	 * @param bianhao
	 * @return
	 */
	public int updatebookorder(String bianhao){
		String sql="update bookorder set accept=sysdate where orderid=?";
		
		List<Object> params=new ArrayList<Object>();
		params.add(bianhao);
		return db.update(sql, params);
	}
	
	
	/**
	 * 通过编号修改这个订单的验收时间
	 * @param bianhao
	 * @return
	 */
	public int updatebook(String shuming,String shuliang){
		String sql="update book set bnum=bnum+? where bookname=?";
		
		List<Object> params=new ArrayList<Object>();
		params.add(shuliang);
		params.add(shuming);
		return db.update(sql, params);
	}
	
}
