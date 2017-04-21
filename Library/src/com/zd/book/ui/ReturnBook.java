package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.ReturnBookDao;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

//还书
public class ReturnBook extends Composite {
	private Table table;
	private Text text_1;
	private Shell shell;
	private ReturnBookDao returnbookDao=new ReturnBookDao();
	private String tiaojian="";
	private String jieguo="";
	private String xujiedate="";

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ReturnBook(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(ReturnBook.class, "/images/框框.jpg"));
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("还书管理");
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(481, 88, 92, 23);

		Button button = new Button(composite_1, SWT.NONE);

		button.setText("查询");
		button.setBounds(826, 130, 61, 25);

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(159, 172, 728, 234);

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(77);
		tableColumn_1.setText("借阅者姓名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(80);
		tableColumn_2.setText("借阅证号");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(73);
		tableColumn_3.setText("书籍编号");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(99);
		tableColumn_4.setText("书名");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(110);
		tableColumn_5.setText("借书日期");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(110);
		tableColumn_6.setText("截至时间");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(85);
		tableColumn_7.setText("剩余天数");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);

		menuItem.setText("还书");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(89);
		tableColumn_8.setText("还书日期");

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("借阅者姓名：");
		label_1.setBounds(440, 448, 76, 17);

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setText("借阅证号：");
		label_3.setBounds(673, 448, 61, 17);


		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(673, 513, 76, 17);
		label_2.setText("书 籍 编 号：");

		final Label lblNewLabel = new Label(composite_1, SWT.NONE);		
		lblNewLabel.setBounds(754, 513, 66, 17);

		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setBounds(440, 574, 61, 17);
		label_4.setText("书籍名称：");

		final Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBounds(507, 574, 115, 17);

		Button button_3 = new Button(composite_1, SWT.NONE);

		button_3.setBounds(779, 620, 80, 27);
		button_3.setText("清  空");

		final Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setBounds(604, 131, 88, 25);
		combo.add("姓名");
		combo.add("借书证号");
		combo.add("书籍编号");
		combo.add("查询所有");

		text_1 = new Text(composite_1, SWT.BORDER);		//选择查询填写框
		text_1.setBounds(715, 131, 88, 25);
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		
		button_1.setBounds(661, 620, 80, 27);
		button_1.setText("还  书");
		
		final Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setBounds(740, 448, 80, 17);
		
		final Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setBounds(520, 448, 100, 17);
		
		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setBounds(440, 513, 61, 17);
		label_7.setText("借书日期：");
		
		final Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setBounds(507, 513, 100, 17);
		
		Label label_9 = new Label(composite_1, SWT.NONE);
		label_9.setBounds(673, 574, 61, 17);
		label_9.setText("剩余时间：");
		
		final Label label_10 = new Label(composite_1, SWT.NONE);
		label_10.setBounds(754, 574, 61, 17);
		sashForm.setWeights(new int[] {1});


		showDatas();


		/**
		 * 通过条件进行查询时
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tiaojian=combo.getText().trim();
				jieguo=text_1.getText().trim();	

				String xujie="";

				if("查询所有".equals(tiaojian)){
					showDatas();
				}else if( !"查询所有".equals(tiaojian) && ("".equals(tiaojian) || "".equals(jieguo))){
					MessageDialog.openError(getShell(), "错误提示","您填入的信息为空,查询失败,请重新填写查询");
				}else {
					if ("姓名".equals(tiaojian)){
						if( jieguo!=null || !"".equals(jieguo) ){
							tiaojian="ui.sname";

							showDatas(tiaojian,jieguo);
						}else {
							MessageDialog.openError(getShell(), "错误提示","输入错误，请重新输入");
						}
					}else if ("借书证号".equals(tiaojian)){
						if( (jieguo!=null || !"".equals(jieguo)) ){
							tiaojian="bo.stuid";

							showDatas(tiaojian,jieguo);
						}else {
							MessageDialog.openError(getShell(), "错误提示","输入错误，请重新输入");
						}
					}else if ("书籍编号".equals(tiaojian)){
						if( (jieguo!=null || !"".equals(jieguo)) ){
							tiaojian="bd.bid";

							showDatas(tiaojian,jieguo);
						}else {
							MessageDialog.openError(getShell(), "错误提示","输入错误，请重新输入");
						}
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
					label_6.setText(t.getText(0));		//借阅者姓名
					label_5.setText(t.getText(1));  	//借阅证号
					lblNewLabel.setText(t.getText(2));	//书籍编号
					lblNewLabel_1.setText(t.getText(3));	//书籍名称
					label_8.setText(t.getText(4));		//借书日期
					label_10.setText(t.getText(6));		//剩余天数
				}
			}
		});


		/**
		 * 点击右键还书时
		 */
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();

				String stuid=tis[0].getText(1);		//获取借书证号
				String bid=tis[0].getText(2);	//获取书籍编号
				String bianhao="bd.bid";
				
				List<Map<String,Object>> types=returnbookDao.find(bianhao,bid);	//通过序列号获取罚款金额
				int shengyu=Integer.valueOf(tis[0].getText(6));

				String jine="";
				String zhuangtai="";		//已缴纳和未缴纳和null
				for(Map<String,Object> map:types){
					jine=String.valueOf(map.get("TICKET"));
					zhuangtai=String.valueOf(map.get("STATE"));
				}

				if(Integer.valueOf(shengyu)>0){		//如果剩余时间大于等于0时，不需要罚款
					if(returnbookDao.updateborrow(bid)>0  && returnbookDao.updateborrows(bid)>0 && returnbookDao.updatebookdetailed(bid)>0 ){	//还书
						if(MessageDialog.openQuestion(getShell(), "确认提示","您是否确认还书？")){
							MessageDialog.openWarning(getShell(), "温馨提示","还书成功");
							
							label_6.setText("");		//借阅者姓名
							label_5.setText("");  	//借阅证号
							lblNewLabel.setText("");	//书籍编号
							lblNewLabel_1.setText("");	//书籍名称
							label_8.setText("");
							label_10.setText("");

							showDatas();
						}else {
							MessageDialog.openWarning(getShell(), "确认提示","还书失败...");
						}
						
					}else {
						MessageDialog.openWarning(getShell(), "温馨提示","还书失败");
					}
				}else{	//需要罚款
					if(MessageDialog.openQuestion (getShell(), "罚单","您已超过规定还书日期,是否立即缴纳罚款  "+jine+" 元")){
						if(returnbookDao.updateborrow(bid)>0  && returnbookDao.updateborrows(bid)>0 && returnbookDao.updatebookdetailed(bid)>0){	//还书
							MessageDialog.openWarning(getShell(), "缴纳提示","您已成功缴纳罚款");
							label_6.setText("");		//借阅者姓名
							label_5.setText("");  	    //借阅证号
							lblNewLabel.setText("");	//书籍编号
							lblNewLabel_1.setText("");	//书籍名称
							label_8.setText("");
							label_10.setText("");

							showDatas();
						}else{
							MessageDialog.openWarning(getShell(), "缴纳提示","缴纳罚款失败");
						}
					}else {
						return ;
					}	
				}
			}
		});

		/**
		 * 当点击还书按钮时
		 */
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String stuid=label_5.getText().trim();	//借阅证号
				String bid=lblNewLabel.getText().trim();	//获取书籍编号
				String bianhao="bd.bid";
				
				List<Map<String,Object>> types=returnbookDao.find(bianhao,bid);	//通过序列号获取罚款金额
				int shengyu=Integer.valueOf(label_10.getText().trim());

				String jine="";
				String zhuangtai="";		//已缴纳和未缴纳和null
				for(Map<String,Object> map:types){
					jine=String.valueOf(map.get("TICKET"));
					zhuangtai=String.valueOf(map.get("STATE"));
				}

				if(Integer.valueOf(shengyu)>0){		//如果剩余时间大于等于0时，不需要罚款
					if(returnbookDao.updateborrow(bid)>0  && returnbookDao.updateborrows(bid)>0 && returnbookDao.updatebookdetailed(bid)>0 ){	//还书
						if(MessageDialog.openQuestion(getShell(), "确认提示","您是否确认还书？")){
							MessageDialog.openWarning(getShell(), "温馨提示","还书成功");
							
							label_6.setText("");		//借阅者姓名
							label_5.setText("");  	//借阅证号
							lblNewLabel.setText("");	//书籍编号
							lblNewLabel_1.setText("");	//书籍名称
							label_8.setText("");
							label_10.setText("");

							showDatas();
						}else {
							MessageDialog.openWarning(getShell(), "确认提示","还书失败...");
						}
						
					}else {
						MessageDialog.openWarning(getShell(), "温馨提示","还书失败");
					}
				}else{	//需要罚款
					if(MessageDialog.openQuestion (getShell(), "罚单","您已超过规定还书日期,是否立即缴纳罚款  "+jine+" 元")){
						if(returnbookDao.updateborrow(bid)>0  && returnbookDao.updateborrows(bid)>0 && returnbookDao.updatebookdetailed(bid)>0){	//还书
							MessageDialog.openWarning(getShell(), "缴纳提示","您已成功缴纳罚款");
							label_6.setText("");		//借阅者姓名
							label_5.setText("");  	    //借阅证号
							lblNewLabel.setText("");	//书籍编号
							lblNewLabel_1.setText("");	//书籍名称
							label_8.setText("");
							label_10.setText("");

							showDatas();
						}else{
							MessageDialog.openWarning(getShell(), "缴纳提示","缴纳罚款失败");
						}
					}else {
						return ;
					}	
				}
			}
		});



		/**
		 * 当点击取消按钮时
		 */
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				label_6.setText("");
				label_5.setText("");
				lblNewLabel.setText("");
				lblNewLabel_1.setText("");
				label_8.setText("");
				label_10.setText("");
			}
		});



	}


	/**
	 * 显示添加了的数据		
	 * 显示数据
	 */
	public void showDatas(){
		table.removeAll();//移除表格中的数据
		List<Map<String,Object>> types=returnbookDao.find();

		if(types!=null && types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem (table,SWT.NONE);

				tm.setText(new String[] {String.valueOf(map.get("SNAME")),String.valueOf(map.get("USERID"))
						,String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BDATE")).substring(0, 10)
						,String.valueOf(map.get("ENDDATE")).substring(0,10),String.valueOf(map.get("SY")).substring(0,String.valueOf(map.get("SY")).lastIndexOf("."))
						,"null".equals(String.valueOf(map.get("HDATE")))?"":String.valueOf(map.get("HDATE"))});

			}
		}
	}


	/**
	 * 显示添加了的数据		
	 * 显示数据
	 */
	public void showDatas(String tiaojian,String jieguo){
		table.removeAll();//移除表格中的数据
		List<Map<String,Object>> types=returnbookDao.find(tiaojian,jieguo);

		if(types!=null && types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem (table,SWT.NONE);

				tm.setText(new String[] {String.valueOf(map.get("SNAME")),String.valueOf(map.get("USERID"))
						,String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BDATE")).substring(0, 10)
						,String.valueOf(map.get("ENDDATE")).substring(0,10),String.valueOf(map.get("SY")).substring(0,String.valueOf(map.get("SY")).lastIndexOf("."))
						,"null".equals(String.valueOf(map.get("HDATE")))?"":String.valueOf(map.get("HDATE"))});

			}
		}
	}



	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
