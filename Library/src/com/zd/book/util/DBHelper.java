package com.zd.book.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHelper {
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	/**
	 * 加载驱动
	 */
	static{
		try {
			Class.forName(ReadPro.pro.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			LogUtil.log.error(e.toString());
		}
	}
	/**
	 *获取连接 
	 * @return
	 */
	public Connection getConnection(){
		try {
			con=DriverManager.getConnection(ReadPro.pro.getProperty("url"), ReadPro.pro.getProperty("user"), ReadPro.pro.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log.error(e.toString());

		}
		return con;
	}
	/**
	 * 关闭所有
	 * @param con
	 * @param pstmt
	 * @param rs
	 */
	public void closeAll( Connection con,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LogUtil.log.error(e.toString());

			}
		}

		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LogUtil.log.error(e.toString());

			}
		}

		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LogUtil.log.error(e.toString());

			}
		}
	}
	/**
	 * 获取占位符
	 * @param pstmt预编译
	 * @param params对应占位符的值
	 */
	public void setValue(PreparedStatement pstmt,List<Object> params){
		if(pstmt!=null&& params !=null&&params.size()>0){
			Object obj=null;
			String typeName;
			for(int i=0,len=params.size();i<len;i++){
				obj=params.get(i);
				try {
					if(obj!=null){
						typeName=obj.getClass().getSimpleName();
						if("String".equals(typeName)){
							pstmt.setString(i+1,String.valueOf(params.get(i)));
						}else if("byte[]".equals(typeName)){
							pstmt.setBytes(i+1, (byte[])obj);
						}else{
							pstmt.setString(i+1,String.valueOf(params.get(i)));
						}
					}else{
						pstmt.setString(i+1,null);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					LogUtil.log.error(e.toString());
				}
			}
		}
	}
	/**
	 * 事物的更新操作
	 * @param sql要执行的更新语句
	 * @param params更新语句中，对应占位符？的值
	 * @return执行语句后所影响的行数
	 */
	public int update(String sql,List<Object> params){
		int result=0;
		try {
			con=this.getConnection();//获取连接
			pstmt=con.prepareStatement(sql);//预编译执行语句
			//给占位符赋值
			this.setValue(pstmt, params);
			result=pstmt.executeUpdate();//执行更新			
			con.commit();
		} catch (SQLException e) {
			result=0;
			e.printStackTrace();
			LogUtil.log.error(e.toString());
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				LogUtil.log.error(e1.toString());
			}
		}finally{
			this.closeAll(con, pstmt, null);
		}
		return result;		
	}
	/**
	 * 添加事物的更新操作
	 * @param sql要执行的更新语句
	 * @param params更新语句中，对应占位符？的值
	 * @return执行语句后所影响的行数
	 */
	public int updates(List<String> sqls,List<List<Object>> params){
		int result=0;
		try {
			con=this.getConnection();//获取连接
			con.setAutoCommit(false);//关闭自动提交
			if(sqls!=null&&sqls.size()>0){
				for(int i=0,len=sqls.size();i<len;i++){
					pstmt=con.prepareStatement(sqls.get(i));//预编译执行语句
					//给占位符赋值
					this.setValue(pstmt, params.get(i));
					result=pstmt.executeUpdate();//执行更新			
				}
			}

			con.commit();

		} catch (SQLException e) {
			result=0;
			e.printStackTrace();
			LogUtil.log.error(e.toString());
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				LogUtil.log.error(e1.toString());
			}//一旦报错回滚数据
		}finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				LogUtil.log.error(e.toString());

			}
			this.closeAll(con, pstmt, null);
		}
		return result;		
	}
	/**
	 * 查询操作
	 * @param sql要执行的语句
	 * @param params查询语句中，对应占位符？的值
	 * @return返回满足条件的所有数据
	 */
	public List<Map<String,Object>> find(String sql,List<Object> params){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt,params);//调用方法给占位赋值
			rs=pstmt.executeQuery();//获取结果集
			ResultSetMetaData rsmd=rs.getMetaData();
			int colLen=rsmd.getColumnCount();//获取结果集中列的数量
			String[] colNames=new String[colLen];
			for(int i=0;i<colLen;i++){//取出每个列的列名存放到数组中
				colNames[i]=rsmd.getColumnLabel(i+1);
			}
			Map<String,Object> map=null;
			Object obj;
			String typeName;
			while(rs.next()){//循环取值，每循环一次就是一条记录，存放到一个map中
				map=new HashMap<String,Object>();
				for(int i=0;i<colLen;i++){//循环取出每个列的值
					obj=rs.getObject(colNames[i]);
					if(obj!=null){
						typeName=obj.getClass().getSimpleName();
						if("String".equals(typeName)){
							map.put(colNames[i], rs.getString(colNames[i]));
						}else if("BLOB".equals(typeName)){
							Blob blob =rs.getBlob(colNames[i]);
							byte[] bt=null;
							BufferedInputStream bis=null;
							try {
								bis=new BufferedInputStream(blob.getBinaryStream());
								bt=new byte[(int) blob.length()];
								bis.read(bt);
								map.put(colNames[i],bt);
							} catch (IOException e) {
								e.printStackTrace();
							}finally{
								if(bis!=null){
									try {
										bis.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}else if("BigDecimal".equals(typeName)){
							//try {
							//	map.put(colNames[i], rs.getInt(colNames[i]));
							//} catch (Exception e) {
								map.put(colNames[i], rs.getObject(colNames[i]));
							//}
						}else{
							map.put(colNames[i], rs.getString(colNames[i]));
							
						}
					}else{
						map.put(colNames[i], rs.getString(colNames[i]));//以当前列名为键，以当前值为值
					}	
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log.error(e.toString());

		}
		return list;
	}
	/**
	 * 获取总记录数
	 * @param sql
	 * @param params
	 * @return
	 */
	public double getTotal(String sql,List<Object> params){
		double result=0;
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			rs=pstmt.executeQuery();

			if(rs.next()){
				result=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log.error(e.toString());

		}finally{
			this.closeAll(con, pstmt, rs);
		}
		return result;
	}
	/**
	 * 获取多列
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Double> getNumber(String sql,List<Object> params){
		List<Double> list=new ArrayList<Double>();
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			ResultSetMetaData rsmd=rs.getMetaData();
			int colLen=rsmd.getColumnCount();//获取结果集中列的数量
			if(rs.next()){
				for(int i=0;i<colLen;i++){
					list.add(rs.getDouble(i+1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log.error(e.toString());

		}
		return list;
	}
	/**
	 * 获取多行多列
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<List<Double>> getNumbers(String sql,List<Object> params){
		List<List<Double>> list=new ArrayList<List<Double>>();
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			ResultSetMetaData rsmd=rs.getMetaData();
			int colLen=rsmd.getColumnCount();//获取结果集中列的数量
			List<Double> data=null;
			while(rs.next()){
				data=new ArrayList<Double>();
				for(int i=0;i<colLen;i++){
					data.add(rs.getDouble(i+1));
				}
				list.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.log.error(e.toString());

		}finally{
			this.closeAll(con, pstmt, rs);
		}
		return list;
	}
}
