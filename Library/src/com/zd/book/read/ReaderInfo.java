package com.zd.book.read;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.ReaderInfoDao;
import com.zd.book.login.Login;
import com.zd.book.login.ReadInfo;
import com.zd.book.util.Data;

import com.zd.book.util.MakeCertPic;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class ReaderInfo extends Composite {
	private ReaderInfoDao readerInfoDao=new ReaderInfoDao();
	private ReadInfo readInfo;
	private Login login=new Login();
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Button button;
	private Button button_1;
	private Label label_8;
	private Label label_10;
	private Label lblNewLabel_1;
	private String code;
	
	private MakeCertPic pic=new MakeCertPic();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ReaderInfo(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(ReaderInfo.class, "/images/框框.jpg"));
		setLayout(null);
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setText("个人信息");
		label.setBounds(299, 147, 83, 23);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setText("修改密码");
		label_1.setBounds(668, 147, 87, 27);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(299, 193, 145, 23);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setText("姓       名：");
		label_2.setBounds(221, 194, 69, 20);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setText("性       别：");
		label_3.setBounds(221, 243, 69, 20);
		
		button = new Button(this, SWT.RADIO);
		button.setText("男");
		button.setBounds(299, 245, 33, 17);
		
		button_1 = new Button(this, SWT.RADIO);
		button_1.setText("女");
		button_1.setBounds(365, 245, 41, 17);
		
		text_1 = new Text(this, SWT.BORDER);
		
		text_1.setBounds(299, 411, 145, 23);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setText("身份证号：");
		label_4.setBounds(221, 412, 60, 20);
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_5.setText("邮       箱：");
		label_5.setBounds(221, 472, 69, 20);
		
		text_2 = new Text(this, SWT.BORDER);
		
		text_2.setBounds(299, 471, 145, 23);
		
		Label label_6 = new Label(this, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_6.setText("手 机 号：");
		label_6.setBounds(221, 526, 69, 20);
		
		text_3 = new Text(this, SWT.BORDER);
		
		text_3.setBounds(299, 525, 145, 23);
		
		Label label_7 = new Label(this, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_7.setText("借书证号：");
		label_7.setBounds(220, 326, 61, 20);
		
		label_8 = new Label(this, SWT.NONE);
		label_8.setBounds(299, 328, 145, 17);
		
		Label label_9 = new Label(this, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_9.setText("等      级：");
		label_9.setBounds(221, 372, 69, 20);
		
		label_10 = new Label(this, SWT.NONE);
		label_10.setBounds(299, 374, 83, 17);
		
		Button button_2 = new Button(this, SWT.NONE);
		
		button_2.setText("修  改");
		button_2.setBounds(299, 601, 80, 27);
		
		Button button_3 = new Button(this, SWT.NONE);
		
		button_3.setText("确认修改");
		button_3.setBounds(679, 472, 80, 27);
		
		final Label label_11 = new Label(this, SWT.NONE);
		label_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_11.setBounds(764, 393, 60, 20);
		
		text_4 = new Text(this, SWT.BORDER);
		
		text_4.setBounds(679, 390, 73, 23);
		
		Label label_12 = new Label(this, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_12.setText("验 证 码：");
		label_12.setBounds(599, 391, 61, 20);
		
		Label label_13 = new Label(this, SWT.NONE);
		label_13.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_13.setText("确认新密码：");
		label_13.setBounds(583, 326, 77, 20);
		
		text_5 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		
		text_5.setBounds(679, 325, 145, 23);
		
		text_6 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		
		
		text_6.setBounds(679, 256, 145, 23);
		
		Label label_14 = new Label(this, SWT.NONE);
		label_14.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_14.setText("新 密 码：");
		label_14.setBounds(599, 257, 61, 20);
		
		Label label_15 = new Label(this, SWT.NONE);
		label_15.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_15.setText("旧 密 码：");
		label_15.setBounds(599, 194, 61, 20);
		
		text_7 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		
		text_7.setBounds(679, 193, 145, 23);
		
		final Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setBounds(682, 222, 117, 17);
		
		Label label_16 = new Label(this, SWT.NONE);
		label_16.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_16.setBounds(220, 286, 61, 20);
		label_16.setText("证件类型：");
		
		lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(299, 286, 145, 17);
		
		final Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_2.setBounds(682, 354, 145, 17);
		
		final Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_3.setBounds(679, 428, 145, 17);
		
		Label lblNewLabel_4 = new Label(this, SWT.WRAP | SWT.SHADOW_NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		lblNewLabel_4.setBounds(516, 173, 20, 360);
		lblNewLabel_4.setText("读书之法，在循序而渐进，熟读而精思。 | 朱熹");
		
		final Label label_17 = new Label(this, SWT.NONE);
		label_17.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_17.setBounds(299, 440, 145, 17);
		
		final Label label_18 = new Label(this, SWT.NONE);
		label_18.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_18.setBounds(299, 500, 145, 17);
		
		final Label label_19 = new Label(this, SWT.NONE);
		label_19.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_19.setBounds(299, 554, 145, 17);
		File file=new File("");
		//OutputStream os=new FileOutputStream(file);
		//label_11.setImage(makeCertPic.getCertPic(154, 17, os));
		showData();
		
		//验证码
		Map<String,Object> map=pic.getCertPicByte();
		ImageData data=new ImageData(new ByteArrayInputStream((byte[])map.get("imageByte") ));
		
		label_11.setImage(new Image(null,data));
		
		Label label_20 = new Label(this, SWT.NONE);
		label_20.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label_20.setBounds(475, 95, 92, 23);
		label_20.setText("基本信息");
		code=(String)map.get("code");
		
		//刷新验证码
		label_11.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				Map<String,Object> map=pic.getCertPicByte();
				ImageData data=new ImageData(new ByteArrayInputStream((byte[]) map.get("imageByte")));
				code=(String)map.get("code");
				System.out.println(code);
				label_11.setImage(new Image(null,data));
			}
		});
		//验证码是否正确
		text_4.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(text_4.getText().trim().equals(code)){
					lblNewLabel_3.setText("");
				}else{
					lblNewLabel_3.setText("*验证码错误，请重新输入");
				}
			}
		});
		
		
		//邮箱的监听
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String mail=text_2.getText().trim();
				
				if(text_2.getText().trim().equals("")){
					label_18.setText("");
				}else if(emailFormat(mail)){
					label_18.setText("");
				}else{
					label_18.setText("*邮箱格式不正确");
				}
			}
		});
		//手机号码的监听
		text_3.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(String.valueOf(text_3.getText().trim()).equals("")){
					label_19.setText("");
				}else if(String.valueOf(text_3.getText().trim()).length()==11){
					label_19.setText("");
				}else{
					label_19.setText("*请输入正确的电话号码");
				}
				
			}
		});
		//身份证的监听
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(String.valueOf(text_1.getText().trim()).equals("")){
					label_17.setText("");
				}else if(String.valueOf(text_1.getText().trim()).length()==18){
					label_17.setText("");
				}else{
					label_17.setText("*请输入正确的身份证号码");
				}

				
			}
		});
		//确认密码
		text_6.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(text_5.getText().trim().equals("")==false){
					if(text_5.getText().trim().equals(text_6.getText().trim())){
						lblNewLabel_2.setText("");
					}else if(text_5.getText().trim().equals("")){
						lblNewLabel_2.setText("");
					} else{
						lblNewLabel_2.setText("*前后密码不一致....");
					}
				}
			}
		});
		text_5.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				if(text_5.getText().trim().equals(text_6.getText().trim())){
					lblNewLabel_2.setText("");
				}else if(text_5.getText().trim().equals("")){
					lblNewLabel_2.setText("");
				} else{
					lblNewLabel_2.setText("*前后密码不一致....");
				}
				
				
			}
		});
		
		//修改密码
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(lblNewLabel_3.getText().trim().equals("")==false||lblNewLabel_2.getText().trim().equals("")==false||lblNewLabel.getText().trim().equals("")==false){
					MessageDialog.openError(getShell(), "温馨提示", "修改失败....");
					
				}else{
					String pwad=text_5.getText().trim();
					if(readerInfoDao.update(pwad)>0){
						MessageDialog.openInformation(getShell(), "温馨提示", "修改成功....");
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "修改失败....");
					}
				}
				
			}
		});
		
		//点击修改
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(label_17.getText().trim().equals("")==false||label_18.getText().trim().equals("")==false||label_19.getText().trim().equals("")==false){
					MessageDialog.openError(getShell(), "温馨提示", "修改失败....");
				}else{
					String sname=text.getText().trim();
					String sex="";
					if(button.getSelection()){
						sex="男";
					}else if(button_1.getSelection()){
						sex="女";
					}
					String useridentity=text_1.getText().trim();
					String mail=text_2.getText().trim();
					String usertel=text_3.getText().trim();
					if(readerInfoDao.update(sname, sex, useridentity, mail, usertel)>0){
						
						MessageDialog.openInformation(getShell(), "温馨提示", "修改成功....");
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "修改失败....");
					}
				}
			}
		});
		
		//原密码的监听
		text_7.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				List<Map<String,Object>> list=readerInfoDao.find(Data.username);
				String upwad="";
				for(Map<String,Object> map:list){
					upwad=String.valueOf(map.get("PWAD"));
				}
				if(text_7.getText().trim().equals(upwad)){
					lblNewLabel.setText("");	
				}else if(text_7.getText().trim().equals("")){
					lblNewLabel.setText("");
				}else{
					lblNewLabel.setText("*密码错误...");
				}
				
				
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public void showData(){
		
		List<Map<String, Object>> list;
		try {
			
			list = readerInfoDao.find(Data.username);
			for(Map<String,Object> map:list){
				text.setText(String.valueOf(map.get("SNAME")));
				if(String.valueOf(map.get("SEX")).trim().equals("男")){
					button.setSelection(true);
				}else if(String.valueOf(map.get("SEX")).trim().equals("女")){
					button_1.setSelection(true);
				}
				text_1.setText(String.valueOf(map.get("USERIDENTITY")));
				text_2.setText(String.valueOf(map.get("MAIL")));
				text_3.setText(String.valueOf(map.get("USERTEL")));
				label_8.setText(String.valueOf(map.get("USERID")));
				label_10.setText(String.valueOf(map.get("RANKNAME")));
				lblNewLabel_1.setText(String.valueOf(map.get("TYPE")));
			}
		} catch (Exception e) {
		
			e.printStackTrace();
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
}
