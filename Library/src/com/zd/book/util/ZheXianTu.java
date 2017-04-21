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

public class ZheXianTu {
	public void ZheXianTus(List<Map<String,Object>> list){//折线图
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset(); 
		//mDataset.addValue(1, "First", "2013");  
		String [] name=new String[50];
		int i=0;
		for(Map<String,Object> map:list){
			name[i]=String.valueOf(map.get("BOOKNAME")+"_"+String.valueOf(map.get("BESPEAKDATE")).substring(0, 10));
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
				mDataset.addValue(count,name[j].substring(0,name[j].lastIndexOf("_")),name[j].substring(name[j].lastIndexOf("_")+1) );
				
			}
			count=0;
		}


		JFreeChart mChart = ChartFactory.createLineChart(  
				"预约情况",  
				"年份",  
				"数量",  
				mDataset,  
				PlotOrientation.VERTICAL,  // 图表方向：水平、垂直  
				true,   
				false,   
				false
				);  

		configFont(mChart);

		ChartFrame mChartFrame = new ChartFrame("折线图", mChart);  
		mChartFrame.pack();  
		mChartFrame.setVisible(true); 

	}
	public void ZheXianTuss(List<Map<String,Object>> list){//折线图
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset(); 
		//mDataset.addValue(1, "First", "2013");  
		String [] name=new String[50];
		int i=0;
		for(Map<String,Object> map:list){
			name[i]=String.valueOf(map.get("BOOKNAME")+"_"+String.valueOf(map.get("BDATE")).substring(0, 10));
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
				mDataset.addValue(count,name[j].substring(0,name[j].lastIndexOf("_")),name[j].substring(name[j].lastIndexOf("_")+1) );
				
			}
			count=0;
		}


		JFreeChart mChart = ChartFactory.createLineChart(  
				"借阅情况",  
				"年份",  
				"数量",  
				mDataset,  
				PlotOrientation.VERTICAL,  // 图表方向：水平、垂直  
				true,   
				false,   
				false
				);  

		configFont(mChart);

		ChartFrame mChartFrame = new ChartFrame("折线图", mChart);  
		mChartFrame.pack();  
		mChartFrame.setVisible(true); 

	}
	public void ZheXianTuOrder(List<Map<String,Object>> list){//折线图
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset(); 
		//mDataset.addValue(1, "First", "2013");  
		String [] name=new String[50];
		String [] booknum=new String[50];
		int [] counts=new int[50];
		int i=0;
		for(Map<String,Object> map:list){
			name[i]=String.valueOf(map.get("BOOKNAME")+"_"+String.valueOf(map.get("ORDERDATE")).substring(0, 10));
			booknum[i]=String.valueOf(map.get("BOOKNUM"));
			counts[i]=Integer.valueOf(booknum[i]).intValue();
			
			//mDataset.addValue(count, name[i].substring(0,name[i].lastIndexOf("_")), name[i].substring(name[i].lastIndexOf("_")+1));
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
				mDataset.addValue(countnum[j], name[j].substring(0,name[j].lastIndexOf("_")), name[j].substring(name[j].lastIndexOf("_")+1));
				
			}
			count=0;
		}
		
		JFreeChart mChart = ChartFactory.createLineChart(  
				"订单情况",  
				"年份",  
				"数量",  
				mDataset,  
				PlotOrientation.VERTICAL,  // 图表方向：水平、垂直  
				true,   
				false,   
				false
				);  

		configFont(mChart);

		ChartFrame mChartFrame = new ChartFrame("折线图", mChart);  
		mChartFrame.pack();  
		mChartFrame.setVisible(true); 

	}
	//字体配置方法(解决中文问题)
	private static void configFont(JFreeChart chart) {
		chart.setTitle(new TextTitle(chart.getTitle().getText(), new Font("隶书", Font.BOLD, 25)));//标题
		CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot

		CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
		categoryAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));//设置横坐标字体
		categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));//设置数据字体


		ValueAxis rangeAxis = plot.getRangeAxis();//获取纵坐标
		rangeAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
		rangeAxis.setLabelPaint(Color.BLUE);

		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  // 底部

		plot.setBackgroundPaint(Color.LIGHT_GRAY);  
		plot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线  
		plot.setOutlinePaint(Color.RED);//边界线  
	}
}
