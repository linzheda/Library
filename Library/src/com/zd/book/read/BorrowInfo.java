package com.zd.book.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.BorrowInfoDao;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class BorrowInfo extends Composite {
	private Table table;
	private Table table_1;
	private BorrowInfoDao borrowInfoDao=new BorrowInfoDao();
	private Text text;
	private Text text_1;
	private boolean flag;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BorrowInfo(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(BorrowInfo.class, "/images/框框.jpg"));

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 106, 23);
		label.setText("借阅信息");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(251, 197, 557, 174);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("书籍编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(103);
		tableColumn_1.setText("书名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(140);
		tableColumn_2.setText("借阅日期");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(116);
		tableColumn_3.setText("归还日期");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(94);
		tableColumn_4.setText("罚单");

		Button button = new Button(this, SWT.NONE);

		button.setBounds(748, 148, 60, 25);
		button.setText("查询");

		table_1 = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(309, 467, 446, 174);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("书籍编号");

		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("书名");

		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_7.setWidth(140);
		tableColumn_7.setText("预约日期");

		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("状态");

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setBounds(250, 150, 61, 20);
		label_2.setText("借阅信息：");

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setBounds(250, 420, 61, 20);
		label_3.setText("预约信息：");

		Button button_1 = new Button(this, SWT.NONE);

		button_1.setBounds(737, 418, 60, 25);
		button_1.setText("查询");

		final Combo combo = new Combo(this, SWT.NONE);


		combo.setBounds(402, 149, 88, 25);
		combo.add("书籍编号");
		combo.add("书名");
		combo.add("按时间段查询");
		combo.add("罚单");
		combo.add("查询所有");
		final Combo combo_1 = new Combo(this, SWT.NONE);

		combo_1.setBounds(402, 419, 88, 25);
		combo_1.add("书籍编号");
		combo_1.add("书名");
		combo_1.add("按时间段查询");
		combo_1.add("状态");
		combo_1.add("查询所有");

		text = new Text(this, SWT.BORDER);
		text.setBounds(517, 149, 73, 25);

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(517, 419, 73, 25);

		final CalendarCombo calendarCombo = new CalendarCombo(this, SWT.NONE);
		calendarCombo.setBounds(517, 148, 88, 25);
		calendarCombo.setVisible(false);
		final CalendarCombo calendarCombo_1 = new CalendarCombo(this, SWT.NONE);
		calendarCombo_1.setBounds(641, 148, 88, 25);
		calendarCombo_1.setVisible(false);

		final CalendarCombo calendarCombo_2 = new CalendarCombo(this, SWT.NONE);
		calendarCombo_2.setBounds(505, 420, 88, 25);
		calendarCombo_2.setVisible(false);
		final CalendarCombo calendarCombo_3 = new CalendarCombo(this, SWT.NONE);
		calendarCombo_3.setBounds(627, 420, 88, 25);
		calendarCombo_3.setVisible(false);
		
		final Combo combo_2 = new Combo(this, SWT.NONE);
		combo_2.setBounds(517, 149, 88, 25);
		combo_2.add("有罚单");
		combo_2.add("没有罚单");
		combo_2.setVisible(false);
		showData();
		showDatas();
		//时间段的设置
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("按时间段查询")){
					calendarCombo.setVisible(true);
					calendarCombo_1.setVisible(true);
					combo_2.setVisible(false);
					text.setVisible(false);
				}else if(combo.getText().trim().equals("罚单")){
					combo_2.setVisible(true);
					text.setVisible(false);
					calendarCombo.setVisible(false);
					calendarCombo_1.setVisible(false);
				} else{
					calendarCombo.setVisible(false);
					calendarCombo_1.setVisible(false);
					text.setVisible(true);
				}
			}
		});
		//预约表
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo_1.getText().trim().equals("按时间段查询")){
					calendarCombo_2.setVisible(true);
					calendarCombo_3.setVisible(true);
					text_1.setVisible(false);
				}else{
					calendarCombo_2.setVisible(false);
					calendarCombo_3.setVisible(false);
					text_1.setVisible(true);
				}
			}
		});
		//点击查询（借阅表）
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text.getText().trim();
				if(tiaojian.equals("书籍编号")){
					tiaojian="bd.bid";
					if(borrowInfoDao.find(tiaojian,jieguo)!=null&&borrowInfoDao.find(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}
				}else if(tiaojian.equals("书名")){
					tiaojian="b.bookname";
					if(borrowInfoDao.find(tiaojian, jieguo)!=null&&borrowInfoDao.find(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("罚单")){
					if(combo_2.getText().trim().equals("有罚单")){
						flag=true;
						tiaojian="br.ticket";
						if(borrowInfoDao.findT(flag)!=null&&borrowInfoDao.findT(flag).size()>0){
							showDataT(tiaojian,jieguo);

						}else{
							MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
						}
						
					}else{
						flag=false;
						tiaojian="br.ticket";
						if(borrowInfoDao.findT(flag)!=null&&borrowInfoDao.findT(flag).size()>0){
							showDataT(tiaojian,jieguo);

						}else{
							MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
						}
					}
					
				}else if(tiaojian.equals("按时间段查询")){

					String bdate=calendarCombo.getDateAsString();
					String hdate=calendarCombo_1.getDateAsString();
					
					if(borrowInfoDao.findDate(hdate,bdate)!=null&&borrowInfoDao.findDate(hdate,bdate).size()>0){
						showDataDate(hdate,bdate);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}


				}else if(tiaojian.equals("查询所有")){
					if(borrowInfoDao.find()!=null){
						showData();
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}
			}
		});
		//点击查询(预约表)
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo_1.getText().trim();
				String jieguo=text_1.getText().trim();
				if(tiaojian.equals("书籍编号")){
					tiaojian="b.bookid";
					if(borrowInfoDao.finds(tiaojian,jieguo)!=null&&borrowInfoDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);
					}
				}else if(tiaojian.equals("书名")){
					tiaojian="b.bookname";
					if(borrowInfoDao.finds(tiaojian, jieguo)!=null&&borrowInfoDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("状态")){
					tiaojian="bs.bespeakstate";
					if(borrowInfoDao.finds(tiaojian, jieguo)!=null&&borrowInfoDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("按时间段查询")){

					String bdate=calendarCombo_2.getDateAsString();
					String hdate=calendarCombo_3.getDateAsString();
				
					if(borrowInfoDao.findsDate(hdate,bdate)!=null&&borrowInfoDao.findsDate(hdate,bdate).size()>0){
						showDatasDate(hdate,bdate);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("查询所有")){
					if(borrowInfoDao.find()!=null){
						showDatas();
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

	//显示数据借阅表
	public void showData(){
		if(table!=null){
			table.removeAll();
		}
		List<Map<String,Object>> list=borrowInfoDao.find();
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("HDATE")).equals("null")?"":String.valueOf(map.get("HDATE")),String.valueOf(map.get("TICKET")).equals("null")?"":String.valueOf(map.get("TICKET"))});	

			}
		}
	}
	//显示数据借阅表条件
	public void showData(String tiaojian,String jieguo){
		if(table!=null){
			table.removeAll();
		}
		List<Map<String,Object>> list=borrowInfoDao.find(tiaojian,jieguo);
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("HDATE")).equals("null")?"":String.valueOf(map.get("HDATE")),String.valueOf(map.get("TICKET")).equals("null")?"":String.valueOf(map.get("TICKET"))});	

			}
		}
	}
	//显示数据借阅表条件罚单
		public void showDataT(String tiaojian,String jieguo){
			if(table!=null){
				table.removeAll();
			}
			List<Map<String,Object>> list=borrowInfoDao.findT(flag);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("HDATE")).equals("null")?"":String.valueOf(map.get("HDATE")),String.valueOf(map.get("TICKET")).equals("null")?"":String.valueOf(map.get("TICKET"))});	

				}
			}
		}
	//显示数据借阅表条件riqi
	public void showDataDate(String hdate,String bdate){
		if(table!=null){
			table.removeAll();
		}
		List<Map<String,Object>> list=borrowInfoDao.findDate(hdate,bdate);
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("HDATE")).equals("null")?"":String.valueOf(map.get("HDATE")),String.valueOf(map.get("TICKET")).equals("null")?"":String.valueOf(map.get("TICKET"))});	

			}
		}
	}
	//显示数据预约表
	public void showDatas(){
		if(table_1!=null){
			table_1.removeAll();
			List<Map<String,Object>> list=borrowInfoDao.finds();
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table_1,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BESPEAKDATE")),String.valueOf(map.get("BESPEAKSTATE"))});	

				}
			}
		}
	}
	//显示数据预约表条件
	public void showDatas(String tiaojian,String jieguo ){
		if(table_1!=null){
			table_1.removeAll();
			List<Map<String,Object>> list=borrowInfoDao.finds(tiaojian,jieguo);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table_1,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BESPEAKDATE")),String.valueOf(map.get("BESPEAKSTATE"))});	

				}
			}
		}
	}
	//显示数据预约表条件riqi
	public void showDatasDate(String hdate,String bdate){
		if(table_1!=null){
			table_1.removeAll();
			List<Map<String,Object>> list=borrowInfoDao.findsDate(hdate,bdate);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table_1,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BESPEAKDATE")),String.valueOf(map.get("BESPEAKSTATE"))});	

				}
			}
		}
	}
}
