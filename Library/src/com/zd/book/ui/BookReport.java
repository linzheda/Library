package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.BookReportDao;
import com.zd.book.util.BingTu;
import com.zd.book.util.CreateEXCEL;
import com.zd.book.util.ZheXianTu;
import com.zd.book.util.ZhuzhuangTu;

import org.eclipse.swt.widgets.Label;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;

public class BookReport extends Composite {
	private Text text;
	private Table table;
	private BookReportDao bookReportDao=new BookReportDao();
	private Text text_1;
	private Table table_1;
	private boolean flag;
	private Combo combo_3;
	private Combo combo_4;
	private BingTu bingTu=new BingTu();
	private ZhuzhuangTu zhuzhuangTu=new ZhuzhuangTu();
	private ZheXianTu zheXianTu=new ZheXianTu();
	private CreateEXCEL createEXCEL=new CreateEXCEL();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookReport(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(BookReport.class, "/images/框框.jpg"));

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 138, 25);
		label.setText("书籍借阅详细");

		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setBounds(167, 162, 724, 400);

		final TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("预约详细");


		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(0, 0);
		table.setSize(732, 171);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(153);
		tableColumn.setText("书籍编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(116);
		tableColumn_1.setText("书名");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(155);
		tableColumn_4.setText("借阅证号");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(180);
		tableColumn_2.setText("预约日期");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(111);
		tableColumn_3.setText("状态");

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(26, 194, 61, 17);
		label_1.setText("预约详细：");

		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(135, 191, 88, 25);
		combo.add("书籍编号");
		combo.add("书名");
		combo.add("按时间段查询");
		combo.add("状态");
		combo.add("借阅证号");
		combo.add("查询所有");

		text = new Text(composite, SWT.BORDER);
		text.setBounds(247, 191, 73, 25);

		Button button = new Button(composite, SWT.NONE);
		button.setBounds(470, 190, 60, 25);
		button.setText("查询");

		final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);
		calendarCombo.setBounds(247, 191, 88, 25);
		calendarCombo.setVisible(false);
		final CalendarCombo calendarCombo_1 = new CalendarCombo(composite, SWT.NONE);
		calendarCombo_1.setBounds(360, 191, 88, 25);
		calendarCombo_1.setVisible(false);

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(360, 308, 80, 27);
		button_1.setText("图表");

		combo_3 = new Combo(composite, SWT.NONE);
		combo_3.setBounds(135, 255, 88, 25);
		combo_3.add("柱状图");
		combo_3.add("饼图");
		combo_3.add("折线图");

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(559, 189, 80, 27);
		btnNewButton_1.setText("导出EXCEL");

		final CalendarCombo calendarCombo_4 = new CalendarCombo(composite, SWT.NONE);
		calendarCombo_4.setBounds(135, 310, 88, 25);

		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(26, 258, 87, 17);
		label_3.setText("选择图表类型：");

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(26, 318, 80, 17);
		lblNewLabel.setText("选择时间段：");

		final CalendarCombo calendarCombo_5 = new CalendarCombo(composite, SWT.NONE);
		calendarCombo_5.setBounds(247, 310, 88, 25);



		//导出预约表
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String,Object>> list=bookReportDao.finds();
				createEXCEL.CreateEXCELs(list);
				MessageDialog.openInformation(getShell(), "温馨提示", "完整的预约表已导出，请前往D盘查看");
			}
		});

		//报表预约表图表
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bdate=calendarCombo_4.getDateAsString();
				String hdate=calendarCombo_5.getDateAsString();
				List<Map<String,Object>> list=bookReportDao.findsDate(hdate,bdate);
				combo_3.getText();
				if(combo_3.getText().trim().equals("柱状图")){
					zhuzhuangTu.ZhuzhuangTus(list);
				}else if(combo_3.getText().trim().equals("饼图")){
					bingTu.BingTus(list);
				}else if(combo_3.getText().trim().equals("折线图")){
					zheXianTu.ZheXianTus(list);
				}


			}
		});
		//点击查询预约表
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text.getText().trim();
				if(tiaojian.equals("书籍编号")){
					tiaojian="b.bookid";
					if(bookReportDao.finds(tiaojian,jieguo)!=null&&bookReportDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);
					}
				}else if(tiaojian.equals("书名")){
					tiaojian="b.bookname";
					if(bookReportDao.finds(tiaojian, jieguo)!=null&&bookReportDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("借书证号")){
					tiaojian="br.userid";
					if(bookReportDao.finds(tiaojian, jieguo)!=null&&bookReportDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("状态")){
					tiaojian="bs.bespeakstate";
					if(bookReportDao.finds(tiaojian,jieguo)!=null&&bookReportDao.finds(tiaojian,jieguo).size()>0){
						showDatas(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("按时间段查询")){

					String bdate=calendarCombo.getDateAsString();
					String hdate=calendarCombo_1.getDateAsString();

					if(bookReportDao.findsDate(hdate,bdate)!=null&&bookReportDao.findsDate(hdate,bdate).size()>0){
						showDatasDate(hdate,bdate);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("查询所有")){
					if(bookReportDao.find()!=null){
						showDatas();
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}



			}
		});

		//预约表
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().trim().equals("按时间段查询")){
					calendarCombo.setVisible(true);
					calendarCombo_1.setVisible(true);
					text.setVisible(false);
				}else{
					calendarCombo.setVisible(false);
					calendarCombo_1.setVisible(false);
					text.setVisible(true);
				}
			}
		});

		final TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("借阅详细");


		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);

		table_1 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setSize(732, 176);
		table_1.setLocation(0, 0);

		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_5.setWidth(64);
		tableColumn_5.setText("书籍编号");

		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_6.setWidth(76);
		tableColumn_6.setText("书名");

		TableColumn tblclmnNewColumn = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn.setWidth(102);
		tblclmnNewColumn.setText("借书证号");

		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_7.setWidth(147);
		tableColumn_7.setText("借阅日期");

		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_8.setWidth(151);
		tableColumn_8.setText("归还日期");

		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_9.setWidth(87);
		tableColumn_9.setText("罚单");

		TableColumn tableColumn_10 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("罚单状态");

		combo_4 = new Combo(composite_1, SWT.NONE);
		combo_4.setBounds(123, 254, 88, 25);
		combo_4.add("柱状图");
		combo_4.add("饼图");
		combo_4.add("折线图");

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(27, 206, 61, 17);
		label_2.setText("借阅详细：");

		final Combo combo_1 = new Combo(composite_1, SWT.NONE);
		combo_1.setBounds(123, 203, 88, 25);
		combo_1.add("书籍编号");
		combo_1.add("书名");
		combo_1.add("借书证号");
		combo_1.add("按时间段查询");
		combo_1.add("罚单");
		combo_1.add("查询所有");

		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(244, 203, 73, 23);
		final CalendarCombo calendarCombo_3 = new CalendarCombo(composite_1, SWT.NONE);
		calendarCombo_3.setBounds(377, 206, 100, 25);
		calendarCombo_3.setVisible(false);
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setBounds(509, 203, 60, 25);
		btnNewButton.setText("查询");

		Button btnexcel = new Button(composite_1, SWT.NONE);
		btnexcel.setBounds(592, 201, 80, 27);
		btnexcel.setText("导出EXCEL");

		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setBounds(27, 307, 73, 17);
		label_4.setText("选择时间段：");

		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setBounds(27, 257, 79, 17);
		label_5.setText("选择图表类型：");

		final CalendarCombo calendarCombo_6 = new CalendarCombo(composite_1, SWT.NONE);
		calendarCombo_6.setBounds(123, 307, 88, 25);

		final CalendarCombo calendarCombo_7 = new CalendarCombo(composite_1, SWT.NONE);
		calendarCombo_7.setBounds(244, 307, 88, 25);

		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setBounds(360, 305, 80, 27);
		button_2.setText("图表");

		final CalendarCombo calendarCombo_2 = new CalendarCombo(composite_1, SWT.NONE);
		calendarCombo_2.setBounds(244, 206, 100, 25);
		calendarCombo_2.setVisible(false);

		final Combo combo_2 = new Combo(composite_1, SWT.NONE);
		combo_2.setBounds(244, 203, 80, 25);
		combo_2.setVisible(false);
		combo_2.add("有罚单");
		combo_2.add("没有罚单");
		//报表借阅表
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bdate=calendarCombo_6.getDateAsString();
				String hdate=calendarCombo_7.getDateAsString();
				List<Map<String,Object>> list=bookReportDao.findDate(hdate,bdate);
				combo_4.getText();
				if(combo_4.getText().trim().equals("柱状图")){
					zhuzhuangTu.ZhuzhuangTuss(list);
				}else if(combo_4.getText().trim().equals("饼图")){
					bingTu.BingTuss(list);
					System.out.println(list);
				}else if(combo_4.getText().trim().equals("折线图")){
					zheXianTu.ZheXianTuss(list);
				}
			}
		});
		//导出借阅表
		btnexcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String,Object>> list=bookReportDao.find();
				createEXCEL.CreateEXCELss(list);
				MessageDialog.openInformation(getShell(), "温馨提示", "完整的借阅表已导出，请前往D盘查看");
			}
		});
		//点击查询借阅表
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo_1.getText().trim();
				String jieguo=text_1.getText().trim();
				if(tiaojian.equals("书籍编号")){
					tiaojian="bd.bid";
					if(bookReportDao.find(tiaojian,jieguo)!=null&&bookReportDao.find(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}
				}else if(tiaojian.equals("书名")){
					tiaojian="b.bookname";
					if(bookReportDao.find(tiaojian, jieguo)!=null&&bookReportDao.find(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("借书证号")){
					tiaojian="br.stuid";
					if(bookReportDao.find(tiaojian, jieguo)!=null&&bookReportDao.find(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("罚单")){
					if(combo_2.getText().trim().equals("有罚单")){

						flag=true;
						tiaojian="br.ticket";

						if(bookReportDao.findT(flag)!=null&&bookReportDao.findT(flag).size()>0){
							showDataT(tiaojian,jieguo);

						}else{
							flag=false;
							MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
						}
					}else if(combo_2.getText().trim().equals("没有罚单")){
						flag=false;
						tiaojian="br.ticket";

						if(bookReportDao.findT(flag)!=null&&bookReportDao.findT(flag).size()>0){
							showDataT(tiaojian,jieguo);

						}else{
							MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
						}
					}

				}else if(tiaojian.equals("按时间段查询")){

					String bdate=calendarCombo_2.getDateAsString();
					String hdate=calendarCombo_3.getDateAsString();

					if(bookReportDao.findDate(hdate,bdate)!=null&&bookReportDao.findDate(hdate,bdate).size()>0){
						System.out.println(bookReportDao.findDate(hdate,bdate));
						showDataDate(hdate,bdate);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}


				}else if(tiaojian.equals("查询所有")){
					if(bookReportDao.find()!=null){
						showData();
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}
			}
		});


		//时间段的设置借阅表
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo_1.getText().trim().equals("按时间段查询")){
					calendarCombo_2.setVisible(true);
					calendarCombo_3.setVisible(true);
					text_1.setVisible(false);
					combo_2.setVisible(false);

				}else if(combo_1.getText().trim().equals("罚单")){
					text_1.setVisible(false);
					combo_2.setVisible(true);
					calendarCombo_2.setVisible(false);
					calendarCombo_3.setVisible(false);

				}else{
					calendarCombo_2.setVisible(false);
					calendarCombo_3.setVisible(false);
					combo_2.setVisible(false);
					text_1.setVisible(true);
				}

			}
		});
		showData();
		showDatas();


	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	//显示数据借阅表
	public void showData(){
		if(table_1!=null){
			table_1.removeAll();
		}
		List<Map<String,Object>> list=bookReportDao.find();
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table_1,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("STUID")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("HDATE")).equals("null")?"":String.valueOf(map.get("HDATE")),String.valueOf(map.get("TICKET")).equals("null")?"":String.valueOf(map.get("TICKET")),String.valueOf(map.get("STATE")).equals("null")?"":String.valueOf(map.get("STATE"))});	

			}
		}
	}
	//显示数据借阅表条件
	public void showData(String tiaojian,String jieguo){
		if(table_1!=null){
			table_1.removeAll();
		}
		List<Map<String,Object>> list=bookReportDao.find(tiaojian,jieguo);
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table_1,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("STUID")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("HDATE")).equals("null")?"":String.valueOf(map.get("HDATE")),String.valueOf(map.get("TICKET")).equals("null")?"":String.valueOf(map.get("TICKET")),String.valueOf(map.get("STATE")).equals("null")?"":String.valueOf(map.get("STATE"))});	

			}
		}
	}
	//显示数据借阅表条件fadan
	public void showDataT(String tiaojian,String jieguo){
		if(table_1!=null){
			table_1.removeAll();
		}
		List<Map<String,Object>> list=bookReportDao.findT(flag);
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table_1,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("STUID")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("HDATE")).equals("null")?"":String.valueOf(map.get("HDATE")),String.valueOf(map.get("TICKET")).equals("null")?"":String.valueOf(map.get("TICKET")),String.valueOf(map.get("STATE")).equals("null")?"":String.valueOf(map.get("STATE"))});	

			}
		}
	}
	//显示数据借阅表条件riqi
	public void showDataDate(String hdate,String bdate){
		if(table_1!=null){
			table_1.removeAll();
		}
		List<Map<String,Object>> list=bookReportDao.findDate(hdate,bdate);
		if(list!=null&&list.size()>0){
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table_1,SWT.NONE);
				ti.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("STUID")),String.valueOf(map.get("BDATE")),String.valueOf(map.get("HDATE")).equals("null")?"":String.valueOf(map.get("HDATE")),String.valueOf(map.get("TICKET")).equals("null")?"":String.valueOf(map.get("TICKET")),String.valueOf(map.get("STATE")).equals("null")?"":String.valueOf(map.get("STATE"))});	

			}
		}
	}


	//显示数据预约表
	public void showDatas(){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=bookReportDao.finds();
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("USERID")),String.valueOf(map.get("BESPEAKDATE")),String.valueOf(map.get("BESPEAKSTATE"))});	

				}
			}
		}
	}
	//显示数据预约表条件
	public void showDatas(String tiaojian,String jieguo ){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=bookReportDao.finds(tiaojian,jieguo);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("USERID")),String.valueOf(map.get("BESPEAKDATE")),String.valueOf(map.get("BESPEAKSTATE"))});	

				}
			}
		}
	}
	//显示数据预约表条件riqi
	public void showDatasDate(String hdate,String bdate){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=bookReportDao.findsDate(hdate,bdate);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("USERID")),String.valueOf(map.get("BESPEAKDATE")),String.valueOf(map.get("BESPEAKSTATE"))});	

				}
			}
		}
	}
}
