package com.zd.book.util;

import org.eclipse.swt.custom.StackLayout;

import com.zd.book.ui.Acceptance;
import com.zd.book.ui.BookClass;
import com.zd.book.ui.BookImage;
import com.zd.book.ui.BookInfo;
import com.zd.book.ui.BorrowBook;
import com.zd.book.ui.*;


public class DataInfo {
	public static StackLayout stackLayout=new StackLayout();
	public static BookInfo bookInfo;
	public static BookClass bookClass;
	public static BookImage bookImage;
	public static Acceptance acceptance;//新书验收
	public static BorrowBook borrowBook;//借书
	public static LostBook lostBook;//丢失书籍
	public static Order order;//新书下单
	public static PutawayBook putawayBook;//新书上架
	public static RenewBook renewBook;//续借书籍
	public static ReturnBook returnBook;//还书
	public static BookReport bookReport;//书籍报表
	public static OrderReport orderReport;//订单报表
	
	public static Zhuce zhuce;//注册
	public static Fadan guanli;//管理
	public static Dengji dengji;//等级
	public static Xinxi xinxi;//信息
	public static Guanlixinxi guanlixinxi;//管理员信息
	public static Mail mail;//邮件
	public static Barcode barcode;
}
