package nl.gjosse.gui;

import nl.gjosse.internet.Client;


public class ClickEventListener {
	public static boolean alreadyClicked = false;
	public static int selected;
	
	public static void click(int id) {
		if(!GameWindow.nonSelectableId.contains(id)) {
			
			if(alreadyClicked) {
				PlayerPanel doneClicked = GameWindow.players[selected];
				doneClicked.removeBorder();
				//Client.sendMessage("removeSelection "+doneClicked.id);
			} 
			
			PlayerPanel clicked = GameWindow.players[id];
			clicked.addBorder();
			Client.sendMessage("add_selection "+id+" "+clicked.rColor+" "+clicked.gColor+" "+clicked.bColor);
			alreadyClicked = true;
			selected = id;
		}
	}
}
