package com.zd.book.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class XinxiDao {
	private DBHelper db=new DBHelper();
	/**
	 * 查询rankname
	 */

	public List<Map<String,Object>> find2(){
		String sql="select rankname from rank";
		return db.find(sql, null);
	}
	/**
	 * 查询rankid
	 */

	public List<Map<String,Object>> find3(String rankname){
		String sql="select rankid from rank where rankname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(rankname);
		return db.find(sql, params);
	}
	/**
	 * 查询所有的方法
	 * @return
	 */
	public List<Map<String,Object>> find(){
		String sql="select u.userid,u.sname,u.sex,u.useridentity,u.type,u.mail,u.usertel,u.udate,r.rankname,r.rankid,u.userstate "
				+ " from userinfo u,rank r "
				+ " where u.rankid=r.rankid ";
		return db.find(sql, null);
	}
	/**
	 * 按条件查询
	 */
	public List<Map<String,Object>> find1(String tiaojian,String jieguo){
		String sql="select u.userid,u.pwad,u.sname,u.sex,u.useridentity,u.type,u.mail,u.usertel,u.udate,r.rankname,u.userstate "
				+ " from userinfo u , rank r"
				+ " where u.rankid=r.rankid and "+tiaojian+"=?";
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);
	}
	/**
	 * 修改
	 * @param sname
	 * @param sex
	 * @param useridentity
	 * @param type
	 * @param mail
	 * @param usertel
	 * @param rankid
	 * @param userstate
	 * @param userid
	 * @return
	 */
	public int update(String sname,String sex,String useridentity,String type,String mail,BigInteger usertel,String rankid,String userstate,int userid){
		String sql="update userinfo set sname=?,sex=?,useridentity=?,type=?,mail=?,usertel=?,rankid=?,userstate=? where userid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(sname);
		params.add(sex);
		params.add(useridentity);
		params.add(type);
		params.add(mail);
		params.add(usertel);
		params.add(rankid);
		params.add(userstate);
		params.add(userid);
		return db.update(sql, params);
		
	}
	/**
	 * 查询等级的方法
	 * @return
	 */
	public List<Map<String,Object>> find1(){
		String sql="select rankname from rank";
		return db.find(sql, null);
	}
	//重置密码
	public int updatePwad(String userid){
		List<Object> params=new ArrayList<Object>();
		String sql="update userinfo set pwad='a' where userid=?";
		params.add(userid);
		return db.update(sql, params);
	}
}
