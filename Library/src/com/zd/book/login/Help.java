package com.zd.book.login;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;

public class Help {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Help window = new Help();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
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
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setImage(SWTResourceManager.getImage(Help.class, "/images/log.ico"));
		shell.setSize(483, 392);
		shell.setText("使用说明");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 12, SWT.NORMAL));
		lblNewLabel.setText("\r\n--等级升级规则\r\n--借阅者的等级是根据借阅者归还过的书本的本数来划分：\r\n          归还过的本数           对应等级\r\n            0-3                    青铜\r\n            4-5                    白银\r\n            6-7                    黄金\r\n            8-9                    白金\r\n           10-11                   钻石\r\n           12-13                   大师\r\n          14及以上                 王者\r\n等级越高，可借阅本数和时长将会增加！！！\r\n\r\n读者和管理员只能同时在线一个账号，如需登录其他帐号，\r\n请先退出当前登陆的账号！\r\n\r\n\r\n如果您借阅的书籍逾期未归还将会被冻结，请及时归还所借书籍！\r\n\r\n\r\n扫描条形码前请保证焦点在您要输入内容的文本框中！");

	}

}
