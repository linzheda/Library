package com.zd.book.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zd.book.Dao.MailDao;

import org.eclipse.swt.widgets.Label;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Mail extends Composite {
	private Text text;
	private MailDao mailDao=new MailDao();
	private Text text_1;
	private Table table;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws MessagingException 
	 */
	public Mail(Composite parent, int style) throws MessagingException {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(Mail.class, "/images/框框.jpg"));

		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 16, SWT.BOLD));
		label.setBounds(475, 95, 108, 28);
		label.setText("发送邮件");

		text = new Text(this, SWT.BORDER | SWT.MULTI);
		text.setBounds(624, 253, 272, 213);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setBounds(540, 256, 61, 17);
		label_1.setText("邮件内容：");

		Button button = new Button(this, SWT.NONE);

		button.setBounds(624, 521, 80, 27);
		button.setText("书籍催促");

		Button button_1 = new Button(this, SWT.NONE);

		button_1.setBounds(720, 521, 80, 27);
		button_1.setText("账号冻结");

		Button button_2 = new Button(this, SWT.NONE);

		button_2.setBounds(330, 521, 80, 27);
		button_2.setText("发送");

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_2.setBounds(166, 194, 61, 17);
		label_2.setText("接收者：");

		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_3.setBounds(540, 194, 61, 17);
		label_3.setText("邮件主题：");

		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(624, 194, 145, 23);

		Button btnNewButton = new Button(this, SWT.NONE);

		btnNewButton.setBounds(816, 521, 80, 27);
		btnNewButton.setText("预约通知");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setBounds(233, 253, 225, 213);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(102);
		tblclmnNewColumn.setText("借阅者编号");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(119);
		tblclmnNewColumn_1.setText("借阅者名称");

		final Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(233, 191, 100, 25);
		combo.add("借阅者编号");
		combo.add("借阅者姓名");
		combo.add("查询所有");
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(350, 191, 73, 25);

		Button button_3 = new Button(this, SWT.NONE);
		button_3.setBounds(436, 192, 60, 25);
		button_3.setText("查询");

		showData();
		//
		//点击查询
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tiaojian=combo.getText().trim();
				String jieguo=text_2.getText().trim();
				if(tiaojian.equals("借阅者编号")){
					tiaojian="u.userid";
					
					if(mailDao.find(tiaojian,jieguo)!=null&&mailDao.find(tiaojian,jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}	
				}else if(tiaojian.equals("借阅者姓名")){
					tiaojian="u.sname";
					if(mailDao.find(tiaojian, jieguo)!=null&&mailDao.find(tiaojian, jieguo).size()>0){
						showData(tiaojian,jieguo);
					}else{
						MessageDialog.openError(getShell(), "温馨提示", "查询失败....");
					}	
				}else if(tiaojian.equals("查询所有")){
					showData();
				}
			}
		});
		//点击发送
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				String[] userid=new String[50];
				if(tis!=null&&tis.length>0){
					for(int i=0;i<tis.length;i++){
						userid[i]=tis[i].getText().trim();
						String mail;
						List<Map<String,Object>> list=mailDao.find(userid[i]);
						if(list==null){
							MessageDialog.openError(getShell(), "温馨提示", "发送失败....");
						}else{
							int flag=0;
							int count=0;
							for(Map<String,Object> map:list){
								mail=String.valueOf(map.get("MAIL"));
								try {
									setMail(mail);
									flag=1;
									count++;
								} catch (MessagingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								break;
							}
							if(flag==1){
								MessageDialog.openInformation(getShell(), "温馨提示", ""+count+"封邮件发送成功....");
							}
							
						}

					}
				}
			}

		});

		//点击书籍催促
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("亲爱的用户，您好！  \n  您借阅的书籍快到期了 ，请及时归还，以避免产生罚单.....");
				text_1.setText("图书馆书籍到期催促");
			}
		});
		//账号冻结
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_1.setText("图书馆账号冻结提醒");
				text.setText("亲爱的用户，您好！  \n  您的账号已被冻结，请及时归还书籍缴纳罚单，以避免无法使用改账号.....");
			}
		});

		//点击预约通知
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_1.setText("图书馆账号预约通知");
				text.setText("亲爱的用户，您好！  \n  您预约的书籍以通过审核，请于一天内到图书馆领书.....");
			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}



	public void setMail(String mail) throws MessagingException{
		Properties props = new Properties(); //Properties 属性文件 
		// 开启debug调试  
		props.setProperty("mail.debug", "true");  
		// 设置邮件服务器主机名 
		props.setProperty("mail.host", "smtp.163.com");  
		// 发送服务器需要身份验证  
		props.setProperty("mail.smtp.auth", "true");  
		// 发送邮件协议名称 
		props.setProperty("mail.transport.protocol", "smtp");  

		// 设置环境信息 
		Session session = Session.getInstance(props);  

		// 创建邮件对象  
		Message msg = new MimeMessage(session);  
		msg.setSubject(text_1.getText()); 
		// 设置邮件内容  
		msg.setText(text.getText());  
		// 设置发件人  
		msg.setFrom(new InternetAddress("15367053290@163.com"));  
		Transport transport = session.getTransport();  
		// 连接邮件服务器  
		transport.connect("15367053290@163.com","3344520qq");
		// 发送邮件  

		transport.sendMessage(msg, new Address[] {new InternetAddress(mail)});
		// 关闭连接  
		transport.close(); 
	}

	//显示数据
	public void showData(){
		table.removeAll();
		List<Map<String,Object>> types=mailDao.find();
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("SNAME"))});
			}
		}

	}
	//显示数据条件
	public void showData(String tioajian,String jieguo){
		table.removeAll();
		List<Map<String,Object>> types=mailDao.find(tioajian,jieguo);
		if(types!=null&&types.size()>0){
			TableItem tm;
			for(Map<String,Object> map:types){
				tm=new TableItem(table,SWT.NONE);
				tm.setText(new String[]{String.valueOf(map.get("USERID")),String.valueOf(map.get("SNAME"))});
			}
		}

	}
}




