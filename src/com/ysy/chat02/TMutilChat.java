package com.ysy.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 在线聊天室
 * @author lenovo
 * 服务端（第四版）
 */
public class TMutilChat {
public static void main(String[] args) throws IOException {
	System.out.println("__server___");
	//1、指定端口  使用ServerSocket创建服务器
	ServerSocket server=new ServerSocket(8888);
//	 * 2、阻塞式等待连接accept
	while(true){
	Socket client=server.accept();
	System.out.println("一个客户端建立了链接");
	
	new Thread(()->{
		DataOutputStream dos=null;
		DataInputStream dis=null;
		try {
			dis = new DataInputStream(client.getInputStream());
			dos=new DataOutputStream(client.getOutputStream());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean isRunning=true;
		while(isRunning){
		String msg;
		try {
			msg = dis.readUTF();
			dos.writeUTF(msg);
			dos.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			isRunning=false;//停止线程
		}
		//4、返回消息
		
		}
		try {
			if(null!=dos)
			{
				dos.close();
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(null!=dis)
			{
				dis.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(null!=client)
			{
				client.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}).start();
	//3、接受消息
	
	
	}

	
}
}
