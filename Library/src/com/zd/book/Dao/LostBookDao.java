package com.zd.book.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.book.util.DBHelper;







public class LostBookDao {
	private DBHelper db=new DBHelper();
	
	/**
	 * 查询书籍价格
	 * @param 
	 * @return
	 */
	public List<Map<String,Object>> find(String bookname){
		String sql="select bookprice from book where bookname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(bookname);
		
		return db.find(sql, params);
	}
	/**
	 * 添加后修改借阅表罚单金额
	 * @param 
	 * @return
	 */
	public int update(String ticket,String bid){
		String sql="update borrow set ticket=? , state='已缴纳' where bid=? and hdate is null ";
		
		List<Object> params=new ArrayList<Object>();
		params.add(ticket);
		params.add(bid);
		System.out.println(666);
		return db.update(sql, params);
	}
	
	/**
	 * 查询显示丢失书籍信息
	 * @param tiaojian
	 * @param jieguo
	 * @return
	 */
	public List<Map<String,Object>> find(){
		String sql="select bd.bid,b.bookname,bc.classname,b.bookph,bd.booksdate,bo.stuid,us.sname"
				   +" from book b"
				   +" inner join bookdetailed bd"
				   +" on bd.bookid=b.bookid"
				   +" inner join bookclass bc"
				   +" on b.classid=bc.classid"
				   +" inner join borrow bo"
				   +" on bo.bid=bd.bid"
				   +" inner join userinfo us"
				   +" on us.userid=bo.stuid"
				   +" where bd.booksdate is not null "
				   +" order by bd.booksdate";
		return db.find(sql, null);
	}
	
	
	/**
	 * 查询显示丢失书籍信息
	 * @param tiaojian
	 * @param jieguo
	 * @return
	 */
	public List<Map<String,Object>> find(String tiaojian,String jieguo){
		String sql="select bd.bid,b.bookname,bc.classname,b.bookph,bd.booksdate,bo.stuid,us.sname"
				   +" from book b"
				   +" inner join bookdetailed bd"
				   +" on bd.bookid=b.bookid"
				   +" inner join bookclass bc"
				   +" on b.classid=bc.classid"
				   +" inner join borrow bo"
				   +" on bo.bid=bd.bid"
				   +" inner join userinfo us"
				   +" on us.userid=bo.stuid"
				   +" where "+tiaojian+"=? and bd.booksdate is not null";
		
		List<Object> params=new ArrayList<Object>();
		params.add(jieguo);
		
		return db.find(sql, params);
	}
	
	/**
	 * 判断输入的书籍在数据库中是否存在
	 * @param panduan
	 * @return
	 */
	public List<Map<String,Object>> findpanduan1(String panduan){
		String sql="select * from book where bookname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(panduan);
		
		return db.find(sql, params);
	}
	/**
	 * 判断输入的书籍编号在书籍库中是否存在
	 * @param panduan
	 * @return
	 */
	public List<Map<String,Object>> findpanduan2(String panduan){
		String sql="select * from bookdetailed where bid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(panduan);
		
		return db.find(sql, params);
	}
	
	
	//------------------------------------------------
	
	/**
	 * 修改书籍详细表中这本书的状态，将丢失日期修改,删除书籍时
	 * @param bianhao
	 * @return
	 */
	public int updatebookdetailed(String bianhao){
		String sql="update bookdetailed set booksdate='',bookstate='未借出' where bid=? ";
		
		List<Object> params=new ArrayList<Object>();
		params.add(bianhao);
		
		return db.update(sql, params);
	}
	
	
	/**
	 * 修改书籍详细表中这本书的状态，将丢失日期修改
	 * @param bianhao
	 * @return
	 */
	public int updatebookdetaileds(String shijian,String bid){
		String sql="update bookdetailed set booksdate=to_date(?,'yyyy-mm-dd') where bid=? ";
		
		List<Object> params=new ArrayList<Object>();
		params.add(shijian);
		params.add(bid);
		System.out.println(123);
		return db.update(sql, params);
	}
	
	
	/**
	 * 修改书籍详细表中这本书的状态，将丢失日期修改,找回书籍时
	 * @param bianhao
	 * @return
	 */
	public int updatebookdetailedss(String bianhao){
		String sql="update bookdetailed set booksdate='',bookstate=default where bid=? ";
		
		List<Object> params=new ArrayList<Object>();
		params.add(bianhao);
		
		return db.update(sql, params);
	}
	
	
	
	
}
