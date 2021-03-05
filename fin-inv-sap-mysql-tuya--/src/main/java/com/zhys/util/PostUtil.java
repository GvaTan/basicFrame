package com.zhys.util;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;


@Slf4j
public class PostUtil {
	
	public static String post(String params, String url) {

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
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("contentType", "UTF-8");
			conn.connect();

			// 获取URLConnection对象对应的输出流

			OutputStreamWriter out = new OutputStreamWriter(conn
					.getOutputStream(), "UTF-8");

			// 发送请求参数

			//out.write(URLEncoder.encode(params,"GBK"));

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
			in.close();

			if (conn != null) {

				// 关闭连接

				conn.disconnect();

			}
			
			return bufferRes.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "no";

		}

	}


	/**
	 * post请求封装 参数为?a=1&b=2&c=3
	 * @param path 接口地址
	 * @param Info 参数
	 * @return
	 * @throws IOException
	 */
	public static JSONObject postResponse(String path,String Info) throws IOException{

		//1, 得到URL对象
		URL url = new URL(path);

		//2, 打开连接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		//3, 设置提交类型
		conn.setRequestMethod("POST");

		//4, 设置允许写出数据,默认是不允许 false
		conn.setDoOutput(true);
		conn.setDoInput(true);//当前的连接可以从服务器读取内容, 默认是true

		//5, 获取向服务器写出数据的流
		OutputStream os = conn.getOutputStream();
		//参数是键值队  , 不以"?"开始
		os.write(Info.getBytes());
		//os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
		os.flush();
		//6, 获取响应的数据
		//得到服务器写回的响应数据
		BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		String str = br.readLine();
		JSONObject json = JSONObject.parseObject(str);

		System.out.println("响应内容为:  " + json);

		return  json;
	}
	/**
	 * post请求封装 参数为{"a":1,"b":2,"c":3}
	 * @param path 接口地址
	 * @param Info 参数
	 * @return
	 * @throws IOException
	 */
	public static JSONObject postResponse(String path, String Info,String contenType) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(path);

		post.setHeader("Content-Type", contenType);
		String result = "";

		try {
			StringEntity s = new StringEntity(Info, "utf-8");
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,contenType));
			post.setEntity(s);
			RequestConfig requestConfig =  RequestConfig.custom().setSocketTimeout(600000).setConnectTimeout(600000).build();
			post.setConfig(requestConfig);
			// 发送请求
			HttpResponse httpResponse = client.execute(post);

			// 获取响应输入流
			InputStream inStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
			StringBuilder strber = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
				strber.append(line + "\n");
			inStream.close();

			result = strber.toString();
			System.out.println(result);

			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("请求服务器成功，做相应处理");
			} else {
				log.error("请求服务端失败");
			}

		} catch (Exception e) {
			log.error("请求异常"+e.getMessage());
			throw new RuntimeException(e);
		}

		return JSONObject.parseObject(result);
	}


	
public static void main(String[] args) {
	
	post("","https://jinbao.pinduoduo.com/network/api/common/queryTopGoodsList");
	}


}
