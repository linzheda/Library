package com.zd.book.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadInfoDao {
	private DBHelper db=new DBHelper();
	//查询借阅表
	public List<Map<String,Object>> find(){
		String sql="Select count(bo.brid) num from borrow bo,bookdetailed bd "
				+ " where  bo.bid=bd.bid and  hdate is null and stuid=? and bd.booksdate is null";
		String stuid=Data.username;
		List<Object> params=new ArrayList<Object>();
		params.add(stuid);
		return db.find(sql,params);
	}

}
