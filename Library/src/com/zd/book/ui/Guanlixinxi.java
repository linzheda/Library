package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.GuanlixinxiDao;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class Guanlixinxi extends Composite {
	private Text text_1;
	private Text text_2;
	private GuanlixinxiDao guanlixinxidao=new GuanlixinxiDao();
	private Text text;
	private Text text_3;
	private Text text_4;
	Button button_1 = null;
	Button button_2=null;
	private Text text_5;
	private Text text_6;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Guanlixinxi(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Guanlixinxi.class, "/images/框框.jpg"));
		setLayout(null);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setBounds(637, 180, 75, 17);
		label_1.setText("原  密  码：");

		text_1 = new Text(this, SWT.BORDER | SWT.PASSWORD);

		text_1.setBounds(718, 179, 120, 23);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setBounds(637, 257, 65, 17);
		label_2.setText("新  密  码：");

		text_2 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		

		text_2.setBounds(718, 256, 120, 23);

		Button button = new Button(this, SWT.NONE);

		button.setBounds(348, 487, 80, 27);
		button.setText("修  改");

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 166, 23);
		label.setText("管理员信息修改");

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setBounds(277, 179, 65, 19);
		label_3.setText("名      字：");

		text = new Text(this, SWT.BORDER);
		text.setBounds(348, 175, 145, 23);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_4.setBounds(277, 230, 66, 17);
		label_4.setText("性      别：");

		button_1 = new Button(this, SWT.RADIO);
		button_1.setBounds(352, 232, 43, 17);
		button_1.setText("男");

		button_2 = new Button(this, SWT.RADIO);
		button_2.setBounds(401, 232, 40, 17);
		button_2.setText("女");

		Label label_5 = new Label(this, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_5.setBounds(277, 284, 61, 17);
		label_5.setText("身份证号：");

		text_3 = new Text(this, SWT.BORDER);

		text_3.setBounds(348, 283, 145, 23);

		Label label_6 = new Label(this, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_6.setBounds(277, 354, 61, 17);
		label_6.setText("电话号码：");

		text_4 = new Text(this, SWT.BORDER);

		text_4.setBounds(348, 353, 145, 23);

		final Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setBounds(350, 318, 142, 17);

		final Label label_7 = new Label(this, SWT.NONE);
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setBounds(348, 382, 144, 17);

		final Label label_8 = new Label(this, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setBounds(718, 219, 100, 17);

		final Label label_9 = new Label(this, SWT.NONE);
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_9.setBounds(719, 367, 100, 17);

		Label label_10 = new Label(this, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_10.setBounds(630, 329, 72, 20);
		label_10.setText("确认新密码：");

		text_5 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		
		text_5.setBounds(719, 328, 120, 23);
		
		Label label_11 = new Label(this, SWT.NONE);
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_11.setBounds(277, 423, 65, 17);
		label_11.setText("密      码：");
		
		text_6 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		text_6.setBounds(348, 420, 145, 23);
		
		Button button_3 = new Button(this, SWT.NONE);
		
		button_3.setBounds(718, 394, 80, 27);
		button_3.setText("修  改");

		showData();

		//确认新密码的监听
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(text_5.getText().trim().equals("")==false){
					if(text_2.getText().trim().equals(text_5.getText().trim())){
						label_9.setText("");
					}else if(text_5.getText().trim().equals("")){
						label_9.setText("");
					}else{
						label_9.setText("*前后密码不一致");
					}
				}
			}
		});
		text_5.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(text_2.getText().trim().equals(text_5.getText().trim())){
					label_9.setText("");
				}else if(text_5.getText().trim().equals("")){
					label_9.setText("");
				}else{
					label_9.setText("*前后密码不一致");
				}
				
			}
		});
		//原密码的监听
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				List<Map<String,Object>> list= guanlixinxidao.find();
				String upwad=text_1.getText().trim();
				for(Map<String,Object> map:list){
					if(upwad.equals(String.valueOf(map.get("PWAD")))){
						label_8.setText("");
					}else if(upwad.equals("")){
						label_8.setText("");
					}else{
						label_8.setText("*密码不正确");
					}
				}


			}
		});
		//电话号吗的监听
		text_4.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(String.valueOf(text_4.getText().trim()).equals("")){
					label_7.setText("");
				}else if(String.valueOf(text_4.getText().trim()).length()==11){
					label_7.setText("");
				}else{
					label_7.setText("*请输入正确的电话号码");
				}
			}
		});

		//身份证的监听
		text_3.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(String.valueOf(text_3.getText().trim()).equals("")){

					lblNewLabel.setText("");
				}else if(String.valueOf(text_3.getText().trim()).length()==18){
					lblNewLabel.setText("");
				}else{

					lblNewLabel.setText("*请输入正确的身份证号码");
				}


			}
		});
		//点击修改右
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if((label_8.getText().trim()).equals("")==false||(label_9.getText().trim()).equals("")==false){
					MessageDialog.openError(getShell(), "失败提示", "修改失败");
				}else{
					String upwad=text_1.getText().trim();
					String pwad=text_2.getText().trim();
					List<Map<String,Object>> list= guanlixinxidao.find();
					for(Map<String,Object> map:list){
						if(upwad.equals(String.valueOf(map.get("PWAD")))){
							
							if(guanlixinxidao.update(pwad)>0){
								MessageDialog.openInformation(getShell(), "成功提示", "修改成功");
								showData();
								text_1.setText("");
								text_2.setText("");
								text_5.setText("");
							}else{
								MessageDialog.openError(getShell(), "失败提示", "修改失败");
								showData();
								text_1.setText("");
								text_2.setText("");
								text_5.setText("");
							}
						}else if(!upwad.equals(map)){
							MessageDialog.openError(getShell(), "失败提示", "原密码错误，请重新输入");
							showData();
							text_1.setText("");
							text_2.setText("");
							text_5.setText("");
							
						}

					}
				}
				
			}
		});
		//点击修改左
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if((lblNewLabel.getText().trim()).equals("")==false||(label_7.getText().trim()).equals("")==false){
					MessageDialog.openError(getShell(), "失败提示", "修改失败");
				}else{
					String upwad=text_6.getText().trim();
					String pwad=text_2.getText().trim();
					List<Map<String,Object>> list= guanlixinxidao.find();
					for(Map<String,Object> map:list){
						if(upwad.equals(String.valueOf(map.get("PWAD")))){
							String mname=text.getText().trim();
							String tel=String.valueOf(text_4.getText().trim());
							String manageidentity=text_3.getText().trim();
							String sex=null;
							if(button_1.getSelection()){
								sex="男";
							}else if(button_2.getSelection()){
								sex="女";
							}
							if(guanlixinxidao.update(mname,tel,manageidentity,sex)>0){
								MessageDialog.openInformation(getShell(), "成功提示", "修改成功");
								showData();
								text_6.setText("");
								
							}else{
								MessageDialog.openError(getShell(), "失败提示", "修改失败");
								showData();
								text_6.setText("");
							}
						}else if(!upwad.equals(map)){
							MessageDialog.openError(getShell(), "失败提示", "原密码错误，请重新输入");
							showData();
							text_6.setText("");
							
						}

					}
				}
				



			}
		});
	}
	//显示数据
	public void showData(){
		List<Map<String,Object>> list=guanlixinxidao.find1();
		if(list!=null&&list.size()>0){
			for(Map<String,Object> map:list){
				text.setText(String.valueOf(map.get("MNAME")));
				text_4.setText(String.valueOf(map.get("TEL")));
				text_3.setText(String.valueOf(map.get("MANAGEIDENTITY")));
				if("男".equals(String.valueOf(map.get("SEX")))){

					button_1.setSelection(true);
				}else if("女".equals(String.valueOf(map.get("SEX")))){
					button_2.setSelection(true);
				}


			}
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
