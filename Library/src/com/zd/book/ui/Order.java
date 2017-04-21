package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.OrderDao;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

//新书下单
public class Order extends Composite {
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private OrderDao orderdao=new OrderDao();
	private Text text_5;
	private Book book=new Book();
	/**
	 * 下单
	 * @param parent
	 * @param style
	 */
	public Order(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Order.class, "/images/框框.jpg"));
		setLayout(null);

		Label label = new Label(this, SWT.NONE);
		label.setText("新书下单");
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 92, 23);

		Button button = new Button(this, SWT.NONE);

		button.setText("查询");
		button.setBounds(803, 131, 61, 25);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);

		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(235, 178, 629, 255);

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(74);
		tableColumn_1.setText("订单编号");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(83);
		tableColumn_2.setText("书名");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(92);
		tableColumn_3.setText("出版社");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(49);
		tableColumn_4.setText("单价");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(75);
		tableColumn_5.setText("下单数量");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(110);
		tableColumn_6.setText("下单时间");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(55);
		tableColumn_7.setText("总额");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		
		menuItem_1.setText("取消订单");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(87);
		tableColumn.setText("商家");

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setText("书     名：");
		label_1.setBounds(443, 484, 61, 17);

		text = new Text(this, SWT.BORDER);
		text.setVisible(true);
		text.setBounds(513, 480, 100, 23);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("单      价：");
		label_2.setBounds(235, 530, 61, 17);

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(296, 528, 100, 23);

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setText("出 版 社 ：");
		label_3.setBounds(661, 482, 61, 17);

		text_2 = new Text(this, SWT.BORDER);
		text_2.setVisible(true);
		text_2.setBounds(736, 481, 100, 23);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setText("下单数量：");
		label_4.setBounds(442, 533, 61, 17);

		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(511, 529, 100, 23);

		Button button_1 = new Button(this, SWT.NONE);

		button_1.setText("下订单");
		button_1.setBounds(316, 582, 80, 27);

		Button button_2 = new Button(this, SWT.NONE);

		button_2.setText("取消订单");
		button_2.setBounds(424, 582, 80, 27);

		final Button btnNewButton = new Button(this, SWT.NONE);
		//btnNewButton.setVisible(false);
		btnNewButton.setBounds(533, 582, 80, 27);
		btnNewButton.setText("修  改");

		Label label_5 = new Label(this, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_5.setBounds(235, 483, 61, 17);
		label_5.setText("订单编号：");

		final Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(300, 485, 99, 17);

		final Combo combo = new Combo(this, SWT.READ_ONLY);
		combo.setBounds(591, 132, 85, 25);
		String[] s1={"订单编号","书名","出版社","商家"};
		combo.setItems(s1);

		text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(698, 132, 85, 25);

		Label label_6 = new Label(this, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_6.setBounds(661, 530, 66, 17);
		label_6.setText("商     家：");

		text_5 = new Text(this, SWT.BORDER);
		text_5.setBounds(734, 526, 99, 23);

		Button button_3 = new Button(this, SWT.NONE);

		button_3.setBounds(642, 582, 80, 27);
		button_3.setText("清空");
		
		final Label lblP = new Label(this, SWT.NONE);
		lblP.setVisible(false);
		lblP.setBounds(513, 484, 103, 20);
		
		
		final Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setBounds(736, 486, 100, 20);
		
		final Button button_4 = new Button(this, SWT.NONE);
		
		button_4.setBounds(756, 582, 80, 27);
		button_4.setText("查询书籍");
		
		showData();
		//点击查询
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				button_4.setEnabled(false);
				book.open();
				button_4.setEnabled(true);
			}
		});
		//右键取消订单
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String orderid=lblNewLabel.getText().trim();
				int bostate=0;
				if(orderdao.update(bostate,orderid)>0){
					showData();
					text_3.setText("");
					text_1.setText("");
					text_5.setText("");
					lblP.setText("");
					lblNewLabel.setText("");
					lblNewLabel.setText("");
					//btnNewButton.setVisible(false);
					text.setVisible(true);
					text_2.setVisible(true);
					lblP.setVisible(false);
					lblNewLabel_1.setVisible(false);
					MessageDialog.openInformation(getShell(), "成功提示", "取消订单成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "取消订单失败....");
				}
			}
		});
		//右键点击修改
//		menuItem.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				btnNewButton.setVisible(true);
//				text.setVisible(false);
//				text_2.setVisible(false);
//				lblP.setVisible(true);
//				lblNewLabel_1.setVisible(true);
//				TableItem[] tis=table.getSelection();
//				if(tis!=null&&tis.length>0){
//					TableItem t=tis[0];
//					lblNewLabel.setText(t.getText(0));
//					lblP.setText(t.getText(1));
//					lblNewLabel_1.setText(t.getText(2));
//					text_1.setText(t.getText(3));
//					text_3.setText(t.getText(4));
//					text_5.setText(t.getText(7));
						
					
//				}
				
				
//			}
//		});
		//点击修改
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bookname=lblP.getText().trim();
				String orderid=lblNewLabel.getText().trim();
				String bookph=lblNewLabel_1.getText().trim();
				String seller=text_5.getText().trim();
				String price=text_1.getText().trim();
				String booknum=text_3.getText().trim();
				if(orderdao.update1(price,booknum,seller,orderid)>0){
					showData();
					text_3.setText("");
					text_1.setText("");
					text_5.setText("");
					lblP.setText("");
					lblNewLabel.setText("");
					text_2.setText("");
					//btnNewButton.setVisible(false);
					text.setVisible(true);
					text_2.setVisible(true);
					lblP.setVisible(false);
					lblNewLabel_1.setVisible(false);
					MessageDialog.openInformation(getShell(), "成功提示", "修改订单成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "修改订单失败....");
				}
			}
		});

		//点击取消订单
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String orderid=lblNewLabel.getText().trim();
				int bostate=0;
				if(orderdao.update(bostate,orderid)>0){
					showData();
					text_3.setText("");
					text_1.setText("");
					text_5.setText("");
					lblP.setText("");
					lblNewLabel.setText("");
					lblNewLabel.setText("");
					//btnNewButton.setVisible(false);
					text.setVisible(true);
					text_2.setVisible(true);
					lblP.setVisible(false);
					lblNewLabel_1.setVisible(false);
					MessageDialog.openInformation(getShell(), "成功提示", "取消订单成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "取消订单失败....");
				}
			}
		});

		//点击取消(清空)
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_3.setText("");
				text_1.setText("");
				text_5.setText("");
				text.setText("");
				lblNewLabel.setText("");
				text_2.setText("");
				
				lblP.setText("");
				lblNewLabel_1.setText("");;
				text.setVisible(true);
				text_2.setVisible(true);
				lblP.setVisible(false);
				lblNewLabel_1.setVisible(false);
			}
		});

		//点击下订单
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bookname=null;
				if(text.getVisible()==true){
					bookname=text.getText().trim();
				}else if(lblP.getVisible()==true){
					bookname=lblP.getText().trim();
				}
				
				Integer bookid=null;
				List<Map<String,Object>> list=orderdao.find2(bookname);
				for(Map<String,Object> map:list){
					for(Map.Entry<String,Object> s:map.entrySet()){
						bookid=Integer.valueOf(String.valueOf(s.getValue()));
						
					}
				}

				String seller=text_5.getText().trim();
				String price=text_1.getText().trim();
				String booknum=text_3.getText().trim();	

				if(orderdao.add(bookid,seller,price,booknum)>0){
					showData();
					text_3.setText("");
					text_1.setText("");
					text_5.setText("");
					text.setText("");
					lblNewLabel.setText("");
					text_2.setText("");
					//btnNewButton.setVisible(false);
					text.setVisible(true);
					text_2.setVisible(true);
					lblP.setVisible(false);
					lblNewLabel_1.setVisible(false);
					MessageDialog.openInformation(getShell(), "成功提示", "下单成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "下单失败....");
				}
			}
		});

		//点击表格
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//btnNewButton.setVisible(true);
				
				text.setVisible(false);
				text_2.setVisible(false);
				lblP.setVisible(true);
				lblNewLabel_1.setVisible(true);
				TableItem[] ti=table.getSelection();
				if(ti!=null&&ti.length>0){
					TableItem t=ti[0];
					lblNewLabel.setText(t.getText(0));
					lblP.setText(t.getText(1));
					lblNewLabel_1.setText(t.getText(2));
					text_1.setText(t.getText(3));
					text_3.setText(t.getText(4));
					text_5.setText(t.getText(7));
				
				}
			}
		});

		//点击查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text_4.getText().trim();
				if("订单编号".equals(tiaojian)){
					tiaojian="bookorder.orderid";
					if(orderdao.find1(tiaojian, jieguo)!=null&&(orderdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_4.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("书名".equals(tiaojian)){
					tiaojian="book.bookname";
					if(orderdao.find1(tiaojian, jieguo)!=null&&(orderdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_4.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("出版社".equals(tiaojian)){
					tiaojian="book.bookph";
					if(orderdao.find1(tiaojian, jieguo)!=null&&(orderdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_4.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("商家".equals(tiaojian)){
					tiaojian="bookorder.seller";
					if(orderdao.find1(tiaojian, jieguo)!=null&&(orderdao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_4.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}
			}
		});


	}

	//显示表的数据1
	public void showData(){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=orderdao.find();
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("ORDERID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("PRICE")),String.valueOf(map.get("BOOKNUM")),String.valueOf(map.get("ORDERDATE")),String.valueOf(map.get("BOOKORDER.PRICE*BOOKORDER.BOOKNUM")),String.valueOf(map.get("SELLER"))});	
				}
			}
		}
	}
	//显示表的数据2
	public void showData(String tiaojian, String jieguo){
		if(table!=null){
			table.removeAll();

			List<Map<String,Object>> list=orderdao.find1(tiaojian, jieguo);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("ORDERID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("PRICE")),String.valueOf(map.get("BOOKNUM")),String.valueOf(map.get("ORDERDATE")),String.valueOf(map.get("BOOKORDER.PRICE*BOOKORDER.BOOKNUM")),String.valueOf(map.get("SELLER"))});	
				}
			}
		}
	}


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
