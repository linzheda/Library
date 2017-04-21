package com.zd.book.Dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;




public class BorrowBookDao {
	private DBHelper db=new DBHelper();
	//查询书籍状态
	public  List<Map<String,Object>> find(){
		List<Object> params=new ArrayList<Object>();
		String sql="select sum(case when bd.bookstate='未借出' then 1 else 0 end) no, "
				+" b.bookid,b.bookname,b.bookauthor,b.bookph,bc.classname,u.sname,u.userid,bs.bespeakid,bs.bespeakdate,bs.bespeakstate "
				+" from book b,userinfo u, bespeak bs, bookdetailed bd,bookclass bc "
				+" where b.bookid=bs.bookid and bs.userid=u.userid and bd.bookid=b.bookid and bc.classid=b.classid and bd.booksdate is null "
				+ " and (bs.bespeakstate='待审核' or bs.bespeakstate='已通过') "
				+" group by b.bookid ,b.bookname,b.bookauthor,b.bookph,bc.classname,u.sname,u.userid,bs.bespeakid,bs.bespeakdate,bs.bespeakstate ";

		return db.find(sql, null);
	}
	//查询书籍状态条件
		public  List<Map<String,Object>> finds(String tiaojian ,String jieguo){
			List<Object> params=new ArrayList<Object>();
			String sql="select sum(case when bd.bookstate='未借出' then 1 else 0 end) no, "
					+" b.bookid,b.bookname,b.bookauthor,b.bookph,bc.classname,u.sname,u.userid,bs.bespeakid,bs.bespeakdate,bs.bespeakstate "
					+" from book b,userinfo u, bespeak bs, bookdetailed bd,bookclass bc "
					+" where b.bookid=bs.bookid and bs.userid=u.userid and bd.bookid=b.bookid and bc.classid=b.classid and bd.booksdate is null "
					+ " and (bs.bespeakstate='待审核' or bs.bespeakstate='已通过') and "+tiaojian+"=? "
					+" group by b.bookid ,b.bookname,b.bookauthor,b.bookph,bc.classname,u.sname,u.userid,bs.bespeakid,bs.bespeakdate,bs.bespeakstate ";
			params.add(jieguo);
			return db.find(sql, params);
		}
	//通过审核
	public int update (String bespeakid){
		List<Object> params=new ArrayList<Object>();
		String sql ="update bespeak  set bespeakstate='已通过' where bespeakid=? ";
		params.add(bespeakid);
		return db.update(sql, params);
	}
	//不借阅
	public int updates (String bespeakid){
		List<Object> params=new ArrayList<Object>();
		String sql ="update bespeak  set bespeakstate='未通过' where bespeakid=? ";
		params.add(bespeakid);
		return db.update(sql, params);
	}
	//右键借阅
	public List<Map<String,Object>> findBD(String bookid){//查询什么书没被借走
		List<Object> params=new ArrayList<Object>();
		String sql="select * from bookdetailed where bookstate='未借出' and bookid=? and booksdate is null";
		params.add(bookid);
		return db.find(sql, params);
	}
	public int updateBDs(String bespeakid){//修改预约表状态
		List<Object> params=new ArrayList<Object>();
		String sql="update bespeak  set bespeakstate='已借阅' where bespeakid=?  ";
		params.add(bespeakid);
		return db.update(sql, params); 
	}
	public int updateBD(String bid){//修改书籍状态
		List<Object> params=new ArrayList<Object>();
		String sql="update bookdetailed set bookstate='借出' where bid=?";
		params.add(bid);
		return db.update(sql, params);
	}
	public int addBD(String userid ,String bid){//借阅表增加记录
		List<Object> params=new ArrayList<Object>();
		//System.out.println(userid +555 +bid);
		String sql="insert into borrow values(seq_brid.nextval,?,?,sysdate,'','','','','') ";
		params.add(userid);
		params.add(bid);
		return db.update(sql, params);
	}
	
	
	
	//点击借阅
	public int updatebook(String userid ,String bid){//插入数据
		List<Object> params=new ArrayList<Object>();
		String sql="insert  into borrow values (seq_brid.nextval,?,?,sysdate,'','','','','') ";
		params.add(userid);
		params.add(bid);
		return db.update(sql, params);
	}
	public int updatebook(String bid){
		List<Object> params=new ArrayList<Object>();
		String sql="update bookdetailed set bookstate='借出' where bid=?";
		params.add(bid);
		return db.update(sql, params);
	}
	//查询用户表
	public List<Map<String,Object>> findU(String userid ){
		
		List<Object> params=new ArrayList<Object>();
		String sql="select * from userinfo where userid=? ";
		params.add(userid);
		return db.find(sql, params);
	}
	//查询书籍编号
	public List<Map<String,Object>> findB(String bid){
		
		List<Object> params=new ArrayList<Object>();
		String sql="select * from bookdetailed where bid=? and bookstate='未借出' ";
		params.add(bid);
		return db.find(sql, params);
	}
	



}
