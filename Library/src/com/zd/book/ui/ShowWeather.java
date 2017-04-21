package com.zd.book.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jdom.Element;

import com.zd.book.util.Weather;

public class ShowWeather {

	protected Shell shell;
	private Display display;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ShowWeather window = new ShowWeather();
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
		shell.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		shell.setBackgroundImage(SWTResourceManager.getImage(ShowWeather.class, "/images/20091212_17370529145dbf1f8c8dCcqVWNv386KQ.jpg"));
		
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		shell.setSize(367, 220);
		shell.setLocation((display.getClientArea().width-shell.getSize().x), (display.getClientArea().height-shell.getSize().y));
		shell.setImage(SWTResourceManager.getImage(ShowWeather.class, "/images/log.ico"));
		shell.setText("今日天气");
		shell.setLayout(null);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblNewLabel.setBounds(0, 0, 351, 48);
		
		List<Element> es = Weather.getWeather();
		Map<String,Object>list =new HashMap<String,Object> ();
		int count =0;
		
		for(Element e:es){
			count++;
			list.put(String.valueOf(count),e.getText());
		}
		
		StringBuffer ti=new StringBuffer();
		ti.append(String.valueOf(list.get("1"))+"\n");
		ti.append(String.valueOf(list.get("5"))+"\n");
		ti.append(String.valueOf(list.get("6"))+"\n");
		lblNewLabel.setText(ti.toString());
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label.setBounds(0, 48, 434, 24);
		
		
		StringBuffer tis=new StringBuffer();
		tis.append(String.valueOf(list.get("8"))+"   ");
		tis.append(String.valueOf(list.get("9"))+"   ");
		tis.append(String.valueOf(list.get("10"))+"   ");

		label.setText(tis.toString());
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		
		lblNewLabel_1.setImage(SWTResourceManager.getImage(ShowWeather.class, "/weather/"+String.valueOf(list.get("11"))));
		lblNewLabel_1.setBounds(25, 72, 20, 20);
		
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setImage(SWTResourceManager.getImage(ShowWeather.class, "/weather/"+String.valueOf(list.get("12"))));
		lblNewLabel_2.setBounds(110, 72, 20, 20);
		//下一天
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_1.setBounds(0, 92, 351, 17);
		StringBuffer tiss=new StringBuffer();
		tiss.append(String.valueOf(list.get("13"))+"   ");
		tiss.append(String.valueOf(list.get("14"))+"   ");
		tiss.append(String.valueOf(list.get("15"))+"   ");
		label_1.setText(tiss.toString());
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(25, 109, 20, 20);
		lblNewLabel_3.setImage(SWTResourceManager.getImage(ShowWeather.class, "/weather/"+String.valueOf(list.get("16"))));
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(110, 109, 20, 20);
		lblNewLabel_4.setImage(SWTResourceManager.getImage(ShowWeather.class, "/weather/"+String.valueOf(list.get("17"))));
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblNewLabel_5.setBounds(0, 129, 351, 17);
		//下下天
		StringBuffer tisss=new StringBuffer();
		tisss.append(String.valueOf(list.get("18"))+"   ");
		tisss.append(String.valueOf(list.get("19"))+"   ");
		tisss.append(String.valueOf(list.get("20"))+"   ");
		lblNewLabel_5.setText(tisss.toString());
		
		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setBounds(25, 146, 20, 20);
		lblNewLabel_6.setImage(SWTResourceManager.getImage(ShowWeather.class, "/weather/"+String.valueOf(list.get("21"))));
		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setBounds(110, 152, 20, 20);
		lblNewLabel_7.setText("");
		lblNewLabel_7.setImage(SWTResourceManager.getImage(ShowWeather.class, "/weather/"+String.valueOf(list.get("22"))));
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
		},10000);
		
	}
}
