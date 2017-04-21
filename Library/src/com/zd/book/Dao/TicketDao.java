package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;


public class TicketDao {
	private DBHelper db=new DBHelper();
	
	//查询借阅表
	public List<Map<String,Object>> find(){		
		String sql="select bd.bid,b.bookname,bo.brid,u.userid,bo.bdate,bo.hdate,bo.renew,bo.ticket,"
				+ " to_char(case when  bo.renew is null then sysdate-(bo.bdate+r.datenum) else sysdate-(bo.renew+r.datenum) end )as g "
				+" from bookdetailed bd , book b,borrow bo,userInfo u,rank r "
				+" where bd.bookid=b.bookid and bo.stuid=u.userid and r.rankid=u.rankid and bo.bid=bd.bid "
				+ " and bo.state is null";
		return db.find(sql, null);
	}
//修改罚单
	public int update(int a,String brid){
		List<Object> params=new ArrayList<Object>();
		String sql="update borrow  set ticket="+a*0.1+" , state='未缴纳' where brid=? ";
		params.add(brid);
		return db.update(sql, params);
	}





}
