package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ClientNet.WriteClass;

public class idView extends JFrame implements ActionListener{
	
	public static JTextField Id;
	JLabel label;
	JButton insertBtn;
	
	EchoView ev;
	WriteClass wc;
	
	public idView(WriteClass wc, EchoView ev) {
		
		this.wc = wc;
		this.ev = ev;
		
		label = new JLabel("아이디 입력 : ");
		Id = new JTextField(10);
		insertBtn = new JButton("로그인");
		insertBtn.addActionListener(this);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(label);
		c.add(Id);
		c.add(insertBtn);
		
		setBounds(800,300,290,100);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == insertBtn) {
			
			wc.send_echo();
			// WriteClass의 메소드로 넘어가서 서버에게 소켓 송신.
			ev.id_input = true;
			// id를 idlabel에게 입력했기 때문에 true로 전환.			
			ev.setVisible(true);
			// 에코채팅 ui 전환.
			this.dispose();
			// id UI의 창을 종료.
		}
		
	}
}
