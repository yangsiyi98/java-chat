package com.ysy.chat03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * ���Ͷ�
 * ��д����
 * @author lenovo
 *
 */
public class Send implements Runnable{
	private Socket client;
	private BufferedReader console;
	private DataOutputStream dos;
//	private DataInputStream dis;
	private boolean isRunning=true;
	public Send(Socket client)
	{
		this.client=client;
		console=new BufferedReader(new InputStreamReader(System.in));
		try {
			dos=new DataOutputStream(client.getOutputStream());
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
	 * �ӿ���̨��ȡ��Ϣ
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
