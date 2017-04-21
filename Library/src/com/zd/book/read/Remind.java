package com.zd.book.read;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;

import org.eclipse.wb.swt.SWTResourceManager;

public class Remind {

	protected Shell shell;
	private Display display;
	public String num="";
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Remind window = new Remind();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
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
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shell.setSize(262, 207);
		shell.setText("温馨提示");
		shell.setLocation((display.getClientArea().width-shell.getSize().x), (display.getClientArea().height-shell.getSize().y));
		shell.setLayout(null);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 246, 120);
		lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setText(" \n 亲爱的用户，您好！ \n\n   您当前有"+num+"本书未归还，\n   如果使用完，请及时归还...\n\n\t图书馆小提示");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBackgroundImage(SWTResourceManager.getImage(Remind.class, "/images/u=1542798435,1936271349&fm=21&gp=0.jpg"));
		label.setBounds(125, 126, 90, 30);
		over();
		
		
	}
	public void over(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				display.syncExec(new Runnable(){
					@Override
					public void run() {
						shell.dispose();
					}
					
				});
				
			}
		},5000);
		
	}
	

}
