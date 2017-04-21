package com.zd.book.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import com.zd.book.Dao.BookDao;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class Book {

	protected Shell shell;
	private  Table table;
	private  BookDao bookdao=new BookDao();
	private Text text;
	private Text text_1;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Book window = new Book();
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
		shell.setImage(SWTResourceManager.getImage(Book.class, "/images/log.ico"));
		shell.setSize(530, 364);
		shell.setText("书籍信息");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(null);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 67, 514, 190);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(66);
		tableColumn.setText("书籍编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("书名");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(76);
		tableColumn_2.setText("价格");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(69);
		tableColumn_3.setText("作者");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(96);
		tableColumn_4.setText("出版社");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(103);
		tableColumn_5.setText("书籍种类");
		
		final Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setBounds(198, 23, 88, 25);
		String[] s2={"书籍编号","作者","书名","出版社","种类","显示所有"};
		combo.setItems(s2);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(310, 23, 73, 23);
		
		Button button = new Button(composite, SWT.NONE);
		
		button.setBounds(403, 23, 80, 27);
		button.setText("查询");
		
		Button button_1 = new Button(composite, SWT.NONE);
		
		button_1.setBounds(66, 268, 80, 27);
		button_1.setText("上一页");
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(175, 278, 17, 17);
		label.setText("第");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(198, 272, 39, 23);
		text_1.setText("1");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(243, 278, 17, 17);
		label_1.setText("页");
		
		Button button_2 = new Button(composite, SWT.NONE);
		
		button_2.setBounds(276, 268, 80, 27);
		button_2.setText("下一页");
		
		Button button_3 = new Button(composite, SWT.NONE);
		
		button_3.setBounds(387, 268, 80, 27);
		button_3.setText("跳转");
		showData();
		
		//点击跳转
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i=Integer.valueOf(text_1.getText().trim());
				showData();
			}
		});
		
		//点击上一页
		
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if("1".equals(text_1.getText().trim())){
					text_1.setText("1");
				}else{
					int i=Integer.valueOf(text_1.getText());
					text_1.setText(String.valueOf(i-1));
					showData();
				}
				
			}
		});
		//点击下一页
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i=Integer.valueOf(text_1.getText());
				text_1.setText(String.valueOf(i+1));
				showData();
			}
		});
		//点击查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text.getText().trim();
				if("书籍编号".equals(tiaojian)){
					tiaojian="book.bookid";
					if(bookdao.find1(tiaojian, jieguo)!=null&&(bookdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("书名".equals(tiaojian)){
					tiaojian="book.bookname";
					if(bookdao.find1(tiaojian, jieguo)!=null&&(bookdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("作者".equals(tiaojian)){
					tiaojian="book.bookauthor";
					if(bookdao.find1(tiaojian, jieguo)!=null&&(bookdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("出版社".equals(tiaojian)){
					tiaojian="book.bookph";
					if(bookdao.find1(tiaojian, jieguo)!=null&&(bookdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("种类".equals(tiaojian)){
					tiaojian="bookclass.classname";
					if(bookdao.find1(tiaojian, jieguo)!=null&&(bookdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("显示所有".equals(tiaojian)){
					
					if(bookdao.find1()!=null&&(bookdao.find1()).size()>0){
						showData1();
						text_1.setText("1");
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}
			}
		});
	}
	 protected Shell getShell() {
		// TODO Auto-generated method stub
		return null;
	}
	//显示表的数据
		public void showData1(){
			if(table!=null){
				table.removeAll();
				List<Map<String,Object>> list=bookdao.find1();
				if(list!=null&&list.size()>0){
					TableItem ti;
					for(Map<String,Object> map:list){
						ti=new TableItem(table,SWT.NONE);
						ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKPRICE")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME"))});	
					}
				}
			}
		}

	//显示表的数据1
	public void showData(){
		if(table!=null){
			table.removeAll();
			int i=Integer.valueOf(text_1.getText().trim());
			List<Map<String,Object>> list=bookdao.find(i);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKPRICE")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME"))});	
				}
			}
		}
	}
	//显示表的数据2
	public void showData(String tiaojian, String jieguo){
		if(table!=null){
			table.removeAll();
			
			List<Map<String,Object>> list=bookdao.find1(tiaojian, jieguo);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKPRICE")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME"))});	
				}
			}
		}
	}
}
