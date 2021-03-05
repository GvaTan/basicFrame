package com.zhys.pool;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;
import com.zhys.util.EnvironmentPro;
import com.zhys.util.MyDestinationDataProvider;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
@Log
public class JCoDestinationPool {
    @Value("${httpclient.dev_or_prod}")
    private String dev_or_prod;


    MyDestinationDataProvider myProvider = MyDestinationDataProvider.getInstance();
    JCoDestination destination  ;
    Properties connectProperties2 = new Properties();
    String destinationName2 = "ABAP_AS2";
//    此方法会在构造方法之后执行
    @PostConstruct
    public void init(){
        Environment.registerDestinationDataProvider(myProvider);
        // Register the MyDestinationDataProvider 环境注册


        System.out.println("Test destination - " + destinationName2);
        log.info("");

        if(StringUtils.isNotEmpty(dev_or_prod)&&dev_or_prod.equals("prod")){
            log.info("SAP正式环境》》》》》》》》》》》》》");
            connectProperties2.setProperty(DestinationDataProvider.JCO_ASHOST,

                    "saps401.tuya-inc.com");

            connectProperties2.setProperty(DestinationDataProvider.JCO_SYSNR, "00");

            connectProperties2

                    .setProperty(DestinationDataProvider.JCO_CLIENT, "800");

            connectProperties2.setProperty(DestinationDataProvider.JCO_USER,

                    "RFCUSER");

            connectProperties2.setProperty(DestinationDataProvider.JCO_PASSWD,

                    "Welcome1");
        }else{
            log.info("SAP测试环境》》》》》》》》》》》》》");
            connectProperties2.setProperty(DestinationDataProvider.JCO_ASHOST,

                    "192.168.0.10");

            connectProperties2.setProperty(DestinationDataProvider.JCO_SYSNR, "00");

            connectProperties2

                    .setProperty(DestinationDataProvider.JCO_CLIENT, "200");

            connectProperties2.setProperty(DestinationDataProvider.JCO_USER,

                    "ZJINSHUI02");

            connectProperties2.setProperty(DestinationDataProvider.JCO_PASSWD,

                    "Aa123456");
        }




        connectProperties2.setProperty(DestinationDataProvider.JCO_LANG, "ZH");

        connectProperties2.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,

                "10");

        connectProperties2.setProperty(

                DestinationDataProvider.JCO_POOL_CAPACITY, "3");



        // Add a destination

        myProvider.addDestination(destinationName2, connectProperties2);
    }

    {

    }

public JCoDestination  getJCoDestination(){

    try {
        JCoDestination DES_ABAP_AS2 = JCoDestinationManager

                .getDestination(destinationName2);
        DES_ABAP_AS2.ping();

        System.out.println("Destination - " + destinationName2 + " is ok");
        log.info("Destination - " + destinationName2 + " is ok");
        destination  =  DES_ABAP_AS2;
        return destination;
    } catch (Exception ex) {

        ex.printStackTrace();

        System.out.println("Destination - " + destinationName2

                + " is invalid");
        log.info("Destination - " + destinationName2

                + " is invalid"+ex.getMessage());
    }

    return  null;
}







}
