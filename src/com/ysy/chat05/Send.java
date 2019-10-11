package com.ysy.chat05;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * 封装发送端
 * 读写分离
 * @author lenovo
 *
 */
public class Send implements Runnable{
	private Socket client;
	private BufferedReader console;
	private DataOutputStream dos;
//	private DataInputStream dis;
	private boolean isRunning=true;
	private String name;
	public Send(Socket client,String name)
	{
		this.client=client;
		this.name=name;
		console=new BufferedReader(new InputStreamReader(System.in));
		try {
			dos=new DataOutputStream(client.getOutputStream());
			//发送名字
			send(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("===2===");
			this.release();
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning)
		{
			String msg=getStrFromConsole();
			if(!msg.equals(""))
			{
				send(msg);
			}
		}
	}
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
	/**
	 * 从控制台获取消息
	 * @return
	 */
	private String getStrFromConsole()
	{
		try{
			return console.readLine();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	private void release()
	{
		this.isRunning=false;
		Utils.close(dos,client);
	}
}
