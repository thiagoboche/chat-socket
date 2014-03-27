package br.ufrj.tp.chat.client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public static JTextArea txtHistory;
	public static JTextArea txtAction;
	public static JButton btnSend;


	public Window(String client){

		setTitle("Chat - " + client);
		setSize(500, 600);
		setResizable(false);
		JPanel pnlHistory = new JPanel();
		JPanel pnlActions = new JPanel();
		txtHistory = new JTextArea();
		txtAction = new JTextArea();
		btnSend = new JButton("Send");

		txtHistory.setLineWrap(true);
		txtHistory.setWrapStyleWord(true);
		JScrollPane scrHistory = new JScrollPane(txtHistory);
		scrHistory.setPreferredSize(new Dimension(490,450));
		txtHistory.setEditable(false);
		txtHistory.setText("");
		pnlHistory.add(scrHistory);

		txtAction.setBorder(BorderFactory.createLoweredBevelBorder());
		txtAction.setLineWrap(true);
		txtAction.setWrapStyleWord(true);
		txtAction.setPreferredSize(new Dimension(390,110));
		btnSend.setPreferredSize(new Dimension(95,110));
		btnSend.setVerticalTextPosition(JButton.BOTTOM);
		btnSend.setHorizontalTextPosition(JButton.CENTER);
		pnlActions.add(txtAction);
		pnlActions.add(btnSend);

		this.add(pnlHistory, BorderLayout.NORTH);
		this.add(pnlActions, BorderLayout.SOUTH);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public static void writeText(String text){
		txtHistory.setText(txtHistory.getText()  + text + "\n");
	}
}
