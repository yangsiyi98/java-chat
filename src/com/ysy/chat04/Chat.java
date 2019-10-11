package com.ysy.chat04;

/**
 * �ͻ��ˣ���װ
 * ��������ʵ��Ⱥ��
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Chat {
	private static CopyOnWriteArrayList<Channel> all=new CopyOnWriteArrayList<Channel>();//���������޸ı߱���
	public static void main(String[] args) throws IOException {
	System.out.println("__server___");
	//1��ָ���˿�  ʹ��ServerSocket����������
	ServerSocket server=new ServerSocket(8888);
	
//	 * 2������ʽ�ȴ�����accept
	while(true){
	Socket client=server.accept();
	System.out.println("һ���ͻ��˽���������");
	Channel c=new Channel(client);
	all.add(c);//�����Ա
	new Thread(c).start();
	
	
	}

	
}
	//һ���ͻ�����һ��Channel
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
			
			this.send("��ӭ��ĵ���");
			sendOthers(this.name+"������������",true);
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
	
	//���͸�������
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
				other.send(this.name+"��������˵��"+msg);//Ⱥ��
			}else
			{
				other.send(msg);//ϵͳ��Ϣ
			}
		}
	}
	//�ͷ���Դ
	private void release()
	{
		this.isRunning=false;
		Utils.close(dis,dos,client);
		all.remove(this);
		sendOthers(this.name+"�뿪��������", true);
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
