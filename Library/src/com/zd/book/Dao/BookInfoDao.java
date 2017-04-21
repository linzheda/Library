package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class BookInfoDao {
	private DBHelper db=new DBHelper();
	//查询
	public List<Map<String,Object>> find(){
		String sql="select b.jpg,a.*,bc.classname from book b inner join  (select b.bookid,b.bookname,b.bookprice,b.bnum,b.bookauthor,b.bookph,b.classid,sum(case when bd.bookstate='未借出' then 1 else 0 end) no, sum(case when bd.bookstate='借出' then 1 else 0 end) yes"
				+" from book b"
				+" inner join bookdetailed bd"
				+" on b.bookid =bd.bookid"
				+" group by b.bookid,b.bookname,b.bookauthor,b.bookph,b.classid,b.bookprice,b.bnum,b.bstate,bd.booksdate  having b.bstate=1 and bd.booksdate is null) a"
				+" on a.bookid= b.bookid "
				+" inner join bookclass bc"
				+" on b.classid=bc.classid";
		
		return db.find(sql, null);
	}
	//查询按条件
	public List<Map<String,Object>> finds(String tiaojian,String jieguo){
		String sql="select b.jpg,a.*,bc.classname from book b inner join  (select b.bookid,b.bookname,b.bookprice,b.bnum,b.bookauthor,b.bookph,b.classid,sum(case when bd.bookstate='未借出' then 1 else 0 end) no, sum(case when bd.bookstate='借出' then 1 else 0 end) yes"
				+" from book b"
				+" inner join bookdetailed bd"
				+" on b.bookid =bd.bookid"
				+" group by b.bookid,b.bookname,b.bookauthor,b.bookph,b.classid,b.bookprice,b.bnum,b.bstate having b.bstate=1 and "+tiaojian+"=?) a"
				+" on a.bookid= b.bookid "
				+" inner join bookclass bc"
				+" on b.classid=bc.classid";
		List<Object> params=new ArrayList<Object>();
		
		params.add(jieguo);
		
		return db.find(sql,params);
	}
	
	//新增
	public int add(String bookname,String bookauthor,String bookph,String bookprice,String classid,byte[] jpg){
		
		String sql="insert into book values(seq_bookid.nextval,?,?,?,?,?,?,0,0,1,'','')";
		List<Object> params=new ArrayList<Object>();
		params.add(bookname);
		params.add(bookprice);
		params.add(bookauthor);
		params.add(bookph);
		params.add(jpg);
		params.add(classid);
		return db.update(sql, params);
	}
	//修改状态 （删除）
	public int del(String ids){
		List<Object> params=new ArrayList<Object>();
		String sql="update book set bstate=0 where bookid=?";
				params.add(ids);
		
		return db.update(sql, params);
	}
	//修改
	public int update(String bookid,String bookname,String bookauthor,String bookph,String bookprice,String classid,byte[] jpg,boolean isChangePic) {
		List<Object> params=new ArrayList<Object>();
		String sql="update book set bookname=?,bookauthor=?,bookph=?,bookprice=?,classid=?";
		params.add(bookname);
		params.add(bookauthor);
		params.add(bookph);
		params.add(bookprice);
		params.add(classid);
		
		if(isChangePic){
			sql+=",jpg=?";
			params.add(jpg);
		}
		sql+=" where bookid=?";
		params.add(bookid);
		return db.update(sql, params);
	}
	
	
	
}
