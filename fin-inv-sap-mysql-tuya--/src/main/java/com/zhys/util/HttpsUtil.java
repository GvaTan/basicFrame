package com.zhys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HttpsUtil {
    /**
     *
     * @param args 0 证书  1 密码  2 delete链接 3
     * @throws DocumentException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void main(String[] args) throws DocumentException, ClientProtocolException, IOException {


        String url="https://www.fapiao.com:63089/fpt-dsqz/invoice";
        String docXmlText ="{ \"interface\": { \"globalInfo\": { \n" +
                "\"appId\": \"ba76fd6c75e5415a4763eb6666d3508150cabc8e2d301fd17c7aeb3915fb14c9\",  \"interfaceId\": \"\",  \"interfaceCode\": \"DFXJ1004\",  \"requestCode\": \"DZFPQZ\",  \n" +
                "\"requestTime\": \"2020-01-16 23:24:17\",  \"responseCode\": \"DS\",  \n" +
                "\"dataExchangeId\": \"DZFPQZDFXJ100120200116645071417\"     },  \"returnStateInfo\": { \"returnCode\": \"\",  \"returnMessage\": \"\"     },  \"Data\": { \"dataDescription\": { \"zipCode\": \"0\"         },  \n" +
                "\"content\": \"eyAKIlJFUVVFU1RfQ09NTU9OX0ZQQ1giOiB7ICJGUFFRTFNIIjogIjAwMDAwMDAwMjAxMDEiLCAiWFNGX05TUlNCSCI6ICI5MTMzMDI4MjY3MTIwNTI0MlkiIH0gCn0=\", \n" +
                "\"contentKey\": \"kgQEjqAVMQEJX9SgUR4Vk/gGhXkt5fRGpoXva8+QYd+nd4Xb+nGjPdInpCCDdx7v\"     } } }";
        String cerPath="D:\\gnjt.jks";
        String passWord="05242Y";
//        String deleteId=null;
//        /*
//         * Get方式
//         */
//        HttpGet httpGet=getHttpGet(url);//创建HttpGet对象
//        String bodyGet = send(url, "utf-8",httpGet,cerPath,passWord);
//        System.out.println(bodyGet);        //打印post response

        /*
         * Post方式带参数
         */
        HttpPost httpPost=getHttpPostJson(url, docXmlText);//创建HttpPost对象
        String bodyPost = send(url, "utf-8",httpPost,cerPath,passWord);    //执行HttpPost请求
        System.out.println(bodyPost);       //打印post response

        /*
         * Delete方式
         */
//        HttpDelete httpDelete=getHttpDelete(url+"/这里填DeleteId"); //创建HTTPDelete 对象
//        String bodyDelete = send(args[2], "utf-8",httpDelete,args[0],args[1]);  //执行HttpDelete请求
//        System.out.println(bodyDelete);    //打印delete response



    }

    /**
     *
     * @param url Xml文件路径
     * @return Xml 文件字符串
     * @throws DocumentException
     */
    public static String getXml(String url) throws DocumentException{
        SAXReader reader = new SAXReader();
        // 读取一个文件，把这个文件转成Document对象
        Document document = reader.read(new File(url));
        // 获取根元素
        Element root = document.getRootElement();
        // 把文档转成字符串
        String docXmlText = document.asXML();
        return docXmlText;
    }
    /**
     * 证书验证
     * @param keyStorePath 生成keyStore的路径
     * @param keyStorepass 对应keyStore的密码
     * @return
     */
    public static SSLContext custom(String keyStorePath, String keyStorepass){
        SSLContext sc = null;
        FileInputStream instream = null;
        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            instream = new FileInputStream(new File(keyStorePath));
            trustStore.load(instream, keyStorepass.toCharArray());
            // 相信自己的CA和所有自签证书
            sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
            }
        }
        return sc;
    }

    /**
     *
     * @param   url 进行Get请求的地址
     * @return  返回HttpGet
     */
    public static HttpGet getHttpGet(String url){
        HttpGet httpGet = new HttpGet(url);
        return httpGet;
    }

    /**
     *
     * @param url 进行Post请求的地址
     * @param Xml 传输的Xml文件对应的字符串
     * @return 返回HttpPost
     */
    public static HttpPost getHttpPostJson(String url,String Xml){
        HttpPost httpPost = new HttpPost(url);
        HttpEntity requestEntity = new StringEntity(Xml, "UTF-8");  //设置请求体
        httpPost.setEntity(requestEntity);
        httpPost.addHeader("Content-Type", "application/json");     //设置请求头
        return httpPost;
    }
    /**
     *
     * @param url 进行Delete请求的地址
     * @param id Delete删除的Id
     * @return 返回HttpDelete
     */
    public static HttpDelete getHttpDeleteId(String url,String id){
        HttpDelete httpDelete=new HttpDelete(url+"/"+id);
        return httpDelete;
    }
    public static HttpDelete getHttpDelete(String url){
        HttpDelete httpDelete=new HttpDelete(url);
        return httpDelete;
    }
    public static String send(String url, String encoding,HttpUriRequest method,String cerPath,String cerPassword) throws ClientProtocolException, IOException {

        String body = "";
        SSLContext sslcontext = custom(cerPath, cerPassword);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext)).build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
        CloseableHttpResponse response = client.execute(method);      //执行相应方法请求

        System.err.println(method.toString().split("http")[0]+response.getStatusLine().getStatusCode()); //获取响应状态码
        HttpEntity entity = response.getEntity();                     //获取响应实体

        if (entity != null) {
            body = EntityUtils.toString(entity, encoding);             //实体转字符串
        }

        // EntityUtils.consume(entity);

        response.close();
        return body;
    }
}

