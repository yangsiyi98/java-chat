package com.ysy.chat02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 在线聊天室
 * @author lenovo
 * 客户端（第四版）目标：实现多个客户可以正常发多条信息
 * 问题：其他客户必须等待之前的客户退出才可使用
 * 解决办法：加入多线程
 * 新问题：对象过多，后期维护不便
 */
public class TMulitClient {
public static void main(String[] args) throws UnknownHostException, IOException {
	System.out.println("__client___");
	
//	* 1、建立连接 使用Socket创建客户端+服务的地址和端口
	Socket client=new Socket("localhost",8888);
	//2、客户端发送消息
	BufferedReader console=new BufferedReader(new InputStreamReader(System.in));
	DataOutputStream dos=new DataOutputStream(client.getOutputStream());
	DataInputStream dis=new DataInputStream(client.getInputStream());
	boolean isRunning=true;
	while(isRunning)
	{
		String msg=console.readLine();
		dos.writeUTF(msg);
		dos.flush();
		//3、获取消息

		msg=dis.readUTF();
		System.out.println(msg);
	}

	dos.close();
	dis.close();
	client.close();
}
}
