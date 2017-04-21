package com.zd.book.util;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class ZhuzhuangTu {
	public void ZhuzhuangTus(List<Map<String,Object>> list){//柱状图
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  //建立一个默认的柱状图

		//添加数据
		//dataset.addValue(100, "北京", "苹果");  
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
				dataset.addValue(count,name[j],name[j]);
			}
			count=0;
		}
		
		// 2. 构造chart  
		JFreeChart chart = ChartFactory.createBarChart3D(  
				"预约情况", // 图表标题  
				"书名", // 目录轴的显示标签--横轴  
				"数量", // 数值轴的显示标签--纵轴  
				dataset, // 数据集  
				PlotOrientation.VERTICAL, // 图表方向：水平、  
				true, // 是否显示图例 
				false, // 是否生成工具  
				false // 是否生成URL链接  
				);  


		//3. 处理chart中文显示问题    
		configFont(chart);

		// 4. chart 以swing形式输出  
		ChartFrame pieFrame = new ChartFrame("柱状图", chart);  /*chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。
        													该第一个参数的数据是放在窗口左上角的，不是正中间的标题。*/
		pieFrame.pack();  //以合适的大小展现图形
		pieFrame.setVisible(true);  //图形是否可见

	}
	public void ZhuzhuangTuss(List<Map<String,Object>> list){//柱状图
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  //建立一个默认的柱状图

		//添加数据
		//dataset.addValue(100, "北京", "苹果");  
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
				dataset.addValue(count,name[j],name[j]);
			}
			count=0;
		}
		
		// 2. 构造chart  
		JFreeChart chart = ChartFactory.createBarChart3D(  
				"借阅情况", // 图表标题  
				"书名", // 目录轴的显示标签--横轴  
				"数量", // 数值轴的显示标签--纵轴  
				dataset, // 数据集  
				PlotOrientation.VERTICAL, // 图表方向：水平、  
				true, // 是否显示图例 
				false, // 是否生成工具  
				false // 是否生成URL链接  
				);  


		//3. 处理chart中文显示问题    
		configFont(chart);

		// 4. chart 以swing形式输出  
		ChartFrame pieFrame = new ChartFrame("柱状图", chart);  /*chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。
        													该第一个参数的数据是放在窗口左上角的，不是正中间的标题。*/
		pieFrame.pack();  //以合适的大小展现图形
		pieFrame.setVisible(true);  //图形是否可见

	}
	public void ZhuzhuangTuOrder(List<Map<String,Object>> list){//柱状图
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  //建立一个默认的柱状图

		//添加数据
		
		String [] name=new String[50];
		String [] booknum=new String[50];
		int [] counts=new int[50];
		int i=0;
		for(Map<String,Object> map:list){
			name[i]=String.valueOf(map.get("BOOKNAME"));
			booknum[i]=String.valueOf(map.get("BOOKNUM"));
			counts[i]=Integer.valueOf(booknum[i]).intValue();
			//dataset.addValue(count,name[i],name[i]);
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
				dataset.addValue(countnum[j],name[j],name[j]);
				
			}
			count=0;
		}
		
		
		// 2. 构造chart  
		JFreeChart chart = ChartFactory.createBarChart3D(  
				"订单情况", // 图表标题  
				"书名", // 目录轴的显示标签--横轴  
				"数量", // 数值轴的显示标签--纵轴  
				dataset, // 数据集  
				PlotOrientation.VERTICAL, // 图表方向：水平、  
				true, // 是否显示图例 
				false, // 是否生成工具  
				false // 是否生成URL链接  
				);  


		//3. 处理chart中文显示问题    
		configFont(chart);

		// 4. chart 以swing形式输出  
		ChartFrame pieFrame = new ChartFrame("柱状图", chart);  /*chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。
        													该第一个参数的数据是放在窗口左上角的，不是正中间的标题。*/
		pieFrame.pack();  //以合适的大小展现图形
		pieFrame.setVisible(true);  //图形是否可见

	}
	//字体配置方法(解决中文问题)
	private static void configFont(JFreeChart chart) {
		chart.setTitle(new TextTitle(chart.getTitle().getText(), new Font("隶书", Font.BOLD, 25)));//标题
		CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot

		CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
		categoryAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));//设置横坐标字体
		categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));

		ValueAxis rangeAxis = plot.getRangeAxis();//获取纵坐标
		rangeAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
		rangeAxis.setLabelPaint(Color.BLUE);

		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  // 底部
	}

}
