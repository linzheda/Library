package com.zd.book.util;

import java.io.InputStream;
import java.util.Properties;

public class ReadPro extends Properties{
	public static ReadPro pro=new ReadPro();

	private ReadPro(){
		InputStream is=this.getClass().getClassLoader().getResourceAsStream("db.properties");
		try {
			this.load(is);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.log.error(e.toString());
		}
	}
}
