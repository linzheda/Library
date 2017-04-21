package com.zd.book.Dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;



public class ZhuceDao {
private DBHelper db=new DBHelper();
/**
 * 查询rankid
 */

public List<Map<String,Object>> find2(String rankname1){
	String sql="select rankid from rank where rankname=?";
	List<Object> params=new ArrayList<Object>();
	params.add(rankname1);
	return db.find(sql, params);
}

/**
 * 注册
 * @param pwad
 * @param sname
 * @param sex
 * @param useridentity
 * @param type
 * @param mail
 * @param usertel
 * @param rankid
 * @param userstate
 * @return
 */

	public int add(String pwad,String sname,String sex,String useridentity,String type,String mail,BigInteger usertel,int rankid,String userstate){
		String sql="insert into userinfo values(seq_userid.nextval,?,?,?,?,?,?,?,sysdate,?,?,'','')";
		List<Object> params=new ArrayList<Object>();
		params.add(pwad);
		params.add(sname);
		params.add(sex);
		params.add(useridentity);
		params.add(type);
		params.add(mail);
		params.add(usertel);
		
		params.add(rankid);
		params.add(userstate);
		
		return db.update(sql, params);
	}
	/**
	 * 查询所有的方法
	 * @return
	 */
	public List<Map<String,Object>> find(){
		String sql="select userid,pwad,sname,sex,useridentity,type,mail,usertel,udate,rankid,userstate from userinfo";
		return db.find(sql, null);
	}
	/**
	 * 查询等级的方法
	 * @return
	 */
	public List<Map<String,Object>> find1(){
		String sql="select rankname from rank";
		return db.find(sql, null);
	}
}
