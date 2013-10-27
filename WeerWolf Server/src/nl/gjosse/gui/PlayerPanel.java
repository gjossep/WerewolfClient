package nl.gjosse.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.gjosse.ext.StretchIcon;

public class PlayerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public static int id;
	public static String name;
	public JLabel lblNewLabel = new JLabel();
	private JLabel cardIcon;
	
	public static int rColor, gColor, bColor;
	
	public PlayerPanel(String name, StretchIcon icon, final int id, boolean isPlayer) {
		Random ran = new Random();
		this.setOpaque(false);
		this.name = name;
		rColor = ran.nextInt(255);
		gColor = ran.nextInt(255);
		bColor = ran.nextInt(255);
		
		this.id = id;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				ClickEventListener.click(id);
			}
		});
		setLayout(null);
		
		cardIcon = new JLabel();
		cardIcon.setIcon(icon);
		cardIcon.setBounds(6, 34, 163, 164);
		add(cardIcon);
		
		JLabel lblName = new JLabel(name);
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(6, 6, 163, 16);
		add(lblName);
		
//		if(isPlayer == true) {
//			cardIcon.setBorder(BorderFactory.createLineBorder(new Color(rColor, gColor, bColor), 2));
//		}

	}
	public void addBorder() {
		cardIcon.setBorder(BorderFactory.createLineBorder(new Color(rColor, gColor, bColor), 4));
		this.revalidate();
		this.validate();
	}
	public void addBorder(int r, int g, int b) {
		cardIcon.setBorder(BorderFactory.createLineBorder(new Color(r, g, b), 4));
		this.revalidate();
		this.validate();
	}
	
	public void removeBorder() {
		System.out.println("Removing "+id);
		cardIcon.setBorder(null);
		this.revalidate();
		this.validate();
	}
	
	public void changeIcon(StretchIcon icon) {
		cardIcon.setIcon(icon);
		this.revalidate();
		this.validate();
	}

}
