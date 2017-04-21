package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.layout.FillLayout;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.BookClassDao;
import com.zd.book.Dao.BookInfoDao;


import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class BookInfo extends Composite {
	private Text text;
	private Table table;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private BookInfoDao bookInfoDao=new BookInfoDao();
	private FileInputStream is=null;
	private File fl;
	private Map<String,Image> imageData=new HashMap<String,Image>();
	private boolean isHavePic=true;
	private Text text_7;
	private BookClassDao bookClassDao=new BookClassDao();
	private Combo combo_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookInfo(Composite parent, int style) {
		super(parent, SWT.NONE);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(BookInfo.class, "/images/框框.jpg"));
		setLayout(null);

		Label label = new Label(this, SWT.NONE);
		label.setText("书籍信息管理");
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 142, 22);

		final Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(688, 134, 88, 25);
		combo.add("书籍编号");
		combo.add("书籍名称");
		combo.add("作者");
		combo.add("出版社");
		combo.add("查询所有");

		text = new Text(this, SWT.BORDER);
		text.setBounds(797, 134, 101, 25);

		Button button = new Button(this, SWT.NONE);

		button.setText("查询");
		button.setBounds(917, 133, 61, 25);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setBounds(108, 172, 870, 215);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(88);
		tblclmnNewColumn.setText("图片");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("书籍编号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("书籍名称");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("作者");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("出版社");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(74);
		tableColumn_8.setText("类别");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(71);
		tableColumn_4.setText("价格");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(74);
		tableColumn_5.setText("未借出数");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(84);
		tableColumn_6.setText("借出数");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(75);
		tableColumn_7.setText("库存数");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.CHECK);

		menuItem.setText("删除");

		final Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(407, 411, 80, 17);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setText("书籍名称：");
		label_2.setBounds(567, 411, 61, 17);

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(634, 408, 88, 23);

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setText("作    者：");
		label_3.setBounds(776, 411, 54, 17);

		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(836, 408, 86, 23);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setText("出 版 社：");
		label_4.setBounds(335, 475, 61, 17);

		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(406, 472, 101, 23);

		Button button_1 = new Button(this, SWT.NONE);

		button_1.setText("上传图片");
		button_1.setBounds(177, 560, 80, 27);

		Label label_5 = new Label(this, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_5.setText("未借出数：");
		label_5.setBounds(567, 475, 61, 17);

		Label label_6 = new Label(this, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_6.setText("借出数：");
		label_6.setBounds(776, 475, 54, 17);

		Label label_7 = new Label(this, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_7.setText("库 存 数：");
		label_7.setBounds(335, 530, 61, 17);

		Button button_2 = new Button(this, SWT.NONE);

		button_2.setText("添加");
		button_2.setBounds(627, 595, 80, 27);
		button_2.setVisible(false);

		Button button_3 = new Button(this, SWT.NONE);

		button_3.setText("修  改");
		button_3.setBounds(733, 595, 80, 27);

		Button button_4 = new Button(this, SWT.NONE);

		button_4.setText("删  除");
		button_4.setBounds(842, 595, 80, 27);

		final Label label_8 = new Label(this, SWT.NONE);
		label_8.setBackgroundImage(SWTResourceManager.getImage(BookInfo.class, "/images/0049588c6e9b8b0df8fa.jpg"));
		label_8.setBounds(165, 408, 100, 100);

		Label label_9 = new Label(this, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_9.setText("书籍编号：");
		label_9.setBounds(335, 411, 61, 17);

		Label label_10 = new Label(this, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_10.setBounds(563, 530, 54, 17);
		label_10.setText("价    格 ：");

		text_7 = new Text(this, SWT.BORDER);
		text_7.setBounds(634, 529, 73, 23);

		combo_1 = new Combo(this, SWT.NONE);
		combo_1.setBounds(836, 529, 88, 25);

		Label label_11 = new Label(this, SWT.NONE);
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_11.setBounds(776, 530, 52, 17);
		label_11.setText("类    别：");

		final Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(634, 475, 73, 17);

		final Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(836, 475, 61, 17);

		final Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(406, 532, 61, 17);

		Button button_5 = new Button(this, SWT.NONE);

		button_5.setBounds(177, 527, 80, 27);
		button_5.setText("取消图片");
		showData();
		changeType();

		//点击修改
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String bookId=label_1.getText().trim();
				String bookName=text_1.getText().trim();
				String bookAuthor=text_2.getText().trim();
				String bookPh=text_3.getText().trim();
				String bookPrice=text_7.getText().trim();
				String classid=combo_1.getText().trim();
				classid=classid.substring(0,classid.lastIndexOf("_"));
				byte [] bt=null;
				boolean isChangePic=true;
				if(fl!=null){
					FileInputStream fis=null;
					try {
						fis=new FileInputStream(fl);
						bt=new byte[fis.available()];
						fis.read(bt);
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						if(fis!=null){//说明要修改图片
							try {
								fis.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}else{//说明
					if(isHavePic){//不修改图片
						isChangePic=false;
					}else{//取消图片
						isChangePic=true;
					}
				}
				if(bookInfoDao.update(bookId,bookName, bookAuthor, bookPh, bookPrice, classid,bt,isChangePic)>0){
					showData();
				}else{
					MessageDialog.openError(getShell(), "错误提示", "跟新失败.....");
				}
			}
		});
		//点击查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text.getText().trim();
				if(tiaojian.equals("书籍编号")){
					tiaojian="b.bookid";
					if(bookInfoDao.finds(tiaojian,jieguo)!=null&&bookInfoDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
						
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}

				}else if(tiaojian.equals("书籍名称")){
					tiaojian="b.bookname";
					if(bookInfoDao.finds(tiaojian, jieguo)!=null&&bookInfoDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);

					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("作者")){
					tiaojian="b.bookauthor";
					if(bookInfoDao.finds(tiaojian, jieguo)!=null&&bookInfoDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("出版社")){
					tiaojian="b.bookph";
					if(bookInfoDao.finds(tiaojian, jieguo)!=null&&bookInfoDao.finds(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}else if(tiaojian.equals("查询所有")){
					if(bookInfoDao.find()!=null){
						showData();
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}
				}



			}
		});

		//右键点击删除
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis==null||tis.length<=0){
					MessageDialog.openWarning(getShell(), "温馨提示", "请选择您要删除的数据...");
				}else{
					int i=0;
					StringBuffer ids=new StringBuffer();
					for(i=0;i<tis.length-1;i++){
						ids.append(tis[i].getText(1)+",");
					}
					ids.append(tis[i].getText(1));
					if(MessageDialog.openConfirm(getShell(), "删除确认","您确认要删除选中的数据吗？")){
						//调用dao层执行删除
						if(bookInfoDao.del(ids.toString())>0){
							showData();
						}else{
							MessageDialog.openError(getShell(), "失败提示", "数据删除失败....");
						}

					}else{
						return;
					}
				}


			}
		});
		//点击删除
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis==null||tis.length<=0){
					MessageDialog.openWarning(getShell(), "温馨提示", "请选择您要删除的数据...");
				}else{
					int i=0;
					StringBuffer ids=new StringBuffer();
					for(i=0;i<tis.length-1;i++){
						ids.append(tis[i].getText(1)+",");
					}
					ids.append(tis[i].getText(1));
					if(MessageDialog.openConfirm(getShell(), "删除确认","您确认要删除选中的数据吗？")){
						//调用dao层执行删除
						if(bookInfoDao.del(ids.toString())>0){
							showData();
						}else{
							MessageDialog.openError(getShell(), "失败提示", "数据删除失败....");
						}

					}else{
						return;
					}
				}
			}
		});

		//点击添加
		/*button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String bookName=text_1.getText().trim();
				String bookAuthor=text_2.getText().trim();
				String bookPh=text_3.getText().trim();
				String bookPrice=text_7.getText().trim();
				String classid=combo_1.getText().trim();
				classid=classid.substring(0,classid.lastIndexOf("_"));
				byte[] bt =null;
				FileInputStream fis=null;
				if(fl!=null){
					try {
						fis=new FileInputStream(fl);
						bt=new byte[fis.available()];
						fis.read(bt);
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally{
						if(fis!=null){
							try {
								fis.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
				if(bookInfoDao.add(bookName,bookAuthor,bookPh,bookPrice,classid,bt)>0){
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_7.setText("");
					combo_1.setText("");
					label_8.setImage(SWTResourceManager.getImage(BookInfo.class, "/images/0049588c6e9b8b0df8fa.jpg"));
					showData();
				}else{
					MessageDialog.openError(getShell(), "失败提示", "商品信息添加失败....");
				}
				fl=null;



			}
		});*/


		//点击取消图片
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				label_8.setImage(SWTResourceManager.getImage(BookInfo.class, "/images/0049588c6e9b8b0df8fa.jpg"));
				isHavePic=false;
				fl=null;
			}
		});
		//数据显示
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					label_8.setImage(imageData.get(tis[0].getText(1)));
					label_1.setText(t.getText(1));
					text_1.setText(t.getText(2));
					text_2.setText(t.getText(3));
					text_3.setText(t.getText(4));
					String typeName=t.getText(5);
					String [] items=combo_1.getItems();
					for(int i=0 ; i<items.length;i++){
						if(items[i].contains("_"+typeName)){
							combo_1.select(i);
							break;
						}
					}
					text_7.setText(t.getText(6));
					lblNewLabel.setText(t.getText(7));
					lblNewLabel_1.setText(t.getText(8));
					lblNewLabel_2.setText(t.getText(9));
				}
			}
		});

		//上传图片
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog file=new FileDialog(getShell());
				file.setFilterExtensions(new String[]{"*.jpg","*.gif","*.png"});
				String path=file.open();
				if(path!=null&&!"".equals(path)){
					fl=new File(path);
					if(fl.exists()){
						InputStream is;
						try {
							is = new FileInputStream(fl);
							label_8.setImage(scaledImage(label_8.getSize().x,label_8.getSize().y,is));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});

	}

	@Override
	protected void checkSubclass() {

	}
	/**
	 * 修改图片大小
	 * @param width
	 * @param height
	 * @param is
	 * @return
	 */
	protected Image scaledImage(int width,int height,InputStream is){
		ImageData id=new ImageData(is);
		id=id.scaledTo(width, height);
		return new Image(null,id);
	}
	//显示数据
	public void showData(){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=bookInfoDao.find();
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					if(map.get("JPG")!=null&&!"".equals(map.get("JPG"))){
						ti.setImage(this.getImage((byte[])map.get("JPG"),40,40));
						imageData.put(String.valueOf(map.get("BOOKID")), this.getImage((byte[])map.get("JPG"),100,100));
					}
					ti.setText(new String[]{"",String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("BOOKPRICE")),String.valueOf(map.get("NO")),String.valueOf(map.get("YES")),String.valueOf(map.get("BNUM"))});	
				}
			}
		}
	}
	//显示数据
	public void showData(String tiaojian,String jieguo){
		
			table.removeAll();
			List<Map<String,Object>> list=bookInfoDao.finds(tiaojian,jieguo);
			
			if(list!=null&&list.size()>0){
				TableItem ti;
				
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					if(map.get("JPG")!=null&&!"".equals(map.get("JPG"))){
						ti.setImage(this.getImage((byte[])map.get("JPG"),40,40));
						imageData.put(String.valueOf(map.get("BOOKID")), this.getImage((byte[])map.get("JPG"),100,100));
					}
					ti.setText(new String[]{"",String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("BOOKPRICE")),String.valueOf(map.get("NO")),String.valueOf(map.get("YES")),String.valueOf(map.get("BNUM"))});	
				}
			}
		
	}
	
	//获取图片
	private Image getImage(byte[] bt, int width, int height) {
		ImageData data=new ImageData(new ByteArrayInputStream(bt));
		data=data.scaledTo(width, height);
		return new Image(null,data);
	}
	
	//获取图片
	private Image getImage(Image image, int width, int height) {
		if(image==null){
			return SWTResourceManager.getImage(BookInfo.class, "/images/0049588c6e9b8b0df8fa.jpg");
		}
		ImageData data=image.getImageData();
		data=data.scaledTo(width, height);
		return new Image(null,data);
	}
	//类别的显示
	public void changeType(){
		combo_1.removeAll();
		List<Map<String,Object>> map=bookClassDao.find();
		if(map!=null&&map.size()>0){
			for(Map<String,Object> m:map){
				combo_1.add(String.valueOf(m.get("CLASSID"))+"_"+String.valueOf(m.get("CLASSNAME")));
			}
		}
	}
}
