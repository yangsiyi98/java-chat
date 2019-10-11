package com.ysy.chat01;
/**
 * 在线聊天室
 * 服务端（第一版）
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat {
public static void main(String[] args) throws IOException {
	System.out.println("__server___");
	//1、指定端口  使用ServerSocket创建服务器
	ServerSocket server=new ServerSocket(8888);
	//2、阻塞式等待连接accept
	Socket client=server.accept();
	System.out.println("一个客户端建立了链接");
	
	//3、接受消息
	DataInputStream dis=new DataInputStream(client.getInputStream());
	String msg=dis.readUTF();
	//4、返回消息
	DataOutputStream dos=new DataOutputStream(client.getOutputStream());

	dos.writeUTF(msg);
	dos.flush();

	dos.close();
	dis.close();
	client.close();
}
}
