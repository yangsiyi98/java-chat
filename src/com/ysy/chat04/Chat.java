package com.ysy.chat04;

/**
 * 客户端：封装
 * 加入容器实现群聊
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Chat {
	private static CopyOnWriteArrayList<Channel> all=new CopyOnWriteArrayList<Channel>();//容器，边修改边遍历
	public static void main(String[] args) throws IOException {
	System.out.println("__server___");
	//1、指定端口  使用ServerSocket创建服务器
	ServerSocket server=new ServerSocket(8888);
	
//	 * 2、阻塞式等待连接accept
	while(true){
	Socket client=server.accept();
	System.out.println("一个客户端建立了链接");
	Channel c=new Channel(client);
	all.add(c);//管理成员
	new Thread(c).start();
	
	
	}

	
}
	//一个客户代表一个Channel
static class Channel implements Runnable{
	private DataOutputStream dos;
	private DataInputStream dis;
	private Socket client;
	private boolean isRunning;
	private String name;
	public Channel(Socket client){
		this.client=client;
		try {
			dis = new DataInputStream(client.getInputStream());
			dos=new DataOutputStream(client.getOutputStream());
			isRunning=true;
			this.name=receive();
			
			this.send("欢迎你的到来");
			sendOthers(this.name+"来到了聊天室",true);
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
	
	//发送给其他人
	private void sendOthers(String msg,boolean isSys)
	{
		for(Channel other:all)
		{
			if(other==this)
			{
				continue;
			}
			if(!isSys)
			{
				other.send(this.name+"对所有人说："+msg);//群聊
			}else
			{
				other.send(msg);//系统消息
			}
		}
	}
	//释放资源
	private void release()
	{
		this.isRunning=false;
		Utils.close(dis,dos,client);
		all.remove(this);
		sendOthers(this.name+"离开了聊天室", true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning)
		{
			String msg=receive();
			if(!msg.equals(""))
			{
				//send(msg);
				sendOthers(msg,false);
			}
		}
	}
}

}
