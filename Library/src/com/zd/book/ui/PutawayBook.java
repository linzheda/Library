package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.PutawayBookDao;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

/**
 * 新书上架
 * @author PPT-T
 *
 */
public class PutawayBook extends Composite {
	private Table table;
	private Text text_1;
	private Text text;
	String tiaojian="";
	String jieguo="";
	private PutawayBookDao putawaybookDao=new PutawayBookDao();
	private Map<String,Image> imageData=new HashMap<String,Image>();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PutawayBook(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(PutawayBook.class, "/images/框框.jpg"));
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		composite.setLayout(null);

		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(481, 88, 92, 23);
		label.setText("新书上架");

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);

		table.setBounds(202, 162, 656, 195);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(64);
		tableColumn_1.setText("图片");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(51);
		tableColumn.setText("序列号");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(93);
		tableColumn_2.setText("书籍名称");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(86);
		tableColumn_6.setText("书籍类别");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(78);
		tableColumn_3.setText("作者");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(129);
		tableColumn_4.setText("出版社");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(73);
		tblclmnNewColumn.setText("已上架量");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(77);
		tableColumn_5.setText("剩余库存量");

		Button button_2 = new Button(composite, SWT.NONE);

		button_2.setBounds(797, 128, 61, 25);
		button_2.setText("查询");

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("已上架量：");
		label_1.setBounds(683, 475, 61, 17);

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("作      者：");
		label_2.setBounds(458, 431, 61, 17);

		final Label label_5 = new Label(composite, SWT.NONE);
		label_5.setBounds(544, 431, 80, 17);

		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("出 版 社 ：");
		label_6.setBounds(683, 431, 61, 17);

		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("书籍名称：");
		label_7.setBounds(458, 388, 61, 17);

		final Label label_8 = new Label(composite, SWT.NONE);
		label_8.setBounds(544, 388, 80, 17);

		final Label label_9 = new Label(composite, SWT.NONE);
		label_9.setBounds(757, 431, 80, 17);

		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setText("库 存 量 ：");
		label_10.setBounds(458, 475, 61, 17);

		Label label_11 = new Label(composite, SWT.NONE);
		label_11.setText("书籍类别：");
		label_11.setBounds(683, 388, 61, 17);

		final Label label_12 = new Label(composite, SWT.NONE);
		label_12.setBounds(544, 475, 80, 17);

		final Label label_13 = new Label(composite, SWT.NONE);
		label_13.setBounds(757, 388, 80, 17);

		Button button = new Button(composite, SWT.NONE);

		button.setText("确认上架");
		button.setBounds(664, 551, 80, 27);

		Button button_3 = new Button(composite, SWT.NONE);

		button_3.setBounds(778, 551, 80, 27);
		button_3.setText("清  空");

		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(589, 129, 88, 25);
		combo.add("书名");
		combo.add("作者");
		combo.add("书籍类别");
		combo.add("查询所有");

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(695, 129, 80, 25);

		final Label label_14 = new Label(composite, SWT.NONE);
		label_14.setText("");
		label_14.setImage(SWTResourceManager.getImage(PutawayBook.class, "/images/0049588c6e9b8b0df8fa.jpg"));
		label_14.setBounds(253, 388, 130, 150);

		final Label label_15 = new Label(composite, SWT.NONE);
		label_15.setBounds(765, 475, 80, 17);

		Label label_16 = new Label(composite, SWT.NONE);
		label_16.setBounds(458, 520, 61, 17);
		label_16.setText("上 架 量 ：");

		text = new Text(composite, SWT.BORDER);
		text.setBounds(544, 514, 80, 23);


		showData();
		
		/**
		 * 多条件查询时
		 */
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tiaojian=combo.getText().trim();		
				jieguo=text_1.getText().trim();		//获取因条件输入的数据


				if("查询所有".equals(tiaojian)){
					showData();
				}else if( !"查询所有".equals(tiaojian) && ("".equals(tiaojian) || "".equals(jieguo))){
					MessageDialog.openError(getShell(), "错误提示","您填入的信息为空,查询失败,请重新填写查询");
				}else {
					if("书名".equals(tiaojian)){
						tiaojian="b.bookname";

						showData(tiaojian,jieguo);
					}else if("作者".equals(tiaojian)){
						tiaojian="b.bookauthor";

						showData(tiaojian,jieguo);
					}else if("书籍类别".equals(tiaojian)){
						tiaojian="bc.classname";

						showData(tiaojian,jieguo);
					}
				}	

			}
		});



		/**
		 * 对表格进行监听
		 */
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();

				if(tis!=null&&tis.length>0){
					TableItem t=tis[0];
					
					if(imageData.containsKey(tis[0].getText(1))){
						label_14.setImage(imageData.get(tis[0].getText(1)));	//图片
					}else{
						label_14.setImage(SWTResourceManager.getImage(PutawayBook.class, "/images/0049588c6e9b8b0df8fa.jpg"));	 	//图片
					}
					
					label_8.setText(t.getText(2));		//书名
					label_13.setText(t.getText(3));		//书籍类别
					label_5.setText(t.getText(4));		//作者
					label_9.setText(t.getText(5));		//出版社
					label_12.setText(t.getText(7));		//库存量
					label_15.setText(t.getText(6));		//已上架数量
				}
			}
		});


		/**
		 * 点击确认上架按钮时
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String shuliang1=text.getText().trim();		//获取上架数量
				String shuming=label_8.getText().trim();		//获取书名
				String shuliang2=shuliang1;
				List<Map<String,Object>> types=putawaybookDao.find(shuming);

				String bianhao="";
				for(Map<String,Object> maps:types){
					bianhao=String.valueOf(maps.get("BOOKID"));
				}


				if(label_8.getText()==null || "".equals(label_8.getText()) || "".equals(text.getText())){
					MessageDialog.openWarning(getShell(),"验收提示","信息框无数据,验收失败");
				}else if(Integer.valueOf(text.getText().trim())>Integer.valueOf(label_12.getText())){	//当输入的上架数量大于库存量时报错
					MessageDialog.openError(getShell(), "错误提示","您输入的上架量大于库存量，上架失败");
					text.setText("");
				}else{
					if(MessageDialog.openQuestion(getShell(), "上架确认","您是否确认上架?")){
						if(putawaybookDao.updatebook(shuliang1, shuliang2, shuming)>0){
							for(int i=0;i<Integer.valueOf(shuliang1);i++){	//循环插入几本书
								if(putawaybookDao.addbookdetailed(bianhao)>0){
									System.out.println(1);
								}
							}

							MessageDialog.openWarning(getShell(), "上架提示", "上架成功");

							label_14.setImage(SWTResourceManager.getImage(PutawayBook.class, "/images/默认.jpg"));	 	//图片
							label_8.setText("");		//书名
							label_13.setText("");		//书籍类别
							label_5.setText("");		//作者
							label_9.setText("");		//出版社
							label_12.setText("");		//库存量
							label_15.setText("");		//已上架数量
							text.setText("");

							showData();
						}else {
							MessageDialog.openWarning(getShell(), "上架提示","上架失败");
						}

					}else {
						return ;
					}
				}

			}
		});


		/**
		 * 点击取消时
		 */
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				label_14.setImage(SWTResourceManager.getImage(PutawayBook.class, "/images/默认.jpg"));
				label_8.setText("");		//书名
				label_13.setText("");		//书籍类别
				label_5.setText("");		//作者
				label_9.setText("");		//出版社
				label_12.setText("");		//库存量
				label_15.setText("");		//已上架数量
				text.setText("");
			}
		});

	}




	/**
	 * 查找显示数据
	 */
	public void showData(){
		table.removeAll();//移除表格中的数据
		List<Map<String,Object>> types=putawaybookDao.find();

		if(types!=null && types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem (table,SWT.NONE);
				if(map.get("JPG")!=null&&!"".equals(map.get("JPG"))){
					tm.setImage(this.getImage((byte[])map.get("JPG"),40,40));
					imageData.put(String.valueOf(map.get("BOOKID")), this.getImage((byte[])map.get("JPG"),100,100));
				}
				tm.setText(new String[] {"",String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME"))
						,String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("SNUM"))
						,String.valueOf(map.get("BNUM"))});
			}
		}
	}




	/**
	 * 查找显示数据
	 */
	public void showData(String tiaojian,String jieguo){
		table.removeAll();//移除表格中的数据
		List<Map<String,Object>> types=putawaybookDao.find(tiaojian,jieguo);

		if(types!=null && types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem (table,SWT.NONE);
				if(map.get("JPG")!=null&&!"".equals(map.get("JPG"))){
					tm.setImage(this.getImage((byte[])map.get("JPG"),40,40));
					imageData.put(String.valueOf(map.get("BOOKID")), this.getImage((byte[])map.get("JPG"),100,100));
				}
				tm.setText(new String[] {"",String.valueOf(map.get("BOOKID")),String.valueOf(map.get("BOOKNAME"))
						,String.valueOf(map.get("CLASSNAME")),String.valueOf(map.get("BOOKAUTHOR")),String.valueOf(map.get("BOOKPH")),String.valueOf(map.get("SNUM"))
						,String.valueOf(map.get("BNUM"))});
			}
		}
	}


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
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

	//获取图片
	private Image getImage(byte[] bt, int width, int height) {
		ImageData data=new ImageData(new ByteArrayInputStream(bt));
		data=data.scaledTo(width, height);
		return new Image(null,data);
	}

	//获取图片
	private Image getImage(Image image, int width, int height) {
		if(image==null){
			return SWTResourceManager.getImage(PutawayBook.class, "/images/默认.jpg");
		}
		ImageData data=image.getImageData();
		data=data.scaledTo(width, height);
		return new Image(null,data);
	}
}
