package ClientNet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.SingletonClass;
import dto.NetData;
import view.EchoView;
import view.idView;

public class WriteClass {
	
	EchoView ev;
	Socket socket;
	
	public WriteClass(EchoView ev) {
		this.ev = ev;
		socket = ev.socket;
	}
	
	public void send_echo() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			// 객체를 전송하기 때문에 objectstream로 받아옴.
			SingletonClass sc = SingletonClass.getInstance();
			// 싱글턴의 객체를 송신하기 위해 불러옴.
			NetData nd2 = sc.nd;
			
			if(!ev.id_input) {
				ev.idlabel.setText("ID : " + idView.Id.getText());
				// 처음 idiveiw에서 메소드를 호출했다면 idlabel의 제목을  textfield에서 채운 문자열로 저장.
			}
			String name = idView.Id.getText();
			nd2.setName(name);
			// idview의 textfield에서 채운 문자열을 객체에 저장.
			oos.writeObject(nd2);
			// 완성한 객체를 서버로 전송.
			oos.flush();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}		
	}
}
