package com.ysy.chat03;

/**
 * 服务端（第五版）：封装
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TMutilChat {
public static void main(String[] args) throws IOException {
	System.out.println("__server___");
	//1、指定端口  使用ServerSocket创建服务器
	ServerSocket server=new ServerSocket(8888);
	
//	 * 2、阻塞式等待连接accept
	while(true){
	Socket client=server.accept();
	System.out.println("一个客户端建立了链接");
	new Thread(new Channel(client)).start();
	
	
	}

	
}

//一个客户代表一个Channel
static class Channel implements Runnable{
	private DataOutputStream dos;
	private DataInputStream dis;
	private Socket client;
	private boolean isRunning;
	public Channel(Socket client){
		this.client=client;
		try {
			dis = new DataInputStream(client.getInputStream());
			dos=new DataOutputStream(client.getOutputStream());
			isRunning=true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("-----1-----");
			release();
		}
	}
	//接受消息
	private String receive()
	{
		String msg="";
		try {
			msg=dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("-----2-----");
			release();
		}
		return msg;
		
	}
	//发送消息
	private void send(String msg)
	{
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("-----3-----");
			release();
		}
	}
	//释放资源
	private void release()
	{
		this.isRunning=false;
		Utils.close(dis,dos,client);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning)
		{
			String msg=receive();
			if(!msg.equals(""))
			{
				send(msg);
			}
		}
	}
}

}
