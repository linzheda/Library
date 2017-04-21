package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class UserDao {
	private DBHelper db=new DBHelper();
	//查询
	public  List<Map<String,Object>> find(){
		String sql="select u.userid,u.sname,r.rankname,r.numbers,sum(case when bo.hdate is null then 1 else 0 end ) sum "
				+" from userinfo u,rank r,borrow bo "
				+" where u.rankid=r.rankid and bo.stuid=u.userid "
				+" group by  u.userid,u.sname,r.rankname,r.numbers ";
		
		return db.find(sql, null);
	}
	//查询 条件
	public  List<Map<String,Object>> find(String tiaojian,String jieguo){
		List<Object> params=new ArrayList<Object>();
		String sql="select u.userid,u.sname,r.rankname,r.numbers,sum(case when bo.hdate is null then 1 else 0 end ) sum "
				+" from userinfo u,rank r,borrow bo "
				+" where u.rankid=r.rankid and bo.stuid=u.userid "
				+" group by  u.userid,u.sname,r.rankname,r.numbers "
				+ " having "+tiaojian+"=? ";
		params.add(jieguo);
		return db.find(sql, params);
	}
	//---------------------------------------------------------------------------------------
	//查询分页
	public  List<Map<String,Object>> finds(String i){
		String sql="select * from ( "
				+" select e.* ,rownum s "
				+" from (select u.userid,u.sname,r.rankname,r.numbers,sum(case when bo.hdate is null then 1 else 0 end ) sum  "
					+"	from userinfo u,rank r,borrow bo "
					+"	where u.rankid=r.rankid and bo.stuid=u.userid "
					+"	group by  u.userid,u.sname,r.rankname,r.numbers ) e where rownum<=1*10 "
					+" )where s>=("+i+"-1)*10+"+1+" ";
					return db.find(sql, null);
	}
	//--------------------------------------------------------------------------------
	public String  fY(String sql,String i){
		List<Object> params =new ArrayList<Object>();
		String sqls="select * from ( "
				+" select e.* ,rownum s"
				+" from ( "+sql+") e where rownum<=1*10 "
				+" )where s=("+i+"-1)*10+2 ";
		return sqls;
	}


}
