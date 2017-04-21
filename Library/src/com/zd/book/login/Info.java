package com.zd.book.login;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ibm.icu.text.SimpleDateFormat;
import com.zd.book.util.DataInfo;
import com.zd.book.util.Music;
import com.zd.book.util.TrayUtil;
import com.zd.book.Dao.TicketDao;
import com.zd.book.ui.*;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Button;

public class Info{

	protected Shell shell;
	private Tray tray;
	private Display display ;
	private TicketDao ticketDao=new TicketDao();
	private ShowWeather showWeather=new ShowWeather();

	private boolean flag;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Info window = new Info();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws MessagingException 
	 */
	public void open() throws MessagingException {
		flag=true;
		display = Display.getDefault();
		createContents();
		shell.open();
		tianqi();//天气预报
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws MessagingException 
	 */
	protected void createContents() throws MessagingException {
		shell = new Shell();
		shell.setMinimumSize(new Point(844, 474));
		shell.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		shell.setFont(SWTResourceManager.getFont("楷体", 14, SWT.BOLD | SWT.ITALIC));
		shell.setSize(844, 474);
		shell.setImage(SWTResourceManager.getImage(Info.class, "/images/log.ico"));
		
		shell.setMaximized(true);
		shell.setText("图书管理系统");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		tray=display.getSystemTray();
		TrayUtil trayUtil=new TrayUtil(shell,tray);
		trayUtil.tray();

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setBackgroundImage(SWTResourceManager.getImage(Info.class, "/images/界面图片.jpg"));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(null);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		Tree tree = new Tree(composite, SWT.BORDER);
		tree.setFont(SWTResourceManager.getFont("楷体", 11, SWT.BOLD));
		tree.setBackgroundMode(SWT.INHERIT_DEFAULT);
		tree.setBackgroundImage(SWTResourceManager.getImage(Info.class, "/images/2007310371712858.bmp"));


		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setText("图书借还管理");

		TreeItem treeItem_1 = new TreeItem(treeItem, SWT.NONE);
		treeItem_1.setText("借书");
		treeItem_1.setExpanded(true);

		TreeItem treeItem_2 = new TreeItem(treeItem, SWT.NONE);
		treeItem_2.setText("还书");
		treeItem_2.setExpanded(true);

		TreeItem trtmNewTreeitem = new TreeItem(treeItem, SWT.NONE);
		trtmNewTreeitem.setText("续借");

		TreeItem treeItem_3 = new TreeItem(treeItem, SWT.NONE);
		treeItem_3.setText("丢失书籍");

		TreeItem trtmNewTreeitem_3 = new TreeItem(treeItem, SWT.NONE);
		trtmNewTreeitem_3.setFont(SWTResourceManager.getFont("楷体", 10, SWT.BOLD));
		trtmNewTreeitem_3.setText("书籍借阅报表");
		treeItem.setExpanded(true);

		TreeItem treeItem_4 = new TreeItem(tree, SWT.NONE);
		treeItem_4.setText("图书信息管理");

		TreeItem treeItem_5 = new TreeItem(treeItem_4, SWT.NONE);
		treeItem_5.setText("书籍管理");
		treeItem_4.setExpanded(true);

		TreeItem treeItem_7 = new TreeItem(tree, SWT.NONE);
		treeItem_7.setText("图书类别管理");

		TreeItem treeItem_8 = new TreeItem(treeItem_7, SWT.NONE);
		treeItem_8.setText("类别信息及修改");
		treeItem_7.setExpanded(true);

		TreeItem treeItem_13 = new TreeItem(tree, SWT.NONE);
		treeItem_13.setText("用户信息管理");

		TreeItem treeItem_16 = new TreeItem(treeItem_13, SWT.NONE);
		treeItem_16.setText("新用户注册");

		TreeItem treeItem_17 = new TreeItem(treeItem_13, SWT.NONE);
		treeItem_17.setText("罚单管理");

		TreeItem treeItem_19 = new TreeItem(treeItem_13, SWT.NONE);
		treeItem_19.setText("借阅等级设置");

		TreeItem trtmNewTreeitem_5 = new TreeItem(treeItem_13, SWT.NONE);
		trtmNewTreeitem_5.setFont(SWTResourceManager.getFont("楷体", 10, SWT.BOLD));
		trtmNewTreeitem_5.setText("信息查询与修改");
		treeItem_13.setExpanded(true);

		TreeItem treeItem_9 = new TreeItem(tree, SWT.NONE);
		treeItem_9.setText("新书管理");

		TreeItem treeItem_10 = new TreeItem(treeItem_9, SWT.NONE);
		treeItem_10.setText("新书下单");

		TreeItem treeItem_11 = new TreeItem(treeItem_9, SWT.NONE);
		treeItem_11.setText("新书验收");
		treeItem_11.setExpanded(true);

		TreeItem treeItem_12 = new TreeItem(treeItem_9, SWT.NONE);
		treeItem_12.setText("新书上架");

		TreeItem treeItem_6 = new TreeItem(treeItem_9, SWT.NONE);
		treeItem_6.setText("订单报表");
		treeItem_9.setExpanded(true);

		TreeItem treeItem_20 = new TreeItem(tree, SWT.NONE);
		treeItem_20.setText("管理员信息修改");

		TreeItem trtmNewTreeitem_1 = new TreeItem(treeItem_20, SWT.NONE);
		trtmNewTreeitem_1.setText("信息修改");
		treeItem_20.setExpanded(true);

		TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_2.setText("发送邮件");

		TreeItem trtmNewTreeitem_4 = new TreeItem(trtmNewTreeitem_2, SWT.NONE);
		trtmNewTreeitem_4.setText("邮件发送");
		trtmNewTreeitem_2.setExpanded(true);
		
		TreeItem treeItem_14 = new TreeItem(tree, SWT.NONE);
		treeItem_14.setText("条形码");
		
		TreeItem treeItem_15 = new TreeItem(treeItem_14, SWT.NONE);
		treeItem_15.setText("条形码与参考表");
		treeItem_14.setExpanded(true);

		SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);

		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setBackgroundImage(null);
		composite_1.setLayout(DataInfo.stackLayout);
		BookImage bookImage = new BookImage(composite_1,SWT.NONE);
		bookImage.setSize(664, 455);
		DataInfo.bookImage=bookImage;
		BookClass bookClass = new BookClass(composite_1,SWT.NONE);
		bookClass.setSize(347, 430);
		DataInfo.bookClass =bookClass;
		BookInfo bookInfo = new BookInfo(composite_1,SWT.NONE);
		bookInfo.setSize(117, 430);
		DataInfo.bookInfo=bookInfo;
		Acceptance acceptance = new Acceptance(composite_1,SWT.NONE);
		acceptance.setSize(761, 694);
		DataInfo.acceptance=acceptance;
		DataInfo.borrowBook=new BorrowBook(composite_1,SWT.NONE);
		DataInfo.lostBook=new LostBook(composite_1,SWT.NONE);
		DataInfo.order=new Order(composite_1,SWT.NONE);
		DataInfo.putawayBook=new PutawayBook(composite_1,SWT.NONE);
		DataInfo.renewBook=new RenewBook(composite_1,SWT.NONE);
		DataInfo.returnBook=new ReturnBook(composite_1,SWT.NONE);
		DataInfo.bookReport=new BookReport(composite_1,SWT.NONE);
		DataInfo.orderReport=new OrderReport(composite_1,SWT.NONE);

		DataInfo.zhuce=new Zhuce(composite_1,SWT.NONE);
		DataInfo.guanli=new Fadan(composite_1,SWT.NONE);
		DataInfo.dengji=new Dengji(composite_1,SWT.NONE);
		DataInfo.xinxi=new Xinxi(composite_1,SWT.NONE);
		DataInfo.guanlixinxi=new Guanlixinxi(composite_1,SWT.NONE);
		DataInfo.mail=new Mail(composite_1,SWT.NONE);
		DataInfo.barcode=new Barcode(composite_1,SWT.NONE);





		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(Info.class, "/images/图书管理1.jpg"));

		Label label = new Label(composite_2, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setFont(SWTResourceManager.getFont("楷体", 13, SWT.BOLD | SWT.ITALIC));
		label.setBounds(8, 580, 92, 27);
		label.setText("系统时间：");

		final Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel.setBounds(15, 610, 141, 50);

		final Button btnNewButton = new Button(composite_2, SWT.NONE);
		if(Music.flags==1){
			btnNewButton.setImage(SWTResourceManager.getImage(Info.class, "/images/停止图标.jpg"));
		}else{
			
			btnNewButton.setImage(SWTResourceManager.getImage(Info.class, "/images/播放图标.jpg"));
		}
		
		btnNewButton.setBounds(40, 50, 64, 64);
		sashForm_1.setWeights(new int[] {760, 94});
		sashForm.setWeights(new int[] {121, 857});

		new Thread(){
			public void run(){
				while(flag){
					lblNewLabel.getDisplay().asyncExec(new Runnable(){
						public void run() {
							SimpleDateFormat sdf=new SimpleDateFormat("   HH:mm:ss\n\n  yyyy-MM-dd");
							String s=sdf.format(new Date());
							lblNewLabel.setText(s);
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();




		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem t=(TreeItem) e.item;
				String txt=t.getText().trim();

				DataInfo.stackLayout.topControl=DataInfo.bookImage;
				DataInfo.bookImage.setVisible(true);
				DataInfo.bookClass.setVisible(false);
				DataInfo.bookInfo.setVisible(false);
				DataInfo.acceptance.setVisible(false);
				DataInfo.borrowBook.setVisible(false);
				DataInfo.lostBook.setVisible(false);
				DataInfo.order.setVisible(false);
				DataInfo.putawayBook.setVisible(false);
				DataInfo.renewBook.setVisible(false);
				DataInfo.returnBook.setVisible(false);
				DataInfo.bookReport.setVisible(false);
				DataInfo.orderReport.setVisible(false);

				DataInfo.zhuce.setVisible(false);
				DataInfo.guanli.setVisible(false);
				DataInfo.dengji.setVisible(false);
				DataInfo.xinxi.setVisible(false);
				DataInfo.guanlixinxi.setVisible(false);
				DataInfo.mail.setVisible(false);
				DataInfo.barcode.setVisible(false);

				if("书籍管理".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.bookInfo;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(true);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);


				}else if("类别信息及修改".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.bookClass;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(true);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("新书验收".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.acceptance;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(true);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("借书".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.borrowBook;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(true);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("丢失书籍".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.lostBook;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(true);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("新书下单".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.order;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(true);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("新书上架".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.putawayBook;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(true);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("续借".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.renewBook;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(true);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("还书".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.returnBook;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(true);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("书籍借阅报表".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.bookReport;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(true);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("订单报表".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.orderReport;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(true);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("新用户注册".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.zhuce;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(true);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("罚单管理".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.guanli;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(true);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("信息查询与修改".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.xinxi;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(true);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("借阅等级设置".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.dengji;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(true);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("信息修改".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.guanlixinxi;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(true);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(false);

				}else if("邮件发送".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.mail;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(true);
					DataInfo.barcode.setVisible(false);

				}else if("条形码与参考表".equals(txt)){
					DataInfo.stackLayout.topControl=DataInfo.barcode;
					DataInfo.bookImage.setVisible(false);
					DataInfo.bookClass.setVisible(false);
					DataInfo.bookInfo.setVisible(false);
					DataInfo.acceptance.setVisible(false);
					DataInfo.borrowBook.setVisible(false);
					DataInfo.lostBook.setVisible(false);
					DataInfo.order.setVisible(false);
					DataInfo.putawayBook.setVisible(false);
					DataInfo.renewBook.setVisible(false);
					DataInfo.returnBook.setVisible(false);
					DataInfo.bookReport.setVisible(false);
					DataInfo.orderReport.setVisible(false);
					DataInfo.zhuce.setVisible(false);
					DataInfo.guanli.setVisible(false);
					DataInfo.dengji.setVisible(false);
					DataInfo.xinxi.setVisible(false);
					DataInfo.guanlixinxi.setVisible(false);
					DataInfo.mail.setVisible(false);
					DataInfo.barcode.setVisible(true);

				}
			}
		});
		//罚单自动添加的操作

		List<Map<String,Object>> list=ticketDao.find();
		
		for(Map<String,Object> map:list){
			if(String.valueOf(map.get("G")).substring(0,1).equals("-")){
				
			}else{
			
				int a=Integer.parseInt(String.valueOf(map.get("G")).substring(0, String.valueOf(map.get("G")).lastIndexOf(".")));
				
				String brid=String.valueOf(map.get("BRID"));

				if(ticketDao.update(a, brid)>0){

				}else{
					
				}
			}
		}
		//天气预报
		
		
		
		
		
		
		//背景音乐
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(Music.flags==1){
					Music.over();
					btnNewButton.setImage(SWTResourceManager.getImage(Info.class, "/images/播放图标.jpg"));
				}else{
					try {
						Music.begin();
						btnNewButton.setImage(SWTResourceManager.getImage(Info.class, "/images/停止图标.jpg"));
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);


		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				flag=false;
				tray.dispose();
				if(Music.flags==1){
					Music.over();
				}
			}
		});

	}
	//天气预报
	public void tianqi(){
		showWeather.open();
	}
}
