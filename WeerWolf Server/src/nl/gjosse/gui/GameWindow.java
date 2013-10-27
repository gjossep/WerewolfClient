package nl.gjosse.gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import nl.gjosse.ext.StretchIcon;
import javax.swing.JButton;

public class GameWindow extends JPanel {

	/**
	 * Create the panel.
	 */
	public static PlayerPanel[] players;
	public static ArrayList<Integer> nonSelectableId = new ArrayList<Integer>();
	JPanel panel;
	public static int size;
	
	public static PlayerPanel getPlayer(int i) {
		return players[i];
	}
	public static int getArraySize() {
		return players.length;
	}
	
	
	public GameWindow() {
		setLayout(null);
		
		super.setBounds(6, 0, 901, 496);
		super.setOpaque(false);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 901, 496);
		panel.setOpaque(false);
		add(panel);
		panel.setLayout(new GridLayout(2, 0, 5, 5));
		
	}
	public static void changeSize(int change) {
		size = change;
		players = new PlayerPanel[size];
	}
	
	public void addPlayer(int id,String name, boolean self) {
		System.out.println("Player being added with name "+name+"and "+self);
		PlayerPanel player;
		if(self == true) {
			System.out.println("Player is self");
			player = new PlayerPanel(name, new StretchIcon(Window.class.getResource("/nl/gjosse/res/werewolf.png")), id, self);
			nonSelectableId.add(id);
		} else {
			System.out.println("player is not self");
			player = new PlayerPanel(name, new StretchIcon(Window.class.getResource("/nl/gjosse/res/backofcard.png")), id, self);
		}
		panel.add(player);
		players[id] = player;
		refreshGui();
	}
	
	public void refreshGui() {
		super.revalidate();
		super.validate();
	}

}
