package br.ufrj.tp.chat.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class Chat implements ActionListener, KeyListener {
	private Client client;

	public static void main(String[] args) {
		Chat chat = new Chat();
		chat.openConnectionWindow();
	}

	private void openConnectionWindow() {
		JFrame window = new ConnectionWindow(this);
		window.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (window.getWidth() / 2),
			(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (window.getHeight() / 2));
		
		window.setVisible(true);
	}

	public void start(String clientId) throws UnknownHostException, IOException {
		client = new Client(clientId);
		client.start();

		openChatWindow(clientId);
	}

	private void openChatWindow(String clientId) {
		final JFrame window = new Window(clientId);
		window.setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (window.getWidth() / 2),
			(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (window.getHeight() / 2));
		
		Window.btnSend.addActionListener(this);
		Window.txtAction.addKeyListener(this);
		window.setVisible(true);

		window.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) { 
				try {
					client.sendByeByeMessage();
					client.shutdown();
					window.dispose();
				} catch (IOException e1) {}
			} 
		});
		
		Window.writeText("Connection stabilished with " + client.getAddress());
		Window.writeText("Connected as " + clientId);
		client.sendIntroMessage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Window.btnSend){
			send(Window.txtAction.getText());
			Window.txtAction.setText("");
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10 && arg0.getSource() == Window.txtAction){
			send(Window.txtAction.getText());

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 10 && arg0.getSource() == Window.txtAction){
		Window.txtAction.setText(new String(""));
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	public void send(String message){
		client.sendMessage(message);
	}
}
