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

import com.zd.book.Dao.BookClassDao;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BookClass extends Composite {
	private Table table;
	private Text text_1;
	private BookClassDao bookClassDao=new BookClassDao();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookClass(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(BookClass.class, "/images/框框.jpg"));
		setLayout(null);

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 138, 23);
		label.setText("书籍类别管理");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);

		table.setBounds(394, 176, 304, 187);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("类别编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("类别名称");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("状态");

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setBounds(390, 402, 75, 17);
		label_1.setText("类别编号：");

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setBounds(594, 402, 61, 17);
		label_2.setText("类别名称：");

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(661, 401, 73, 23);

		final Button button_1 = new Button(this, SWT.RADIO);
		button_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		button_1.setBounds(389, 451, 45, 17);
		button_1.setText("可用");

		final Button button_2 = new Button(this, SWT.RADIO);
		button_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		button_2.setBounds(461, 451, 57, 17);
		button_2.setText("不可用");

		Button button_3 = new Button(this, SWT.NONE);

		button_3.setBounds(547, 520, 80, 27);
		button_3.setText("新  增");

		Button button_4 = new Button(this, SWT.NONE);

		button_4.setBounds(654, 520, 80, 27);
		button_4.setText("修  改");

		final Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(471, 402, 61, 17);

		showData();
		//数的操作  把表中的数据显示在下方
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					lblNewLabel.setText(t.getText(0));
					text_1.setText(t.getText(1));

					if("可用".equals(t.getText(2))){
						button_1.setSelection(true);
						button_2.setSelection(false);
					}else{
						button_2.setSelection(true);
						button_1.setSelection(false);
					}
				}
			}
		});
		

		//点击修改
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String classid=lblNewLabel.getText().trim();
				String classname=text_1.getText().trim();
				String cstate="1";
				if(button_2.getSelection()){
					cstate="0";
				}else{
					cstate="1";
				}
				if(classname.equals("")||classname==null){
					MessageDialog.openError(getShell(),"错误提示", "类型名称不能为空....");
				}else{
					if(bookClassDao.update(classid,classname,cstate)>0){
						//则在 表中显示
						showData();
						lblNewLabel.setText("");
						text_1.setText("");
					}else{
						MessageDialog.openError(getShell(), "错误提示", "新增失败....");
					}
				}


			}
		});

		//点击新增
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String classname=text_1.getText().trim();
				String cstate="1";

				if(button_2.getSelection()){
					cstate="0";
				}else{
					cstate="1";
				}
				if(classname.equals("")||classname==null){
					MessageDialog.openError(getShell(),"错误提示", "类型名称不能为空....");
				}else{
					if(bookClassDao.add(classname,cstate)>0){
						//则在 表中显示
						showData();
						lblNewLabel.setText("");
						text_1.setText("");
					}else{
						MessageDialog.openError(getShell(), "错误提示", "新增失败....");
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
		table.removeAll();
		List<Map<String,Object>> types=bookClassDao.find();
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("CLASSID")),String.valueOf(map.get("CLASSNAME")),"0".equals(String.valueOf(map.get("CSTATE")))?"不可用":"可用"});
			}
		}

	}


}
