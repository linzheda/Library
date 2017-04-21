package com.zd.book.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.RenewDao;

import org.eclipse.swt.widgets.Label;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Renew extends Composite {
	private Table table;
	private RenewDao renewDao=new RenewDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Renew(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Renew.class, "/images/框框.jpg"));

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(501, 95, 46, 23);
		label.setText("续借");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(165, 172, 728, 257);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(70);
		tableColumn.setText("书籍编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("书名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(70);
		tableColumn_2.setText("作者");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("出版社");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(141);
		tableColumn_4.setText("借阅日期");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(141);
		tableColumn_6.setText("规定还书日期");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("续借时间");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.CHECK);
		
		menuItem.setText("续借");

		Button button = new Button(this, SWT.NONE);

		button.setBounds(750, 469, 80, 27);
		button.setText("续借");
		showData();
		
		//右键续借
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis= table.getSelection();
				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					String brid=t.getText(0);
					System.out.println(brid);
					if(renewDao.add(brid)>0){
						MessageDialog.openInformation(getShell(), "温馨提示", "续借成功....");
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
					
					if(renewDao.add(brid)>0){
						MessageDialog.openInformation(getShell(), "温馨提示", "续借成功....");
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
		List<Map<String,Object>> list=renewDao.find();	
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("G")),String.valueOf(map.get("RENEW")).equals("null")?"":String.valueOf(map.get("RENEW"))});	

			}
		}
	}
}
