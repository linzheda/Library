package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.LostBookDao;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

//丢失书籍
public class LostBook extends Composite {
	private Table table;
	private Text text_1;
	private LostBookDao lostbookDao=new LostBookDao();
	String tiaojian="";
	String jieguo="";
	private Text text;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LostBook(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(LostBook.class, "/images/框框.jpg"));
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(null);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("丢失管理");
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 92, 23);
		
		Button button = new Button(composite, SWT.NONE);
		
		button.setText("查询");
		button.setBounds(791, 151, 61, 25);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(206, 195, 646, 238);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(81);
		tableColumn_1.setText("书籍编号");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(93);
		tableColumn_2.setText("书名");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(93);
		tableColumn_3.setText("书籍类别");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(92);
		tableColumn_4.setText("出版社");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(111);
		tableColumn_5.setText("丢失日期");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		
		menuItem.setText("删除");
		
		TableColumn tblclmnz = new TableColumn(table, SWT.CENTER);
		tblclmnz.setWidth(85);
		tblclmnz.setText("借书证号");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(87);
		tableColumn_6.setText("借阅者姓名");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setText("书     名：");
		label_2.setBounds(206, 509, 58, 17);
		
		final Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setText("丢失日期：");
		label_3.setBounds(645, 561, 61, 17);
		
		final Label label_4 = new Label(composite, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setText("书籍编号：");
		label_4.setBounds(645, 511, 61, 17);
		
		Button button_2 = new Button(composite, SWT.NONE);
		
		button_2.setText("修  改");
		button_2.setBounds(553, 607, 80, 27);
		
		final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);	
		calendarCombo.setBounds(735, 553, 100, 25);
		
		Button button_3 = new Button(composite, SWT.NONE);
		
		button_3.setBounds(655, 607, 80, 27);
		button_3.setText("找  回");
		
		Button button_4 = new Button(composite, SWT.NONE);
		
		button_4.setBounds(753, 607, 80, 27);
		button_4.setText("清  空");
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(574, 152, 88, 25);
		combo.add("书名");
		combo.add("书籍编号");
		combo.add("种类名称");
		combo.add("借书人账号");
		combo.add("借书人姓名");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(688, 152, 80, 25);
		
		Button button_1 = new Button(composite, SWT.NONE);
		
		button_1.setBounds(447, 607, 80, 27);
		button_1.setText("添  加");
		
		text = new Text(composite, SWT.BORDER);
		
		text.setBounds(287, 506, 100, 23);
		
		text_2 = new Text(composite, SWT.BORDER);
		
		text_2.setBounds(735, 506, 100, 23);
		
		final Label label_1 = new Label(composite, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(258, 483, 129, 17);
		
		final Label label_5 = new Label(composite, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setBounds(706, 483, 129, 17);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_6.setBounds(206, 561, 61, 17);
		label_6.setText("借书证号：");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(285, 558, 102, 23);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_7.setBounds(428, 561, 69, 17);
		label_7.setText("姓      名：");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(503, 558, 92, 23);
		sashForm.setWeights(new int[] {140});

		
		showData();
		/**
		 * 对书名输入框进行监听
		 */
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String shuming=text.getText().trim();
				List<Map<String,Object>> list=lostbookDao.findpanduan1(shuming);		//判断输入的书籍名称在数据中是否存在
				String bookname="";
				for(Map<String,Object> map:list){
					bookname=String.valueOf(map.get("BOOKNAME"));
				}
				if(shuming.equals("") || bookname.equals("")){
					label_1.setText("此书数据不存在");
				}else{
					label_1.setText("");
				}
			}
		});
		
		/**
		 * 对书记编号框进行监听
		 */
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String bianhao=text_2.getText().trim();
				List<Map<String,Object>> list=lostbookDao.findpanduan2(bianhao);		//判断输入的书籍名称在数据中是否存在
				String bid="";
				for(Map<String,Object> map:list){
				bid=String.valueOf(map.get("BID"));
				}
				if(bianhao.equals("") || bid.equals("")){
					label_5.setText("书籍编号不存在");
				}else{
					label_5.setText("");
				}
			}
		});
		
		/**
		 * 通过书名、书籍编号、种类名称 进行查询
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tiaojian=combo.getText().trim();		
				jieguo=text_1.getText().trim();		//获取因条件输入的数据
				
				if(tiaojian==null || "".equals(tiaojian) || jieguo==null || "".equals(jieguo)){
					MessageDialog.openError(getShell(), "错误提示","您填入的信息为空,查询失败,请重新填写查询");
				}else {
					if("书名".equals(tiaojian)){
						tiaojian="b.bookname";
						
						showData(tiaojian,jieguo);
					}else if("书籍编号".equals(tiaojian)){
						tiaojian="bd.bid";
						
						showData(tiaojian,jieguo);
					}else if("种类名称".equals(tiaojian)){
						tiaojian="bc.classname";
						
						showData(tiaojian,jieguo);
					}else if("借书人账号".equals(tiaojian)){
						tiaojian="bc.classname";
						
						showData(tiaojian,jieguo);
					}else if("借书人姓名".equals(tiaojian)){
						tiaojian="us.sname";
						
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
					text.setText(t.getText(1));		//书名
					text_2.setText(t.getText(0));  	//书籍编号
					calendarCombo.setText(t.getText(4));	//丢失时间
					text_3.setText(t.getText(5));
					text_4.setText(t.getText(6));
					
				}
			}
		});
		
		
		/**
		 * 当右键点击删除时
		 */
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				
				String bianhao=tis[0].getText(0);    //获取书籍编号
				
				if(lostbookDao.updatebookdetailed(bianhao)>0){	//删除成功,并没有找回
					MessageDialog.openWarning(getShell(), "删除提示","删除成功");
					showData(tiaojian,jieguo);
					text.setText("");
					text_2.setText("");
					calendarCombo.setText("");
					text_3.setText("");
					text_4.setText("");
					label_1.setText("");
					label_5.setText("");
					showData();
					
				}else {
					MessageDialog.openWarning(getShell(), "删除提示","删除失败");
					text.setText("");
					text_2.setText("");
					calendarCombo.setText("");
					text_3.setText("");
					text_4.setText("");
					label_1.setText("");
					label_5.setText("");
					showData();
					return ;
				}
			}
		});
		
		
		
		/**
		 * 当点击添加按钮时
		 */
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bookname=text.getText().trim();  	//获取书名
				String bid=text_2.getText().trim();	 		//获取书籍编号
				
				String shijian=calendarCombo.getDateAsString(); 	//获取时间 
				String bookprice=null;
				String stuid=text_3.getText().trim();//借书证号
				List<Map<String,Object>> list=lostbookDao.find(bookname);
				
				System.out.println(shijian);
				System.out.println(bid);
				
				for(Map<String,Object> map:list){
					for(Map.Entry<String,Object> s:map.entrySet()){
						bookprice=String.valueOf(s.getValue());
						
					}
				}
				System.out.println(bookprice);
				String ticket=bookprice;
				String bianhao=text_2.getText().trim();
				if("".equals(bookname) || "".equals(bid)){
					MessageDialog.openError(getShell(), "错误提示","数据为空，请重新填写...");
					text.setText("");
					text_2.setText("");
					calendarCombo.setText("");
					text_3.setText("");
					text_4.setText("");
					label_1.setText("");
					label_5.setText("");
					showData();
					
				}else{
					if(MessageDialog.openQuestion(getShell(), "确认提示","是否确认添加数据?")){
						
						if(lostbookDao.update(ticket,bid)>0 && lostbookDao.updatebookdetaileds(shijian, bid)>0){	//修改成功
							MessageDialog.openWarning(getShell(), "添加提示","数据添加成功...");
							showData();
							text.setText("");
							text_2.setText("");
							calendarCombo.setText("");
							text_3.setText("");
							text_4.setText("");
							label_1.setText("");
							label_5.setText("");
							showData();
						}else{
							MessageDialog.openWarning(getShell(), "添加提示","数据添加失败...");
							text.setText("");
							text_2.setText("");
							calendarCombo.setText("");
							text_3.setText("");
							text_4.setText("");
							label_1.setText("");
							label_5.setText("");
							showData();
						}
					}else return ;
				}
			}
		});
		
		
		/**
		 * 当点击修改按钮时
		 */
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String shijian=calendarCombo.getDateAsString();
				String bianhao=text_2.getText().trim();
				
				if(lostbookDao.updatebookdetaileds(shijian, bianhao)>0){
					MessageDialog.openWarning(getShell(), "修改提示","修改成功");
					showData();
					text.setText("");
					text_2.setText("");
					calendarCombo.setText("");
					text_3.setText("");
					text_4.setText("");
					label_1.setText("");
					label_5.setText("");
					showData();
				}else{
					MessageDialog.openWarning(getShell(), "修改提示","修改失败");
					text.setText("");
					text_2.setText("");
					calendarCombo.setText("");
					text_3.setText("");
					text_4.setText("");
					label_1.setText("");
					label_5.setText("");
					showData();
					return;
				}
			}
		});
		
		
		/**
		 * 当点击找回按钮时
		 */
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bianhao=text_2.getText().trim();
				
				if(MessageDialog.openQuestion(getShell(), "确认提示","是否确认修改数据信息?")){
					if(lostbookDao.updatebookdetailedss(bianhao)>0){	
						MessageDialog.openWarning(getShell(), "找回提示","书本修改成功");
						text.setText("");
						text_2.setText("");
						calendarCombo.setText("");
						text_3.setText("");
						text_4.setText("");
						label_1.setText("");
						label_5.setText("");
						showData();
					}else{
						MessageDialog.openWarning(getShell(), "找回提示","书本修改失败");
						text.setText("");
						text_2.setText("");
						calendarCombo.setText("");
						text_3.setText("");
						text_4.setText("");
						label_1.setText("");
						label_5.setText("");
						showData();
					}
				}else {
					return ;
				}
			}
		});
		
		/**
		 * 当点击取消按钮时
		 */
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				text_2.setText("");
				calendarCombo.setText("");
				text_3.setText("");
				text_4.setText("");
				label_1.setText("");
				label_5.setText("");
				showData();
			}
		});
		
		

	}

	
	/**
	 * 查找显示数据
	 */
	public void showData(){
		table.removeAll();//移除表格中的数据
		List<Map<String,Object>> types=lostbookDao.find();

		if(types!=null && types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem (table,SWT.NONE);
				tm.setText(new String[] {String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("BOOKSDATE")).substring(0, 10),String.valueOf(map.get("STUID")),String.valueOf(map.get("SNAME"))});
			}
		}
	}
	
	
	/**
	 * 查找显示数据
	 */
	public void showData(String tiaojian,String jieguo){
		table.removeAll();//移除表格中的数据
		List<Map<String,Object>> types=lostbookDao.find(tiaojian,jieguo);

		if(types!=null && types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem (table,SWT.NONE);
				tm.setText(new String[] {String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("BOOKSDATE")).substring(0, 10),String.valueOf(map.get("STUID")),String.valueOf(map.get("SNAME"))});
			}
		}
	}
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
