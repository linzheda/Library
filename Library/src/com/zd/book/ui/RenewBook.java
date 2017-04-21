package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;



import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.RenewBookDao;

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

//续借书籍
public class RenewBook extends Composite {
	private Text text;
	private Table table;
	private RenewBookDao renewBookDao=new RenewBookDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RenewBook(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(RenewBook.class, "/images/框框.jpg"));
		setLayout(null);

		Label label = new Label(this, SWT.NONE);
		label.setText("续借管理");
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(471, 71, 92, 23);

		final Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(632, 125, 88, 25);
		combo.add("借书证号");
		combo.add("书籍编号");
		combo.add("书名");
		combo.add("作者");
		combo.add("出版社");
		combo.add("查询所有");

		text = new Text(this, SWT.BORDER);
		text.setBounds(740, 125, 100, 25);

		Button btnNewButton = new Button(this, SWT.NONE);

		btnNewButton.setBounds(859, 123, 60, 25);
		btnNewButton.setText("查询");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(115, 171, 804, 364);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("借书证号");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("书籍编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("书名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("作者");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("出版社");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("借阅日期");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("规定还书日期");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("续借时间");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.CHECK);

		menuItem.setText("续借");

		Button button = new Button(this, SWT.NONE);

		button.setBounds(760, 560, 80, 27);
		button.setText("续借");
		showData();

		//点击查询
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String  jieguo=text.getText().trim();
				List<Map<String,Object>> dateNum=renewBookDao.findDate();
				if(tiaojian.equals("借书证号")){
					tiaojian="u.userid";
					if(renewBookDao.find(dateNum, tiaojian, jieguo)!=null){
						showDatas(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("书籍编号")){
					
					if(renewBookDao.find(dateNum)!=null){
						showDatasB(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("书名")){
					tiaojian="b.bookname";
					if(renewBookDao.find(dateNum, tiaojian, jieguo)!=null){
						showDatas(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("作者")){
					tiaojian="b.bookauthor";
					if(renewBookDao.find(dateNum, tiaojian, jieguo)!=null){
						showDatas(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("出版社")){
					tiaojian="b.bookph";
					if(renewBookDao.find(dateNum, tiaojian, jieguo)!=null){
						showDatas(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("查询所有")){
					showData();
				}

			}


		});

		//右键续借
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis= table.getSelection();
				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					String brid=t.getText(0);
					System.out.println(brid);
					if(renewBookDao.add(brid)>0){
						MessageDialog.openInformation(getShell(),  "温馨提示", "续借成功....");
						
						showData();
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "续借失败...");
					}
				}
			}
		});

		//点击续借
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis= table.getSelection();
				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					String brid=t.getText(0);
					System.out.println(brid);
					if(renewBookDao.add(brid)>0){
						MessageDialog.openInformation(getShell(),  "温馨提示", "续借成功....");
						showData();
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "续借失败...");
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
		if(table!=null){
			table.removeAll();
		}
		List<Map<String,Object>> tianshu=renewBookDao.findDate();
		List<Map<String,Object>> list=renewBookDao.find(tianshu);	
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("G")),String.valueOf(map.get("RENEW")).equals("null")?"":String.valueOf(map.get("RENEW"))});	

			}
		}
	}
	//显示数据
	public void showDatas(String tiaojian,String jieguo ){
		if(table!=null){
			table.removeAll();
		}
		List<Map<String,Object>> tianshu=renewBookDao.findDate();
		List<Map<String,Object>> list=renewBookDao.find(tianshu,tiaojian,jieguo);	
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("G")),String.valueOf(map.get("RENEW")).equals("null")?"":String.valueOf(map.get("RENEW"))});	

			}
		}
	}
	
	//显示数据  书籍编号
		public void showDatasB(String tiaojian,String jieguo ){
			if(table!=null){
				table.removeAll();
			}
			List<Map<String,Object>> tianshu=renewBookDao.findDates(jieguo);
			System.out.println(tianshu);
			List<Map<String,Object>> list=renewBookDao.find(tianshu);	
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("STUID")),String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("G")),String.valueOf(map.get("RENEW")).equals("null")?"":String.valueOf(map.get("RENEW"))});	

				}
			}
		}



}
