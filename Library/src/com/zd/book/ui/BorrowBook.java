package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.widgets.Display;

import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.BorrowBookDao;


import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.ui.forms.widgets.FormToolkit;


import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;

//借书
public class BorrowBook extends Composite {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private BorrowBookDao borrowbookDao=new BorrowBookDao();
	private Shell shell;
	private Table table;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private User user=new User();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BorrowBook(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(BorrowBook.class, "/images/框框.jpg"));
		setLayout(null);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(65, 175, 918, 234);
		

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(56);
		tableColumn.setText("序列号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(84);
		tableColumn_1.setText("借阅者姓名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(75);
		tableColumn_2.setText("借书证号");

		TableColumn tableColumn_10 = new TableColumn(table, SWT.CENTER);
		tableColumn_10.setWidth(80);
		tableColumn_10.setText("书籍编号");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(86);
		tableColumn_3.setText("书名");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(66);
		tableColumn_4.setText("作者");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(101);
		tableColumn_5.setText("出版社");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(62);
		tableColumn_6.setText("书籍类别");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(52);
		tableColumn_7.setText("状态");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(150);
		tableColumn_8.setText("预约开始时间");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem_2 = new MenuItem(menu, SWT.CHECK);

		menuItem_2.setText("通过审核");

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);

		menuItem.setText("借阅");

		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);

		menuItem_1.setText("不借阅");

		TableColumn tableColumn_9 = new TableColumn(table, SWT.CENTER);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("未借出数");

		text_1 = new Text(this, SWT.BORDER);

		text_1.setBounds(522, 497, 129, 23);
		

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setText("借书证号：");
		label_1.setBounds(439, 498, 71, 22);
		

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setText("书籍编号：");
		label_2.setBounds(738, 498, 61, 22);
		

		text_2 = new Text(this, SWT.BORDER);

		text_2.setBounds(800, 497, 128, 23);
		

		Button button = new Button(this, SWT.NONE);

		button.setText("借  阅");
		button.setBounds(848, 556, 80, 27);
		

		Button button_2 = new Button(this, SWT.NONE);

		button_2.setText("查询");
		button_2.setBounds(922, 135, 61, 25);
		

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("借书管理");
		label_3.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label_3.setBounds(475, 95, 92, 23);
		

		final Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(706, 136, 88, 25);
		formToolkit.adapt(combo);
		
		combo.add("书籍名称");
		combo.add("书籍编号");
		combo.add("作者");
		combo.add("出版社");
		combo.add("借书证编号");

		combo.add("查询所有");

		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(815, 136, 88, 25);
	

		final Button btnNewButton = new Button(this, SWT.NONE);

		btnNewButton.setBounds(65, 134, 80, 27);
		
		btnNewButton.setText("查看用户");

		final Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(522, 448, 129, 22);
		

		final Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setBounds(800, 448, 127, 22);
		
		showData();
		
		//借书证号的监听
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String userid=text_1.getText().trim();
				if(borrowbookDao.findU(userid)!=null&&borrowbookDao.findU(userid).size()>0){
					label.setText("");
				}else if(userid.equals("")){
					label.setText("");
				}else{
					label.setText("*借书证号错误....");
				}
			}
		});
		//书籍编号的监听
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String bid=text_2.getText().trim();
				if(borrowbookDao.findB(bid)!=null&&borrowbookDao.findB(bid).size()>0){
					label_4.setText("");
				}else if(bid.equals("")){
					label_4.setText("");
				}else{
					label_4.setText("*书籍编号错误....");
				}
			}
		});

		//点击查看用户
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnNewButton.setEnabled(false);
				user.open();
				btnNewButton.setEnabled(true);

			}
		});
		//点击查询
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String tiaojian=combo.getText().trim();
				String jieguo=text_3.getText().trim();
				if(tiaojian.equals("书籍编号")){
					tiaojian="b.bookid";
					if(borrowbookDao.finds(tiaojian,jieguo)!=null&&borrowbookDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("书籍名称")){
					tiaojian="b.bookname";
					if(borrowbookDao.finds(tiaojian, jieguo)!=null&&borrowbookDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("作者")){
					tiaojian="b.bookauthor";
					if(borrowbookDao.finds(tiaojian, jieguo)!=null&&borrowbookDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("出版社")){
					tiaojian="b.bookph";
					if(borrowbookDao.finds(tiaojian, jieguo)!=null&&borrowbookDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("借书证编号")){
					tiaojian="u.userid";
					if(borrowbookDao.finds(tiaojian, jieguo)!=null&&borrowbookDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("查询所有")){
					if(borrowbookDao.find()!=null){
						showData();
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}
			}
		});

		//右键点击通过审核
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis==null||tis.length<=0){
					MessageDialog.openWarning(getShell(), "温馨提示", "请选择您要的数据...");
				}else{
					int i=0;
					StringBuffer ids=new StringBuffer();
					for(i=0;i<tis.length-1;i++){
						ids.append(tis[i].getText(0)+",");
					}
					ids.append(tis[i].getText(0));
					if(MessageDialog.openConfirm(getShell(), "通过确认","您确认要通过选中的数据吗？")){
						//调用dao层执行通过
						if(borrowbookDao.update(ids.toString())>0){
							showData();
						}else{
							MessageDialog.openError(getShell(), "失败提示", "数据通过失败....");
						}

					}else{
						return;
					}
				}
			}
		});
		//右键点击借阅时
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti=table.getSelection()[0];
				if(ti==null){
					MessageDialog.openWarning(getShell(), "温馨提示", "请选择您要的数据...");
				}else{
					int i=0;
					StringBuffer ids=new StringBuffer();
					ids.append(ti.getText(0)+",");
					ids.append(ti.getText(3)+",");
					ids.append(ti.getText(2));
					String bespeakid=ti.getText(0);
					//System.out.println(bespeakid);
					String bookid=ti.getText(3);
					//System.out.println(bookid);
					String userid=ti.getText(2);
					//System.out.println(userid);
					String bid="";
					List<Map<String,Object>> list=borrowbookDao.findBD(bookid);
					if(list!=null){
						for(Map<String,Object> map:list){
							bid=String.valueOf(map.get("BID"));
							break;
						}
						System.out.println(bid);
						if(borrowbookDao.updateBD(bid)>0&&borrowbookDao.addBD(userid, bid)>0&&borrowbookDao.updateBDs(bespeakid)>0){
							MessageDialog.openInformation(getShell(), "温馨提示", "借阅成功....");
							showData();
						}else{
							MessageDialog.openError(getShell(), "失败提示", "借阅失败....");
						}	
					}else{
						MessageDialog.openError(getShell(), "失败提示", "借阅失败....");
					}

				}
			}
		});
		//右键点击不借阅
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis==null||tis.length<=0){
					MessageDialog.openWarning(getShell(), "温馨提示", "请选择您要的数据...");
				}else{
					int i=0;
					StringBuffer ids=new StringBuffer();
					for(i=0;i<tis.length-1;i++){
						ids.append(tis[i].getText(0)+",");
					}
					ids.append(tis[i].getText(0));
					if(MessageDialog.openConfirm(getShell(), "不通过确认","您确认不通过选中的数据吗？")){
						//调用dao层执行通过
						if(borrowbookDao.updates(ids.toString())>0){
							showData();
						}else{
							MessageDialog.openError(getShell(), "失败提示", "数据不通过失败....");
						}

					}else{
						return;
					}

				}
			}
		});
		//点击借阅时
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(label_4.getText().trim().equals("")==false||label.getText().trim().equals("")==false){
					MessageDialog.openError(getShell(), "温馨提示", "信息错误...");
				}else{
					String userid =text_1.getText().trim();
					String bid=text_2.getText().trim();
					if(borrowbookDao.updatebook(userid, bid)>0&&borrowbookDao.updatebook( bid)>0){
						MessageDialog.openInformation(getShell(), "温馨提示", "借阅成功....");
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "借阅失败....");
					}

				}


			}
		});





	}








	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	//显示数据
	public void showData(){
		table.removeAll();
		List<Map<String,Object>> types=borrowbookDao.find();
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("BESPEAKID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("USERID")),String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("BESPEAKSTATE")),String.valueOf(map.get("BESPEAKDATE")),String.valueOf(map.get("NO"))});
			}
		}

	}
	//显示数据条件
	public void showData(String tiaojian,String jieguo ){
		table.removeAll();
		List<Map<String,Object>> types=borrowbookDao.finds(tiaojian,jieguo);
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("BESPEAKID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("USERID")),String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("BESPEAKSTATE")),String.valueOf(map.get("BESPEAKDATE")),String.valueOf(map.get("NO"))});
			}
		}

	}

}
