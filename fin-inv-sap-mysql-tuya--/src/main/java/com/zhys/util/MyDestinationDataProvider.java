package com.zhys.util;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import java.util.HashMap;
import java.util.Properties;

public class MyDestinationDataProvider  implements DestinationDataProvider {
    private DestinationDataEventListener eL;



    private HashMap<String, Properties> destinations;



    private static MyDestinationDataProvider provider = new MyDestinationDataProvider();



    private MyDestinationDataProvider() {// 单例模式

        if (provider == null) {

            destinations = new HashMap<String, Properties>();

        }

    }



    public static MyDestinationDataProvider getInstance() {

        return provider;

    }



    // 实现接口：获取连接配置属性

    public Properties getDestinationProperties(String destinationName) {

        if (destinations.containsKey(destinationName)) {

            return destinations.get(destinationName);

        } else {

            throw new RuntimeException("Destination " + destinationName

                    + " is not available");

        }

    }



    public void setDestinationDataEventListener(DestinationDataEventListener eventListener) {

        this.eL = eventListener;

    }



    public boolean supportsEvents() {

        return true;

    }



    /**

     * Add new destination 添加连接配置属性

     *

     * @param properties

     *            holds all the required data for a destination

     **/

    public void addDestination(String destinationName, Properties properties) {

        synchronized (destinations) {

            destinations.put(destinationName, properties);

        }

    }
}
