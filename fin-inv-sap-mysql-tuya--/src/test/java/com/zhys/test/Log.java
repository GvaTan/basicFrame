/**
 * 
 */
package com.zhys.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class Log {
    public static void main(String[] args) {
    	 final Logger logger = LoggerFactory.getLogger(Log.class);
    	 
    	 logger.info("从SAP获取信息开始》》》》》》》》》{}，{}，{}", new Object[] { "2222", "3333", "222" });
	}
}
