package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.BarcodeDao;
import com.zd.book.util.CreateEXCEL;
import com.zd.book.util.Data;
import com.zd.book.util.OneBarcodeUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class Barcode extends Composite {
	private Table table;
	private Text text;
	private Text text_1;
	private boolean flag=false;
	private OneBarcodeUtil oneBarcodeUtil=new OneBarcodeUtil();
	private BarcodeDao barcodeDao=new BarcodeDao();
	private CreateEXCEL createEXCEL=new CreateEXCEL();
	private JFileChooser fc = new JFileChooser();
	private int flags ;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Barcode(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Barcode.class, "/images/框框.jpg"));

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 168, 21);
		label.setText("条形码与参照表");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(307, 170, 507, 193);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(73);
		tableColumn_3.setText("条形码编号");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(102);
		tableColumn.setText("书籍名称");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(104);
		tableColumn_1.setText("作者");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(111);
		tableColumn_2.setText("出版社");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(92);
		tableColumn_4.setText("类别");

		Button btnNewButton = new Button(this, SWT.NONE);

		btnNewButton.setBounds(195, 336, 80, 27);
		btnNewButton.setText("导出EXCEL");

		text = new Text(this, SWT.BORDER);
		text.setBounds(455, 504, 93, 23);

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(570, 504, 73, 23);
		text_1.setVisible(false);

		Button btnNewButton_1 = new Button(this, SWT.NONE);

		btnNewButton_1.setBounds(671, 502, 80, 27);
		btnNewButton_1.setText("生成条形码");

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_1.setBounds(208, 170, 68, 27);
		label_1.setText("参 照 表：");

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setBounds(342, 409, 87, 27);
		label_2.setText("条形码的生成：");

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setBounds(455, 409, 93, 27);
		label_3.setText("是否生成多条？");

		final Button button = new Button(this, SWT.RADIO);
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));

		button.setBounds(455, 458, 33, 17);
		button.setText("是");

		final Button button_1 = new Button(this, SWT.RADIO);
		button_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));

		button_1.setBounds(515, 458, 33, 17);
		button_1.setText("否");

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_4.setBounds(609, 458, 130, 17);
		label_4.setText("小提示：请输入纯数字");
		showData();
		button_1.setSelection(true);
		//点击是
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				button_1.setSelection(false);
				button.setSelection(true);
				text_1.setVisible(true);
				flag=true;
			}
		});
		//点击否
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				button.setSelection(false);
				button_1.setSelection(true);
				text_1.setVisible(false);
				flag=false;
			}
		});
		//点击生成条形码
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {



				String one=text.getText().trim();
				
				int x=Integer.parseInt(one);
				
				int count =0;
				if(flag==false){
					oneBarcodeUtil.OneBarcode(one, one);
					count=1;
				}else if(flag==true){
					String two=text_1.getText().trim();
					int y=Integer.parseInt(two);
					count=0;
					for(int i=x;i<=y;i++){
						oneBarcodeUtil.OneBarcode(String.valueOf(i),String.valueOf(i) );
						count++;
					}
				}

				MessageDialog.openInformation(getShell(), "温馨提示", ""+count+"个条形码生成，前往D/image盘查看....");


			}
		});
		//点击导出EXCLE
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String,Object>> list=barcodeDao.find();
				createEXCEL.CreateEXCELbarcode(list);
				MessageDialog.openInformation(getShell(), "温馨提示", "参考表已导出，请前往D盘查看...");
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
		List<Map<String,Object>> types=barcodeDao.find();
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("BID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME"))});
			}
		}

	}
}
