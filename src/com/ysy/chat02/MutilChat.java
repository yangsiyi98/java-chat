package com.ysy.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * ����������
 * @author lenovo
 * ����ˣ������棩
 */
public class MutilChat {
public static void main(String[] args) throws IOException {
	System.out.println("__server___");
	//1��ָ���˿�  ʹ��ServerSocket����������
	ServerSocket server=new ServerSocket(8888);
//	 * 2������ʽ�ȴ�����accept
	while(true){
	Socket client=server.accept();
	System.out.println("һ���ͻ��˽���������");
	
	//3��������Ϣ
	DataInputStream dis=new DataInputStream(client.getInputStream());
	DataOutputStream dos=new DataOutputStream(client.getOutputStream());
	boolean isRunning=true;
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
}
