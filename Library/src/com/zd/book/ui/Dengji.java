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

import com.zd.book.Dao.DengjiDao;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Dengji extends Composite {
	private Table table;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private DengjiDao dengjidao=new DengjiDao();

	/**
	 * 借阅等级设置
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Dengji(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Dengji.class, "/images/框框.jpg"));
		setLayout(null);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);

		table.setBounds(326, 162, 424, 218);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(140);
		tableColumn.setText("读者等级");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(140);
		tableColumn_1.setText("最大借书数量");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(140);
		tableColumn_2.setText("可借天数");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("修改");

		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.setText("删除");

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label.setBounds(370, 431, 91, 17);
		label.setText("读 者 等 级 ：");

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(487, 430, 107, 23);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setBounds(370, 480, 91, 17);
		label_1.setText("最大借书数量：");

		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(487, 479, 107, 23);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setBounds(370, 524, 91, 17);
		label_2.setText("可 借 天 数 ：");

		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(487, 523, 107, 23);

		Button button = new Button(this, SWT.NONE);

		button.setBounds(635, 428, 80, 27);
		button.setText("修改");

		Button button_1 = new Button(this, SWT.NONE);

		button_1.setBounds(635, 477, 80, 27);
		button_1.setText("添加");

		Button button_3 = new Button(this, SWT.NONE);

		button_3.setBounds(635, 521, 80, 27);
		button_3.setText("删除");

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label_3.setBounds(470, 95, 138, 23);
		label_3.setText("借阅登记设置");
		showData();
		//右键点击修改
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis!=null&&tis.length>0){
					String rankname =text_1.getText().trim();
					int numbers=Integer.valueOf(text_2.getText().trim());
					int datenum=Integer.valueOf(text_3.getText().trim());
					if(dengjidao.update(numbers, datenum, rankname)>0){
						showData();
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						MessageDialog.openInformation(getShell(), "成功提示", "修改成功");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "修改失败....");
					}
				}
			}
		});

		//右键删除数据
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis==null||tis.length<=0){
					MessageDialog.openWarning(getShell(), "温馨提示", "请选择您要删除的数据...");
				}else{
					int i=0;
					StringBuffer rankname=new StringBuffer();
					for(i=0;i<tis.length-1;i++){
						rankname.append(tis[i].getText(0)+",");
					}
					rankname.append(tis[i].getText(0));
					if(MessageDialog.openConfirm(getShell(), "删除确认","您确认要删除选中的数据吗？")){
						//调用dao层执行删除
						if(dengjidao.del(rankname.toString())>0){
							showData();
							text_1.setText("");
							text_2.setText("");
							text_3.setText("");
						}else{
							MessageDialog.openError(getShell(), "失败提示", "数据删除失败....");
						}

					}else{
						return;
					}
				}


			}
		});

		//点击删除时
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rankname=text_1.getText().trim();
				if(dengjidao.del(rankname.toString())>0){
					showData();
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					MessageDialog.openInformation(getShell(), "成功提示", "删除成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "数据删除失败....");
				}
			}
		});


		//点击添加时
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rankname=text_1.getText().trim();
				int numbers=Integer.valueOf(text_2.getText().trim());
				int datenum=Integer.valueOf(text_3.getText().trim());
				List<Map<String,Object>> list=dengjidao.find();
				int flag=1;
				if(rankname==null||"".equals(rankname)&&numbers==0||"".equals(numbers)&&datenum==0||"".equals(datenum)){
					MessageDialog.openError(getShell(),"错误提示", "信息不完全,添加失败...");
				}else if(rankname!=null){
					for(Map<String,Object> map:list){
						if(rankname.equals(map.get("RANKNAME"))){
							MessageDialog.openError(getShell(),"错误提示", "等级名已有,添加失败...");
							flag=0;
						}
					}

				}else if(flag==1&&rankname!=null){
					if(dengjidao.add(rankname,numbers,datenum)>0){
						//则在 表中显示
						showData();
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						MessageDialog.openInformation(getShell(), "成功提示", "添加成功");
					}else{
						MessageDialog.openError(getShell(),"失败提示", "添加失败....");
					}
				}

			}
		});

		//点击修改时
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rankname =text_1.getText().trim();
				int numbers=Integer.valueOf(text_2.getText().trim());
				int datenum=Integer.valueOf(text_3.getText().trim());
				if(dengjidao.update(numbers, datenum, rankname)>0){
					showData();
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					MessageDialog.openInformation(getShell(), "成功提示", "修改成功");
				}else{
					MessageDialog.openError(getShell(), "失败提示", "修改失败....");
				}
			}
		});

		//点击表格时
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					text_1.setText(t.getText(0));
					text_2.setText(t.getText(1));
					text_3.setText(t.getText(2));

				}
			}
		});

	}



	//显示表的数据
	public void showData(){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=dengjidao.find();
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("RANKNAME")),String.valueOf(map.get("NUMBERS")),String.valueOf(map.get("DATENUM"))});	
				}
			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
