package com.zd.book.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.BespeakDao;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Bespeak extends Composite {
	private Text text;
	private Table table;
	private BespeakDao bespeakDao=new BespeakDao();


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Bespeak(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Bespeak.class, "/images/框框.jpg"));

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 100, 22);
		label.setText("预约借书");

		final Combo combo = new Combo(this, SWT.NONE);
		combo.add("书籍编号");
		combo.add("书籍名称");
		combo.add("作者");
		combo.add("出版社");
		combo.add("查询所有");

		combo.setBounds(528, 174, 88, 25);

		text = new Text(this, SWT.BORDER);
		text.setBounds(640, 174, 100, 23);

		Button btnNewButton = new Button(this, SWT.NONE);

		btnNewButton.setBounds(760, 173, 60, 25);
		btnNewButton.setText("查询");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setBounds(215, 217, 605, 258);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("书籍编号");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("书名");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("作者");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("出版社");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("类别");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("可借");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.CHECK);

		menuItem.setText("预约");
		showData();
		//右键预约
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis =table.getSelection();
				TableItem t=tis[0];
				if(String.valueOf(t.getText(5)).equals("0")){
					if(MessageDialog.openQuestion(getShell(), "温馨提示","书籍暂无,是否等待?")){
						MessageDialog.openInformation(getShell(), "提示", "预约成功等待管理员审核....");
						bespeakDao.add(String.valueOf(t.getText(0)));
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "您放弃了此次预约....");
					}

				}else{
					MessageDialog.openInformation(getShell(), "提示", "预约成功等待管理员审核....");
					bespeakDao.add(String.valueOf(t.getText(0)));
				}





			}
		});

		//点击查询
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text.getText().trim();
				if(tiaojian.equals("书籍编号")){
					tiaojian="b.bookid";
					if(bespeakDao.finds(tiaojian,jieguo)!=null&&bespeakDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}
				}else if(tiaojian.equals("书籍名称")){
					tiaojian="b.bookname";
					if(bespeakDao.finds(tiaojian, jieguo)!=null&&bespeakDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("作者")){
					tiaojian="b.bookauthor";
					if(bespeakDao.finds(tiaojian, jieguo)!=null&&bespeakDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("出版社")){
					tiaojian="b.bookph";
					if(bespeakDao.finds(tiaojian, jieguo)!=null&&bespeakDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("查询所有")){
					if(bespeakDao.find()!=null){
						showData();
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
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
			List<Map<String,Object>> list=bespeakDao.find();
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("NO"))});	
				}
			}
		}
	}
	//显示数据
	public void showData(String tiaojian,String jieguo){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=bespeakDao.finds(tiaojian,jieguo);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("NO"))});	

				}
			}
		}
	}
}
