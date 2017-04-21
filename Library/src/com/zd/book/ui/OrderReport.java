package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.OrderReportDao;
import com.zd.book.util.BingTu;
import com.zd.book.util.CreateEXCEL;
import com.zd.book.util.ZheXianTu;
import com.zd.book.util.ZhuzhuangTu;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class OrderReport extends Composite {
	private Text text;
	private Table table;
	private OrderReportDao orderReportDao=new OrderReportDao();
	private CreateEXCEL createEXCEL=new CreateEXCEL();
	private BingTu bingTu=new BingTu();
	private ZhuzhuangTu zhuzhuangTu=new ZhuzhuangTu();
	private ZheXianTu zheXianTu=new ZheXianTu();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OrderReport(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(OrderReport.class, "/images/框框.jpg"));

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 99, 27);
		label.setText("订单详细");

		final Combo combo = new Combo(this, SWT.NONE);
		
		combo.setBounds(476, 160, 88, 25);
		combo.add("订单编号");
		combo.add("书籍名称");
		combo.add("商家");
		combo.add("时间段");
		combo.add("查询所有");

		text = new Text(this, SWT.BORDER);
		text.setBounds(657, 160, 99, 23);

		Button button = new Button(this, SWT.NONE);

		button.setBounds(850, 159, 60, 25);
		button.setText("查询");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(79, 207, 831, 238);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(84);
		tableColumn.setText("订单编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("书籍名称");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("作者");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("出版社");

		TableColumn tableColumn_9 = new TableColumn(table, SWT.CENTER);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("商家");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("下单时间");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("验收时间");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(50);
		tableColumn_6.setText("单价");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(44);
		tableColumn_7.setText("数量");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(49);
		tableColumn_8.setText("总额");

		Button btnexcel = new Button(this, SWT.NONE);

		btnexcel.setBounds(538, 472, 80, 27);
		btnexcel.setText("导出EXCEL");

		final Combo combo_1 = new Combo(this, SWT.NONE);
		combo_1.setBounds(648, 474, 88, 25);
		combo_1.add("柱状图");
		combo_1.add("饼图");
		combo_1.add("折线图");

		final CalendarCombo calendarCombo = new CalendarCombo(this, SWT.NONE);
		calendarCombo.setBounds(648, 521, 88, 25);

		final CalendarCombo calendarCombo_1 = new CalendarCombo(this, SWT.NONE);
		calendarCombo_1.setBounds(648, 574, 88, 25);

		Button button_1 = new Button(this, SWT.NONE);
		
		button_1.setBounds(538, 572, 80, 27);
		button_1.setText("图表");

		final CalendarCombo calendarCombo_2 = new CalendarCombo(this, SWT.NONE);
		calendarCombo_2.setBounds(590, 160, 88, 25);
		calendarCombo_2.setVisible(false);

		final CalendarCombo calendarCombo_3 = new CalendarCombo(this, SWT.NONE);
		calendarCombo_3.setBounds(730, 160, 88, 25);
		calendarCombo_3.setVisible(false);
		showData();
		//点击图表
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String orderdate=calendarCombo.getDateAsString();
				String orderdates=calendarCombo_1.getDateAsString();
				List<Map<String,Object>> list=orderReportDao.findDate(orderdate,orderdates);
				
				combo_1.getText();
				if(combo_1.getText().trim().equals("柱状图")){
				zhuzhuangTu.ZhuzhuangTuOrder(list);
				}else if(combo_1.getText().trim().equals("饼图")){
					bingTu.BingTuOrder(list);
				}else if(combo_1.getText().trim().equals("折线图")){
					zheXianTu.ZheXianTuOrder(list);
				}
				
			}
		});

		//导出EXCEL
		btnexcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String,Object>> list=orderReportDao.find();
				createEXCEL.CreateEXCELorder(list);
				MessageDialog.openInformation(getShell(), "温馨提示", "成功导出完整的订单表，请前往D盘查看....");
			}
		});
		//combo的监听
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(combo.getText().trim().equals("订单编号")){
					text.setVisible(true);
					calendarCombo_2.setVisible(false);
					calendarCombo_3.setVisible(false);
				}else if(combo.getText().trim().equals("书籍名称")){
					text.setVisible(true);
					calendarCombo_2.setVisible(false);
					calendarCombo_3.setVisible(false);
					
				}else if(combo.getText().trim().equals("商家")){
					text.setVisible(true);
					calendarCombo_2.setVisible(false);
					calendarCombo_3.setVisible(false);
				}else if(combo.getText().trim().equals("时间段")){
					text.setVisible(false);
					calendarCombo_2.setVisible(true);
					calendarCombo_3.setVisible(true);
				}else if(combo.getText().trim().equals("查询所有")){
					text.setVisible(true);
					calendarCombo_2.setVisible(false);
					calendarCombo_3.setVisible(false);
				}
				
			}
		});
		//点击查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian;
				String jieguo=text.getText().trim();
				if(combo.getText().trim().equals("订单编号")){
					tiaojian="bo.orderid";
					if(orderReportDao.finds(tiaojian,jieguo)!=null&&orderReportDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示 ","查询失败...");
					}
				}else if(combo.getText().trim().equals("书籍名称")){
					
					tiaojian="b.bookname";
					if(orderReportDao.finds(tiaojian,jieguo)!=null&&orderReportDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示 ","查询失败...");
					}

				}else if(combo.getText().trim().equals("商家")){
					tiaojian="bo.seller";
					if(orderReportDao.finds(tiaojian,jieguo)!=null&&orderReportDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示 ","查询失败...");
					}
					
				}else if(combo.getText().trim().equals("时间段")){
					String orderdate=calendarCombo_2.getDateAsString();
					String orderdates=calendarCombo_3.getDateAsString();
					if(orderReportDao.findDate(orderdate, orderdates)!=null&&orderReportDao.findDate(orderdate, orderdates).size()>0){
						
						showDataDate(orderdate,orderdates);
					}else{
						MessageDialog.openError(getShell(), "温馨提示 ","查询失败...");
					}
					

				}else if(combo.getText().trim().equals("查询所有")){
					showData();
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
		List<Map<String,Object>> list=orderReportDao.find();	
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("ORDERID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("SELLER")),String.valueOf(map.get("ORDERDATE")),String.valueOf(map.get("ACCEPT")).equals("null")?"":String.valueOf(map.get("ACCEPT")),String.valueOf(map.get("PRICE")),String.valueOf(map.get("BOOKNUM")),String.valueOf(map.get("G"))});	

			}
		}
	}
	//显示数据
	public void showDatas(String tiaojian,String jieguo ){
		if(table!=null){
			table.removeAll();
		}
		List<Map<String,Object>> list=orderReportDao.finds(tiaojian,jieguo);	
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("ORDERID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("SELLER")),String.valueOf(map.get("ORDERDATE")),String.valueOf(map.get("ACCEPT")).equals("null")?"":String.valueOf(map.get("ACCEPT")),String.valueOf(map.get("PRICE")),String.valueOf(map.get("BOOKNUM")),String.valueOf(map.get("G"))});	

			}
		}
	}
	//显示数据
	public void showDataDate(String orderdate,String orderdates ){
		if(table!=null){
			table.removeAll();
		}
		List<Map<String,Object>> list=orderReportDao.findDate(orderdate,orderdates);	
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("ORDERID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("SELLER")),String.valueOf(map.get("ORDERDATE")),String.valueOf(map.get("ACCEPT")).equals("null")?"":String.valueOf(map.get("ACCEPT")),String.valueOf(map.get("PRICE")),String.valueOf(map.get("BOOKNUM")),String.valueOf(map.get("G"))});	

			}
		}
	}
}
