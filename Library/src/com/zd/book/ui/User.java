package com.zd.book.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import com.zd.book.Dao.UserDao;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseAdapter;

public class User {

	protected Shell shell;
	private Table table;
	private Text text;
	private UserDao userDao=new UserDao();
	private Text text_1;
	private String yeshu;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			User window = new User();
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
		shell.setImage(SWTResourceManager.getImage(User.class, "/images/log.ico"));
		shell.setSize(518, 349);
		shell.setText("用户借阅情况");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(null);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setBounds(0, 51, 502, 217);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		
		tableColumn.setText("用户编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("用户姓名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("等级");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(95);
		tableColumn_3.setText("可借阅本数");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(103);
		tableColumn_4.setText("已借阅本数");

		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(43, 16, 117, 25);
		combo.add("用户编号");
		combo.add("用户姓名");
		combo.add("查询所有");
		text = new Text(composite, SWT.BORDER);
		text.setBounds(189, 16, 117, 25);

		Button button = new Button(composite, SWT.NONE);

		button.setBounds(335, 14, 60, 25);
		button.setText("查询");

		final Button button_1 = new Button(composite, SWT.NONE);
		
		
		button_1.setImage(SWTResourceManager.getImage(User.class, "/images/上一页按钮1.jpg"));
		
		button_1.setBounds(43, 273, 80, 27);

		Label label = new Label(composite, SWT.NONE);
		label.setBounds(180, 278, 14, 17);
		label.setText("第");

		text_1 = new Text(composite, SWT.BORDER);
		
		text_1.setText("1");
		text_1.setBounds(200, 275, 34, 23);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(249, 278, 23, 17);
		label_1.setText("页");

		final Button btnNewButton = new Button(composite, SWT.NONE);
		
		
		btnNewButton.setImage(SWTResourceManager.getImage(User.class, "/images/go按钮.jpg"));

		btnNewButton.setBounds(277, 274, 41, 27);

		final Button button_2 = new Button(composite, SWT.NONE);
		
		
		button_2.setImage(SWTResourceManager.getImage(User.class, "/images/下一页按钮1.jpg"));
		
		button_2.setBounds(364, 276, 80, 27);
		String i="1";
		showData(i);
		//go的颜色变动
		btnNewButton.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				btnNewButton.setImage(SWTResourceManager.getImage(User.class, "/images/go按钮2.jpg"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				btnNewButton.setImage(SWTResourceManager.getImage(User.class, "/images/go按钮.jpg"));
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				btnNewButton.setImage(SWTResourceManager.getImage(User.class, "/images/go按钮3.jpg"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				btnNewButton.setImage(SWTResourceManager.getImage(User.class, "/images/go按钮2.jpg"));
			}
		});
		//下一页颜色变动
		button_2.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(User.class, "/images/下一页按钮2.jpg"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(User.class, "/images/下一页按钮1.jpg"));
			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(User.class, "/images/下一页按钮3.jpg"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(User.class, "/images/下一页按钮2.jpg"));
			}
		});
		//上一页颜色变动
		button_1.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseHover(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(User.class, "/images/上一页按钮2.jpg"));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(User.class, "/images/上一页按钮1.jpg"));
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(User.class, "/images/上一页按钮3.jpg"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(User.class, "/images/上一页按钮2.jpg"));
			}
		});
		
//		//页数的监听
//		text_1.addModifyListener(new ModifyListener() {
//			public void modifyText(ModifyEvent arg0) {
//				String a=text_1.getText().trim();
//				if(a.equals("1")){
//					button_1.setVisible(false);
//				}else{
//					button_1.setVisible(false);
//				}
//				
//			}
//		});

		//点击下一页
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				yeshu=text_1.getText().trim();
				yeshu=String.valueOf(Integer.parseInt(yeshu)+1);
				text_1.setText(yeshu);
				userDao.finds(yeshu);
				showData(yeshu);
			}
		});
		//点击上一页
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				yeshu=text_1.getText().trim();
				yeshu=String.valueOf(Integer.parseInt(yeshu)-1);
				text_1.setText(yeshu);
				userDao.finds(yeshu);
				showData(yeshu);
			}
		});
		//点击go
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				yeshu=text_1.getText().trim();
				userDao.finds(yeshu);
				showData(yeshu);
			}
		});
		//点击查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text.getText().trim();
				if(tiaojian.equals("用户编号")){
					tiaojian="u.userid";
					if(userDao.find(tiaojian,jieguo)!=null&&userDao.find(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(shell, "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("用户名称")){
					tiaojian="u.sname";
					if(userDao.find(tiaojian, jieguo)!=null&&userDao.find(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(shell, "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("查询所有")){
					String i="1";
					showData(i);
					text_1.setText("1");
				}




			}
		});

	}





	//显示数据
	public void showData(){
		table.removeAll();
		List<Map<String,Object>> types=userDao.find();
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("RANKNAME")),String.valueOf(map.get("NUMBERS")),String.valueOf(map.get("SUM"))});
			}
		}

	}
	//显示数据条件
	public void showData(String tiaojian,String jieguo){
		table.removeAll();
		List<Map<String,Object>> types=userDao.find(tiaojian,jieguo);
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("RANKNAME")),String.valueOf(map.get("NUMBERS")),String.valueOf(map.get("SUM"))});
			}
		}

	}
	//显示数据
	public void showData(String yeshu){
		table.removeAll();
		List<Map<String,Object>> types=userDao.finds(yeshu);
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("RANKNAME")),String.valueOf(map.get("NUMBERS")),String.valueOf(map.get("SUM"))});
			}
		}

	}
}
