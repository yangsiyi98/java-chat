package com.ysy.chat05;

import java.io.Closeable;

/**
 * 工具类
 * @author lenovo
 *
 */
public class Utils {
/**
 * 释放资源
 */
	public static void close(Closeable...targets)
	{
		for(Closeable target:targets)
		{
			try{
				if(null!=target)
				{
					target.close();
				}
			}catch(Exception e){
				
			}
		}
	}

}
