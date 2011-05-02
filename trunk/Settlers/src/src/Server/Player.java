package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player {
	
	private String name;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	public Player(String name, Socket socket) throws IOException {
		this.name = name;
		this.socket = socket;
		this.in = new DataInputStream(socket.getInputStream());
		this.out = new DataOutputStream(socket.getOutputStream());
	}
	
	public DataInputStream in() {
		return this.in;
	}
	
	public DataOutputStream out() {
		return this.out;
	}
	
	public String name() {
		return this.name;
	}
	

}
