package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Text;

import java.io.File;

import java.math.BigInteger;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.XinxiDao;
import com.zd.book.Dao.ZhuceDao;

import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class Zhuce extends Composite {
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Combo combo_1;
	private File fl;

	private ZhuceDao zhucedao=new ZhuceDao();
	private XinxiDao xinxidao=new XinxiDao();
	private Text text_4;
	private Combo combo_2;

	/**
	 *  新用户注册
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Zhuce(Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(Zhuce.class, "/images/框框.jpg"));
		setBackgroundMode(SWT.INHERIT_DEFAULT);

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label.setBounds(297, 186, 39, 17);
		label.setText("姓名：");

		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(352, 185, 150, 23);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setBounds(588, 186, 39, 17);
		label_1.setText("性别：");

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setBounds(276, 238, 61, 17);
		label_2.setText("身份证号：");

		text_1 = new Text(this, SWT.BORDER);


		text_1.setBounds(352, 237, 150, 23);

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setBounds(276, 285, 61, 17);
		label_3.setText("电话号码：");

		text_2 = new Text(this, SWT.BORDER);


		text_2.setBounds(352, 284, 150, 23);


		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setBounds(566, 238, 61, 17);
		label_4.setText("身份类型：");

		combo_1 = new Combo(this,SWT.DROP_DOWN | SWT.READ_ONLY);
		combo_1.setBounds(645, 237, 88, 25);
		String[] st={"学生证","教师证"};
		combo_1.setItems(st);

		Label label_5 = new Label(this, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_5.setBounds(588, 285, 39, 17);
		label_5.setText("等级：");

		combo_2 = new Combo(this, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo_2.setBounds(645, 284, 88, 25);
		//SimpleDateFormat sd=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//lblNewLabel.setText(sd.format(new Date()));


		Label label_8 = new Label(this, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_8.setBounds(588, 334, 39, 17);
		label_8.setText("状态：");

		Button button = new Button(this, SWT.NONE);

		button.setBounds(479, 437, 80, 27);
		button.setText("添加");

		final Button button_2 = new Button(this, SWT.RADIO);
		button_2.setBounds(645, 188, 50, 17);
		button_2.setText("男");

		final Button button_3 = new Button(this, SWT.RADIO);
		button_3.setBounds(701, 188, 41, 17);
		button_3.setText("女");

		Label label_7 = new Label(this, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_7.setBounds(301, 334, 35, 17);
		label_7.setText("邮箱：");

		text_4 = new Text(this, SWT.BORDER);

		text_4.setBounds(352, 333, 150, 23);

		final Combo combo = new Combo(this,SWT.READ_ONLY);
		combo.setBounds(645, 333, 88, 25);
		String[] ss={"正常","冻结"};
		combo.setItems(ss);

		Label label_9 = new Label(this, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label_9.setBounds(457, 95, 115, 23);
		label_9.setText("新用户注册");

		final Label label_6 = new Label(this, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_6.setBounds(352, 214, 128, 17);

		final Label label_10 = new Label(this, SWT.NONE);
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_10.setBounds(352, 266, 128, 17);

		final Label label_12 = new Label(this, SWT.NONE);
		label_12.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_12.setBounds(352, 313, 150, 17);

		changeType();
		//邮箱的监听
		text_4.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String mail=text_4.getText().trim();
				
				if(text_4.getText().trim().equals("")){
					label_12.setText("");
				}else if(emailFormat(mail)){
					label_12.setText("");
				}else{
					label_12.setText("*邮箱格式不正确");
				}

			}
		});

		/**
		 * 电话号码的监听
		 */
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				//				 boolean b = ("0123456789".indexOf(arg0.text)>=0);     
				//				 arg0.doit = b;//设置只能输入数字
				if(String.valueOf(text_2.getText().trim()).length()==11){
					label_10.setText("");
				}else if(String.valueOf(text_2.getText().trim()).equals("")){
					label_10.setText("");
				}else{

					label_10.setText("*请输入正确的电话号码");
				}
			}
		});

		/**
		 * 身份证号监听
		 */
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(String.valueOf(text_1.getText().trim()).length()==18){
					label_6.setText("");
				}else if(String.valueOf(text_1.getText().trim()).equals("")){
					label_6.setText("");
				}else{

					label_6.setText("*请输入正确的身份证号");
				}

			}
		});


		/**
		 * 点击添加
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(label_6.getText().trim().equals("")==false||label_10.getText().trim().equals("")==false||label_12.getText().trim().equals("")==false){
					MessageDialog.openError(getShell(), "失败提示", "用户注册失败....");
				}else{
					String pwad="a";
					String sname=text_3.getText().trim();
					String useridentity=text_1.getText().trim();
					BigInteger usertel = new BigInteger(text_2.getText().trim());
					String type=combo_1.getText().trim();
					
					String sex=null;
					if(button_2.getSelection()){
						sex="男";
					}else if(button_3.getSelection()){
						sex="女";
					}
					String mail=text_4.getText().trim();
					String rankname1=combo_2.getText();
					Integer rankid=0;
					List<Map<String,Object>> list=zhucedao.find2(rankname1);
					for(Map<String,Object> map:list){
						for(Map.Entry<String,Object> s:map.entrySet()){
							rankid=Integer.valueOf(String.valueOf(s.getValue()));

						}
					}

					String userstate=combo.getText().trim();


					if(zhucedao.add(pwad,sname,sex,useridentity,type,mail,usertel,rankid,userstate)>0){
						text_3.setText("");
						text_1.setText("");
						text_2.setText("");
						text_4.setText("");
						MessageDialog.openInformation(getShell(), "成功提示", "用户注册成功,初始密码为：a");
					}else{
						MessageDialog.openError(getShell(), "失败提示", "用户注册失败....");
					}

				}

				
			}
		});

	}

	//类型的显示
	public void changeType(){
		combo_2.removeAll();
		List<Map<String,Object>> map=zhucedao.find1();
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
