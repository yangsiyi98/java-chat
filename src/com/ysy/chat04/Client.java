package com.ysy.chat04;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ����������
 * @author lenovo
 * �ͻ��ˣ���������ʵ��Ⱥ��
 */
public class Client {
public static void main(String[] args) throws UnknownHostException, IOException {
	System.out.println("__client___");
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	System.out.println("�������û���");//û�п�����������
	String name=br.readLine();
//	* 1���������� ʹ��Socket�����ͻ���+����ĵ�ַ�Ͷ˿�
	Socket client=new Socket("localhost",8888);
	
	//2���ͻ��˷�����Ϣ
	new Thread(new Send(client,name)).start();
	new Thread(new Receive(client)).start();
	
	}
}
