package com.ysy.chat05;

import java.io.Closeable;

/**
 * ������
 * @author lenovo
 *
 */
public class Utils {
/**
 * �ͷ���Դ
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
