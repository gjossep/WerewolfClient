package nl.gjosse.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	static Socket socket;
	static int id;
	static int port = 9999;
	public static void start() {
		try {
			System.out.println("Client trying to connect!");
			socket = new Socket("localhost", port);
			
			id = Integer.parseInt(readMessage());
			if(id >= 0) {
				System.out.println("Id Collected! ID of "+id);

				double calc = ((id + 15) * (id - 15)) * socket.getPort();
				System.out.println("Doing response with calc of "+calc);
				sendMessage(""+calc);

				if(readMessage().equalsIgnoreCase("correct")) {
					System.out.println("Check was good!");
					sendMessage("Gjosse"+id);
					Thread readIn = new Thread(new ReadIn(id, socket));
					readIn.start();
				} else {
					System.out.println("Wrong!");
				}
			} else {
				System.out.println("Connection bussy!");
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String readMessage() {
		BufferedReader br;
		String response = "";
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			response = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	public static void sendMessage(String message) {
		try {
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			pw.println(message);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
