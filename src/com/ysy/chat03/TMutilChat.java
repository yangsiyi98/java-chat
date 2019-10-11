package com.ysy.chat03;

/**
 * ����ˣ�����棩����װ
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TMutilChat {
public static void main(String[] args) throws IOException {
	System.out.println("__server___");
	//1��ָ���˿�  ʹ��ServerSocket����������
	ServerSocket server=new ServerSocket(8888);
	
//	 * 2������ʽ�ȴ�����accept
	while(true){
	Socket client=server.accept();
	System.out.println("һ���ͻ��˽���������");
	new Thread(new Channel(client)).start();
	
	
	}

	
}

//һ���ͻ�����һ��Channel
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
	//������Ϣ
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
	//������Ϣ
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
	//�ͷ���Դ
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
