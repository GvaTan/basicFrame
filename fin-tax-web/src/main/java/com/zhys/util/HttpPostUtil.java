/**
 * Project Name:backgroundManagementSystem
 * File Name:HttpPostUtil.java
 * Package Name:com.micropower.common
 * Date:Jun 17, 20164:51:41 PM
 * Copyright (c) 2016, 1173499611@qq.com All Rights Reserved.
 *
*/

package com.zhys.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * ClassName:HttpPostUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     Jun 17, 2016 4:51:41 PM <br/>
 * @author   lihui
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Slf4j
public class HttpPostUtil {
	
	public static boolean post(String params, String url) {

		StringBuffer bufferRes = new StringBuffer();

		try {

			URL realUrl = new URL(
					url);

			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();

			// 连接超时

			conn.setConnectTimeout(25000);

			// 读取超时 --服务器响应比较慢,增大时间

			conn.setReadTimeout(25000);

			HttpURLConnection.setFollowRedirects(true);

			// 请求方式

			conn.setRequestMethod("POST");
          
			conn.setDoOutput(true);

			conn.setDoInput(true);

			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");

			conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");

			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			conn.connect();

			// 获取URLConnection对象对应的输出流

			OutputStreamWriter out = new OutputStreamWriter(conn
					.getOutputStream(), "UTF-8");

			// 发送请求参数

			//out.write(URLEncoder.encode(params,"GBK"));
            log.info("请求参数：{}",params);
			out.write(params);

			out.flush();

			out.close();

			InputStream in = conn.getInputStream();

			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));

			String valueString = null;

			while ((valueString = read.readLine()) != null) {

				bufferRes.append(valueString);

			}

			System.out.println("返回："+bufferRes.toString());
			log.info("返回："+bufferRes.toString());
			in.close();

			if (conn != null) {

				// 关闭连接

				conn.disconnect();

			}
			JSONObject jsonObject = JSONObject.parseObject(bufferRes.toString());


			return jsonObject.getBoolean("isSuccess");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("异常："+e.getMessage());
			log.error("异常："+e.getMessage());
			return false;

		}

	}
	
	public static void main(String[] args) {
	}

}

