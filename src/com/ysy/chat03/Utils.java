package com.ysy.chat03;

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
	public static void close(Closeable...targets)//可传不定参数
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
