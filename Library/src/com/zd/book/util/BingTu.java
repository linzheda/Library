package com.zd.book.util;


import java.awt.Font;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class BingTu {
	public void BingTus(List<Map<String,Object>> list){//饼图
		DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
		String [] name=new String[50];
		int i=0;
		for(Map<String,Object> map:list){
			name[i]=String.valueOf(map.get("BOOKNAME"));
			
			i++;
		}
		int count=0;
		for(int j=0;j<i;j++){
			for(int x=0;x<i;x++){
				if(name[j].equals("1")==false&&name[j].equals(name[x])){
					count++;
					if(count>=2){
						name[x]="1";
					}	
				}	
			}
			if(name[j].equals("1")==false){
				dpd.setValue(name[j],count);
				
				
			}
			count=0;
		}
		JFreeChart chart=ChartFactory.createPieChart("预约情况",dpd,true,false,false); 
		//第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示图例，第四个参数表示是否生成工具，第五个参数表示图中是否生成URL链接

		configFont(chart);

		ChartFrame chartFrame=new ChartFrame("饼图",chart); 
		//chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
		chartFrame.pack(); //以合适的大小展现图形
		chartFrame.setVisible(true);//图形是否可见
	}
	public void BingTuss(List<Map<String,Object>> list){//饼图
		DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
		String [] name=new String[50];
		int i=0;
		for(Map<String,Object> map:list){
			name[i]=String.valueOf(map.get("BOOKNAME"));
			
			i++;
		}
		int count=0;
		for(int j=0;j<i;j++){
			for(int x=0;x<i;x++){
				if(name[j].equals("1")==false&&name[j].equals(name[x])){
					count++;
					if(count>=2){
						name[x]="1";
					}	
				}	
			}
			if(name[j].equals("1")==false){
				dpd.setValue(name[j],count);
				
			}
			count=0;
		}
		JFreeChart chart=ChartFactory.createPieChart("借阅情况",dpd,true,false,false); 
		//第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示图例，第四个参数表示是否生成工具，第五个参数表示图中是否生成URL链接

		configFont(chart);

		ChartFrame chartFrame=new ChartFrame("饼图",chart); 
		//chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
		chartFrame.pack(); //以合适的大小展现图形
		chartFrame.setVisible(true);//图形是否可见
	}
	public void BingTuOrder(List<Map<String,Object>> list){//饼图
		DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
		String [] name=new String[50];
		String [] booknum=new String[50];
		int [] counts=new int[50];
		int i=0;
		
		for(Map<String,Object> map:list){
			name[i]=String.valueOf(map.get("BOOKNAME"));
			booknum[i]=String.valueOf(map.get("BOOKNUM"));
			counts[i]=Integer.valueOf(booknum[i]).intValue();
			i++;
		}
		int[] countnum=new int[50];
		int count=0;
		for(int j=0;j<i;j++){
			for(int x=0;x<i;x++){
				if(name[j].equals("1")==false&&name[j].equals(name[x])){
					count++;
					countnum[j]=countnum[j]+counts[x];
					if(count>=2){
						name[x]="1";
					}	
				}	
			}
			if(name[j].equals("1")==false){
				dpd.setValue(name[j],countnum[j]);
				
			}
			count=0;
		}
		
		JFreeChart chart=ChartFactory.createPieChart("订单情况",dpd,true,false,false); 
		//第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示图例，第四个参数表示是否生成工具，第五个参数表示图中是否生成URL链接

		configFont(chart);

		ChartFrame chartFrame=new ChartFrame("饼图",chart); 
		//chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
		chartFrame.pack(); //以合适的大小展现图形
		chartFrame.setVisible(true);//图形是否可见
	}

	//字体配置方法(解决中文问题)
	private static void configFont(JFreeChart chart) {
		Font font = new Font(" 黑体", Font.PLAIN, 20);
		PiePlot plot = (PiePlot) chart.getPlot(); 	// 饼图形的绘制结构对象
		//设置饼状图体里的的各个标签字体
		plot.setLabelFont(font);

		//标题中文
		chart.getTitle().setFont(font);

		//底部中文
		chart.getLegend().setItemFont(font);
	}

}
