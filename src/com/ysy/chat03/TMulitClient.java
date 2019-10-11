package com.ysy.chat03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 在线聊天室
 * 客户端（第五版）
 * @author lenovo
 *
 */
public class TMulitClient {
public static void main(String[] args) throws UnknownHostException, IOException {
	System.out.println("__client___");
	
//	* 1、建立连接 使用Socket创建客户端+服务的地址和端口
	Socket client=new Socket("localhost",8888);
	
	//2、客户端发送消息
	new Thread(new Send(client)).start();
	new Thread(new Receive(client)).start();
	
	}
}
