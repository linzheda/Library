package com.zd.book.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class RegisterUtil {	
		/**
		 * 将数据存入注册表
		 * @param entry
		 * @throws BackingStoreException
		 */
	
	public void addRecord(Map<String,String> entry) throws BackingStoreException{
		//
		
		
		//Preferences pf=Preferences.systemNodeForPackage(RegisterUtil.class);
		Preferences pf=Preferences.userNodeForPackage(RegisterUtil.class);
		if(entry!=null&&!entry.isEmpty()){
			Set<String> keys=entry.keySet();
			for(String key:keys){
				pf.put(key, entry.get(key));
			}
		}
		pf.flush();
	}
	/**
	 * 获取注册表中的信息
	 * @return
	 * @throws BackingStoreException 
	 */
	public Map<String,String> getRecord() throws BackingStoreException{
		Preferences pf=Preferences.userNodeForPackage(RegisterUtil.class);
		String[] keys=pf.keys();
		Map<String,String> map=new HashMap<String,String>();
		if(keys!=null&&keys.length>0){
			for(String key:keys){
				map.put(key, pf.get(key, ""));
			}
			
		}
		return map;
	}
	/**
	 * 删除注册表中的信息
	 * @param key
	 */
	
	public void removeRecord(String key){
		Preferences pf=Preferences.userNodeForPackage(RegisterUtil.class);
		pf.remove(key);
	}
	
	
	
	

}
