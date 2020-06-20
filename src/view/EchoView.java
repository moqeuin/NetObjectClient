package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ClientNet.WriteClass;
import data.SingletonClass;

public class EchoView extends JFrame implements ActionListener{
	
	public Socket socket;
	// 생성한 클라이언트 소켓을 저장하기 위한 변수.
	WriteClass wc;
	// 서버에게 소켓을 보내기위한 클래스.
	public Boolean id_input = false;
	// 처음 실행했다면 idlabel에게 idview에서 등록한 id를 입력하기 위해서 false로 대입. 입력했다면 true.
	
	JLabel label;
	public JLabel idlabel;
	JButton sendBtn;
	JButton exitBtn;	
	public JTextArea recv_txt;	
	JPanel panel1;
	JPanel panel2;
	
	
	public 	EchoView(Socket socket) {
		
		this.socket = socket;
		wc = new WriteClass(this);
		//  해당 ui와 ui의 소켓을 이용해서 서버에게 소켓을 송신.
		new idView(wc, this);
		// idview창에서 writeclass의 send_echo메소드를 사용하기위해 매개변수로 넣어줌.
		
		// 에코채팅 ui
		label = new JLabel("에코채팅");
		label.setBackground(Color.orange);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		idlabel = new JLabel();
		idlabel.setBackground(Color.white);
		idlabel.setOpaque(true);
		idlabel.setHorizontalAlignment(JLabel.CENTER);
		sendBtn = new JButton("보내기");
		sendBtn.addActionListener(this);
		exitBtn = new JButton("나가기");
		exitBtn.addActionListener(this);	
		recv_txt = new JTextArea(30,30);
			
		JScrollPane sp = new JScrollPane(recv_txt);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(label,BorderLayout.NORTH);
		panel1.add(sp,BorderLayout.CENTER);
				
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.white);
		panel2.add(idlabel);
		panel2.add(sendBtn);
		panel2.add(exitBtn);
				
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(panel1,BorderLayout.CENTER);
		c.add(panel2,BorderLayout.SOUTH);
				
		setBounds(600, 200, 500, 600);
		setVisible(false);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==sendBtn) {
			
			SingletonClass sc = SingletonClass.getInstance();
			// 싱글턴에서 저장한 객체를 불러주기위해 객체를 불러옴.
			if(sc.nd.getNumber()==sc.nd.getTurn()) {
				// 객체의 저장한 멤버변수 넘버와 턴의 수가 같다면 메세지 송신 권한 인정.
				wc.send_echo();
				// 상대방에게 메세지를 서버를 통해서 송신.
			}
			else JOptionPane.showMessageDialog(null, "상대의 차례를 기다리십시오.");			
		}
		if(obj==exitBtn) {
			System.exit(0);
			// 나가기 버튼을 누르면 종료.
		}		
	}
}
