package com.ysy.chat01;

/**
 * ����������
 * @author lenovo
 * ����ˣ��ڶ��棩
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MutilChat {
public static void main(String[] args) throws IOException {
	System.out.println("__server___");
	//1��ָ���˿�  ʹ��ServerSocket����������
	ServerSocket server=new ServerSocket(8888);
//	 * 2������ʽ�ȴ�����accept
	Socket client=server.accept();
	System.out.println("һ���ͻ��˽���������");
	
	//3��������Ϣ
	DataInputStream dis=new DataInputStream(client.getInputStream());
	DataOutputStream dos=new DataOutputStream(client.getOutputStream());
	boolean isRunning=true;
	//ͬ�ͻ���
	while(isRunning){
		String msg=dis.readUTF();
		//4��������Ϣ
		dos.writeUTF(msg);
		dos.flush();
	}

	dos.close();
	dis.close();
	client.close();
}
}
