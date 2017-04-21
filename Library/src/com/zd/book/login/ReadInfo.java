package com.zd.book.login;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ibm.icu.text.SimpleDateFormat;
import com.zd.book.read.Bespeak;
import com.zd.book.read.BorrowInfo;
import com.zd.book.read.ReaderInfo;
import com.zd.book.read.Remind;
import com.zd.book.read.Renew;
import com.zd.book.read.ReportForms;
import com.zd.book.ui.BookImage;
import com.zd.book.util.Music;
import com.zd.book.util.ReadDataInfo;
import com.zd.book.util.ReadInfoDao;
import com.zd.book.util.TrayUtil;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class ReadInfo{

	protected Shell shell;
	private Tray tray;
	private Display display ;
	private boolean flag;
	private ReadInfoDao readInfoDao=new ReadInfoDao();
	private Remind remind=new Remind();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ReadInfo window = new ReadInfo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		flag=true;
		display = Display.getDefault();
		createContents();
		shell.open();
		tishi();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(844, 474);
		shell.setImage(SWTResourceManager.getImage(ReadInfo.class, "/images/log.ico"));
		shell.setMaximized(true);
		shell.setText("图书管理系统");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		tray=display.getSystemTray();
		TrayUtil trayUtil=new TrayUtil(shell,tray);
		trayUtil.tray();

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setBackgroundImage(SWTResourceManager.getImage(ReadInfo.class, "/images/界面图片.jpg"));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		Tree tree = new Tree(composite, SWT.BORDER);
		tree.setFont(SWTResourceManager.getFont("楷体", 11, SWT.BOLD));
		tree.setBackgroundImage(SWTResourceManager.getImage(ReadInfo.class, "/images/2007310371712858.bmp"));


		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setText("借阅系统");

		TreeItem treeItem_1 = new TreeItem(treeItem, SWT.NONE);
		treeItem_1.setText("预约借书");
		treeItem_1.setExpanded(true);

		TreeItem trtmNewTreeitem = new TreeItem(treeItem, SWT.NONE);
		trtmNewTreeitem.setText("续借");
		treeItem.setExpanded(true);

		TreeItem treeItem_2 = new TreeItem(tree, SWT.NONE);
		treeItem_2.setText("个人信息");

		TreeItem treeItem_3 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_3.setText("基本信息");

		TreeItem treeItem_4 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_4.setText("借阅信息");
		treeItem_2.setExpanded(true);

		SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);

		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setBackgroundImage(null);
		composite_1.setLayout(ReadDataInfo.stackLayout);

		ReadDataInfo.bookImage=new BookImage(composite_1,SWT.NONE);
		ReadDataInfo.bespeak=new Bespeak(composite_1,SWT.NONE);
		ReadDataInfo.readerInfo=new ReaderInfo(composite_1,SWT.NONE);
		ReadDataInfo.borrowInfo=new BorrowInfo(composite_1,SWT.NONE);
		ReadDataInfo.renew=new Renew(composite_1,SWT.NONE);
		ReadDataInfo.reportForms=new ReportForms(composite_1,SWT.NONE);

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_2.setBackgroundImage(SWTResourceManager.getImage(ReadInfo.class, "/images/图书管理1.jpg"));

		Label label = new Label(composite_2, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 13, SWT.BOLD | SWT.ITALIC));
		label.setBounds(12, 580, 92, 27);
		label.setText("系统时间：");

		final Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBounds(15, 610, 141, 50);
		final Button btnNewButton = new Button(composite_2, SWT.NONE);
		if(Music.flags==1){
			btnNewButton.setImage(SWTResourceManager.getImage(Info.class, "/images/停止图标.jpg"));
		}else{

			btnNewButton.setImage(SWTResourceManager.getImage(Info.class, "/images/播放图标.jpg"));
		}
		btnNewButton.setBounds(40, 50, 64, 64);
		sashForm_1.setWeights(new int[] {664, 87});
		sashForm.setWeights(new int[] {100, 754});
		new Thread(){
			public void run(){
				while(flag){
					lblNewLabel.getDisplay().asyncExec(new Runnable(){
						@Override
						public void run() {
							SimpleDateFormat sdf=new SimpleDateFormat("   HH:mm:ss\n\n  yyyy-MM-dd");
							String s=sdf.format(new Date());
							lblNewLabel.setText(s);
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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

				ReadDataInfo.stackLayout.topControl=ReadDataInfo.bookImage;
				ReadDataInfo.bespeak.setVisible(false);
				ReadDataInfo.readerInfo.setVisible(false);
				ReadDataInfo.borrowInfo.setVisible(false);
				ReadDataInfo.bookImage.setVisible(true);
				ReadDataInfo.renew.setVisible(false);
				ReadDataInfo.reportForms.setVisible(false);
				if("基本信息".equals(txt)){
					ReadDataInfo.stackLayout.topControl=ReadDataInfo.readerInfo;
					ReadDataInfo.bespeak.setVisible(false);
					ReadDataInfo.readerInfo.setVisible(true);
					ReadDataInfo.bookImage.setVisible(false);
					ReadDataInfo.borrowInfo.setVisible(false);
					ReadDataInfo.renew.setVisible(false);
					ReadDataInfo.reportForms.setVisible(false);
				}else if("预约借书".equals(txt)){
					ReadDataInfo.stackLayout.topControl=ReadDataInfo.bespeak;
					ReadDataInfo.bespeak.setVisible(true);
					ReadDataInfo.readerInfo.setVisible(false);
					ReadDataInfo.bookImage.setVisible(false);
					ReadDataInfo.borrowInfo.setVisible(false);
					ReadDataInfo.renew.setVisible(false);
					ReadDataInfo.reportForms.setVisible(false);
				}else if("借阅信息".equals(txt)){
					ReadDataInfo.stackLayout.topControl=ReadDataInfo.borrowInfo;
					ReadDataInfo.bespeak.setVisible(false);
					ReadDataInfo.borrowInfo.setVisible(true);
					ReadDataInfo.readerInfo.setVisible(false);
					ReadDataInfo.bookImage.setVisible(false);
					ReadDataInfo.renew.setVisible(false);
					ReadDataInfo.reportForms.setVisible(false);
				}else if("续借".equals(txt)){
					ReadDataInfo.stackLayout.topControl=ReadDataInfo.renew;
					ReadDataInfo.renew.setVisible(true);
					ReadDataInfo.bespeak.setVisible(false);
					ReadDataInfo.borrowInfo.setVisible(false);
					ReadDataInfo.readerInfo.setVisible(false);
					ReadDataInfo.bookImage.setVisible(false);
					ReadDataInfo.reportForms.setVisible(false);
				}else if("借阅报表".equals(txt)){
					ReadDataInfo.stackLayout.topControl=ReadDataInfo.reportForms;
					ReadDataInfo.renew.setVisible(false);
					ReadDataInfo.bespeak.setVisible(false);
					ReadDataInfo.borrowInfo.setVisible(false);
					ReadDataInfo.readerInfo.setVisible(false);
					ReadDataInfo.bookImage.setVisible(false);
					ReadDataInfo.reportForms.setVisible(true);
				}
			}
		});


		//音乐
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


	public void tishi(){
		//几本书未归还
		List<Map<String,Object>> list=readInfoDao.find();
		if(list!=null && list.size()>0){
			String num="";
			for(Map<String,Object> map:list){
				num=String.valueOf(map.get("NUM"));

			}
			if(num.equals("0")){

			}else{
				remind.num=num;
				remind.open();
			}

		} 
	}
}
