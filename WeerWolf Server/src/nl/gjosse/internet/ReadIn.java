package nl.gjosse.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import nl.gjosse.gui.GameWindow;
import nl.gjosse.gui.PlayerPanel;
import nl.gjosse.gui.Window;

public class ReadIn implements Runnable {
	int id; 
	Socket socket;
	public ReadIn(int id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Starting ReadIn Thread!");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input;
			while((input = br.readLine()) != null) {
				System.out.println("Input is "+input);
				if(input.contains("new_player")) {
					newPlayer(input);
				} else if(input.contains("setsize")) {
					setSize(input);
				} else if(input.contains("new_selection")) {
					System.out.println("New Selection");
					newSelection(input);
				} else if(input.contains("remove_selection")) {
					//removeSelection(input);
				} else {
					System.out.println("Wrong Input: "+input);
				}
			}
			System.out.println("Out of the loop!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void removeSelection(String input) {
		String[] split = input.split(" ");
		int playerID = Integer.parseInt(split[1]);
		System.out.println("Remove border: "+playerID);
		
		for(PlayerPanel player : GameWindow.players) {
			if(player.id == playerID) {
				player.removeBorder();
				System.out.println("Border removed of "+player.id);
			}
		}
	}

	private void newSelection(String input) {
		System.out.println("New Selection!");
		String[] split = input.split(" ");
		int playerID = Integer.parseInt(split[1]);
		int rColor = Integer.parseInt(split[2]);
		int gColor = Integer.parseInt(split[3]);
		int bColor = Integer.parseInt(split[4]);
		System.out.println("Selected Command: "+input);
				
		System.out.println(GameWindow.getArraySize()+", "+GameWindow.getPlayer(0).name);
		for(int i = 0; i<= GameWindow.getArraySize(); i++) {
			PlayerPanel players = GameWindow.getPlayer(i);
			System.out.println("ID "+players.id+", Name: "+players.name+", Index: "+i);
			if(players.id == playerID) {
				players.addBorder(rColor,gColor, bColor);
			}
		}
	}
	
	public void setSize(String input) {
		String[] split = input.split(" ");
		int size = Integer.parseInt(split[1]);
		GameWindow.changeSize(size);
		System.out.println("Changed Size of array to "+size);
	}

	public void newPlayer(String input) {
		String[] split = input.split(" ");
		String name = split[1];
		int playerID = Integer.parseInt(split[2]);
		System.out.println("PlayerID "+playerID+", And id is "+id);
		if(playerID == id) {
			Window.gameWindow.addPlayer(playerID,name, true);
		} else {
			Window.gameWindow.addPlayer(playerID,name, false);
		}
	}

}
