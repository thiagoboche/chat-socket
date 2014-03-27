package br.ufrj.tp.chat.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static final String SERVER_DEFAULT_IP_ADDRESS = "127.0.0.1";
	private static final int SERVER_DEFAULT_PORT = 6000;

	private Socket socket;
	private PrintWriter clientToServer;
	private ClientReaderService clientReader;
	private String clientId;

	public Client(String clientId) {
		this.clientId = clientId;
	}

	public String getAddress(){
		return SERVER_DEFAULT_IP_ADDRESS + ":" + SERVER_DEFAULT_PORT;
	}

	public void start() throws UnknownHostException, IOException {
		socket = new Socket(SERVER_DEFAULT_IP_ADDRESS, SERVER_DEFAULT_PORT);
		clientToServer = new PrintWriter(socket.getOutputStream());
		clientReader = new ClientReaderService(socket);
		clientReader.start();
	}

	public void sendMessage(String message) {
		clientToServer.println(clientId + ": " + message);
		clientToServer.flush();
	}

	public void sendIntroMessage() {
		clientToServer.println(clientId + " entered the room.");
		clientToServer.flush();
	}

	public void sendByeByeMessage() {
		clientToServer.println(clientId + " left the room.");
		clientToServer.flush();
	}

	public void shutdown() throws IOException {
		socket.close();
	}
}
