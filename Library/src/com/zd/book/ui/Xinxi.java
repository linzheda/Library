package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.XinxiDao;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class Xinxi extends Composite {
	private Table table;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private XinxiDao xinxidao=new XinxiDao();
	String tiaojian = null;
	String jieguo = null;
	private Combo combo_2;
	/**
	 * 用户信息查询与修改
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Xinxi(Composite parent, int style) {
		super(parent, style);
		setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Xinxi.class, "/images/框框.jpg"));
		setLayout(null);

		final Combo combo = new Combo(this, SWT.READ_ONLY);
		combo.setBounds(628, 120, 130, 25);
		String[] s1={"姓名","性别","借书证号","等级","账号状态","查询所有"};
		combo.setItems(s1);

		Button button = new Button(this, SWT.NONE);

		button.setText("查询");
		button.setBounds(872, 120, 60, 25);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		table.setBounds(122, 162, 810, 239);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(65);
		tableColumn.setText("借书证号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(60);
		tableColumn_1.setText("姓名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(50);
		tableColumn_2.setText("性别");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(116);
		tableColumn_3.setText("身份证号");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(60);
		tableColumn_4.setText("证件类型");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(114);
		tableColumn_5.setText("邮箱");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("手机号");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("登记日期");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(71);
		tableColumn_8.setText("等级");

		TableColumn tableColumn_9 = new TableColumn(table, SWT.CENTER);
		tableColumn_9.setWidth(70);
		tableColumn_9.setText("状态");

		Label label = new Label(this, SWT.NONE);
		label.setText("借书证号：");
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label.setBounds(269, 429, 61, 17);

		final Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(336, 431, 61, 17);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("姓     名：");
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setBounds(418, 429, 61, 17);

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(485, 428, 73, 23);

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("性     别：");
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setBounds(418, 470, 62, 17);

		final Button button_1 = new Button(this, SWT.RADIO);
		button_1.setText("男");
		button_1.setBounds(486, 470, 33, 17);

		final Button button_2 = new Button(this, SWT.RADIO);
		button_2.setText("女");
		button_2.setBounds(525, 470, 33, 17);

		final Combo combo_1 = new Combo(this, SWT.READ_ONLY);
		combo_1.setBounds(485, 509, 73, 25);
		String[] s2={"学生证","教师证"};
		combo_1.setItems(s2);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("证件类型：");
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setBounds(418, 510, 61, 17);


		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("登记日期：");
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_5.setBounds(269, 510, 61, 17);

		final Label label_6 = new Label(this, SWT.NONE);
		label_6.setBounds(235, 554, 122, 17);

		Label label_7 = new Label(this, SWT.NONE);
		label_7.setText("等     级：");
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_7.setBounds(418, 552, 60, 17);

		combo_2 = new Combo(this, SWT.READ_ONLY);
		combo_2.setBounds(485, 551, 73, 25);


		Label label_8 = new Label(this, SWT.NONE);
		label_8.setText("身份证号：");
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_8.setBounds(586, 429, 61, 17);

		text_2 = new Text(this, SWT.BORDER);


		text_2.setBounds(653, 428, 135, 23);

		Label label_9 = new Label(this, SWT.NONE);
		label_9.setText("邮     箱：");
		label_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_9.setBounds(587, 469, 60, 17);

		text_3 = new Text(this, SWT.BORDER);
		
		text_3.setBounds(653, 468, 135, 23);

		Label label_10 = new Label(this, SWT.NONE);
		label_10.setText("手 机 号：");
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_10.setBounds(586, 510, 60, 17);

		text_4 = new Text(this, SWT.BORDER);


		text_4.setBounds(653, 509, 135, 23);

		Label label_11 = new Label(this, SWT.NONE);
		label_11.setText("状     态：");
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_11.setBounds(587, 552, 60, 17);

		final Combo combo_3 = new Combo(this,SWT.READ_ONLY);
		combo_3.setBounds(654, 551, 88, 25);
		String[] s4={"正常","冻结"};
		combo_3.setItems(s4);

		Button button_3 = new Button(this, SWT.NONE);

		button_3.setText("修  改");
		button_3.setBounds(746, 607, 80, 27);

		text_5 = new Text(this, SWT.BORDER);
		text_5.setBounds(783, 120, 73, 25);

		Label label_12 = new Label(this, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label_12.setBounds(426, 70, 219, 25);
		label_12.setText("用户信息查询与修改");

		final Label label_13 = new Label(this, SWT.NONE);
		label_13.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_13.setBounds(804, 431, 130, 17);

		final Label label_14 = new Label(this, SWT.NONE);
		label_14.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_14.setBounds(804, 474, 130, 17);

		final Label label_15 = new Label(this, SWT.NONE);
		label_15.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_15.setBounds(804, 512, 130, 17);

		Button button_4 = new Button(this, SWT.NONE);

		button_4.setBounds(854, 607, 80, 27);
		button_4.setText("重置密码");
		showData();
		changeType();
		//邮箱的监听
		text_3.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String mail=text_3.getText().trim();
				
				if(text_3.getText().trim().equals("")){
					label_14.setText("");
				}else if(emailFormat(mail)){
					label_14.setText("");
				}else {
					label_14.setText("*邮箱格式不正确");
				}
			}
		});
		//手机号的监听
		text_4.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {

				if(String.valueOf(text_4.getText().trim()).equals("")){
					label_15.setText("");
				}else if(String.valueOf(text_4.getText().trim()).length()==10){
					label_15.setText("");
				}else{
					label_15.setText("*请输入正确的电话号码");
				}

			}
		});

		//身份证号的监听
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(String.valueOf(text_2.getText().trim()).equals("")){
					label_13.setText("");
				}else if(String.valueOf(text_2.getText().trim()).length()==17){
					label_13.setText("");
				}else{

					label_13.setText("*请输入正确的身份证号");
				}
			}
		});
		//点击重置密码
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String userid=label_1.getText().trim();
				if(xinxidao.updatePwad(userid)>0){
					MessageDialog.openInformation(getShell(), "温馨提示", "重置密码成功，密码为a...");
				}else{
					MessageDialog.openError(getShell(), "温馨提示","重置密码失败...");
				}


			}
		});
		//点击修改时
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(label_13.getText().trim().equals("")==false||label_14.getText().trim().equals("")==false||label_15.getText().trim().equals("")==false){
					MessageDialog.openError(getShell(), "失败提示", "用户注册失败....");
				}else{
					int userid=Integer.valueOf(label_1.getText().trim());
					String sname =text_1.getText().trim();
					String sex=null;
					if(button_1.getSelection()){
						sex="男";
					}else if(button_2.getSelection()){
						sex="女";
					}
					String useridentity=text_2.getText().trim();
					String type=combo_1.getText().trim();
					String mail=text_3.getText().trim();
					BigInteger usertel=new BigInteger(text_4.getText().trim());
					String rankname=combo_2.getText().trim();
					String rankid=null;
					List<Map<String,Object>> list=xinxidao.find3(rankname);
					for(Map<String,Object> map:list){
						for(Map.Entry<String,Object> s:map.entrySet()){
							rankid=String.valueOf(s.getValue());

						}
					}	
					String userstate=combo_3.getText().trim();
					if(xinxidao.update(sname,sex,useridentity,type,mail,usertel,rankid,userstate,userid)>0){
						showData();

						MessageDialog.openInformation(getShell(), "成功提示", "修改成功");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "修改失败....");
					}
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
					label_1.setText(t.getText(0));
					text_1.setText(t.getText(1));

					if("男".equals(String.valueOf( t.getText(2).trim()))){
						button_1.setSelection(true);
						button_2.setSelection(false);
					}else if("女".equals(String.valueOf( t.getText(2).trim()))){
						button_2.setSelection(true);
						button_1.setSelection(false);
					}
					text_2.setText(t.getText(3));
					combo_1.setText(t.getText(4));
					text_3.setText(t.getText(5));
					text_4.setText(t.getText(6));
					label_6.setText(t.getText(7));
					String rankname=t.getText(8).trim();
					String [] items=combo_2.getItems();

					for(int i=0 ; i<items.length;i++){
						if(items[i].equals(rankname)){
							combo_1.select(i);
							break;
						}
					}
					combo_2.setText(rankname);
					combo_3.setText(t.getText(9));

					label_15.setText("");
					label_13.setText("");
				}
			}
		});


		//点击查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text_5.getText().trim();
				if("姓名".equals(tiaojian)){
					tiaojian="u.sname";
					if(xinxidao.find1(tiaojian, jieguo)!=null&&(xinxidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_5.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("性别".equals(tiaojian)){
					tiaojian="u.sex";
					if(xinxidao.find1(tiaojian, jieguo)!=null&&(xinxidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_5.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("借书证号".equals(tiaojian)){
					tiaojian="u.userid";
					if(xinxidao.find1(tiaojian, jieguo)!=null&&(xinxidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_5.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("等级".equals(tiaojian)){
					tiaojian="r.rankname";
					if(xinxidao.find1(tiaojian, jieguo)!=null&&(xinxidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_5.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("账号状态".equals(tiaojian)){
					tiaojian="u.userstate";
					if(xinxidao.find1(tiaojian, jieguo)!=null&&(xinxidao.find1(tiaojian, jieguo)).size()>0){
						showData(tiaojian,jieguo);
						text_5.setText("");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "查询失败....");
					}
				}else if("查询所有".equals(tiaojian)){
					showData();
				}
			}



		});

	}
	//显示表的数据1
	public void showData(){
		if(table!=null){
			table.removeAll();
			List<Map<String,Object>> list=xinxidao.find();
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("SEX")),String.valueOf(map.get("USERIDENTITY")),String.valueOf(map.get("TYPE")),String.valueOf(map.get("MAIL")),String.valueOf(map.get("USERTEL")),String.valueOf(map.get("UDATE")),String.valueOf(map.get("RANKNAME")),String.valueOf(map.get("USERSTATE"))});	
				}
			}
		}
	}

	//显示表的数据2
	public void showData(String tiaojian, String jieguo){
		if(table!=null){
			table.removeAll();

			List<Map<String,Object>> list=xinxidao.find1(tiaojian, jieguo);
			if(list!=null&&list.size()>0){
				TableItem ti;
				for(Map<String,Object> map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("SNAME")),String.valueOf(map.get("SEX")),String.valueOf(map.get("USERIDENTITY")),String.valueOf(map.get("TYPE")),String.valueOf(map.get("MAIL")),String.valueOf(map.get("USERTEL")),String.valueOf(map.get("UDATE")),String.valueOf(map.get("RANKNAME")),String.valueOf(map.get("USERSTATE"))});	
				}
			}
		}
	}
	//类型的显示
	public void changeType(){
		combo_2.removeAll();
		List<Map<String,Object>> map=xinxidao.find1();
		if(map!=null&&map.size()>0){
			for(Map<String,Object> m:map){
				combo_2.add(String.valueOf(m.get("RANKNAME")));
			}
		}
	}
	//验证邮箱是否正确
	public static boolean emailFormat(String email) 
	{ 
		boolean tag = true; 
		final String pattern1 = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$"; 
		final Pattern pattern = Pattern.compile(pattern1); 
		final Matcher mat = pattern.matcher(email); 
		if (!mat.matches()) { 
			tag = false; 
		} 
		return tag; 
	} 
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
