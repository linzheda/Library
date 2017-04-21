package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;





public class ReturnBookDao {
	private DBHelper db=new DBHelper();

	
	public List<Map<String,Object>> find(){
		String sql="select bo.state,bo.ticket,r.datenum,bo.renew,ui.sname,ui.userid,bd.bid,b.bookname,bo.bdate, "
				  +" case when bo.renew is null then (r.datenum+bo.bdate) else (bo.renew+r.datenum) end as enddate, "
				  +" to_char(case when bo.renew is null then (r.datenum+bo.bdate)-sysdate else (bo.renew+r.datenum)-sysdate end )as sy,bo.hdate "
				  +" from rank r,borrow bo,userinfo ui,bookdetailed bd,book b "
				  +" where r.rankid=ui.rankid and bo.stuid=ui.userid and bo.bid=bd.bid and bd.bookid=b.bookid and bo.hdate is null and bd.booksdate is null"
				  +" order by enddate ";
	
		return db.find(sql, null);

	}
	
	public List<Map<String,Object>> find(String tiaojian,String jieguo){
		String sql="select bo.state,bo.ticket,r.datenum,bo.renew,ui.sname,ui.userid,bd.bid,b.bookname,bo.bdate, "
				  +" case when bo.renew is null then (r.datenum+bo.bdate) else (bo.renew+r.datenum) end as enddate, "
				  +" to_char(case when bo.renew is null then (r.datenum+bo.bdate)-sysdate else (bo.renew+r.datenum)-sysdate end )as sy,bo.hdate "
				  +" from rank r,borrow bo,userinfo ui,bookdetailed bd,book b "
				  +" where r.rankid=ui.rankid and bo.stuid=ui.userid and bo.bid=bd.bid and bd.bookid=b.bookid and "+tiaojian+"=? and bo.hdate is null and bd.booksdate is  null "
				  +" order by enddate ";
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		return db.find(sql, params);

	}

	
	
	//----------------------------------------------------------------------------
	
	/**
	 * 点击还书时，修改borrow表 ，修改还书日期
	 * @param bid
	 * @return
	 */
	public int updateborrow(String bid){
		String sql="update borrow set hdate=sysdate where bid=? ";
		
		List<Object> params=new ArrayList<Object>();
		params.add(bid);
		return db.update(sql, params);
	}
	
	public int updateborrows(String bid){
		String sql="update borrow set state='已缴纳' where bid=?";
		
		List<Object> params=new ArrayList<Object>();
		params.add(bid);
		return db.update(sql, params);
	}

	
	public int updatebookdetailed(String bid){
		String sql="update bookdetailed set bookstate=default where bid=? ";
		
		List<Object> params=new ArrayList<Object>();
		params.add(bid);
		return db.update(sql, params);
	}

	
	

}
