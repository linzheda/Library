package com.zd.book.Dao;

import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class BarcodeDao {
	private DBHelper db=new DBHelper();
	//查询
	public List<Map<String,Object>> find(){
		String sql="select  bd.bid,b.bookname,b.bookauthor,b.bookph,bc.classname "
				+ " from bookdetailed bd,book b,bookclass bc "
				+ " where bd.bookid=b.bookid and b.classid=bc.classid ";
				
		return db.find(sql, null);
	}
	
	
	
	
	
	
}
