package com.zd.book.util;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.wb.swt.SWTResourceManager;


public class TrayUtil {
	//private Display display;
	private Shell shell;
	private Tray tray;
	private Menu menu;


	public TrayUtil( Shell shell, Tray tray) {
		super();
		this.shell = shell;
		this.tray = tray;
	}
	public void tray(){
		//tray=display.getSystemTray();//获取系统托盘
		if(tray==null){
			MessageDialog.openError(shell, "错误提示", "您的系统暂不支持托盘....");
		}else{
			TrayItem item=new TrayItem(tray,SWT.NONE);
			item.setToolTipText("图书管理系统");
			item.setImage(SWTResourceManager.getImage(TrayUtil.class, "/images/log.ico"));


			menu=new Menu(shell,SWT.POP_UP);
			item.addListener(SWT.MenuDetect,new Listener(){
				@Override
				public void handleEvent(Event arg0) {
					menu.setVisible(true);
				}
			});

			MenuItem max=new MenuItem(menu,SWT.PUSH);
			max.setText("最大化");
			max.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
				}
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					shell.setVisible(true);
					shell.setMaximized(true);
				}
			});

			MenuItem min=new MenuItem(menu,SWT.PUSH);
			min.setText("最小化");
			min.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
				}
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					shell.setVisible(true);
					shell.setMinimized(true);
				}
			});

			new MenuItem(menu,SWT.SEPARATOR);



			MenuItem close=new MenuItem(menu,SWT.PUSH);
			close.setText("关闭");
			close.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
				}
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					tray.dispose();
					shell.dispose();

				}
			});








		}




	}

}
