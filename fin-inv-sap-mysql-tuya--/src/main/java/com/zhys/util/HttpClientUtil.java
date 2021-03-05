package com.zhys.util;

import lombok.Getter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * 利用HttpClient进行post请求的工具类
 * @ClassName: HttpClientUtil
 * @Description: TODO
 * @author Devin <xxx>
 * @date 2017年2月7日 下午1:43:38
 *
 */
@Component
@Getter
@Slf4j
public class HttpClientUtil {

//    private static String pfx_url;
//    @SuppressWarnings("resource")
//    public static String doPost(String url,String jsonstr,String charset){
//        HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try{
//
//            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            FileInputStream instream = new FileInputStream(new File(pfx_url));//加载本地的证书进行https加密传输
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
//            log.error("请求失败",ex);
//        }
//        return result;
//    }
//
//    public static void main(String[] args){
//        String url = "https://www.fapiao.com:63089/fpt-dsqz/invoice";
//        String jsonStr = "{ \"interface\": { \"globalInfo\": { \n" +
//                "\"appId\": \"ba76fd6c75e5415a4763eb6666d3508150cabc8e2d301fd17c7aeb3915fb14c9\",  \"interfaceId\": \"\",  \"interfaceCode\": \"DFXJ1004\",  \"requestCode\": \"DZFPQZ\",  \n" +
//                "\"requestTime\": \"2020-01-16 23:31:06\",  \"responseCode\": \"DS\",  \n" +
//                "\"dataExchangeId\": \"DZFPQZDFXJ100120200116861185439\"     },  \"returnStateInfo\": { \"returnCode\": \"\",  \"returnMessage\": \"\"     },  \"Data\": { \"dataDescription\": { \"zipCode\": \"0\"         },  \n" +
//                "\"content\": \"eyAKIlJFUVVFU1RfQ09NTU9OX0ZQQ1giOiB7ICJGUFFRTFNIIjogIjAwMDAwMDAwMjAxMDEiLCAiWFNGX05TUlNCSCI6ICI5MTMzMDI4MjY3MTIwNTI0MlkiIH0gCn0=\", \n" +
//                "\"contentKey\": \"kgQEjqAVMQEJX9SgUR4Vk/gGhXkt5fRGpoXva8+QYd+nd4Xb+nGjPdInpCCDdx7v\"     } } }";
//        String httpOrgCreateTestRtn = HttpClientUtil.doPost(url, jsonStr, "utf-8");
//        System.out.println(httpOrgCreateTestRtn);
//    }
//    @Value("${httpclient.fpx_url}")
//    public  void setPfx_url(String pfx_url) {
//        HttpClientUtil.pfx_url = pfx_url;
//    }
}
