package com.ysy.chat03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ����������
 * �ͻ��ˣ�����棩
 * @author lenovo
 *
 */
public class TMulitClient {
public static void main(String[] args) throws UnknownHostException, IOException {
	System.out.println("__client___");
	
//	* 1���������� ʹ��Socket�����ͻ���+����ĵ�ַ�Ͷ˿�
	Socket client=new Socket("localhost",8888);
	
	//2���ͻ��˷�����Ϣ
	new Thread(new Send(client)).start();
	new Thread(new Receive(client)).start();
	
	}
}
