package com.geekstyle.gamerecord.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 读取constants.properties的工具类,可以通过这个类来读取系统预定义的一些常量
 * 
 * @author Administrator
 * 
 */
public class ConstantsUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(ConstantsUtil.class);

	private static Properties constants = new Properties();
	
	static {
		InputStream in = ConstantsUtil.class.getClassLoader().getResourceAsStream("constants.properties");
		try {
			constants.load(in);
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public static String getConstant(String key) {
		return constants.getProperty(key);
	}

}