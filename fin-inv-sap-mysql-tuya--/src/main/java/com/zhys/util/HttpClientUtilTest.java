//package com.zhys.util;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.params.CoreConnectionPNames;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.net.ssl.SSLContext;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.security.KeyStore;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//
///**
// * 利用HttpClient进行post请求的工具类
// * @ClassName: HttpClientUtil
// * @Description: TODO
// * @author Devin <xxx>
// * @date 2017年2月7日 下午1:43:38
// *
// */
//public class HttpClientUtilTest {
//
//    private static String pfx_url;
//    @SuppressWarnings("resource")
//    public static String doPost(String url,String jsonstr,String charset){
//        HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try{
//
//            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            FileInputStream instream = new FileInputStream(new File("d:/gnjt.pfx"));//加载本地的证书进行https加密传输
//            try {
//                keyStore.load(instream, "05242Y".toCharArray());//设置证书密码
//            } catch (CertificateException e) {
//                e.printStackTrace();
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } finally {
//                instream.close();
//            }
//
//            // Trust own CA and all self-signed certs
//            SSLContext sslcontext = SSLContexts.custom()
//                    .loadKeyMaterial(keyStore, "05242Y".toCharArray())
//                    .build();
//            // Allow TLSv1 protocol only
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                    sslcontext,
//                    new String[]{"TLSv1"},
//                    null,
//                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//            httpClient = HttpClients.custom()
//                    .setSSLSocketFactory(sslsf)
//                    .build();
//
//
//
//            //httpClient = new SSLClient();
//            httpPost = new HttpPost(url);
//            httpPost.addHeader("Content-Type", "application/json");
//            StringEntity se = new StringEntity(jsonstr);
//            se.setContentType("text/json");
//            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
//            httpPost.setEntity(se);
//            RequestConfig requestConfig =  RequestConfig.custom().setSocketTimeout(600000).setConnectTimeout(600000).build();
//            httpPost.setConfig(requestConfig);
//            HttpResponse response = httpClient.execute(httpPost);
//            if(response != null){
//                HttpEntity resEntity = response.getEntity();
//                if(resEntity != null){
//                    result = EntityUtils.toString(resEntity,charset);
//                }
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return result;
//    }
//
//    public static void main(String[] args){
//        String url = "http://localhost:8888/api/testtt";
//        String jsonStr = readString2();
//        jsonStr = JSONObject.toJSONString(jsonStr);
//        String httpOrgCreateTestRtn = HttpClientUtilTest.doPost(url, jsonStr, "utf-8");
//        System.out.println(httpOrgCreateTestRtn);
//    }
//
//    private static String readString2()
//    {
//        StringBuffer str=new StringBuffer("");
//        File file=new File("d://test.txt");
//        try {
//            FileReader fr=new FileReader(file);
//            int ch = 0;
//            while((ch = fr.read())!=-1 )
//            {
////                System.out.print((char)ch+" ");
//                str.append((char)ch);
//            }
//            fr.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.out.println("File reader出错");
//        }
//        return str.toString();
//    }
//}
