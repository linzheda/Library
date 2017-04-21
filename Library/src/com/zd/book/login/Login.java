package com.zd.book.login;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.prefs.BackingStoreException;

import javax.mail.MessagingException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

import com.zd.book.util.Data;
import com.zd.book.util.LoginDao;
import com.zd.book.util.Music;
import com.zd.book.util.RegisterUtil;
import com.zd.book.util.TrayUtil;

public class Login {

	protected Shell shell;
	private Display display ;
	private boolean flag=false;//鼠标没有按下去
	private int clickX;//鼠标按下时x轴坐标
	private int clickY;//鼠标按下时y轴坐标
	private Text text;
	private Label lblNewLabel_1 ;
	private Tray tray;
	private LoginDao login=new LoginDao();
	private RegisterUtil ru=new RegisterUtil();
	private Combo combo;
	private Button button;
	private Button button_1;
	private Map<String,String> loginInfos;
	private Help help=new Help();
	



	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws MalformedURLException 
	 */
	public void open() throws MalformedURLException {
		display = Display.getDefault();
		createContents();
		changeID();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws MalformedURLException 
	 */
	protected void createContents() throws MalformedURLException {
		shell = new Shell(SWT.NONE);
		shell.setImage(SWTResourceManager.getImage(Login.class, "/images/log.ico"));
		shell.setSize(450, 300);
		shell.setText("图书管理系统登录");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setLocation((display.getClientArea().width-shell.getSize().x)/2, (display.getClientArea().height-shell.getSize().y-100)/2);


		tray=display.getSystemTray();
		TrayUtil trayUtil=new TrayUtil(shell,tray);
		trayUtil.tray();
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/登录图片1.jpg"));

		final Label label = new Label(composite, SWT.NONE);
		label.setToolTipText("关闭程序");
		label.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_close_normal.png"));
		label.setBounds(410, 0, 39, 20);
		final Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setToolTipText("最小化");
		lblNewLabel.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_mini_normal.png"));
		lblNewLabel.setBounds(369, 0, 35, 20);

		combo = new Combo(composite, SWT.NONE);
		combo.setBounds(88, 78, 160, 25);
		combo.setText("请输入您的账号：");

		text = new Text(composite, SWT.BORDER);

		text.setText("请输入您的密码");
		text.setBounds(88, 127, 160, 23);
		

		button = new Button(composite, SWT.RADIO);

		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		button.setFont(SWTResourceManager.getFont("楷体", 10, SWT.BOLD));
		button.setBounds(254, 81, 70, 17);
		button.setText("管理员");

		button_1 = new Button(composite, SWT.RADIO);

		button_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		button_1.setFont(SWTResourceManager.getFont("楷体", 10, SWT.BOLD));
		button_1.setBounds(330, 81, 66, 17);
		button_1.setText("借阅者");

		final Button btnNewButton = new Button(composite, SWT.NONE);
		
		btnNewButton.setToolTipText("登录系统");
		btnNewButton.setImage(SWTResourceManager.getImage(Login.class, "/images/7205452_153152401331_2.jpg"));
		btnNewButton.setBounds(88, 203, 93, 31);

		final Button button_2 = new Button(composite, SWT.CHECK);
		button_2.setFont(SWTResourceManager.getFont("楷体", 10, SWT.BOLD));
		button_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		button_2.setBounds(88, 168, 80, 17);
		button_2.setText("记住密码");

		final Button button_3 = new Button(composite, SWT.CHECK);
		
		button_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		button_3.setFont(SWTResourceManager.getFont("楷体", 10, SWT.BOLD));
		button_3.setBounds(174, 168, 86, 17);
		button_3.setText("背景音乐");

		final Label label_1 = new Label(composite, SWT.NONE);

		label_1.setToolTipText("您忘记密码了吗");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_1.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label_1.setBounds(294, 129, 80, 17);
		label_1.setText("忘记密码");

		lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("楷体", 11, SWT.BOLD));
		lblNewLabel_1.setBounds(88, 42, 223, 17);
		
		final Button btnNewButton_2 = new Button(composite, SWT.NONE);
		
		btnNewButton_2.setImage(SWTResourceManager.getImage(Login.class, "/images/help.jpg"));
		btnNewButton_2.setBounds(244, 207, 80, 27);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		
		lblNewLabel_2.setImage(SWTResourceManager.getImage(Login.class, "/images/眼睛视图2.png"));
		lblNewLabel_2.setBounds(254, 130, 22, 17);
		//help的监听
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				btnNewButton_2.setImage(SWTResourceManager.getImage(Login.class, "/images/help1.jpg"));
				btnNewButton_2.setEnabled(false);
				help.open();
				btnNewButton_2.setEnabled(true);
			}
			@Override
			public void mouseUp(MouseEvent e) {
				btnNewButton_2.setImage(SWTResourceManager.getImage(Login.class, "/images/help.jpg"));

			}
		});
		//背景音乐
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button_3.getSelection()){
					try {
						Music.begin();
						
					} catch (MalformedURLException e1) {
						
						e1.printStackTrace();
					}
				}else{
					Music.over();
				}
			}
		});
		
		//登录按键颜色变化
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				btnNewButton.setImage(SWTResourceManager.getImage(Login.class, "/images/变深的按键.jpg"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				btnNewButton.setImage(SWTResourceManager.getImage(Login.class, "/images/7205452_153152401331_2.jpg"));

			}
		});

		//记住密码
		try {
			loginInfos=ru.getRecord();
		} catch (BackingStoreException e2) {
			e2.printStackTrace();
		}

		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				lblNewLabel_1.setText("");
				String key=combo.getText();
				if(loginInfos.containsKey(key)){
					text.setEchoChar('*');
					text.setText(loginInfos.get(key));
					button_2.setSelection(true);
				}else{
					text.setEchoChar('\0');
					text.setText("请输入您的密码");
					button_2.setSelection(false);
				}
			}
		});

		//当账号框中的内容发生变化时
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				if("请输入您的账号".equals(combo.getText().trim())){
					combo.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(combo.getText().trim())){
					combo.setText("请输入您的账号");
				}
			}
		});
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				lblNewLabel_1.setText("");
			}
		});

		//当密码框中的内容发生变化时
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				if("请输入您的密码".equals(text.getText().trim())){
					text.setText("");
					text.setEchoChar('*');
				}
			}
			@Override
			public void focusLost(FocusEvent e) {//当密码框失去焦点时
				if("".equals(text.getText().trim())){
					text.setEchoChar('\0');
					text.setText("请输入您的密码");
				}

			}
		});
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				lblNewLabel_1.setText("");
			}
		});
		//眼睛的监听
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if(text.getText().trim().equals("请输入您的密码")==false){
					text.setEchoChar('*');
				}
			}
			@Override
			public void mouseDown(MouseEvent e) {
				text.setEchoChar('\0');
			}
		});

		//当鼠标移动时
		composite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				flag=true;
				clickX=e.x;
				clickY=e.y;
			}

			@Override
			public void mouseUp(MouseEvent e) {
				flag=false;
			}
		});
		//当面板移动时

		composite.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {
				if(flag){
					shell.setLocation(shell.getLocation().x+e.x-clickX,shell.getLocation().y+e.y-clickY);
				}
			}
		});
		//退出图标
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {//鼠标按下去时
				label.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_close_down.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
		label.addMouseMoveListener(new MouseMoveListener() {//鼠标移动时
			public void mouseMove(MouseEvent arg0) {
				label.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_close_highlight.png"));

			}
		});
		label.addMouseTrackListener(new MouseTrackAdapter() {//鼠标移开
			@Override
			public void mouseExit(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_close_normal.png"));

			}
		});

		////最小化图标
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {//鼠标按下去时
				lblNewLabel.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_mini_down.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				shell.setMinimized(true);
			}
		});
		lblNewLabel.addMouseMoveListener(new MouseMoveListener() {//鼠标移动时
			public void mouseMove(MouseEvent arg0) {
				lblNewLabel.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_mini_highlight.png"));

			}
		});
		lblNewLabel.addMouseTrackListener(new MouseTrackAdapter() {//鼠标移开
			@Override
			public void mouseExit(MouseEvent e) {
				lblNewLabel.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_mini_normal.png"));

			}
		});
		//获取焦点
		combo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode==13){//如果松开的是回车键
					text.setFocus();//则密码获取焦点

				}
			}
		});
		//忘记密码点击时
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(button.getSelection()==true){
					MessageDialog.openError(shell, "忘记密码", "请向超级管理员申请重置密码...");
				}else{
					MessageDialog.openError(shell, "忘记密码", "请向管理员申请重置密码...");
				}

			}
		});

		//忘记密码颜色变动
		label_1.addMouseTrackListener(new MouseTrackAdapter() {//忘记密码颜色变动
			@Override
			public void mouseHover(MouseEvent e) {
				label_1.setForeground(SWTResourceManager.getColor(0, 255, 0));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));

			}
		});
		//点击借阅者时
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeID();
			}
		});
		//点击管理员时
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeID();
			}
		});

		//安全登录
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String uname=combo.getText();
				String pwad=text.getText();
				if(uname.equals("请输入您的账号")||pwad.equals("请输入您的密码")){
					lblNewLabel_1.setText("您输入的账号或密码有误.....");
				}
				if(button_1.getSelection()==true){//借阅者
					List<Map<String,Object>> list=login.findUser(uname, pwad);
					//System.out.println(list);
					if(list.size()>0&&list!=null){

						if(button_2.getSelection()==true){//记住密码
							Map<String,String> map=new HashMap<String,String>();
							map.put(uname,pwad);
							try {
								ru.addRecord(map);
							} catch (BackingStoreException e1) {
								e1.printStackTrace();
							}
						}else{
							ru.removeRecord(uname);
						}
						
						for(Map<String,Object> map:list){//查询状态是否冻结
							if(String.valueOf(map.get("USERSTATE")).equals("冻结")){
								MessageDialog.openError(shell, "状态提示", "您的账号被冻结，请及时缴纳罚单并申请解冻...");
							}else{
								ReadInfo readInfo=new ReadInfo();
								Data.username=uname;
								shell.dispose();
								tray.dispose();
								readInfo.open();
							}
						}
						
					}else{
						lblNewLabel_1.setText("您输入的账号或密码有误.....");
						combo.setFocus();
					}
				}else if(button.getSelection()==true){//管理员
					List<Map<String,Object>> lists=login.findManage(uname, pwad);
					//System.out.println(list);
					if(lists.size()>0&&lists!=null){
						if(button_2.getSelection()==true){
							Map<String,String> map=new HashMap<String,String>();
							map.put(uname,pwad);
							try {
								ru.addRecord(map);
							} catch (BackingStoreException e1) {
								e1.printStackTrace();
							}
						}else{
							ru.removeRecord(uname);
						}

						Info info=new Info();
						Data.manageid=uname;
						shell.dispose();
						tray.dispose();
						try {
							info.open();
						} catch (MessagingException e1) {
							
							e1.printStackTrace();
						}

					}else{
						lblNewLabel_1.setText("您输入的账号或密码有误.....");
						combo.setFocus();
					}
				}
			}
		});

	}
	//显示账号的
	public void changeID(){
		combo.removeAll();
		combo.setText("请输入您的账号");
		if(button.getSelection()==true){
			List<Map<String,Object>> map=login.findManage();
			if(map!=null&&map.size()>0){
				for(Map<String,Object> m:map){
					combo.add(String.valueOf(m.get("MID")));
				}
			}

		}else{
			List<Map<String,Object>> map=login.findUser();
			if(map!=null&&map.size()>0){
				for(Map<String,Object> m:map){
					combo.add(String.valueOf(m.get("USERID")));
				}
			}
		}
	}
}
