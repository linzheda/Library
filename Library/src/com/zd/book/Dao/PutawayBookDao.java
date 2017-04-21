package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;



public class PutawayBookDao {
	private DBHelper db=new DBHelper();
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Map<String,Object>> find(){
		String sql="select b.jpg,b.bookid,b.bookname,bc.classname,b.bookauthor,b.bookph,b.snum,b.bnum"
			       +" from book b,bookclass bc"
			       +" where b.classid=bc.classid";
		
		return db.find(sql, null);
	}
	
	
	/**
	 * 通过条件查询
	 * @return
	 */
	public List<Map<String,Object>> find(String tiaojian,String jieguo){
		String sql="select b.jpg,b.bookid,b.bookname,bc.classname,b.bookauthor,b.bookph,b.snum,b.bnum"
			       +" from book b,bookclass bc"
			       +" where b.classid=bc.classid and "+tiaojian+"=? ";
		
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);
	}
	
	/**
	 * 功过书名找bookid 同种书籍编号
	 * @return
	 */
	public List<Map<String,Object>> find(String shuming){
		String sql="select bookid from book where bookname=?";
		
		List<Object> params=new ArrayList<Object>();
		params.add(shuming);
		return db.find(sql, params);
	}
	
	
	//--------------------------------------------------------------------------------------
	
	public int updatebook(String shuliang1,String shuliang2,String shuming){
		String sql="update book set bnum=bnum-?,snum=snum+? where bookname=?";
		
		List<Object> params=new ArrayList<Object>();
		params.add(shuliang1);
		params.add(shuliang2);
		params.add(shuming);
		
		return db.update(sql, params);
		
	}
	
	
	
	public int addbookdetailed(String bianhao){
		String sql="insert into bookdetailed values(seq_bid.nextval,?,sysdate,default,'','','')";
		
		List<Object> params=new ArrayList<Object>();
		params.add(bianhao);
		return db.update(sql, params);
	}
	
}
