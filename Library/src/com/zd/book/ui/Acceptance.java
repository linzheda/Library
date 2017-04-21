package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.AcceptanceDao;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

//新书验收
public class Acceptance extends Composite {
	private Table table;
	private AcceptanceDao acceptanceDao=new AcceptanceDao();
	private Text text;
	String tiaojian="";
	String jieguo="";


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Acceptance(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Acceptance.class, "/images/框框.jpg"));
		setLayout(null);

		Label label = new Label(this, SWT.NONE);
		label.setText("新书验收");
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 92, 23);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);


		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(160, 175, 739, 218);

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(80);
		tableColumn_1.setText("订单编号");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("书名");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(88);
		tableColumn_3.setText("书籍类别");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("出版社");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(62);
		tableColumn_5.setText("单价");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(69);
		tableColumn_6.setText("下单数量");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(148);
		tableColumn.setText("下单时间");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(86);
		tableColumn_7.setText("总价");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);

		menuItem.setText("验收");

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setText("订单编号：");
		label_1.setBounds(250, 417, 61, 23);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setText("出 版 社：");
		label_2.setAlignment(SWT.CENTER);
		label_2.setBounds(250, 474, 61, 23);

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setText("总     价 ：");
		label_3.setBounds(501, 521, 67, 17);

		final Label lblNewLabel_6 = new Label(this, SWT.NONE);	//总价
		lblNewLabel_6.setBounds(574, 521, 99, 17);

		final Label lblNewLabel_3 = new Label(this, SWT.NONE);	//出版社
		lblNewLabel_3.setBounds(316, 474, 100, 17);

		final Label lblNewLabel = new Label(this, SWT.NONE);		//订单编号
		lblNewLabel.setBounds(317, 419, 99, 17);

		Label label_7 = new Label(this, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_7.setText("书籍名称：");
		label_7.setBounds(501, 417, 64, 23);

		Label label_8 = new Label(this, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_8.setText("单      价：");
		label_8.setAlignment(SWT.CENTER);
		label_8.setBounds(501, 474, 67, 17);

		final Label lblNewLabel_4 = new Label(this, SWT.NONE);	//单价
		lblNewLabel_4.setBounds(571, 474, 99, 17);

		final Label lblNewLabel_1 = new Label(this, SWT.NONE);	//书籍名称
		lblNewLabel_1.setBounds(574, 419, 99, 17);

		Label label_11 = new Label(this, SWT.NONE);
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_11.setText("书籍种类：");
		label_11.setBounds(725, 417, 61, 23);

		final Label lblNewLabel_5 = new Label(this, SWT.NONE);	//下单数量
		lblNewLabel_5.setBounds(792, 477, 86, 17);

		final Label lblNewLabel_2 = new Label(this, SWT.NONE);	//书籍种类
		lblNewLabel_2.setBounds(792, 419, 86, 17);

		Label label_14 = new Label(this, SWT.NONE);
		label_14.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_14.setText("下单数量：");
		label_14.setBounds(725, 477, 61, 20);

		Button button = new Button(this, SWT.NONE);

		button.setText("验  收");
		button.setBounds(706, 558, 80, 27);

		Button button_1 = new Button(this, SWT.NONE);

		button_1.setText("清  空");
		button_1.setBounds(819, 558, 80, 27);

		final Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(615, 134, 88, 27);
		combo.add("订单编号");
		combo.add("书名");
		combo.add("书籍类别");
		combo.add("查询所有");

		text = new Text(this, SWT.BORDER);
		text.setBounds(725, 134, 86, 25);

		Button btnNewButton = new Button(this, SWT.NONE);

		btnNewButton.setBounds(838, 133, 61, 25);
		btnNewButton.setText("查询");

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setBounds(251, 521, 61, 23);
		label_4.setText("下单时间：");

		final Label lblNewLabel_7 = new Label(this, SWT.NONE);	//下单时间
		lblNewLabel_7.setBounds(315, 521, 122, 17);

		showData();

		/**
		 * 通过订单编号、书名、书籍类别 进行查询
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tiaojian=combo.getText().trim();		
				jieguo=text.getText().trim();		//获取因条件输入的数据


				if("查询所有".equals(tiaojian)){
					showData();
				}else if( !"查询所有".equals(tiaojian) && ("".equals(tiaojian) || "".equals(jieguo))){
					MessageDialog.openError(getShell(), "错误提示","您填入的信息为空,查询失败,请重新填写查询");
				}else {
					if("订单编号".equals(tiaojian)){
						tiaojian="bo.orderid";

						showData(tiaojian,jieguo);
					}else if("书名".equals(tiaojian)){
						tiaojian="b.bookname";

						showData(tiaojian,jieguo);
					}else if("书籍类别".equals(tiaojian)){
						tiaojian="bc.classname";

						showData(tiaojian,jieguo);
					}
				}	
			}
		});



		/**
		 * 对table表格实行监听
		 */
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();

				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					lblNewLabel.setText(t.getText(0));		//订单编号
					lblNewLabel_1.setText(t.getText(1));	//书名
					lblNewLabel_2.setText(t.getText(2));	//书籍类别
					lblNewLabel_3.setText(t.getText(3));	//出版社
					lblNewLabel_4.setText(t.getText(4));	//单价
					lblNewLabel_5.setText(t.getText(5));	//下单数量
					lblNewLabel_7.setText(t.getText(6));	//下单时间
					lblNewLabel_6.setText(t.getText(7));	//总价
				}
			}
		});







		/**
		 * 当右键点击验收时
		 */
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();

				String bianhao=tis[0].getText(0);
				String shuliang=tis[0].getText(5);
				String shuming=tis[0].getText(1);

				if(MessageDialog.openQuestion(getShell(), "确认提示","您是否确认验收?")){
					if(acceptanceDao.updatebookorder(bianhao)>0 && acceptanceDao.updatebook(shuming, shuliang)>0){	//确认验收时添加一个验收时间
						MessageDialog.openWarning(getShell(), "验收提示","验收成功");
						showData();
					}else {
						MessageDialog.openWarning(getShell(), "验收提示","验收失败");
					}
				}else {
					return;
				}
			}
		});


		/**
		 * 当点击验收按钮时
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();

				String bianhao=tis[0].getText(0);
				String shuliang=tis[0].getText(5);
				String shuming=tis[0].getText(1);

				if(MessageDialog.openQuestion(getShell(), "确认提示","您是否确认验收?")){
					if(acceptanceDao.updatebookorder(bianhao)>0 && acceptanceDao.updatebook(shuming, shuliang)>0){	//确认验收时添加一个验收时间
						MessageDialog.openWarning(getShell(), "验收提示","验收成功");
						showData();
					}else {
						MessageDialog.openWarning(getShell(), "验收提示","验收失败");
					}
				}else {
					return;
				}
			}
		});


		/**
		 * 当点击取消按钮时
		 */
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblNewLabel.setText("");		//订单编号
				lblNewLabel_1.setText("");	//书名
				lblNewLabel_2.setText("");	//书籍类别
				lblNewLabel_3.setText("");	//出版社
				lblNewLabel_4.setText("");	//单价
				lblNewLabel_5.setText("");	//下单数量
				lblNewLabel_7.setText("");	//下单时间
				lblNewLabel_6.setText("");	//总价
			}
		});

	}




	/**
	 * 查找显示数据
	 */
	public void showData(){
		table.removeAll();//移除表格中的数据
		List<Map<String,Object>> types=acceptanceDao.find();

		if(types!=null && types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem (table,SWT.NONE);
				tm.setText(new String[] {String.valueOf(map.get("ORDERID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("CLASSNAME"))
						,String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("BOOKPRICE")),String.valueOf(map.get("BOOKNUM")),String.valueOf(map.get("ORDERDATE"))
						,String.valueOf(map.get("SUM"))});
			}
		}
	}


	/**
	 * 查找显示数据
	 */
	public void showData(String tiaojian,String jieguo){
		table.removeAll();//移除表格中的数据
		List<Map<String,Object>> types=acceptanceDao.find(tiaojian,jieguo);

		if(types!=null && types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem (table,SWT.NONE);
				tm.setText(new String[] {String.valueOf(map.get("ORDERID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("CLASSNAME"))
						,String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("BOOKPRICE")),String.valueOf(map.get("BOOKNUM")),String.valueOf(map.get("ORDERDATE"))
						,String.valueOf(map.get("SUM"))});
			}
		}
	}



	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
