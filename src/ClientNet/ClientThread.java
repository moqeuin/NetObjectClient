package ClientNet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import data.SingletonClass;
import dto.NetData;
import view.EchoView;

public class ClientThread extends Thread{
	
	Socket socket;
	EchoView ev;
	NetData nd2;
	
	public ClientThread(EchoView ev) {
			while(true) {
				this.ev = ev;
				this.socket = ev.socket;
				try {
					
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					SingletonClass sc = SingletonClass.getInstance();
					nd2 = (NetData)ois.readObject();
					// 서버로부터 수신한 소켓의 객체를 저장하기위해서 싱글턴의 객체를 불러온 후에 그 객체에 저장.
					sc.nd = nd2;
					ev.recv_txt.append(nd2.toString()+"\n");
					// 수신한 객체의 멤버변수 정보를 에코채팅창에 출력.				
				}catch (ClassNotFoundException e) {
					if(nd2==null) {
						System.out.println("접속이 끊겼습니다.");
						break;
					}
				} catch (IOException e) {	
					System.out.println(e.getMessage());
				  }
			}		
	}
}