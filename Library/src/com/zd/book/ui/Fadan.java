package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.FadanDao;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Fadan extends Composite {
	private Text text;
	private Table table;
	private FadanDao guanlidao=new FadanDao();

	/**
	 * 罚单管理
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Fadan(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Fadan.class, "/images/框框.jpg"));
		setLayout(null);

		Button button = new Button(this, SWT.NONE);

		button.setBounds(754, 150, 60, 25);
		button.setText("查询");

		Button button_1 = new Button(this, SWT.NONE);

		button_1.setBounds(592, 581, 80, 27);
		button_1.setText("冻结");

		Button button_2 = new Button(this, SWT.NONE);

		button_2.setBounds(702, 581, 80, 27);
		button_2.setText("解冻");

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label_2.setBounds(475, 95, 112, 27);
		label_2.setText("罚单管理");

		final Combo combo = new Combo(this, SWT.READ_ONLY);
		combo.setBounds(545, 151, 88, 25);
		String[] s1={"借书记录编号","借书证号","姓名","书籍编号","账号状态"};
		combo.setItems(s1);
		combo.add("查询所有");

		text = new Text(this, SWT.BORDER);
		text.setBounds(656, 151, 80, 25);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);

		table.setBounds(228, 194, 586, 214);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(88);
		tableColumn.setText("借书记录编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(79);
		tableColumn_1.setText("借书证号");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(81);
		tableColumn_2.setText("姓名");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(77);
		tableColumn_3.setText("书的编号");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(80);
		tableColumn_4.setText("罚款金额");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(75);
		tableColumn_5.setText("账号状态");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);

		menuItem.setText("冻结");

		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);


		menuItem_1.setText("解冻");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(101);
		tableColumn_6.setText("是否已缴纳罚单");

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label.setBounds(292, 523, 88, 17);
		label.setText("借  书  证  号 :");

		final Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(396, 525, 74, 17);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setBounds(572, 479, 61, 19);
		label_1.setText("姓       名 :");

		final Label label_3 = new Label(this, SWT.NONE);
		label_3.setBounds(656, 481, 80, 17);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setBounds(572, 513, 61, 17);
		label_4.setText("书的编号：");

		final Label label_5 = new Label(this, SWT.NONE);
		label_5.setBounds(656, 513, 80, 17);

		Label label_6 = new Label(this, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_6.setBounds(292, 479, 88, 24);
		label_6.setText("借书记录编号：");

		final Label label_7 = new Label(this, SWT.NONE);
		label_7.setBounds(396, 481, 74, 17);
		showData();
		//右击解冻
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int brid=Integer.valueOf(label_7.getText().trim());
				String userstate ="正常";
				if(guanlidao.update(userstate,brid)>0){
					showData();
					label_7.setText("");
					label_3.setText("");
					lblNewLabel.setText("");
					label_5.setText("");
					MessageDialog.openInformation(getShell(), "成功提示", "解冻成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "解冻失败....");
				}
			}
		});
		//右击冻结
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis!=null&&tis.length>0){
					int brid=Integer.valueOf(label_7.getText().trim());
					String userstate ="冻结";

					if(guanlidao.update(userstate,brid)>0){
						showData();
						label_7.setText("");
						label_3.setText("");
						lblNewLabel.setText("");
						label_5.setText("");
						MessageDialog.openInformation(getShell(), "成功提示", "冻结成功");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "冻结失败....");
					}
				}				

			}
		});

		//点击解冻
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int brid=Integer.valueOf(label_7.getText().trim());
				String userstate ="正常";
				if(guanlidao.update(userstate,brid)>0){
					showData();
					label_7.setText("");
					label_3.setText("");
					lblNewLabel.setText("");
					label_5.setText("");
					MessageDialog.openInformation(getShell(), "成功提示", "解冻成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "解冻失败....");
				}
			}
		});

		//点击冻结
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int brid=Integer.valueOf(label_7.getText().trim());
				String userstate ="冻结";

				if(guanlidao.update(userstate,brid)>0){
					showData();
					label_7.setText("");
					label_3.setText("");
					lblNewLabel.setText("");
					label_5.setText("");
					MessageDialog.openInformation(getShell(), "成功提示", "冻结成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "冻结失败....");
				}
			}
		});
		//点击查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text.getText().trim();
				if("借书记录编号".equals(tiaojian)){
					tiaojian="b.brid";
					if(guanlidao.find1(tiaojian, jieguo)!=null&&(guanlidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("借书证号".equals(tiaojian)){
					tiaojian="b.stuid";
					if(guanlidao.find1(tiaojian, jieguo)!=null&&(guanlidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("姓名".equals(tiaojian)){
					tiaojian="u.sname";
					if(guanlidao.find1(tiaojian, jieguo)!=null&&(guanlidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("书籍编号".equals(tiaojian)){
					tiaojian="b.bid";
					if(guanlidao.find1(tiaojian, jieguo)!=null&&(guanlidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("账号状态".equals(tiaojian)){
					tiaojian="u.userstate";
					if(guanlidao.find1(tiaojian, jieguo)!=null&&(guanlidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("查询所有".equals(tiaojian)){
					showData();
				}
			}
		});
		//点击表格
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					label_7.setText(t.getText(0));
					lblNewLabel.setText(t.getText(1));
					label_3.setText(t.getText(2));
					label_5.setText(t.getText(3));
				}
			}
		});


	}
	//显示表的数据1
	public void showData(){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=guanlidao.find();
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					//System.out.println(map.get("TICKET"));
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BRID")),String.valueOf(map.get("STUID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("BID")),String.valueOf(map.get("TICKET")),String.valueOf(map.get("USERSTATE")),String.valueOf(map.get("STATE"))});	
				}
			}
		}

	}
	//显示表的数据2
	public void showData(String tiaojian,String jieguo){
		if(table!=null){
			table.removeAll();

			List<Map<String,Object>> list=guanlidao.find1(tiaojian, jieguo);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BRID")),String.valueOf(map.get("STUID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("BID")),String.valueOf(map.get("TICKET")),String.valueOf(map.get("USERSTATE")),String.valueOf(map.get("STATE"))});	
				}
			}
		}
	}



















	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
