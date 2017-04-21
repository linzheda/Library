package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;
import com.zd.book.util.Data;

public class BespeakDao {
	private DBHelper db=new DBHelper();
	//查询
	public List<Map<String,Object>> find(){
		String sql="select b.bookid,b.bookname,b.bookauthor,b.bookph,sum(case when bd.bookstate='未借出' then 1 else 0 end) no,bc.classname  from book b "
				+ "inner join bookdetailed bd on b.bookid=bd.bookid "
				+ "inner join bookclass  bc on b.classid=bc.classid "
				+ "group by b.bookname,b.bookauthor,b.bookph,bc.classname,b.bstate,b.bookid "
				+ "having b.bstate=1";
		return db.find(sql, null);
	}
	//查询
	public List<Map<String,Object>> finds(String tiaojian,String jieguo){
		String sql="select b.bookid,b.bookname,b.bookauthor,b.bookph,sum(case when bd.bookstate='未借出' then 1 else 0 end) no,bc.classname  from book b "
				+ " inner join bookdetailed bd on b.bookid=bd.bookid "
				+ " inner join bookclass  bc on b.classid=bc.classid "
				+ " group by b.bookname,b.bookauthor,b.bookph,bc.classname,b.bstate,b.bookid "
				+ " having b.bstate=1 and "+tiaojian+"=?";
		List<Object> params=new ArrayList<Object>();	
		params.add(jieguo);
		return db.find(sql, params);
	}
	// add
	public int add(String bookid){
		String userid=Data.username;
		String sql="insert into bespeak values(seq_bespeakid.nextval,?,?,sysdate,default,default,'','')";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		params.add(bookid);
		return db.update(sql, params);
	}
}
