package main;

import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

import ClientNet.ClientThread;
import view.EchoView;

public class mainClass {
	public static void main(String[] args) {
		try {
			Socket clientSocket = new Socket("127.0.0.1",9000);
			// 클라이언트 소켓을 생성해서 명시한 ip에 서버에 connect()를 보냄.
			System.out.println("서버에 접속했습니다.");
			
			EchoView ev = new EchoView(clientSocket);
			// 에코채팅ui에게 소켓을 매개변수로 보내고 ui를 실행.
			new ClientThread(ev).start();
			// 다른 클라이언트에게 메세지를 받기위해서 스레드를 실행, 그리고 받으면 ui의 textarea에게 보내기 위해서 EchoView를 매개변수로 넣어줌.
			
		} catch (UnknownHostException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}	
	}
}