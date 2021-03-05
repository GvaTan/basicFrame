package com.zhys.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
public class CheckSample {
    static String appId = "<your appid>";//由优识云创提供
    static String key = "<your key>";//由优识云创提供
    //checktest.fapiaoxx.com  测试环境地址
    //check.fapiaoxx.com  正式环境地址
    static String requestUrl = "http://checktest.fapiaoxx.com/invoice/standard/check";
    static String fpdm = "012001700111";//发票代码
    static String fphm = "66345924";//发票号码
    static String kprq = "20180501";//开票日期
    static String fpje = "0";//发票金额
    static String jym = "359533";//发票校验码
    static String sign_type = "md5";//签名方式
    static String version = "v1.1";//版本号
    public static void main2(String[] args) {
    	CheckSample checkSample = new CheckSample();
        // 初始化biz_content
        Biz_content biz_content = checkSample.new Biz_content(fphm,fpdm,kprq,fpje,jym);
        // 初始化originData
        OriginData originData = checkSample.new OriginData(appId,sign_type,JSONObject.toJSONString(biz_content),version);
        // 生成签名
        originData.setSign(getMD5Sign(originData));
        // 提交查验
        submitCheck(originData);
	}
    public static void main1(String[] args) {
        
    }
    public static String submitCheck(OriginData originData){
        //result 为请求返回结果
        String result= "";
        try {
            String requestParam = JSONObject.toJSONString(originData);
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(requestUrl);// 创建httpPost
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000).setConnectionRequestTimeout(10000)
                    .setSocketTimeout(10000).build();//设置连接超时时间
            httpPost.setConfig(requestConfig);
            String charSet = "UTF-8";
            StringEntity entity = new StringEntity(requestParam, charSet);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = null;
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                result= EntityUtils.toString(responseEntity);//result为请求返回结果
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getMD5Sign(OriginData originData){
        Map<String, Object> dataMap = new TreeMap<String, Object>();
        dataMap.put("appId", originData.getAppId());//优识提供
        dataMap.put("timestamp",originData.getTimestamp());//请求时间
        dataMap.put("sign_type", originData.getSign_type());//签名加密方式
        dataMap.put("biz_content", originData.getBiz_content());//查验请求请求参数
        dataMap.put("version", originData.getVersion());//订单号
        dataMap.put("method", originData.getMethod());//请求方式
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        builder.append(key);
        String md5Str="";
        try {
            //加密之后的签名值
            System.out.println(builder.toString());
            md5Str = DigestUtils.md5Hex(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }
    public class OriginData{
        private String  appId;//优识提供
        private String  timestamp;//发送请求的时间，格式例如 2017-07-20 11:07:50   2017-7-20 11:07
        private String  method = "POST";//冗余参数   可不传
        private String  sign_type;//商户生成签名字符串所使用的签名算法类型
        private String  biz_content;//值为json字符串格式，除公共请求参数外所有业务请求参数都必须放在这个参数中传递
        private String  version;//调用的接口版本，目前只支持: v1.0
        private String  sign;//商户请求参数的签名串
        public OriginData(String appId, String timestamp, String method, String sign_type, String biz_content, String version, String sign) {
            this.appId = appId;
            this.timestamp = timestamp;
            this.method = method;
            this.sign_type = sign_type;
            this.biz_content = biz_content;
            this.version = version;
            this.sign = sign;
        }
        public OriginData(String appId, String sign_type, String biz_content, String version) {
            this.appId = appId;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            this.timestamp = simpleDateFormat.format(new Date());
            this.sign_type = sign_type;
            this.biz_content = biz_content;
            this.version = version;
        }
        public String getAppId() {
            return appId;
        }
        public void setAppId(String appId) {
            this.appId = appId;
        }
        public String getTimestamp() {
            return timestamp;
        }
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
        public String getMethod() {
            return method;
        }
        public void setMethod(String method) {
            this.method = method;
        }
        public String getSign_type() { return sign_type; }
        public void setSign_type(String sign_type) { this.sign_type = sign_type; }
        public String getBiz_content() { return biz_content; }
        public void setBiz_content(String biz_content) { this.biz_content = biz_content; }
        public String getVersion() { return version; }
        public void setVersion(String version) { this.version = version; }
        public String getSign() { return sign; }
        public void setSign(String sign) { this.sign = sign; }
    }
    public class Biz_content {
        private String fphm;
        private String fpdm;
        private String kprq;
        private String fpje;
        private String jym;
        public Biz_content(String fphm, String fpdm, String kprq, String fpje, String jym) {
            this.fphm = fphm;
            this.fpdm = fpdm;
            this.kprq = kprq;
            this.fpje = fpje;
            this.jym = jym;
        }
        public String getFphm() { return fphm; }
        public void setFphm(String fphm) { this.fphm = fphm; }
        public String getFpdm() { return fpdm; }
        public void setFpdm(String fpdm) { this.fpdm = fpdm; }
        public String getKprq() { return kprq; }
        public void setKprq(String kprq) { this.kprq = kprq; }
        public String getFpje() { return fpje; }
        public void setFpje(String fpje) { this.fpje = fpje; }
        public String getJym() { return jym; }
        public void setJym(String jym) { this.jym = jym; }
    }
}
