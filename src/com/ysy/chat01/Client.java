package com.ysy.chat01;

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
 * 客户端（第一版）目标：实现一个客户可以正常收发信息（仅发一条）
 */
public class Client {
public static void main(String[] args) throws UnknownHostException, IOException {
	System.out.println("__client___");
	
//	* 1、建立连接 使用Socket创建客户端+服务的地址和端口
	Socket client=new Socket("localhost",8888);
	//2、客户端发送消息（输出流）
	BufferedReader console=new BufferedReader(new InputStreamReader(System.in));
	String msg=console.readLine();
	DataOutputStream dos=new DataOutputStream(client.getOutputStream());

	dos.writeUTF(msg);
	dos.flush();
	//3、获取消息（输入流）
	DataInputStream dis=new DataInputStream(client.getInputStream());
	msg=dis.readUTF();
	System.out.println(msg);
	//4、释放资源
	dos.close();
	dis.close();
	client.close();
}
}
