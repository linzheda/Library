package com.zd.book.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;

public class DengjiDao {
	private DBHelper db=new DBHelper();
	/**
	 * 查询
	 * @return
	 */
	public List<Map<String,Object>> find(){
		String sql="select rankname,numbers,datenum from rank";
		List<Object> params=new ArrayList<Object>();
		return db.find(sql,params);
	}
	/**
	 * 修改
	 * @param numbers
	 * @param datenum
	 * @param rankname
	 * @return
	 */
	public int update(int numbers,int datenum,String rankname){
		String sql="update rank set numbers=?,datenum=? where rankname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(numbers);
		params.add(datenum);
		params.add(rankname);
		return db.update(sql, params);
		
	}
	/**
	 * 增加
	 * @param rankname
	 * @param numbers
	 * @param datenum
	 * @return
	 */
	public int add(String rankname,int numbers,int datenum){
		String sql="insert into rank values(seq_rankid.nextval,?,?,?,'','')";
		List<Object> params=new ArrayList<Object>();
		params.add(rankname);
		params.add(numbers);
		params.add(datenum);
		
		return db.update(sql, params);
	}
	//删除
		public int del(String rankname){
			String sql="";
			List<Object> params=new ArrayList<Object>();
			if(rankname.contains(",")&&!rankname.contains(" or") ){//说明删除多条记录
				sql="delete from rank where rankname in("+rankname+")";
			}else{
				sql="delete from rank where rankname=?";
				params.add(rankname);
			}
			return db.update(sql, params);
		}
}






