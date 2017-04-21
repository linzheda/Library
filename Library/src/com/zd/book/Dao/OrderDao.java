package com.zd.book.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class OrderDao {
	private DBHelper db=new DBHelper();
	/**
	 * 查询所有
	 */
	public List<Map<String,Object>> find(){
		String sql="select bookorder.orderid,book.bookname,book.bookph,bookorder.price,bookorder.booknum,bookorder.orderdate,bookorder.price*bookorder.booknum"
					+",bookorder.seller"
					+" from bookorder" 
					+" inner join book"
					+" on bookorder.bookid=book.bookid where bookorder.bostate = 1";
		return db.find(sql, null);
	}
	/**
	 * 查询bookid
	 */
	public List<Map<String,Object>> find2(String bookname){
		String sql="select book.bookid from book where book.bookname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(bookname);
		return db.find(sql, params);
	}
	/**
	 * 按条件查询
	 */
	public List<Map<String,Object>> find1(String tiaojian,String jieguo){
		String sql="select bookorder.orderid,book.bookname,book.bookph,bookorder.price,bookorder.booknum,bookorder.orderdate,bookorder.price*bookorder.booknum"
				+",bookorder.seller"
				+" from bookorder"
				+" inner join book"
				+" on bookorder.bookid=book.bookid"
				+" where "+tiaojian+"=? and bookorder.bostate = 1";
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);
	}
	//下单
	/**
	 * @param bookid
	 * @param seller
	 * @param price
	 * @param booknum
	 * @return
	 */
	public int add(int bookid,String seller,String price,String booknum){
		String sql="insert into bookorder values (seq_orderid.nextval,?,?,?,sysdate,?,'',default,'','')";
		List<Object> params=new ArrayList<Object>();
		params.add(bookid);
		params.add(seller);
		params.add(price);
		params.add(booknum);
		
		return db.update(sql, params);
	}
	
	//取消订单
	public int update(int bostate,String orderid){
		List<Object> params=new ArrayList<Object>();
		String sql="update bookorder set bostate=? where orderid=?";
		params.add(bostate);
		
		params.add(orderid);
		return db.update(sql, params);
	}
		
		//数据修改2
				public int update1(String price,String booknum,String seller,String orderid){
					List<Object> params=new ArrayList<Object>();
					String sql="update bookorder set price=?,booknum=?,seller=? where orderid=?";
					params.add(price);
					params.add(booknum);
					params.add(seller);
					params.add(orderid);
					return db.update(sql, params);
				}
}
