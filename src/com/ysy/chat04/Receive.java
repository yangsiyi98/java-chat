package com.ysy.chat04;
/**
 * 使用多线程封装接收端
 * 读写分离
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
	private Socket client;
	private DataInputStream dis;
	private boolean isRunning=true;
	public Receive( Socket client){
	try {
		dis=new DataInputStream(client.getInputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("====2====");
		e.printStackTrace();
	}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning)
		{
			String msg=receive();
			if(!msg.equals(""))
			{
				System.out.println(msg);
			}
		}
	}
	private String receive()
	{
		String msg="";
		try {
			msg=dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("-----4-----");
			release();
		}
		return msg;
		
	}
	private void release()
	{
		this.isRunning=false;
		Utils.close(dis,client);
	}
}
