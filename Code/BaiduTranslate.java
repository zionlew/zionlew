package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 2015-04-13 22:25:51
 * @author LH
 * 使用百度翻译接口进行翻译
 */
public class BaiduTranslate {

	//My Baidu Develop API Key
	public String API_KEY = "wQtCiFGncbfSYS0T685xzQCy";
	
	private String BaiduAddr = "http://openapi.baidu.com/public/2.0/bmt/translate?";
//	private String BaiduAddr = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=YourApiKey&q=today&from=auto&to=auto";

	public void Translate(String key) {
	
		try {
			key = URLEncoder.encode(key, "utf-8");
			String urlAddr = BaiduAddr+"client_id=" + API_KEY + "&q=" + key + "&from=en&to=zh";
			
			URL url = new URL(urlAddr);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader bufread = new BufferedReader(in);  
			StringBuffer buff = new StringBuffer();  
			String line;  
			while ((line = bufread.readLine()) != null) {  
			    buff.append(line);  
			}  
			// 对字符进行解码  
			String back = new String(buff.toString().getBytes("ISO-8859-1"),  
			        "UTF-8");  
			JsonToString(back);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
    private void JsonToString(String jstring) {  
		JSONObject a = 	JSONObject.fromObject(jstring);
		JSONArray arr = (JSONArray) a.get("trans_result");
		JSONObject b = JSONObject.fromObject(arr.get(0));
		System.out.println("result: " +b.get("dst"));

   
    }  
    
    
  
}
