package br.ufrj.tp.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReaderService extends Thread {
	private BufferedReader serverToClient;
	private boolean active = true;

	public ClientReaderService(Socket socket) throws IOException {
		serverToClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {
		try {
			while (active) {
				String message = serverToClient.readLine();
				Window.writeText(message);
			}
		} catch (IOException e) {}
	}

	public void shutdown() {
		active = false;
	}
}
