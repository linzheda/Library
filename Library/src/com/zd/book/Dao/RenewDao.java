package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;
import com.zd.book.util.Data;

public class RenewDao {
	private DBHelper db=new DBHelper();

	//查询借阅表续借前
	public List<Map<String,Object>> find(){		
		String userid=Data.username;
		String sql="select bd.bid,b.* ,u.userid,bo.bdate,bo.hdate,bo.renew,bo.ticket,bo.brid,"
				+"case when  bo.renew is null then bo.bdate+r.datenum else bo.renew+r.datenum end as g "
				+"from bookdetailed bd , book b,borrow bo,userInfo u,rank r "
				+"where bd.bookid=b.bookid and bo.stuid=u.userid and r.rankid=u.rankid and bo.bid=bd.bid and bo.ticket is null "
				+ " and userid="+userid+" and bo.hdate is null and bd.booksdate is null";
		
		return db.find(sql, null);
	}
	//查询天数
	public List<Map<String,Object>> findDate(){
		String sql="select br.renew,r.datenum,u.userid,br.bid from userinfo u,rank r,borrow br  where u.rankid=r.rankid and userid=?";
		List<Object> params=new ArrayList<Object>();
		String username=Data.username;
		params.add(username);
		return db.find(sql,params);
	}
	//续借日期的插入
	public int add(String brid){
		String sql="update borrow set renew=sysdate where bid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(brid);
		return db.update(sql, params);
	}


}
