package com.zd.book.util;

import java.io.BufferedReader;  
import java.io.FileNotFoundException;  
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;  
import java.net.URL;  
import java.net.URLConnection;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;  


public class Weather {  

	private String Cityid;  
	private static URLConnection connectionData;  
	private StringBuilder sb;  
	private BufferedReader br;// 读取data数据流   


	public Weather(String cityid) throws IOException{  
		///解析本机IP地址  
		this.Cityid=cityid;  

		///连接中央气象台的APi  
		URL url=new URL("http://m.weather.com.cn/data/" + Cityid + ".html");  
		connectionData = url.openConnection();   
		connectionData.setConnectTimeout(1000);   
		try {   
			br = new BufferedReader(new InputStreamReader(connectionData.getInputStream(), "UTF-8"));   
			sb = new StringBuilder();   
			String line = null;   
			while ((line = br.readLine()) != null){  
				sb.append(line);   
			}  
		} catch (SocketTimeoutException e) {   
			System.out.println("连接超时");   
		} catch (FileNotFoundException e) {   
			System.out.println("加载文件出错");   
		}     

	}
	
	public static List<Element> getWeather(){
		URL url;
		List<Element> es = null;
		try {
			url=new URL("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather?theCityCode=1780&theUserID=");
			connectionData = url.openConnection();
			
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(connectionData.getInputStream());
			
			Element root = document.getRootElement();
			
			es= root.getChildren();
			int count=0;
			
//			for (Element e : es) {
//				System.out.println(e.getText());
//				count++;
//				System.out.println(count);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return es;
	}
	
	public static void main(String[] args) {
		Weather.getWeather();
	}
}