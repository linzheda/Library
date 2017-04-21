package com.zd.book.util;
import java.io.*;
import java.util.List;
import java.util.Map;



import jxl.*;   
import jxl.write.*;   

public class CreateEXCEL {
	public void CreateEXCELs(List<Map<String,Object>> list){
		try {  
			//open file.  
			WritableWorkbook book = Workbook.createWorkbook(new File("d:/预约表.xls"));  

			//create Sheet named "Sheet_1". 0 means this is 1st page.  
			WritableSheet sheet = book.createSheet("Sheet_1", 0); 
			String[] name=new String[]{"书籍编号","书名","借阅证号","预约日期","状态"};

			for(int i=0;i<5;i++){
				Label label=new Label(i,0,name[i]);
				
				sheet.addCell(label);
			}
			String[] names=new String[]{"BOOKID","BOOKNAME","USERID","BESPEAKDATE","BESPEAKSTATE"};
			int j=1;
			for(Map<String,Object> map:list){
				for(int i=0;i<5;i++){
					Label labels=new Label(i,j,String.valueOf(map.get(names[i])));
					sheet.addCell(labels);
				}
				j++;
			}

			//add defined all cell above to case.  
			book.write();  
			//close file case.  
			book.close();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

	}
	public void CreateEXCELss(List<Map<String,Object>> list){
		try {  
			//open file.  
			WritableWorkbook book = Workbook.createWorkbook(new File("d:/借阅表.xls"));  

			//create Sheet named "Sheet_1". 0 means this is 1st page.  
			WritableSheet sheet = book.createSheet("Sheet_1", 0); 
			String[] name=new String[]{"书籍编号","书名","借阅证号","借阅日期","归还日期","罚单","罚单状态"};

			for(int i=0;i<7;i++){
				Label label=new Label(i,0,name[i]);
				
				sheet.addCell(label);
			}
			String[] names=new String[]{"BOOKID","BOOKNAME","STUID","BDATE","HDATE","TICKET","STATE"};
			int j=1;
			for(Map<String,Object> map:list){
				for(int i=0;i<7;i++){
					Label labels=new Label(i,j,String.valueOf(map.get(names[i])));
					sheet.addCell(labels);
				}
				j++;
			}

			//add defined all cell above to case.  
			book.write();  
			//close file case.  
			book.close();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

	}
	public void CreateEXCELorder(List<Map<String,Object>> list){
		try {  
			//open file.  
			WritableWorkbook book = Workbook.createWorkbook(new File("d:/订单表.xls"));  

			//create Sheet named "Sheet_1". 0 means this is 1st page.  
			WritableSheet sheet = book.createSheet("Sheet_1", 0); 
			String[] name=new String[]{"订单编号","书籍名称","作者","出版社","商家","下单时间","验收时间","单价","数量","总额"};

			for(int i=0;i<10;i++){
				Label label=new Label(i,0,name[i]);
				
				sheet.addCell(label);
			}
			String[] names=new String[]{"ORDERID","BOOKNAME","BOOKAUTHOR","BOOKPH","SELLER","ORDERDATE","ACCEPT","PRICE","BOOKNUM","G"};
			int j=1;
			for(Map<String,Object> map:list){
				for(int i=0;i<10;i++){
					Label labels=new Label(i,j,String.valueOf(map.get(names[i])));
					sheet.addCell(labels);
				}
				j++;
			}

			//add defined all cell above to case.  
			book.write();  
			//close file case.  
			book.close();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

	}
	
	
	//
	public void CreateEXCELbarcode(List<Map<String,Object>> list){
		try {  
			//open file.  
			WritableWorkbook book = Workbook.createWorkbook(new File("d:/参考表.xls"));  

			//create Sheet named "Sheet_1". 0 means this is 1st page.  
			WritableSheet sheet = book.createSheet("Sheet_1", 0); 
			String[] name=new String[]{"条形码编号","书籍名称","作者","出版社","类别"};

			for(int i=0;i<5;i++){
				Label label=new Label(i,0,name[i]);
				
				sheet.addCell(label);
			}
			String[] names=new String[]{"BID","BOOKNAME","BOOKAUTHOR","BOOKPH","CLASSNAME"};
			int j=1;
			for(Map<String,Object> map:list){
				for(int i=0;i<5;i++){
					Label labels=new Label(i,j,String.valueOf(map.get(names[i])));
					sheet.addCell(labels);
				}
				j++;
			}

			//add defined all cell above to case.  
			book.write();  
			//close file case.  
			book.close();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

	}




}
