package com.euphe.util.standardUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


/*
 * UserAgentHashMap用于识别目前的解析程序无法识别的UserAgent
 */
public class UserAgentHashMap {
	public static HashMap<String, String> category = new HashMap<String, String>();
	
	public static void init(String path,String separator) {
		//从path中读取UA-类型映射文件
		String tmpStr = "";
		String[] map = new String[2];
		File file = new File(path);
		BufferedReader reader = null;
		try{
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
			reader = new BufferedReader(isr);
			while((tmpStr = reader.readLine())!=null){
				map = tmpStr.split(separator);
				//将读取到的一条UA-类型记录，添加到category这个HashMap中map[0]是UA，map[1]是类型
				category.put(map[0], map[1]);
			}
		}
		catch(Exception e){
			//Init Error
		}
	}

	/*
	 * 通过域名关键词找出该记录属于的类型（社交，游戏，应用……）
	 */
	public static String findCategory(String ua) {
		String type = "";
		try {
			type = category.get(ua);
		} catch (Exception e) {
			// 关键字为空
			return null;
		}
		return type;
	}


}