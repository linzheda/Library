package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class BookDao {
	private DBHelper db=new DBHelper();
	
	/**
	 * 查询所有的方法2
	 * @return
	 */
	public List<Map<String,Object>> find(int i){
		String sql="select * from("
                   +"select e.*,rownum aaa from("
                   +"select book.bookid,book.bookname,book.bookprice,book.bookauthor,book.bookph,bookclass.classname"
                   +" from book"
                   +" inner join bookclass"
                   +" on book.classid = bookclass.classid) e"
                   +" where rownum<="+i+"*5)"
                   +"where aaa> =("+i+"-1)*5+1";
		           return db.find(sql,null);
	}
	/**
	 * 按条件查询
	 * @return
	 */
	public List<Map<String,Object>> find1(String tiaojian,String jieguo){
		String sql="select book.bookid,book.bookname,book.bookprice,book.bookauthor,book.bookph,bookclass.classname"
					+" from book"
					+" inner join bookclass"
					+" on book.classid = bookclass.classid where "+tiaojian+"=?";
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);
	}
	/**
	 * 按条件查询1
	 * @return
	 */
	public List<Map<String,Object>> find1(){
		String sql="select book.bookid,book.bookname,book.bookprice,book.bookauthor,book.bookph,bookclass.classname"
					+" from book"
					+" inner join bookclass"
					+" on book.classid = bookclass.classid";
		List<Object> params=new ArrayList<Object>();
		
		return db.find(sql, params);
	}
}
